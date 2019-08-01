package store.panpan.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import store.panpan.product.domain.ProductType;
import store.panpan.product.mapper.ProductTypeMapper;
import store.panpan.product.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author MatoKuroi
 * @since 2019-07-31
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Override
    public List<ProductType> GetTreeDataLoop() {
        return loop();
    }

    private List<ProductType> loop(){
        //获取所有类型
        List<ProductType> productTypes = baseMapper.selectList(null);
        //装父类 及其下面的子子孙孙
        List<ProductType> result = new ArrayList<>();
        //定义一个map   来存  id 对应的 对象    不用 10*10 获得 子子孙孙  用这种方式 10+10就ok
        Map<Long, ProductType> map = new HashMap<>();
        //装id 对应的镀锡
        for (ProductType productType : productTypes) {
            map.put(productType.getId(), productType);
        }
        //循环取到子子孙孙
        for (ProductType productType : productTypes) {
            if (productType.getPid() == 0 ){
                result.add(productType);
            }else {
                //根据pid 获得父亲
                ProductType parent = map.get(productType.getPid());
                System.out.println(parent);
                parent.getChildren().add(productType);
            }
        }
        System.out.println(result);
        return result;
    }
}
