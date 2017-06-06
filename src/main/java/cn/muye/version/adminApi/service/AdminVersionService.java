package cn.muye.version.adminApi.service;

import cn.muye.menu.domain.Menu;
import cn.muye.menu.adminApi.service.AdminMenuService;
import cn.muye.version.domain.Version;
import cn.muye.version.adminApi.mapper.AdminVersionMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
@Service
@Transactional
public class AdminVersionService {

    @Autowired
    private AdminVersionMapper adminVersionMapper;

    @Autowired
    private AdminMenuService adminMenuService;

    public List<Version> listVersions() {
        return adminVersionMapper.listVersions();
    }

    public void saveVersion(Version version) {
        adminVersionMapper.saveVersion(version);
    }

    public Version getById(Long id) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        return adminVersionMapper.getById(map);
    }

    public void updateVersion(Version versionDb) {
        adminVersionMapper.updateVersion(versionDb);
    }

    public void deleteById(Long id) {
        adminVersionMapper.deleteById(id);
    }

    public Version copyVersion(Long extendedVersionId, Version version) {
        Version versionNew = new Version();
        Map map = Maps.newHashMap();
        map.put("id", extendedVersionId);
        Version extendedVersion = adminVersionMapper.getById(map);
        versionNew.setExtendedVersionCode(extendedVersion != null ? extendedVersion.getVersionCode() : null);
        versionNew.setDescription(version.getDescription());
        versionNew.setVersionCode(version.getVersionCode());
        versionNew.setCreateTime(new Date());
        versionNew.setUrl(version.getUrl());
        adminVersionMapper.saveVersion(versionNew);
        List<Menu> menuList = adminMenuService.getByVersionId(extendedVersionId);
        if (menuList != null && menuList.size() > 0) {
            for (Menu m : menuList) {
                Menu newMenu = new Menu();
                newMenu.setVersionId(versionNew.getId());
                newMenu.setIsLeaf(m.getIsLeaf());
                newMenu.setCreateTime(new Date());
                newMenu.setIsValid(m.getIsValid());
                newMenu.setParentId(m.getParentId());
                newMenu.setName(m.getName());
                newMenu.setOriginId(m.getOriginId());
                newMenu.setContent(m.getContent());
                newMenu.setUrl(m.getUrl());
                adminMenuService.saveMenuAndUpdateOriginId(newMenu, "version");
            }
        }
        return versionNew;
    }

    public Version getByCode(String versionCode) {
        return adminVersionMapper.getByCode(versionCode);
    }
}
