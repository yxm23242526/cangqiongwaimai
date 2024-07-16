package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page") // query
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult querySet = dishService.queryPage(dishPageQueryDTO);
        return Result.success(querySet);
    }

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    public Result<String> add(@RequestBody DishDTO dishDTO){
        dishService.add(dishDTO);
        return Result.success();
    }
}
