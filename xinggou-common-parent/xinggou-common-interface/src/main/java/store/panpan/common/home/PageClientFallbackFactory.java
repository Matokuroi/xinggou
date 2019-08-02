package store.panpan.common.home;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import store.panpan.xinggou.util.AjaxResult;

import java.util.Map;
@Component
public class PageClientFallbackFactory implements FallbackFactory<PageClient>{
    @Override
    public PageClient create(Throwable throwable) {
        return new PageClient() {
            @Override
            public AjaxResult genStaticPage(Map<String, Object> map) {
                return AjaxResult.me().setSuccess(false).setMessage("系统异常");
            }
        };
    }
}
