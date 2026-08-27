package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    /**
     * 查询地址簿列表
     * @return
     */
    List<AddressBook> list();

    /**
     * 新增地址簿
     * @param addressBook
     */
    void save(AddressBook addressBook);

    /**
     * 查询当前用户默认地址
     * @param addressBook
     * @return
     */
    List<AddressBook> getDefault(AddressBook addressBook);

    /**
     * 根据id查询地址簿详情
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 修改地址簿
     * @param addressBook
     */
    void updateById(AddressBook addressBook);

    /**
     * 删除地址簿
     * @param id
     */
    void delete(Long id);

    /**
     * 设置默认地址
     * @param addressBook
     */
    void setDefault(AddressBook addressBook);
}
