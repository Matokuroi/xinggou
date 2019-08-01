package store.panpan.product.service.impl;

import store.panpan.product.domain.Product;
import store.panpan.product.mapper.ProductMapper;
import store.panpan.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
