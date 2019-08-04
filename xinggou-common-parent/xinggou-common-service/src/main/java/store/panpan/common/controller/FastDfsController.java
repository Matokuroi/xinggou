package store.panpan.common.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import store.panpan.xinggou.util.AjaxResult;
import store.panpan.xinggou.util.FastDfsApiOpr;

import java.io.IOException;

@RestController
public class FastDfsController {
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam(value = "file",required = true)MultipartFile file){
        try {
            //获得图片名称 xxx.jpg
            String originalFilename = file.getOriginalFilename();
            //剪切字符串取 . 以后字符  比如 jpg
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String upload = FastDfsApiOpr.upload(file.getBytes(), substring);
            return AjaxResult.me().setResultObj(upload);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败" + e.getMessage());
        }

    }

    @DeleteMapping("/del")
    public AjaxResult delete(@RequestParam(value = "filePath",required = true)String filePath){
        //截取第一个字符以后
        String pathTmp = filePath.substring(1);
        //截取组名
        String groupName = pathTmp.substring(0, pathTmp.indexOf("/"));
        System.out.println(groupName);
        //截取组名以后字符串
        String remotePath = pathTmp.substring(pathTmp.indexOf("/")+1);
        System.out.println(remotePath);
        FastDfsApiOpr.delete(groupName,remotePath);
        return AjaxResult.me();
    }
}
