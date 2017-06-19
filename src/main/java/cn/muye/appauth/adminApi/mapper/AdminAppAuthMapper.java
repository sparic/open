package cn.muye.appauth.adminApi.mapper;

import cn.muye.appauth.domain.AppAuth;
import cn.muye.appauth.dto.AppAuthDto;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/6/14.
 */
public interface AdminAppAuthMapper {

    List<AppAuthDto> list();
}
