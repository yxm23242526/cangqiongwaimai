package com.sky.controller.admin;

import com.sky.constant.RedisConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 批量删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除菜品：{}",ids);
        clearCache(RedisConstant.CATEGORY_PREFIX + '*');
        dishService.delete(ids);
        return Result.success();
    }


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
    @PostMapping
    public Result<String> add(@RequestBody DishDTO dishDTO){
        clearCache(RedisConstant.CATEGORY_PREFIX + dishDTO.getId());
        dishService.add(dishDTO);
        return Result.success();
    }


    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody DishDTO dishDTO){
        clearCache(RedisConstant.CATEGORY_PREFIX + '*');
        dishService.update(dishDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }


    /**
     * 根据分类ID查询所有菜品
     */
    @GetMapping("/list")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }


    /**
     * 起售停售菜品
     * @param status
     * @param id
     * @return
     */
    public Result<String> updateStatus(@PathVariable Integer status, Long id){
        clearCache(RedisConstant.CATEGORY_PREFIX + '*');
        dishService.updateStatus(status, id);
        return Result.success();
    }

    /**
     * 清理redis缓存数据
     * @param pattern
     */
    private void clearCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
