package cn.muye.employee.adminApi.service;

import cn.muye.employee.adminApi.mapper.AdminEmployeeMapper;
import cn.muye.employee.domain.Employee;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Ray.Fu on 2018/3/6.
 */
@Service
@Transactional
public class AdminEmployeeService {

    @Autowired
    private AdminEmployeeMapper adminEmployeeMapper;

    public int update(Employee employee) {
        return adminEmployeeMapper.update(employee);
    }

    public List<Employee> list(String queryObj) {
        Employee employee = JSON.parseObject(queryObj, Employee.class);
        if (employee != null) {
            Map map = Maps.newHashMap();
            map.put("companyName", employee.getCompanyName());
            map.put("position", employee.getPosition());
            map.put("type", employee.getType());
            map.put("userBizId", employee.getUserBizId());
            return adminEmployeeMapper.list(map);
        } else {
            return adminEmployeeMapper.list(null);
        }
    }

    public int delete(Long id) {
        Employee employeeDb = adminEmployeeMapper.findById(id);
        if (employeeDb != null) {
            employeeDb.setIsValid(0);
            return adminEmployeeMapper.update(employeeDb);
        } else {
            return 0;
        }
    }

    public Employee findById(Long id) {
        return adminEmployeeMapper.findById(id);
    }

    public Employee findByEmail(String email) {
        Map map = Maps.newHashMap();
        map.put("email", email);
        return adminEmployeeMapper.findByEmail(map);
    }
}
