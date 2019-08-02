package store.panpan.common.home;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.panpan.xinggou.util.AjaxResult;

import java.util.Map;

@FeignClient(value = "common-service", fallbackFactory = PageClientFallbackFactory.class)
public interface PageClient {
    /**
     根据特定模板传入特定数据,生成静态页面到特定位置
     *
     * Map<String,Object>
     *      model ==数据
     *      tmeplatePath==xxx
     *      staticPagePath = xxx
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public AjaxResult genStaticPage(@RequestBody Map<String, Object> map);
}
