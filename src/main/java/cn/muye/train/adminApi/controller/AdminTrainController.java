package cn.muye.train.adminApi.controller;

import cn.muye.common.WhereRequest;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.employee.adminApi.service.AdminEmployeeService;
import cn.muye.employee.domain.Employee;
import cn.muye.mail.manage.EmailSendManager;
import cn.muye.mail.model.SimpleEmail;
import cn.muye.train.adminApi.service.AdminTrainService;
import cn.muye.train.domain.Train;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sparic on 2018/3/7.
 */
@RestController
public class AdminTrainController {

    private static final Logger log = LoggerFactory.getLogger(AdminTrainController.class);

    @Autowired
    private EmailSendManager emailSendManager;

    @Autowired
    private AdminTrainService adminTrainService;

    @Autowired
    private AdminEmployeeService adminEmployeeService;

    @RequestMapping(value = "admin/train", method = RequestMethod.GET)
    @RequiresPermissions("train:query")
    @ApiOperation(value = "查询课程列表", httpMethod = "GET", notes = "查询课程列表")
    public AjaxResult list(WhereRequest whereRequest) {
        PageHelper.startPage(whereRequest.getPage(), whereRequest.getPageSize(), true, null, true);
        List<Train> trainList = adminTrainService.list(whereRequest.getQueryObj());
        PageInfo<Train> trainPageInfo = new PageInfo(trainList);
        trainPageInfo.setList(trainList);
        return AjaxResult.success(trainPageInfo, "查询成功");
    }

    @RequestMapping(value = "admin/train", method = RequestMethod.POST)
    @RequiresPermissions("train:insert")
    @ApiOperation(value = "新增课程", httpMethod = "POST", notes = "新增课程")
    public AjaxResult addTrain(@ApiParam(value = "课程信息JSON串") @RequestBody Train train) {
        if (null == train || train != null && (StringUtils.isEmpty(train.getTopic()) ||
                StringUtils.isEmpty(train.getTeacher()) || StringUtils.isEmpty(train.getLocation()) ||
                StringUtils.isEmpty(train.getTime()) || StringUtils.isEmpty(train.getWebexUrl()))) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, train, "课程信息为空或信息不全");
        }
        try {
            int num = adminTrainService.save(train);
            if (num > 0) {
                return AjaxResult.success(train,"新增成功");
            } else {
                return AjaxResult.failed(train,"添加失败");
            }
        } catch (Exception e) {
            log.error("addTrain error ==>{}", e);
            return AjaxResult.failed(train, "添加失败");
        } finally {
        }
    }

    @RequestMapping(value = "admin/train", method = RequestMethod.PUT)
    @RequiresPermissions("train:update")
    @ApiOperation(value = "更新课程", httpMethod = "PUT", notes = "更新课程")
    public AjaxResult updateTrain(@ApiParam(value = "课程信息JSON串") @RequestBody Train train) {
        Long id = train.getId();
//        if (null == train || train != null && (id == null || StringUtils.isEmpty(train.getTopic()) ||
//                StringUtils.isEmpty(train.getTeacher()) || StringUtils.isEmpty(train.getLocation()) ||
//                StringUtils.isEmpty(train.getTime()) || StringUtils.isEmpty(train.getWebexUrl()))) {
//            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, train, "课程信息为空或信息不全");
//        }
        Train trainDb = adminTrainService.getById(id);
        if (trainDb == null) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED,"所属公司不存在");
        }
        objectToEntity(trainDb, train);
        int flag = adminTrainService.update(trainDb);
        if (flag > 0) {
            return AjaxResult.success(trainDb, "更新成功");
        } else {
            return AjaxResult.failed("更新失败");
        }
    }

    /**
     *  发送培训邮件
     * @return
     */
    @RequestMapping(value = "admin/train/sendMail", method = RequestMethod.GET)
    @RequiresPermissions("train:send")
    @ApiOperation(value = "发送培训邮件", httpMethod = "GET", notes = "发送培训邮件")
    public AjaxResult sendMail(@ApiParam(value = "邮件实体") @RequestParam(value = "id") Long id) {
        Train trainDb = adminTrainService.getById(id);
        if (trainDb == null) {
            return AjaxResult.failed("不存在的课程");
        }
        String description = trainDb.getDescription();
        String location = trainDb.getLocation();
        String time = trainDb.getTime();
        String teacher = trainDb.getTeacher();
        String topic = trainDb.getTopic();
        String employeeIds = trainDb.getEmployeeIds();
        String webexUrl = trainDb.getWebexUrl();
        String mailSubject = trainDb.getMailSubject();
        if (StringUtils.isEmpty(description) || StringUtils.isEmpty(location) || StringUtils.isEmpty(time) ||
                StringUtils.isEmpty(teacher) || StringUtils.isEmpty(topic) || StringUtils.isEmpty(employeeIds) ||
                StringUtils.isEmpty(webexUrl) || StringUtils.isEmpty(mailSubject)) {
            return AjaxResult.failed("课程信息不全，请检查");
        }
        //获取课程选中的邮件地址
        String[] employeeIdArr = employeeIds.split(",");
        //接收者邮箱
        Set<String> receivers = new HashSet<>();
        for (String empId : employeeIdArr) {
            Employee employeeDb = adminEmployeeService.findById(Long.valueOf(empId));
            receivers.add(employeeDb.getEmail());
        }
        //定义邮件对象
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setSubject(trainDb.getMailSubject());
        simpleEmail.setToSet(receivers);

        Map<String, Object> params = Maps.newHashMap();
        params.put("title", "This's title");
        params.put("topic", topic);
        params.put("description", description);
        params.put("location", location);
        params.put("time", time);
        params.put("teacher", teacher);
        params.put("webexUrl", webexUrl);
        simpleEmail.setAttachment(false);

        try {
            emailSendManager.sendThymeleafEmail(simpleEmail, params, "emailTemplate.html");
            //发送成功修改培训实例状态
            trainDb.setStatus(Constants.MAIL_STATUS_SEND);
            adminTrainService.update(trainDb);
        } catch (MessagingException e) {
            e.printStackTrace();
            return AjaxResult.failed("发送失败");
        }
        return AjaxResult.success("发送成功");
    }

    private void objectToEntity(Train trainDb, Train train) {
        String topic = train.getTopic();
        String time = train.getTime();
        String location = train.getLocation();
        String teacher = train.getTeacher();
        String webexUrl = train.getWebexUrl();
        Integer status = train.getStatus();
        String description = train.getDescription();
        String employeeIds = train.getEmployeeIds();
        if (!StringUtils.isEmpty(topic)) {
            trainDb.setTopic(topic);
        }
        if (!StringUtils.isEmpty(time)) {
            trainDb.setTime(time);
        }
        if (!StringUtils.isEmpty(location)) {
            trainDb.setLocation(location);
        }
        if (!StringUtils.isEmpty(teacher)) {
            trainDb.setTeacher(teacher);
        }
        if (!StringUtils.isEmpty(webexUrl)) {
            trainDb.setWebexUrl(webexUrl);
        }
        if (status != null) {
            trainDb.setStatus(status);
        }
        if (!StringUtils.isEmpty(description)) {
            trainDb.setDescription(description);
        }
        if (!StringUtils.isEmpty(employeeIds)) {
            trainDb.setEmployeeIds(employeeIds);
        }
    }
}
