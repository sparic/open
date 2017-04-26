package cn.muye.service;

import cn.muye.mapper.CategoryMapper;
import cn.muye.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void saveCategory(Category category) {
        categoryMapper.saveCategory(category);
    }

    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    public List<Category> getByPid(Long id) {
        return categoryMapper.getByPid(id);
    }

    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }

    public Category getById(Long id) {
        return categoryMapper.getById(id);
    }
}
