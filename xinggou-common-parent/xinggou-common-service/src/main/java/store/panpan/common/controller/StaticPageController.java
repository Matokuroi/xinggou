package store.panpan.common.controller;


import org.springframework.web.bind.annotation.*;
import store.panpan.common.util.VelocityUtils;
import store.panpan.xinggou.util.AjaxResult;

import java.util.Map;

@RestController
public class StaticPageController {

    /**
     * 页面静态化
     * @param map model数据 templatePath模板路径 targetPath目标文件路径
     * @return 结果
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public AjaxResult genStaticPage(@RequestBody Map<String,Object> map){
        Object model = map.get("model");
        String templatePath = (String) map.get("templatePath");
        String targetPath = (String) map.get("targetPath");
        try {
            VelocityUtils.staticByTemplate(model,templatePath,targetPath);
            return AjaxResult.me().setSuccess(true).setMessage("成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }

}
