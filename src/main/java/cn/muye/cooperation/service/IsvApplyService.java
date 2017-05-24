package cn.muye.cooperation.service;

import cn.muye.core.enums.ApplyStatusType;
import cn.muye.core.Constants;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.mapper.IsvApplyMapper;
import cn.muye.user.domain.User;
import cn.muye.user.service.UserService;
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

    public IsvApply getById(Long id) {
        return isvApplyMapper.getById(id);
    }

    public List<IsvApply> list(Integer page) {
        return isvApplyMapper.list(page);
    }

    public void save(IsvApply isvApply) {
        isvApplyMapper.save(isvApply);
    }

    public void update(IsvApply isvApply) {
        isvApplyMapper.update(isvApply);
        //创建账户
        if (isvApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            User user = new User();
            user.setUserName(isvApply.getUserName());
            user.setPassword(isvApply.getPassword());
            user.setUserRoleId(Constants.ISV_ROLE_ID);
            userService.saveAndBindRole(user);
        }
        sendMail(isvApply);
    }

    private void sendMail(IsvApply isvApply) {
        String subject = null;
        String[] emailArr = new String[]{isvApply.getUserName()};
        String context = null;
        if (isvApply.getStatus().equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "ISV资格" + ApplyStatusType.SUCCESS.getName();
            context = isvApply.getContact() + ",你好，恭喜" + isvApply.getCompanyName() + "获得木爷ISV资格";
            emailArr = new String[]{isvApply.getUserName()};
        } else if (isvApply.getStatus().equals(ApplyStatusType.FAILED.getValue())){
            subject = "ISV资格" + ApplyStatusType.FAILED.getName();
            context = isvApply.getContact() + ",你好，很抱歉" + isvApply.getCompanyName() + "未通过木爷ISV资格审核，原因:" + isvApply.getComment();
        }
        mailUtil.send(emailArr, subject, context);
    }
}
