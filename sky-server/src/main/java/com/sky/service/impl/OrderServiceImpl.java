package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrderMapper;
import com.sky.result.PageResult;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService{


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    @Override
    public PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());
        Page<OrderVO> page = orderMapper.queryConditionSearchOrders(ordersPageQueryDTO);
        page.forEach(orders -> {
            List<OrderDetail> orderDetails = orderDetailMapper.selectList(new LambdaQueryWrapper<OrderDetail>().eq(OrderDetail::getOrderId, orders.getId()));
            orders.setOrderDetailList(orderDetails);
        });
        return new PageResult(page.getTotal(), page);
    }

    @Override
    public OrderStatisticsVO queryOrderStatistics() {

        OrderStatisticsVO orderStatisticsVO = new OrderStatisticsVO();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        Integer toBeConfirmed = orderMapper.selectCount(queryWrapper
                .eq(Orders::getStatus, Orders.TO_BE_CONFIRMED));
        Integer confirmed = orderMapper.selectCount(queryWrapper
                .eq(Orders::getStatus, Orders.CONFIRMED));
        Integer deliveryInProgress = orderMapper.selectCount(queryWrapper
                .eq(Orders::getStatus, Orders.DELIVERY_IN_PROGRESS));
        orderStatisticsVO.setToBeConfirmed(toBeConfirmed);
        orderStatisticsVO.setConfirmed(confirmed);
        orderStatisticsVO.setDeliveryInProgress(deliveryInProgress);
        return orderStatisticsVO;
    }
}
