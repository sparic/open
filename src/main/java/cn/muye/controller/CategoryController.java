package cn.muye.controller;

import cn.muye.bean.AjaxResult;
import cn.muye.model.Category;
import cn.muye.service.CategoryService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public AjaxResult getCategoryList() {
        List<Category> list = categoryService.listCategories();
        return AjaxResult.success(list);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public AjaxResult postCategory(@RequestBody Category category) {
        return post(category);
    }

    private AjaxResult post(Category category) {
        categoryService.saveCategory(category);
        return AjaxResult.success(category);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public AjaxResult getCategory(@PathVariable Long id) {
        List<Category> categoryList = categoryService.getByPid(id);
        return AjaxResult.success(categoryList);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public AjaxResult putCategory(@PathVariable Long id, @RequestBody Category category) {
        Category categoryDb = categoryService.getById(id);
        categoryDb.setName(category.getName());
        categoryDb.setPid(category.getPid());
        categoryDb.setSdkId(category.getSdkId());
        categoryService.updateCategory(categoryDb);
        return AjaxResult.success(categoryDb);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public AjaxResult deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return AjaxResult.success();
    }

    public static void main(String[] args) {
        Category category = new Category();
        category.setPid(null);
        category.setName("test");
        category.setSdkId(1L);
        String json = JSON.toJSONString(category);
        System.out.println(json);
    }
}
