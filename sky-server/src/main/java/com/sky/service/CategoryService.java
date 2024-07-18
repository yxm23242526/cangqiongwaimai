package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    void updateType(CategoryDTO categoryDTO);


    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult queryPages(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 删除分类
     * @param id
     * @return
     */
    void deleteById(Long id);

    /**
     * 修改状态
     * @param status
     */
    void updateStatus(Integer status, Long id);

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    void addType(Category category);

    /**
     * 查询分类
     * @return
     */
    List<Category> queryByType(Integer type);
}
