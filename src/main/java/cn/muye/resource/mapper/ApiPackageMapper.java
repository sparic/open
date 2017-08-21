package cn.muye.resource.mapper;


import cn.muye.resource.domain.ApiPackage;

import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */
public interface ApiPackageMapper {

    void saveApiPackage(ApiPackage apiPackage);

    List<ApiPackage> list();

}
