package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    public Result<String> updateType(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateType(categoryDTO);
        return Result.success();
    }


    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> queryPages(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.queryPages(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable Integer status, Long id) {
        categoryService.updateStatus(status, id);
        return Result.success();
    }


    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    public Result<String> addType(@RequestBody CategoryDTO categoryDTO){
        categoryService.addType(categoryDTO);
        return Result.success();
    }


    /**
     * 根据ID删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> deleteById(Long id){
        categoryService.deleteById(id);
        return Result.success();
    }


    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result<List<Category>> queryByType(Integer type){
        List<Category> categoryList = categoryService.queryByType(type);
        return Result.success(categoryList);
    }
}
