package cn.muye.appauth.api.mapper;

import cn.muye.appauth.domain.AppAuth;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
public interface AppAuthMapper {

    List<AppAuth> list();

    AppAuth getByUserId(Long userId);

    AppAuth getByAppId(String appId);

    void save(AppAuth appAuth);

    void update(AppAuth appAuthDb);
}
