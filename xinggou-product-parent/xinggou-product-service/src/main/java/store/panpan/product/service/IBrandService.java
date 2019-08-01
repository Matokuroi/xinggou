package store.panpan.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import store.panpan.product.domain.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import store.panpan.product.query.BrandQuery;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
public interface IBrandService extends IService<Brand> {
    //分页
    IPage<Brand> selectPageList(BrandQuery query);

    //批量删除
    void deleteMany(List ids);
}
