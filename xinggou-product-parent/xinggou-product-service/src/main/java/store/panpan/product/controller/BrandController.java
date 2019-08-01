package store.panpan.product.controller;

import org.apache.ibatis.annotations.Param;
import store.panpan.product.service.IBrandService;
import store.panpan.product.domain.Brand;
import store.panpan.product.query.BrandQuery;
import store.panpan.xinggou.util.AjaxResult;
import store.panpan.xinggou.util.LetterUtil;
import store.panpan.xinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                brand.setUpdateTime(new Date().getTime());
                brand.setFirstLetter(LetterUtil.getFirstLetter(brand.getName().toUpperCase()));
                brandService.updateById(brand);
            }else{
                brand.setCreateTime(new Date().getTime());
                brand.setFirstLetter(LetterUtil.getFirstLetter(brand.getName().toUpperCase()));
                brandService.save(brand);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            brandService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
    /**
     * 删除一堆对象信息
     * @param
     * @return
     */
    @RequestMapping(value="/deleteMany",method=RequestMethod.POST)
    public AjaxResult deleteMany(@RequestBody Brand brand){
        try {
            String ids = brand.getIds();
            System.out.println("ids"+ids);
            List<String> list = Arrays.asList(ids.split(","));
            System.out.println("list"+list);
            brandService.deleteMany(list);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Brand get(@RequestParam(value="id",required=true) Long id)
    {
        return brandService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Brand> list(){

        return brandService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query)
    {
        IPage<Brand> page = brandService.selectPageList(query);
        return new PageList<>(page.getTotal(),page.getRecords());
    }
}
