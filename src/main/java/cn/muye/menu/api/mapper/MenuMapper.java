package cn.muye.menu.api.mapper;

import cn.muye.menu.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public interface MenuMapper {

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    List<Menu> listMenus(@Param(value = "versionId") Long versionId);

    List<Menu> getByPid(Long id);

    void deleteById(Long id);

    Menu getById(Long id);

    List<Menu> getByVersionId(Long id);

    List<Menu> listMenusByParentId(Long id);

    Long countMenus(Integer page, Long versionId);
}
