package cn.muye.resource.service;

import cn.muye.config.CustomProperties;
import cn.muye.core.AjaxResult;
import cn.muye.resource.domain.ApiPackage;
import cn.muye.resource.domain.Resource;
import cn.muye.resource.mapper.ApiPackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */
@Service
@Transactional
public class ApiPackageService {

    @Autowired
    private ApiPackageMapper apiPackageMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CustomProperties customProperties;

    /**
     * 保存1-人机交互平台 2-业务应用平台两种文件信息
     * @param apiPackage
     */
    public void saveApiPackage(ApiPackage apiPackage) {
        apiPackageMapper.saveApiPackage(apiPackage);
    }

    public List<ApiPackage> list() {
        return apiPackageMapper.list();
    }

}
