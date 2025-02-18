package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    Page<Category> getPages(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);

    @Insert("insert into category (type, name, sort, status, create_time, update_time, create_user, update_user)" +
            "values (#{type}, #{name}, #{sort}, #{status}, #{createTime}, " +
            "#{updateTime}, #{createUser}, #{updateUser})")
    void add(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    List<Category> queryByType(Integer type);
}
