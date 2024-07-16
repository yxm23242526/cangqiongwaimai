package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 修改分类
     * @param categoryDTO
     */
    @Override
    public void updateType(CategoryDTO categoryDTO) {
        Category category = Category.builder().id(categoryDTO.getId()).name(categoryDTO.getName())
                .sort(categoryDTO.getSort()).type(categoryDTO.getType()).build();
        categoryMapper.update(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult queryPages(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> pages = categoryMapper.getPages(categoryPageQueryDTO);
        return new PageResult(pages.getTotal(), pages);
    }

    /**
     * 根据ID删除分类
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(Integer status, Long id) {
        Category category = Category.builder()
                .status(status).id(id).build();
        categoryMapper.update(category);
    }

    /**
     * 新增分类
     * @param categoryDTO
     */
    @Override
    public void addType(CategoryDTO categoryDTO) {
        Long currentId = BaseContext.getCurrentId();
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(currentId);
        category.setUpdateUser(currentId);
        categoryMapper.add(category);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @Override
    //TODO
    public List<Category> queryByType(Integer type) {
        return categoryMapper.queryByType(type);
    }
}
