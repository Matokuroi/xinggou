package store.panpan.product.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import store.panpan.product.domain.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import store.panpan.product.query.BrandQuery;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
public interface BrandMapper extends BaseMapper<Brand> {
    //分页
    IPage<Brand> selectPageList(Page<Brand> page,@Param("query")BrandQuery query);
    //批量删除
    void deleteMany(List ids);
}
