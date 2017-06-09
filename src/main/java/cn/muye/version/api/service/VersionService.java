package cn.muye.version.api.service;

import cn.muye.version.api.mapper.VersionMapper;
import cn.muye.menu.api.service.MenuService;
import cn.muye.menu.domain.Menu;
import cn.muye.version.domain.Version;
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
public class VersionService {

    @Autowired
    private VersionMapper versionMapper;

    public List<Version> listVersions() {
        return versionMapper.listVersions();
    }

    public Version getById(Long id) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        return versionMapper.getById(map);
    }

}
