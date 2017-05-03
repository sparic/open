package cn.muye.service;

import cn.muye.mapper.MenuMapper;
import cn.muye.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public void saveMenu(Menu menu) {
        menuMapper.saveMenu(menu);
    }

    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    /**
     * 分页查询分类
     * @param page
     * @return
     */
    public List<Menu> listMenus(Integer page, Long versionId) {
        return menuMapper.listMenus(page, versionId);
    }

    public List<Menu> getByPid(Long id) {
        return menuMapper.getByPid(id);
    }

    public void deleteById(Long id) {
        menuMapper.deleteById(id);
    }

    public Menu getById(Long id) {
        return menuMapper.getById(id);
    }


    public List<Menu> getByVersionId(Long id) {
        return menuMapper.getByVersionId(id);
    }
}
