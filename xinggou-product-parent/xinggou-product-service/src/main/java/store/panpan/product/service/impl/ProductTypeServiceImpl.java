package store.panpan.product.service.impl;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import store.panpan.common.feign.RedisClient;
import store.panpan.common.home.PageClient;
import store.panpan.product.domain.ProductType;
import store.panpan.product.mapper.ProductTypeMapper;
import store.panpan.product.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import store.panpan.xinggou.util.AjaxResult;

import java.io.Serializable;
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
    public boolean save(ProductType entity) {
        redisClient.set("productTypes", "");
        boolean save = super.save(entity);
        synchronizedOpr();
        return save;
    }

    @Override
    public boolean removeById(Serializable id) {
        redisClient.set("productTypes", "");
        return super.removeById(id);
    }

    @Override
    public boolean updateById(ProductType entity) {
        redisClient.set("productTypes", "");
        boolean b = super.updateById(entity);
        synchronizedOpr();
        return b;
    }

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private PageClient pageClient;

    @Override
    public List<ProductType> GetTreeDataLoop() {
        AjaxResult productTypes = redisClient.get("productTypes");
        String resultObj = (String) productTypes.getResultObj();
        if (StringUtils.isNotBlank(resultObj)){
            System.out.println("进来了 ，redis有数据");
            return JSONArray.parseArray(resultObj, ProductType.class);
        }else {
            System.out.println("没有数据 ，我要去找mysql！！！");
            List<ProductType> loop = loop();
            redisClient.set("productTypes", JSONArray.toJSONString(loop));
            return loop;
        }
    }

    @Override
    public void getHomePage() {
        synchronizedOpr();
    }
    private void synchronizedOpr(){
        //第一次生成
        Map<String, Object> map = new HashMap<>();
        //菜单数据
        List<ProductType> loop = loop();
        //模板路径
        String templatePath = "H:\\ideaworkspace\\xinggou\\xinggou-common-parent\\xinggou-common-service\\src\\main\\resources\\template\\product.type.vm";
        //使用模板生成文件的路径
        String targetPath = "H:\\ideaworkspace\\xinggou\\xinggou-common-parent\\xinggou-common-service\\src\\main\\resources\\template\\product.type.vm.html";
        //loop  菜单数据  ，给模板需要的数据
        map.put("model", loop);
        map.put("templatePath", templatePath);
        map.put("targetPath", targetPath);
        pageClient.genStaticPage(map);

        map = new HashMap<>();
        //模板需要其他的模板数据
        Map<String, String> model = new HashMap<>();
        //使用主页模板的路径
        templatePath= "H:\\ideaworkspace\\xinggou\\xinggou-common-parent\\xinggou-common-service\\src\\main\\resources\\template\\home.vm";
        //模板生成的文件的路径
        targetPath="H:\\ideaworkspace\\xinggou-web-parent\\xinggou-web-home\\home.html";
        model.put("staticRoot","H:\\ideaworkspace\\xinggou\\xinggou-common-parent\\xinggou-common-service\\src\\main\\resources\\");
        map.put("model", model);
        map.put("templatePath", templatePath);
        map.put("targetPath", targetPath);
        pageClient.genStaticPage(map);
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
                parent.getChildren().add(productType);
            }
        }
        return result;
    }
}
