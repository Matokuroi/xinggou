package store.panpan.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.panpan.xinggou.util.AjaxResult;

@FeignClient(value = "common-service",fallbackFactory = RedisClientFFallBackFactory.class)
public interface RedisClient {

    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    AjaxResult set(@RequestParam("key")String key, @RequestParam("value")String value);

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    AjaxResult get(@RequestParam("key")String key);
}
