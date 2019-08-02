package store.panpan.product.service;

import store.panpan.product.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
public interface IProductTypeService extends IService<ProductType> {
    List<ProductType> GetTreeDataLoop();

    void getHomePage();
}
