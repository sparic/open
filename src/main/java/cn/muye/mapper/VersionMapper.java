package cn.muye.mapper;

import cn.muye.model.Version;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
public interface VersionMapper {

    List<Version> listVersions();

    void saveVersion(Version version);

    Version getById(Long id);

    void updateVersion(Version versionDb);

    void deleteById(Long id);
}
