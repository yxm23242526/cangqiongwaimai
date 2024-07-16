package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult queryPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 新增菜品
     * @param dishDTO
     */
    void add(DishDTO dishDTO);
}
