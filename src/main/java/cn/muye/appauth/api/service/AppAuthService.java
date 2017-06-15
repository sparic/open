package cn.muye.appauth.api.service;

import cn.muye.appauth.api.mapper.AppAuthMapper;
import cn.muye.appauth.domain.AppAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
@Service
@Transactional
public class AppAuthService {

    @Autowired
    private AppAuthMapper appAuthMapper;


    public AppAuth getByUserId(Long userId) {
        return appAuthMapper.getByUserId(userId);
    }

    public AppAuth getByAppId(String appId) {
        return appAuthMapper.getByAppId(appId);
    }

    public void save(AppAuth appAuth) {
        appAuthMapper.save(appAuth);
    }

    public void update(AppAuth appAuthDb) {
        appAuthMapper.update(appAuthDb);
    }
}
