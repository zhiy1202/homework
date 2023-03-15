package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.ZZYResult;
import com.zzy.entity.User;
import com.zzy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器 1 学生 2 老师 3 admin
 * </p>
 *
 * @author zzy
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/front/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //查询用户是否存在
    private User userIsExist(String username,Integer userType,String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("user_type",userType);
        if (!"".equals(password) && password != null){
            wrapper.eq("password",password);
        }
        return userService.getOne(wrapper);
    }
    //学生注册
    @PostMapping("/student/register")
    public ZZYResult studentRegister(User user){
        User user1 = userIsExist(user.getUsername(),1,null);
        if (user1 == null){
            user.setUserType(1);
            boolean save = userService.save(user);
            return ZZYResult.getInstance(200,"用户注册成功",save);
        }else{
            return ZZYResult.getInstance(200,"用户存在",false);
        }
    }
    //老师注册
    @PostMapping("/teacher/register")
    public ZZYResult teacherRegister(User user){
        User user1 = userIsExist(user.getUsername(),2,null);
        if (user1 == null){
            user.setUserType(2);
            boolean save = userService.save(user);
            return ZZYResult.getInstance(200,"老师注册成功",save);
        }else{
            return ZZYResult.getInstance(200,"用户存在",false);
        }
    }
    //学生登录
    @PostMapping("/student/login")
    public ZZYResult studentLogin(User user, HttpServletRequest request){
        User user1 = userIsExist(user.getUsername(), 1, user.getPassword());
        if (user1 != null){
            HttpSession session = request.getSession();
            session.setAttribute("studentInfo",user1);
            session.setAttribute("user",user1);
            return ZZYResult.getInstance(200,"登录成功",true);
        }else {
            return ZZYResult.getInstance(200,"登录失败",false);
        }
    }

    //老师登录
    @PostMapping("/teacher/login")
    public ZZYResult teacherLogin(User user, HttpServletRequest request){
        User user1 = userIsExist(user.getUsername(), 2, user.getPassword());
        if (user1 != null){
            HttpSession session = request.getSession();
            session.setAttribute("teacherInfo",user1);
            session.setAttribute("user",user1);
            return ZZYResult.getInstance(200,"登录成功",true);
        }else {
            return ZZYResult.getInstance(200,"登录失败",false);
        }
    }
    //学生退出
    @GetMapping("/student/exit")
    public ZZYResult studentExit(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        session.removeAttribute("studentInfo");
        return ZZYResult.getInstance(200,"学生退出登录",true);
    }

    //老师退出
    @GetMapping("/teacher/exit")
    public ZZYResult teacherExit(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        session.removeAttribute("teacherInfo");
        return ZZYResult.getInstance(200,"老师退出登录",true);
    }
    //获取信息
    @GetMapping("/userInfo")
    public ZZYResult getUserInfo(HttpServletRequest request){
        User user = (User) request.getSession(false).getAttribute("user");
        return ZZYResult.getInstance(200,"获取用户信息",user);
    }

    //用户修改信息
    @PostMapping("/update")
    public ZZYResult userUpdateInfo(User user , HttpServletRequest request){
        User user1 = (User) request.getSession(false).getAttribute("user");
        user.setId(user1.getId());
        boolean b = userService.updateById(user);
        return ZZYResult.getInstance(200,"修改用户信息",b);
    }
}
