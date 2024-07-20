package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;

public interface ShoppingCartService extends IService<ShoppingCart> {
    void addCart(ShoppingCartDTO shoppingCartDTO);

    void clear();
}
