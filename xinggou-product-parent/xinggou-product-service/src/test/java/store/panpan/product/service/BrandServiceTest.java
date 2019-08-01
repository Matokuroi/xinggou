package store.panpan.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import store.panpan.product.domain.Brand;
import store.panpan.product.query.BrandQuery;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTest {

    @Autowired
    private IBrandService brandService;

    @Test
    public void selectPageList() {
        BrandQuery brandQuery = new BrandQuery();
        IPage<Brand> brandIPage = brandService.selectPageList(brandQuery);
        System.out.println(brandIPage.getTotal());
        List<Brand> brands = brandIPage.getRecords();
        brands.forEach(e->{
            System.out.println(e);
        });
    }
}