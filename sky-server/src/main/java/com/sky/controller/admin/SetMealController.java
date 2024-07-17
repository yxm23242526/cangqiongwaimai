package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetMealController {


    @Autowired
    private SetMealService setMealService;
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐{}",setmealPageQueryDTO);
        PageResult pageResult = setMealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }


    @PostMapping
    public Result saveSetMeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐");
        setMealService.addSetMeal(setmealDTO);
        return Result.success();
    }


    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐{}",ids);
        setMealService.delete(ids);
        return Result.success();
    }


    @PostMapping("status/{status}")
    public Result updateSetmealStatus(@PathVariable int status,Long id){
        log.info("套餐起售、停售{}",id);
        setMealService.updateStatus(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> queryById(@PathVariable Long id){
        log.info("根据id查询套餐");
        SetmealVO setmealVO = setMealService.queryById(id);
        return Result.success(setmealVO);
    }


    @PutMapping
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐{}",setmealDTO);
        setMealService.updateSetmeal(setmealDTO);
        return  Result.success();
    }
}
