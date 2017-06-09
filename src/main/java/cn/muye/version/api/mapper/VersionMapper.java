package cn.muye.version.api.mapper;

import cn.muye.version.domain.Version;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2017/5/2.
 */
public interface VersionMapper {

    List<Version> listVersions();

    Version getById(Map map);
}
