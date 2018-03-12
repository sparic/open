package cn.muye.employee.adminApi.controller;

import cn.muye.common.WhereRequest;
import cn.muye.core.AjaxResult;
import cn.muye.core.Constants;
import cn.muye.employee.adminApi.service.AdminEmployeeService;
import cn.muye.employee.domain.Employee;
import cn.muye.user.adminApi.service.AdminUserService;
import cn.muye.user.domain.User;
import cn.muye.utils.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by sparic on 2018/3/6.
 */
@RestController
public class AdminEmployeeController {

    private static final Logger log = LoggerFactory.getLogger(AdminEmployeeController.class);

    @Autowired
    private AdminEmployeeService adminEmployeeService;

    @Autowired
    private AdminUserService adminUserService;

    @Value("${devCenter.pushDirs}")
    protected String DOWNLOAD_HOME;

    @Value("${devCenter.pushHttp}")
    protected String DOWNLOAD_HTTP;

    //员工导出文件前缀
    private static final String EMPLOYEE_FILE_PREFIX = "员工信息_";
    //员工导出文件后缀
    private static final String EMPLOYEE_FILE_SUFFIX = ".csv";
    //CSV文件分隔符
    private static final String NEW_LINE_SEPARATOR = "\n";

    @RequestMapping(value = "admin/employee", method = RequestMethod.GET)
    @RequiresPermissions("employee:query")
    @ApiOperation(value = "查询员工列表", httpMethod = "GET", notes = "查询员工列表")
    public AjaxResult listByUserId(WhereRequest whereRequest) {
        PageHelper.startPage(whereRequest.getPage(), whereRequest.getPageSize(), true, null, true);
        List<Employee> employeeList = adminEmployeeService.list(whereRequest.getQueryObj());
        PageInfo<Employee> employeePageInfo = new PageInfo(employeeList);
        employeePageInfo.setList(employeeList);
        return AjaxResult.success(employeePageInfo, "查询成功");
    }

