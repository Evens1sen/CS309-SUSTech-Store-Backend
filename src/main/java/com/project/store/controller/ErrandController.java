package com.project.store.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.Errand;
import com.project.store.entity.User;
import com.project.store.enums.ErrandStatus;
import com.project.store.service.ErrandService;
import com.project.store.service.UserService;
import com.project.store.util.ImageUtil;
import com.project.store.vo.ErrandVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ApiOperation(value = "获取所有代接取跑腿VO")
    @GetMapping("/listVO")
    public List<ErrandVO> listVO() {
        return errandService.findAllErrandVO();
    }

    @ApiOperation(value = "获取所有分类列表")
    @GetMapping("/list/{type}")
    public List<Errand> listByType(@PathVariable Integer type) {
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("status", 0);
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "获取用户已发布的跑腿")
    @GetMapping("/listReleased")
    public List<Errand> listReleased() {
        User owner = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id", owner.getUid());
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "获取用户已发布的跑腿VO")
    @GetMapping("/listReleasedVO")
    public List<ErrandVO> listReleasedVO() {
        User owner = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id", owner.getUid());

        List<Errand> errandList = errandService.list(wrapper);
        List<ErrandVO> errandVOList = new ArrayList<>();
        errandList.forEach(errand -> {
            ErrandVO errandVO = new ErrandVO();
            BeanUtils.copyProperties(errand, errandVO);
            errandVO.setOwnerNickname(owner.getNickName());
            errandVO.setOwnerIcon(owner.getIcon());
            errandVOList.add(errandVO);
        });
        return errandVOList;
    }

    @ApiOperation(value = "获取用户已接取的跑腿")
    @GetMapping("/listTaken")
    public List<Errand> listTaken() {
        User buyer = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.eq("buyer_id", buyer.getUid());
        return errandService.list(wrapper);
    }

    @ApiOperation(value = "获取用户已接取的跑腿VO")
    @GetMapping("/listTakenVO")
    public List<ErrandVO> listTakenVO() {
        User buyer = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.eq("buyer_id", buyer.getUid());

        List<Errand> errandList = errandService.list(wrapper);
        List<ErrandVO> errandVOList = new ArrayList<>();
        errandList.forEach(errand -> {
            ErrandVO errandVO = new ErrandVO();
            User owner = userService.getById(errand.getOwnerId());
            BeanUtils.copyProperties(errand, errandVO);
            errandVO.setOwnerNickname(owner.getNickName());
            errandVO.setOwnerIcon(owner.getIcon());
            errandVOList.add(errandVO);
        });
        return errandVOList;
    }

    @ApiOperation(value = "根据id获取跑腿单")
    @GetMapping("/findById/{id}")
    public Errand findById(@PathVariable Integer id) {
        return errandService.getById(id);
    }

    @ApiOperation(value = "根据id获取跑腿VO")
    @GetMapping("/findErrandVOById/{id}")
    public ErrandVO findErrandVOById(@PathVariable Integer id) {
        Errand errand = errandService.getById(id);
        ErrandVO errandVO = new ErrandVO();
        User owner = userService.getById(errand.getOwnerId());
        BeanUtils.copyProperties(errand, errandVO);
        errandVO.setOwnerNickname(owner.getNickName());
        errandVO.setOwnerIcon(owner.getIcon());

        return errandVO;
    }

    @ApiOperation(value = "搜索跑腿", notes = "默认按时间排序")
    @GetMapping("/search/{key}")
    public List<Errand> search(@PathVariable String key) {
        QueryWrapper<Errand> wrapper = new QueryWrapper<>();
        wrapper.like("name", key);
        wrapper.eq("status", 0);
        wrapper.orderByDesc("update_time");

        return errandService.list(wrapper);
    }

    @ApiOperation(value = "添加跑腿")
    @PostMapping("/add")
    public boolean add(@RequestBody Errand errand) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (user.getBalance() < errand.getPrice()) {
            return false;
        }
        user.setBalance(user.getBalance() - errand.getPrice());

        errand.setOwnerId(user.getUid());
        errand.setStatus(ErrandStatus.OPENED);
        errand.setBuyerId(null);


        if (!Objects.equals(errand.getImage(), "")) {
            String objectName = ImageUtil.generateObjectName(errand.getId().toString(), 8);
            String url = ImageUtil.postImage(errand.getImage(), objectName);
            errand.setImage(url);
        }

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
        User buyer = userService.getById(StpUtil.getLoginIdAsInt());
        buyer.setBalance(buyer.getBalance() + errand.getPrice());

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

