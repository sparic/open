package cn.muye.employee.api.service;

import cn.muye.employee.api.mapper.EmployeeMapper;
import cn.muye.employee.domain.Employee;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * Created by sparic on 2018/3/6.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    public Employee findByEmail(String email) {
        Map map = Maps.newHashMap();
        map.put("email", email);
        return employeeMapper.findByEmail(map);
    }
}
