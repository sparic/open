package cn.muye.resource.mapper;

import cn.muye.resource.domain.Resource;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/25.
 */
public interface ResourceMapper {

    void saveResource(Resource file);

    List<Resource> listResources(int page);

    void updateFile(Resource file);

    void deleteById(Long id);

    Resource getById(Long id);

    Long countResources(Integer page);
}
