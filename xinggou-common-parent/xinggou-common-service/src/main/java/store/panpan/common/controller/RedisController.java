package store.panpan.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.panpan.common.feign.RedisClient;
import store.panpan.common.util.RedisUtils;
import store.panpan.xinggou.util.AjaxResult;

@RestController
public class RedisController implements RedisClient {
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    @Override
    public AjaxResult set(@RequestParam("key") String key,@RequestParam("value") String value) {
        try {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.me().setSuccess(true).setMessage("储存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("储存失败");
        }
    }
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    @Override
    public AjaxResult get(String key) {
        try {
            String result = RedisUtils.INSTANCE.get(key);
            return AjaxResult.me().setSuccess(true).setMessage("获取成功").setResultObj(result);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取失败");
        }
    }
}
