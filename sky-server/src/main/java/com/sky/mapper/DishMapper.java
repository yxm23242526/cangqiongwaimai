package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {
    Page<Dish> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void update(Dish dish);


    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
}
