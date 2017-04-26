package cn.muye.mapper;

import cn.muye.model.Category;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public interface CategoryMapper {

    void saveCategory(Category category);

    void updateCategory(Category category);

    List<Category> listCategories();

    List<Category> getByPid(Long id);

    void deleteById(Long id);

    Category getById(Long id);
}
