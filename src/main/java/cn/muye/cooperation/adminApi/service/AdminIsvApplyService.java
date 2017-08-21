package cn.muye.cooperation.adminApi.service;

import cn.muye.appauth.api.service.AppAuthService;
import cn.muye.appauth.domain.AppAuth;
import cn.muye.config.CustomProperties;
import cn.muye.cooperation.domain.IsvApply;
import cn.muye.cooperation.dto.IsvApplyDto;
import cn.muye.cooperation.adminApi.mapper.AdminIsvApplyMapper;
import cn.muye.core.Constants;
import cn.muye.core.enums.AgentLevelType;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.mail.domain.Mail;
import cn.muye.mail.service.MailService;
import cn.muye.user.domain.User;
import cn.muye.user.adminApi.service.AdminUserService;
import cn.muye.utils.DateTimeUtils;
import cn.muye.utils.StringUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
@Service
@Transactional
public class AdminIsvApplyService {

    @Autowired
    private AdminIsvApplyMapper adminIsvApplyMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AppAuthService appAuthService;

    @Autowired
    private CustomProperties customProperties;

    private static final int STATUS_SUBMIT = 0; //已提交
    private static final int STATUS_AUDITING = 1; //待审核
    private static final int STATUS_SUCCESS = 2; //成功
    private static final int STATUS_FAILED = 3; //失败

    public IsvApply getById(Long id) {
        return adminIsvApplyMapper.getById(id);
    }

    public List<IsvApplyDto> list(Integer page, List<Integer> statusList) {
        Map map = Maps.newHashMap();
        map.put("statusList", statusList);
        return adminIsvApplyMapper.list(page, map);
    }

    public String update(IsvApply isvApply, String type) {
        adminIsvApplyMapper.update(isvApply);
        if (type.equals(Constants.TYPE_AUDIT_AGENT_APPLY)) {
            Long userId = isvApply.getUserId();
            User userDb = adminUserService.getUserById(userId);
            String msg = null;
            switch (isvApply.getStatus()) {
                case STATUS_AUDITING :
                    msg = "待审核，请耐心等待";
                    break;
                case STATUS_SUCCESS :
                    msg = "认证通过，并发送通知邮件";
                    if (userDb != null) {
                        //审核通过要更改用户等级为具体Level
                        userDb.setLevel(isvApply.getLevel());
                        adminUserService.updateAndBindRole(userDb);
                        //保存app授权信息
                        saveAppId(userDb);
                    }
                    break;
                case STATUS_FAILED :
                    msg = "认证失败，并发送通知邮件";
                    break;
            }
            IsvApplyDto isvApplyDto = adminIsvApplyMapper.getByIdWithUser(isvApply.getId());
//            isvApplyDto.setLevel(isvApply.getLevel());
            if (isvApplyDto != null && !Integer.valueOf(isvApplyDto.getStatus()).equals(STATUS_AUDITING)) {
                createMailInfo(isvApplyDto, userDb);
            }
            return msg;
        }
        return null;
    }

    private void saveAppId(User user) {
        //配置一个8位数的APPID
        String appId = StringUtil.getStringRandom(8);
        AppAuth appAuthDb = appAuthService.getByAppId(appId);
        while (appAuthDb != null) {
            appId = StringUtil.getStringRandom(8);
            appAuthDb = appAuthService.getByAppId(appId);
        }
        AppAuth appAuth = new AppAuth();
        appAuth.setAppId(appId);
        appAuth.setStartTime(new Date());
        appAuth.setEndTime(DateTimeUtils.getInternalTimeByMonth(new Date(), Constants.APP_AUTH_VALID_PERIOD));
        appAuth.setUserId(user.getId());
        appAuth.setAuthLimit(Constants.APP_AUTH_SN_LIMIT);
        appAuthService.save(appAuth);
    }

    private void createMailInfo(IsvApplyDto isvApplyDto, User userDb) {
        String subject = null;
        String context = null;
        String levelName = null;
        String linkAddress;
        if (Integer.valueOf(isvApplyDto.getStatus()).equals(ApplyStatusType.FAILED.getValue())) {
            levelName = "ISV";
        } else {
            Integer level = userDb.getLevel();
            List<String> levelList = new ArrayList<String>(Arrays.asList(AgentLevelType.AGENT.getName(), AgentLevelType.GOLD.getName(), AgentLevelType.PLATINUM.getName(), AgentLevelType.DIAMOND.getName()));
            levelName = levelList.get(level);
        }
        if (Integer.valueOf(isvApplyDto.getStatus()).equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "ISV资格认证" + ApplyStatusType.SUCCESS.getName();
            linkAddress = customProperties.getRootAddress() + "login";
            context = isvApplyDto.getUserName() + ",你好! \t 恭喜贵公司获得木爷机器人"+ levelName +"资格认证 \t 您可以点击以下链接进行登录: \t " + linkAddress;
        } else if (Integer.valueOf(isvApplyDto.getStatus()).equals(ApplyStatusType.FAILED.getValue())) {
            subject = "ISV资格认证" + ApplyStatusType.FAILED.getName();
            linkAddress = customProperties.getRootAddress() + "account/pending";
            context = isvApplyDto.getUserName()+ ",你好! \t很抱歉贵公司未通过木爷机器人"+ levelName +"资格认证 \t 原因:" + isvApplyDto.getDescription() + "\t 您可以点击以下链接重新认证: \t " + linkAddress;
        }
        //创建邮件任务
        Mail mail = new Mail();
        mail.setFromMail(Constants.MAIL_SENDER_ACCOUNT);
        mail.setToMail(isvApplyDto.getEmail());
        mail.setSubject(subject);
        mail.setContext(context);
        mailService.save(mail);
//        mailUtil.send(emailArr, subject, context);
    }

    public IsvApplyDto getByIdWithUser(Long id) {
        return adminIsvApplyMapper.getByIdWithUser(id);
    }

}
