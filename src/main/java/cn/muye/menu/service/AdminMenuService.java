package cn.muye.menu.service;

import cn.muye.menu.domain.Menu;
import cn.muye.menu.mapper.AdminMenuMapper;
import cn.muye.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
@Service
@Transactional
public class AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    public void saveMenuAndUpdateOriginId(Menu menu, String type) {
        adminMenuMapper.saveMenu(menu);
        if (type.equals("version")) {
            menu.setOriginId(menu.getOriginId());
        } else {
            menu.setOriginId(menu.getId());
        }
        adminMenuMapper.updateMenu(menu);
    }

    public void updateMenu(Menu menu) {
        adminMenuMapper.updateMenu(menu);
    }

    /**
     * 不分页
     * @param versionId
     * @return
     */
    public List<Menu> listMenus(Long versionId) {
        return adminMenuMapper.listMenus(versionId);
    }

    public void deleteById(Long id) {
        adminMenuMapper.deleteById(id);
    }

    public Menu getById(Long id) {
        return adminMenuMapper.getById(id);
    }

    public List<Menu> getByVersionId(Long id) {
        return adminMenuMapper.getByVersionId(id);
    }

    public List<Menu> listMenusByParentId(Long id) {
        return adminMenuMapper.listMenusByParentId(id);
    }

}
