package cn.muye.resource.service;

import cn.muye.resource.mapper.ResourceMapper;
import cn.muye.resource.domain.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/4/27.
 */
@Service
@Transactional
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    public List<Resource> listFiles(Integer page) {
        return resourceMapper.listResources(page);
    }


    public void saveFile(Resource file) {
        resourceMapper.saveResource(file);
    }

    public void deleteById(Long id) {
        resourceMapper.deleteById(id);
    }

    public Resource getById(Long id) {
        return resourceMapper.getById(id);
    }

    public Long countResources(Integer page) {
        return resourceMapper.countResources(page);
    }
}
