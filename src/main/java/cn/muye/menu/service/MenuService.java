package cn.muye.menu.service;

import cn.muye.menu.mapper.MenuMapper;
import cn.muye.menu.domain.Menu;
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

    public void saveMenuAndUpdateOriginId(Menu menu, String type) {
        menuMapper.saveMenu(menu);
        if (type.equals("version")) {
            menu.setOriginId(menu.getOriginId());
        } else {
            menu.setOriginId(menu.getId());
        }
        menuMapper.updateMenu(menu);
    }

    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    /**
     * 分页查询分类
     * @param page
     * @return
     */
//    public List<Menu> listMenus(Integer page, Long versionId) {
//        return menuMapper.listMenus(page, versionId);
//    }

    /**
     * 不分页
     * @param versionId
     * @return
     */
    public List<Menu> listMenus(Long versionId) {
        return menuMapper.listMenus(versionId);
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

    public List<Menu> listMenusByParentId(Long id) {
        return menuMapper.listMenusByParentId(id);
    }

    public Long countMenus(Integer page, Long versionId) {
        return menuMapper.countMenus(page, versionId);
    }
}
