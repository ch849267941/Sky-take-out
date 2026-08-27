package com.sky.controller.user;


import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "C端-地址簿接口")
@Slf4j
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询地址簿列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询地址簿列表")
    public Result<List<AddressBook>> list() {
        log.info("查询地址簿列表");
        return Result.success(addressBookService.list());
    }

    /**
     * 新增地址簿
     * @param addressBook
     * @return
     */
    @PostMapping
    @ApiOperation("新增地址簿")
    public Result save(@RequestBody AddressBook addressBook) {
        log.info("新增地址簿");
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * 查询当前用户默认地址
     * @return
     */
    @GetMapping("/default")
    @ApiOperation("查询当前用户默认地址")
    public Result<AddressBook> getDefault() {
        log.info("查询当前用户默认地址");
        List<AddressBook> addressBooks = addressBookService.getDefault(AddressBook.builder().userId(BaseContext.getCurrentId()).isDefault(1).build());
        if (addressBooks != null && addressBooks.size() == 1) {
            return Result.success(addressBooks.get(0));
        }
        return Result.error("没有找到默认地址");
    }

    /**
     * 根据id查询地址簿详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询地址簿详情")
    public Result<AddressBook> getById(@PathVariable Long id) {
        log.info("根据id查询地址簿详情");
        return Result.success(addressBookService.getById(id));
    }

    /**
     * 修改地址簿
     * @param addressBook
     * @return
     */
    @PutMapping
    @ApiOperation("修改地址簿")
    public Result update(@RequestBody AddressBook addressBook) {
        log.info("修改地址簿");
        addressBookService.updateById(addressBook);
        return Result.success();
    }

    /**
     * 删除地址簿
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除地址簿")
    public Result delete(@RequestParam Long id) {
        log.info("删除地址簿");
        addressBookService.delete(id);
        return Result.success();
    }

    /**
     * 设置默认地址
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        log.info("设置默认地址");
        addressBookService.setDefault(addressBook);
        return Result.success();
    }
}
