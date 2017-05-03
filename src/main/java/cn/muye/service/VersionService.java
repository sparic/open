package cn.muye.service;

import cn.muye.mapper.MenuMapper;
import cn.muye.mapper.VersionMapper;
import cn.muye.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
