package cn.muye.cooperation.adminApi.service;

import cn.muye.config.CustomProperties;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.dto.AgentApplyDto;
import cn.muye.cooperation.adminApi.mapper.AdminAgentApplyMapper;
import cn.muye.core.Constants;
import cn.muye.core.enums.ApplyStatusType;
import cn.muye.user.domain.User;
import cn.muye.user.adminApi.service.AdminUserService;
import cn.muye.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/12.
 */
@Service
@Transactional
public class AdminAgentApplyService {

    @Autowired
    private AdminAgentApplyMapper adminAgentApplyMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private CustomProperties customProperties;

    private static final int STATUS_SUBMIT = 0; //已提交
    private static final int STATUS_AUDITING = 1; //待审核
    private static final int STATUS_SUCCESS = 2; //成功
    private static final int STATUS_FAILED = 3; //失败

    public AgentApply getById(Long id) {
        return adminAgentApplyMapper.getById(id);
    }

    public List<AgentApplyDto> list(Integer page, Integer status) {
        return adminAgentApplyMapper.list(page, status);
    }

    public void save(AgentApply agentApply) {
        adminAgentApplyMapper.save(agentApply);
    }

    public String update(AgentApply agentApply, String type, HttpServletRequest request) {
        adminAgentApplyMapper.update(agentApply);
        if (type.equals(Constants.TYPE_AUDIT_AGENT_APPLY)) {
            Long userId = agentApply.getUserId();
            User userDb = adminUserService.getUserById(userId);
            String msg = null;
            switch (agentApply.getStatus()) {
                case STATUS_AUDITING :
                    msg = "待审核，请耐心等待";
                    break;
                case STATUS_SUCCESS :
                    msg = "认证通过，并发送通知邮件";
                    if (userDb != null) {
                        //审核通过要更改用户等级为普通代理商
                        userDb.setLevel(Constants.CUSTOMER_AGENT);
                        adminUserService.updateAndBindRole(userDb);
                    }
                    break;
                case STATUS_FAILED :
                    msg = "认证失败，并发送通知邮件";
                    break;
            }
            AgentApplyDto agentApplyDto = adminAgentApplyMapper.getByIdWithUser(agentApply.getId());
            if (agentApplyDto != null && !Integer.valueOf(agentApplyDto.getStatus()).equals(STATUS_AUDITING)) {
                sendMail(agentApplyDto, request);
            }
            return msg;
        }
        return null;
    }

    private void sendMail(AgentApplyDto agentApplyDto, HttpServletRequest request) {
        String subject = null;
        String[] emailArr = new String[]{agentApplyDto.getEmail()};
        String context = null;
        String linkAddress = null;
        if (Integer.valueOf(agentApplyDto.getStatus()).equals(ApplyStatusType.SUCCESS.getValue())) {
            subject = "代理商资格认证" + ApplyStatusType.SUCCESS.getName();
            linkAddress = customProperties.getRootAddress() + "login";
            context = agentApplyDto.getUserName() + ",你好! \t 恭喜贵公司获得木爷机器人代理商资格 \t 您可以点击以下链接进行登录: \t " + linkAddress;
        } else if (Integer.valueOf(agentApplyDto.getStatus()).equals(ApplyStatusType.FAILED.getValue())) {
            subject = "代理商资格认证" + ApplyStatusType.FAILED.getName();
            linkAddress = customProperties.getRootAddress() + "account/pending";
            context = agentApplyDto.getUserName()+ ",你好! \t很抱歉贵公司未通过木爷机器人代理商资格审核 \t 原因:" + agentApplyDto.getDescription() + "\t 您可以点击以下链接重新认证: \t " + linkAddress;
        }
        mailUtil.send(emailArr, subject, context);
    }

    public AgentApplyDto getByIdWithUser(Long id) {
        return adminAgentApplyMapper.getByIdWithUser(id);
    }

    public AgentApplyDto getDtoByUserId(Long userId) {
        return adminAgentApplyMapper.getDtoByUserId(userId);
    }

    public AgentApply getByUserId(Long userId) {
        return adminAgentApplyMapper.getByUserId(userId);
    }
}
