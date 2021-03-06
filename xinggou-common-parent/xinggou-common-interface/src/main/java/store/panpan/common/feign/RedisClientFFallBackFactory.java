package store.panpan.common.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import store.panpan.xinggou.util.AjaxResult;
@Component
public class RedisClientFFallBackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult set(String key, String value) {
                return AjaxResult.me().setSuccess(false).setMessage("系统异常");
            }

            @Override
            public AjaxResult get(String key) {
                return AjaxResult.me().setSuccess(false).setMessage("系统异常");
            }
        };
    }
}
