package com.sky.controller.user;


import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Api(tags = "购物车相关接口")
@Slf4j
public class ShoppingCartController {


    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param
     * @return
     */
    @PostMapping("/add")
    private Result addCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.addCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    private Result<List<ShoppingCart>> list(){
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);
    }


    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    private Result clear(){
        shoppingCartService.clear();
        return Result.success();
    }
}
