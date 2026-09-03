package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    /**
     * 根据时间范围查询订单总金额
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 根据时间范围查询Top10商品
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getTop10(LocalDate begin, LocalDate end);
}
