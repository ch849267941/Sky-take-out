package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
    * 根据菜品id查询套餐id
    * @param ids
    * @return
    */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 根据套餐id删除套餐菜品
     * @param id
     */
    @Delete("DELETE FROM setmeal_dish WHERE setmeal_id = #{id}")
    void deleteSetmealDishBySetmealId(Long id);

    /**
     * 批量插入套餐菜品
     * @param setmealDishes
     */
    void insertSetmealDish(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id查询套餐菜品
     * @param id
     * @return
     */
    @Select("SELECT * FROM setmeal_dish WHERE setmeal_id = #{id}")
    List<SetmealDish> getSetmealDishesBySetmealId(Long id);


    /**
     * 根据套餐id删除套餐菜品
     * @param ids
     */
    void deleteSetmealDishBySetmealIds(List<Long> ids);
}
