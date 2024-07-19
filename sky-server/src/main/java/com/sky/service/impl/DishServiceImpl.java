package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.AutoFill;
import com.sky.constant.RedisConstant;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.utils.MinioUtil;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO
     */
    @Override
    public PageResult queryPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Override
    public void add(DishDTO dishDTO) {
        Long currentId = BaseContext.getCurrentId();
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setUpdateUser(currentId);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(currentId);
        dish.setCreateTime(LocalDateTime.now());
        dishMapper.insert(dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()){
            dishFlavorMapper.update(flavors, dish.getId());
        }
    }

    /**
     * 修改菜品
     * @param dishDTO
     */
    @Override
    public void update(DishDTO dishDTO) {
        Long currentId = BaseContext.getCurrentId();
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setUpdateUser(currentId);
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.update(dish);
        //更新前需要把flavor以前绑定的数据删除
        dishFlavorMapper.deleteById(dishDTO.getId());
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()){
            dishFlavorMapper.update(flavors, dish.getId());
        }
    }

    /**
     * 通过ID查询菜品
     * @param id
     * @return
     */
    @Override
    public DishVO getById(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }


    /**
     * 批量删除菜品
     * @param ids
     */
    public void delete(List<Long> ids) {
        dishMapper.delete(ids);
        //套餐中关联的菜品也要删除
        setMealDishMapper.delete(new LambdaQueryWrapper<SetmealDish>().in(SetmealDish::getDishId, ids));
    }


    /**
     * 更新菜品状态
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(int status, Long id) {
        Dish dish = Dish.builder().status(status).id(id).build();
        dishMapper.update(dish);
    }

    /**
     * 获取菜品列表
     * @param categoryId
     * @return
     */
    @Override
    public List<Dish> list(Long categoryId) {
        List<Dish> list = dishMapper.list(categoryId);
        return list;
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish.getCategoryId());

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }
}
