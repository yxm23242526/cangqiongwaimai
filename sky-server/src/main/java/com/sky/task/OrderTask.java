package com.sky.task;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderTask {

    private static final int timeOut = 15;
    private static final int timeDelay = 60;

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 处理超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Orders::getStatus, Orders.PENDING_PAYMENT)
                        .ge(Orders::getOrderTime, LocalDateTime.now().minusMinutes(timeOut));
        List<Orders> orders = orderMapper.selectList(queryWrapper);
        if (orders != null && !orders.isEmpty()){
            // List<Long> ids = orders.stream().map(Orders::getId).collect(Collectors.toList());
            // orderMapper.deleteBatchIds(ids);
            orders.forEach(order ->{
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，已取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.updateById(order);
            });
        }
    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliverOrder(){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Orders::getStatus, Orders.DELIVERY_IN_PROGRESS)
                .ge(Orders::getOrderTime, LocalDateTime.now().minusMinutes(timeDelay));
        List<Orders> orders = orderMapper.selectList(queryWrapper);
        if (orders != null && !orders.isEmpty()){
            // List<Long> ids = orders.stream().map(Orders::getId).collect(Collectors.toList());
            // orderMapper.deleteBatchIds(ids);
            orders.forEach(order ->{
                order.setStatus(Orders.COMPLETED);
                orderMapper.updateById(order);
            });
        }
    }
}
