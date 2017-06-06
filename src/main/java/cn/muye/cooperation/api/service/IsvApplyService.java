package cn.muye.cooperation.api.service;

import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.IsvApplyDto;
import cn.muye.cooperation.api.mapper.IsvApplyMapper;
import cn.muye.user.api.service.UserService;
import cn.muye.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
@Service
@Transactional
public class IsvApplyService {

    @Autowired
    private IsvApplyMapper isvApplyMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private UserService userService;

    private static final int STATUS_SUBMIT = 0; //已提交
    private static final int STATUS_AUDITING = 1; //待审核
    private static final int STATUS_SUCCESS = 2; //成功
    private static final int STATUS_FAILED = 3; //失败

    public IsvApply getById(Long id) {
        return isvApplyMapper.getById(id);
    }

    public List<IsvApplyDto> list(Integer page, Integer status) {
        return isvApplyMapper.list(page, status);
    }

    public void save(IsvApply isvApply) {
        isvApplyMapper.save(isvApply);
    }

    public String update(IsvApply isvApply, String type) {
        isvApplyMapper.update(isvApply);
        return null;
    }

    public IsvApplyDto getByIdWithUser(Long id) {
        return isvApplyMapper.getByIdWithUser(id);
    }

    public IsvApplyDto getDtoByUserId(Long userId) {
        return isvApplyMapper.getDtoByUserId(userId);
    }

    public IsvApply getByUserId(Long userId) {
        return isvApplyMapper.getByUserId(userId);
    }
}
