package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.*;
import com.sky.service.WorkSpaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {

    @Autowired
    private WorkSpaceMapper workSpaceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;



    /**
     * 获取工作台今日业务数据
     * @return
     */
    @Override
    public BusinessDataVO getBusinessData() {

        // 获取今日用户注册数
        Map map = new HashMap();
        LocalDate now = LocalDate.now();
        LocalDateTime endTime1 = LocalDateTime.of(now, LocalTime.MAX);
        map.put("endTime", endTime1);
        int userCount1 = userMapper.countByMap(map);
        LocalDateTime endTime2 = LocalDateTime.of(now.plusDays(-1), LocalTime.MAX);
        map.put("endTime", endTime2);
        int userCount2 = userMapper.countByMap(map);

        // 获取有效订单数,订单完成率
        LocalDateTime beginTime = LocalDateTime.of(now, LocalTime.MIN);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime1);
        int OrderCount = orderMapper.countByMap(map);
        map.put("status", 5);
        int validOrderCount = orderMapper.countByMap(map);

        // 设置数据
        if (validOrderCount == 0){
            return BusinessDataVO.builder()
                    .newUsers(userCount1 - userCount2)
                    .validOrderCount(0)
                    .orderCompletionRate(0.0)
                    .unitPrice(0.0)
                    .turnover(0.0)
                    .build();
        }

        // 营业额,平均客单价
        Double turnover = reportMapper.sumByMap(map);
        turnover = turnover == null ? 0.0 : turnover;

        return BusinessDataVO.builder()
                .newUsers(userCount1 - userCount2)
                .validOrderCount(validOrderCount)
                .orderCompletionRate((double) validOrderCount / OrderCount)
                .unitPrice(turnover / validOrderCount)
                .turnover(turnover)
                .build();
    }

    /**
     * 获取工作台订单 overview
     * @return
     */
    @Override
    public OrderOverViewVO getOrderOverView() {
        Map map = new HashMap();
        map.put("beginTime", LocalDateTime.now().with(LocalTime.MIN));
        map.put("status", Orders.TO_BE_CONFIRMED);

        //待接单
        Integer waitingOrders = orderMapper.countByMap(map);

        //待派送
        map.put("status", Orders.CONFIRMED);
        Integer deliveredOrders = orderMapper.countByMap(map);

        //已完成
        map.put("status", Orders.COMPLETED);
        Integer completedOrders = orderMapper.countByMap(map);

        //已取消
        map.put("status", Orders.CANCELLED);
        Integer cancelledOrders = orderMapper.countByMap(map);

        //全部订单
        map.put("status", null);
        Integer allOrders = orderMapper.countByMap(map);

        return OrderOverViewVO.builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }

    /**
     * 获取工作台菜品 overview
     * @return
     */
    @Override
    public DishOverViewVO getDishOverView() {
        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = dishMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = dishMapper.countByMap(map);

        return DishOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    /**
     * 获取工作台套餐 overview
     * @return
     */
    @Override
    public SetmealOverViewVO getSetmealOverView() {
        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = setmealMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = setmealMapper.countByMap(map);

        return SetmealOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }
}
