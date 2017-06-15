package cn.muye.appauth.adminApi.service;

import cn.muye.appauth.adminApi.mapper.AdminAppAuthMapper;
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
public class AdminAppAuthService {

    @Autowired
    private AdminAppAuthMapper adminAppAuthMapper;

    public List<AppAuth> list() {
        List<AppAuth> list = adminAppAuthMapper.list();
        return list;
    }
}