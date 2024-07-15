package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     * @param empId
     */
    void add(EmployeeDTO employeeDTO, Long empId);

    /**
     * 分页查询员工
     * @param employeePageQueryDTO
     * @return
     */
    PageResult getPages(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改密码
     * @param passwordEditDTO
     * @return
     */
    void editPassword(PasswordEditDTO passwordEditDTO);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);
}
