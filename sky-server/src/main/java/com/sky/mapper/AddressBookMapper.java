package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    /**
     * 查询地址簿列表
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 新增地址簿
     * @param addressBook
     */
    @Insert("insert into address_book (user_id,consignee,phone,sex,province_code,province_name,city_code,city_name,district_code,district_name,detail,is_default,label) values (#{userId},#{consignee},#{phone},#{sex},#{provinceCode},#{provinceName},#{cityCode},#{cityName},#{districtCode},#{districtName},#{detail},#{isDefault},#{label})")
    void save(AddressBook addressBook);

    /**
     * 根据id查询地址簿详情
     * @param id
     * @return
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 根据id修改地址簿
     * @param addressBook
     */
    void updateById(AddressBook addressBook);

    /**
     * 删除地址簿
     * @param id
     */
    @Delete("delete from address_book where id = #{id}")
    void delete(Long id);

    /**
     * 根据 用户id修改 是否默认地址
     * @param addressBook
     */
    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(AddressBook addressBook);
}
