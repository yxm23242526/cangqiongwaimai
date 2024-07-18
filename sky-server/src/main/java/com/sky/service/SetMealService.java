package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService extends IService<Setmeal> {
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void addSetMeal(SetmealDTO setmealDTO);

    void delete(List<Long> ids);

    void updateStatus(int status, Long id);

    SetmealVO queryById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);
}
