package store.panpan.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import store.panpan.product.domain.Brand;
import store.panpan.product.domain.Product;
import store.panpan.product.mapper.BrandMapper;
import store.panpan.product.query.BrandQuery;
import store.panpan.product.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public IPage<Brand> selectPageList(BrandQuery query) {
        Page<Brand> page = new Page<>(query.getPageNum(), query.getPageSize());
        return brandMapper.selectPageList(page,query);
    }

    @Override
    public void deleteMany(List ids) {
        brandMapper.deleteMany(ids);
    }
}
