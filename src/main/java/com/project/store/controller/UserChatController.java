package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.project.store.dto.UserChatParam;
import com.project.store.entity.User;
import com.project.store.entity.UserChat;
import com.project.store.service.UserChatService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-24
 */
@Api(tags = "UserChatController")
@RestController
@RequestMapping("/userChat")
public class UserChatController {
    @Autowired
    private UserChatService userChatService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取登录用户与卖家的聊天记录(传入卖家uid)")
    @GetMapping("/findAll/{buy_id}/{sell_id}")
    public List<UserChat> findall( @PathVariable Integer buy_id,@PathVariable Integer sell_id){
        return userChatService.findAllUserChatByUserId(buy_id,sell_id);
    }

    @ApiOperation(value = "添加上传用户与卖家的聊天记录")
    @PostMapping("/addChat/{id}")
    public boolean addChat(@RequestBody UserChatParam userChatParam){
        UserChat userChat = new UserChat();
        BeanUtils.copyProperties(userChatParam,userChat);
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        userChat.setBuyId(user.getUid());
        userChat.setSellId(userChatParam.getSellId());
        userChat.setLineText(userChatParam.getLineText());
        userChat.setCreateAt(LocalDateTime.now());
        return userChatService.save(userChat);
    }
}

