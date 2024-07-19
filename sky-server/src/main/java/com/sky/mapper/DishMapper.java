package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void update(Dish dish);


    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);


    void delete(List<Long> ids);


    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> list(Long categoryId);
}
