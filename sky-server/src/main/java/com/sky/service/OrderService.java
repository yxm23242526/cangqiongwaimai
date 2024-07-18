package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.vo.OrderStatisticsVO;

public interface OrderService extends IService<Orders> {
    PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO queryOrderStatistics();
}
