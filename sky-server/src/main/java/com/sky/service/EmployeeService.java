package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService extends IService<Employee> {

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
     * @param updateId
     * @return
     */
    void editPassword(PasswordEditDTO passwordEditDTO, Long updateId);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 更新员工状态
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);


    /**
     * 修改员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
