package cn.muye.service;

import cn.muye.mapper.VersionMapper;
import cn.muye.model.Menu;
import cn.muye.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
@Service
@Transactional
public class VersionService {

    @Autowired
    private VersionMapper versionMapper;

    @Autowired
    private MenuService menuService;

    public List<Version> listVersions() {
        return versionMapper.listVersions();
    }

    public void saveVersion(Version version) {
        versionMapper.saveVersion(version);
    }

    public Version getById(Long id) {
        return versionMapper.getById(id);
    }

    public void updateVersion(Version versionDb) {
        versionMapper.updateVersion(versionDb);
    }

    public void deleteById(Long id) {
        versionMapper.deleteById(id);
    }

    public Version copyVersion(Long extendedVersionId, Version version) {
        Version versionNew = new Version();
        Version extendedVersion = versionMapper.getById(extendedVersionId);
        versionNew.setExtendedVersionCode(extendedVersion != null ? extendedVersion.getVersionCode() : null);
        versionNew.setDescription(version.getDescription());
        versionNew.setVersionCode(version.getVersionCode());
        versionNew.setCreateTime(new Date());
        versionNew.setUrl(version.getUrl());
        versionMapper.saveVersion(versionNew);
        List<Menu> menuList = menuService.getByVersionId(extendedVersionId);
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
                menuService.saveMenu(newMenu);
            }
        }
        return versionNew;
    }
}
