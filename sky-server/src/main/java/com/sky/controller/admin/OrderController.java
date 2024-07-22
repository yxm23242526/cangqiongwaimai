package com.sky.controller.admin;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("订单搜索");
        PageResult pageResult = orderService.conditionSearchOrders(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> queryOrderStatistics(){
        log.info("各个状态的订单数量统计");
        OrderStatisticsVO statisticsVO = orderService.queryOrderStatistics();
        return Result.success(statisticsVO);
    }

    @GetMapping("/details/{id}")
    public Result<OrderVO> getDetailsById(@PathVariable("id") Long id){
        log.info("订单详情");
        OrderVO orderVO = orderService.getDetailsById(id);
        return Result.success(orderVO);
    }

}
