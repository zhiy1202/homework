package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.ZZYResult;
import com.zzy.entity.User;
import com.zzy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {
    @Autowired
    private IUserService userService;

    //通过用户名搜索
    @GetMapping("/queryUserByUsername/{username}")
    public ZZYResult getUserByUsername(@PathVariable("username") String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return ZZYResult.getInstance(200,"通过用户名查询用户",userService.getOne(wrapper));
    }

    //获取所有老师
    @GetMapping("/allTeacher")
    public ZZYResult getAllTeacher() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_type", 2);
        List<User> list = userService.list(wrapper);
        return ZZYResult.getInstance(200, "所有老师信息", list);
    }

    //获取所有学生
    @GetMapping("/allStudent")
    public ZZYResult getAllStudent() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_type", 1);
        List<User> list = userService.list(wrapper);
        return ZZYResult.getInstance(200, "所有学生信息", list);
    }

    //删除用户
    @DeleteMapping("/del/{id}")
    public ZZYResult delUser(@PathVariable("id") Integer id) {
        return ZZYResult.getInstance(200, "删除用户", userService.removeById(id));
    }

    //管理员登录
    @PostMapping("/login")
    public ZZYResult teacherLogin(User user, HttpServletRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("user_type", 3);
        wrapper.eq("password", user.getPassword());
        User user1 = userService.getOne(wrapper);
        if (user1 != null) {
            HttpSession session = request.getSession();
            session.setAttribute("adminInfo", user1);
            session.setAttribute("user", user1);
            return ZZYResult.getInstance(200, "登录成功", true);
        } else {
            return ZZYResult.getInstance(200, "登录失败", false);
        }
    }
    @GetMapping("/exit")
    public ZZYResult studentExit(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        session.removeAttribute("adminInfo");
        return ZZYResult.getInstance(200,"管理员退出",true);
    }

}