    @RequestMapping(value = "admin/employee", method = RequestMethod.PUT)
    @RequiresPermissions("employee:update")
    @ApiOperation(value = "更新员工信息", httpMethod = "PUT", notes = "更新员工信息")
    public AjaxResult update(@ApiParam(value = "员工信息") @RequestBody Employee employee) {
        Long id = employee.getId();
        String email = employee.getEmail();
        if (id == null || employee != null && (StringUtils.isEmpty(employee.getName()) ||
                StringUtils.isEmpty(employee.getPhone()) || StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(employee.getPosition()) || StringUtils.isEmpty(employee.getCompanyName()) ||
                StringUtils.isEmpty(employee.getUserBizId()) || employee.getType() == null)) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "参数有误");
        }
        Employee employeeDbByEmail = adminEmployeeService.findByEmail(email);
        Employee employeeDb = adminEmployeeService.findById(id);
        if (employeeDbByEmail != null && employee.getEmail().equals(email) &&
                !employeeDbByEmail.getId().equals(employeeDb.getId())) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "邮箱已注册");
        }
        if (!objectToEntity(employeeDb, employee)) {
            return AjaxResult.failed(AjaxResult.CODE_PARAM_MISTAKE_FAILED, "所属公司不存在");
        }
        int flag = adminEmployeeService.update(employeeDb);
        if (flag > 0) {
            return AjaxResult.success(employeeDb, "更新成功");
        } else {
            return AjaxResult.failed("更新失败");
        }
    }

    @RequestMapping(value = "admin/employee/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("employee:delete")
    @ApiOperation(value = "删除员工信息", httpMethod = "DELETE", notes = "删除员工信息")
    public AjaxResult delete(@ApiParam(value = "员工信息id") @PathVariable Long id) {
        Employee employeeDb = adminEmployeeService.findById(id);
        if (employeeDb == null) {
            return AjaxResult.failed("不存在的员工");
        }
        employeeDb.setIsValid(Constants.NOT_VALID);
        int flag = adminEmployeeService.update(employeeDb);
        if (flag > 0) {
            return AjaxResult.success(employeeDb, "删除成功");
        } else {
            return AjaxResult.failed("删除失败");
        }
    }

    @RequestMapping(value = "admin/employee/export", method = RequestMethod.GET)
    @RequiresPermissions("employee:export")
    @ApiOperation(value = "导出员工信息", httpMethod = "GET", notes = "导出员工信息")
    public AjaxResult export(@ApiParam(value = "查询条件") @RequestParam(value = "queryObj") String queryObj) {
        OutputStreamWriter out = null;
        CSVPrinter printer = null;
        try {
            //CSV相对路径
            String exportPath = Constants.EXPORT_DIR_NAME + File.separator + Constants.EMPLOYEE_DIR_NAME;
            File exportCsvDir = new File(DOWNLOAD_HOME + File.separator + exportPath);
            if (!exportCsvDir.exists()) {
                exportCsvDir.mkdirs();
            }
            //生成CSV文件
            String employeeCsvName = EMPLOYEE_FILE_PREFIX + DateTimeUtils.getNormalNameDateTime() + EMPLOYEE_FILE_SUFFIX;
            File employeeCsvFile = new File(exportCsvDir, employeeCsvName);
            // 创建CSV写对象
            String[] FILE_HEADER = new String[]{"姓名", "手机", "邮箱", "公司", "职位", "类别"};
            CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR).withFirstRecordAsHeader();
            // 这是写入CSV的代码
            out = new OutputStreamWriter(new FileOutputStream(employeeCsvFile), "GB2312");
            printer = new CSVPrinter(out, format);
            //写入列头数据
            printer.printRecord(FILE_HEADER);
            List<Employee> employeeList = adminEmployeeService.list(queryObj);
            if (employeeList != null && employeeList.size() > 0) {
                for (Employee employee : employeeList) {
                    List<String> employeeStr = Lists.newArrayList();
                    employeeStr.add(employee.getName());
                    employeeStr.add(String.valueOf(employee.getPhone()));
                    employeeStr.add(String.valueOf(employee.getEmail()));
                    employeeStr.add(String.valueOf(employee.getCompanyName()));
                    employeeStr.add(String.valueOf(employee.getPosition()));
                    employeeStr.add(employee.getType() == null ? null :
                            employee.getType() == 1 ? "诺亚" : "人形");
                    printer.printRecord(employeeStr);
                }
            }
            String employeeFileHttpPath = DOWNLOAD_HTTP + File.separator + exportPath + File.separator + employeeCsvName;
            employeeFileHttpPath = employeeFileHttpPath.replaceAll("\\\\", "/");
            return AjaxResult.success(employeeFileHttpPath, "导出成功");
        } catch (IOException e) {
            log.error("AdminEmployeeController method[export] error ==>", e);
            return AjaxResult.failed(1, "报表导出错误");
        } finally {
            try {
                if (null != printer) {
                    printer.flush();
                    printer.close();
                }
                if (null != out)
                    out.close();
            } catch (Exception e) {
                log.error("报表导出错误", e);
            }
        }
    }

    private Boolean objectToEntity(Employee employeeDb, Employee employee) {
        employeeDb.setIsValid(employee.getIsValid());
        User userDb = adminUserService.getByBizId(employee.getUserBizId());
        if (userDb != null) {
            employeeDb.setUserBizId(employee.getUserBizId());
            employeeDb.setCompanyName(userDb.getCompany());
        } else {
            return false;
        }
        employeeDb.setName(employee.getName());
        employeeDb.setEmail(employee.getEmail());
        employeeDb.setPhone(employee.getPhone());
        employeeDb.setPosition(employee.getPosition());
        employeeDb.setType(employeeDb.getType());
        return true;
    }

}
