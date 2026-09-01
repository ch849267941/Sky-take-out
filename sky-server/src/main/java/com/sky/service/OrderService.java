package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    /**
     * 提交订单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * 此处已架空
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 分页查询历史订单
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult historyOrders(int page, int pageSize, Integer status);

    /**
     * 取消订单
     * @param id
     */
    void cancel(Long id) throws Exception;

    /**
     * 查看订单详情
     * @param id
     * @return
     */
    OrderVO orderDetail(Long id);

    /**
     * 用户重复下单
     * @param id
     */
    void repetition(Long id);

    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 订单统计
     * @return
     */
    OrderStatisticsVO orderStatistics();

    /**
     * 订单确认
     * @param ordersConfirmDTO
     */
    void orderConfirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 订单拒单
     * @param ordersRejectionDTO
     */
    void orderRejection(OrdersRejectionDTO ordersRejectionDTO);

    /**
     * 订单取消
     * @param ordersCancelDTO
     */
    void orderCancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 订单送餐
     * @param id
     */
    void orderDelivery(Long id);

    /**
     * 订单完成
     * @param id
     */
    void orderComplete(Long id);
}
