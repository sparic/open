package cn.muye.employee.adminApi.mapper;


import cn.muye.employee.domain.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by sparic on 2018/3/6.
 */
public interface AdminEmployeeMapper {

    Employee findById(Long id);

    int delete(Long id);

    int update(Employee employeeDb);

    List<Employee> list(Map map);

    Employee findByEmail(Map map);
}
