package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;

import java.time.LocalDateTime;

public interface WorkSpaceService {
    /**
     * 获取工作台今日业务数据
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 获取工作台订单 overview
     * @return
     */
    OrderOverViewVO getOrderOverView();

    /**
     * 获取工作台菜品 overview
     * @return
     */
    DishOverViewVO getDishOverView();

    /**
     * 获取工作台套餐 overview
     * @return
     */
    SetmealOverViewVO getSetmealOverView();
}
