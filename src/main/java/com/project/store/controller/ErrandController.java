package com.project.store.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.Errand;
import com.project.store.entity.User;
import com.project.store.enums.ErrandStatus;
import com.project.store.service.ErrandService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-12-16
 */
@Api(tags = "ErrandController")
@RestController
@RequestMapping("/errand")
public class ErrandController {

    @Autowired
    private ErrandService errandService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取所有跑腿")
    @GetMapping("/list")
    public List<Errand> list() {
        return errandService.list();
    }

    @ApiOperation(value = "获取所有分类列表")
    @GetMapping("/list/{type}")
    public List<Errand> listByType(@PathVariable Integer type) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", type);
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "获取用户已发布的跑腿")
    @GetMapping("/listReleased")
    public List<Errand> listReleased() {
        User owner = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("owner_id", owner.getUid());
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "获取用户已接取的跑腿")
    @GetMapping("/listTaken")
    public List<Errand> listTaken() {
        User buyer = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("buyer_id", buyer.getUid());
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "根据id获取跑腿单")
    @GetMapping("/findById/{id}")
    public Errand findById(@PathVariable Integer id) {
        return errandService.getById(id);
    }

    @ApiOperation(value = "搜索跑腿", notes = "默认按时间排序")
    @GetMapping("/search/{key}")
    public List<Errand> search(@PathVariable String key) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name", key);
        wrapper.orderByDesc("update_time");
        List<Errand> errandList = errandService.list(wrapper);

        return errandList;
    }

    @ApiOperation(value = "添加跑腿")
    @PostMapping("/add")
    public boolean add(@RequestBody Errand errand) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        errand.setOwnerId(user.getUid());
        errand.setStatus(ErrandStatus.OPENED);
        return errandService.save(errand);
    }

    @ApiOperation(value = "接取跑腿订单")
    @PutMapping("take/{id}")
    public boolean take(@PathVariable Integer id) {
        User buyer = userService.getById(StpUtil.getLoginIdAsInt());
        Errand errand = errandService.getById(id);
        errand.setBuyerId(buyer.getUid());
        errand.setStatus(ErrandStatus.RUNNING);
        return errandService.saveOrUpdate(errand);
    }

    @ApiOperation(value = "确认跑腿送达")
    @PutMapping("confirm/{id}")
    public boolean confirm(@PathVariable Integer id) {
        Errand errand = errandService.getById(id);
        errand.setStatus(ErrandStatus.CLOSED);
        return errandService.saveOrUpdate(errand);
    }

    @ApiOperation(value = "修改跑腿信息")
    @PutMapping("/update/{id}")
    public boolean update(@RequestBody Errand errand) {
        return errandService.saveOrUpdate(errand);
    }

    @ApiOperation(value = "删除跑腿")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return errandService.removeById(id);
    }

}

