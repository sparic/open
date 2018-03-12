package cn.muye.employee.api.mapper;

import cn.muye.employee.domain.Employee;

import java.util.Map;

/**
 * Created by sparic on 2018/3/6.
 */
public interface EmployeeMapper {

    int save(Employee employee);

    Employee findByEmail(Map map);
}
