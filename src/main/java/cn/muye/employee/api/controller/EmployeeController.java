package cn.muye.employee.api.controller;

import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.employee.api.service.EmployeeService;
import cn.muye.employee.domain.Employee;
import cn.muye.user.adminApi.service.AdminUserService;
import cn.muye.user.api.controller.UserController;
import cn.muye.user.api.service.UserService;
import cn.muye.user.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 * Created by Ray.Fu on 2018/3/6.
 */
@RestController
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ApiOperation(value = "前台新增员工", httpMethod = "POST", notes = "前台新增员工")
    public AjaxResult addEmployee(@ApiParam(value = "员工对象") @RequestBody Employee employee) {
        String userBizId = employee.getUserBizId();
        String email = employee.getEmail();
        if (null == employee || employee != null && (StringUtils.isEmpty(employee.getName()) ||
                StringUtils.isEmpty(employee.getPhone()) || StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(employee.getPosition()) || StringUtils.isEmpty(userBizId))) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, employee, "员工信息为空或信息不全");
        }
        try {
            Employee employeeDb = employeeService.findByEmail(email);
            if (employeeDb != null) {
                return AjaxResult.failed(employeeDb, "邮箱已注册");
            }
            User userDb = adminUserService.getByBizId(userBizId);
            if (userDb == null) {
                return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED,"不存在的公司账号");
            }
            String companyName = userDb.getCompany();
            Integer type = userDb.getType();
            employee.setCompanyName(companyName);
            employee.setType(type);
            employee.setIsValid(Constants.VALID);
            int num = employeeService.save(employee);
            if (num > 0) {
                return AjaxResult.success(employee,"新增成功");
            } else {
                return AjaxResult.failed(employee,"添加失败");
            }
        } catch (Exception e) {
            log.error("addEmployee error ==>", e);
            return AjaxResult.failed(employee, "添加失败");
        } finally {
        }
    }

}
