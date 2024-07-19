package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService extends IService<Dish> {

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

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);

    /**
     * 通过ID查询菜品
     * @param id
     * @return
     */
    DishVO getById(Long id);


    void delete(List<Long> ids);


    void updateStatus(int status, Long id);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    List<DishVO> listWithFlavor(Dish dish);
}
