package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.ZZYResult;
import com.zzy.entity.ClassRoom;
import com.zzy.entity.Subject;
import com.zzy.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/admin/room")
public class ClassRoomController {
    @Autowired
    private IClassRoomService classRoomService;

    private ClassRoom classRoomIsExists(String className,Integer grade){
        QueryWrapper<ClassRoom> wrapper = new QueryWrapper<>();
        wrapper.eq("class_name",className);
        wrapper.eq("grade",grade);
        return classRoomService.getOne(wrapper);
    }
    //新增班级
    @PostMapping("/add")
    public ZZYResult add(ClassRoom classRoom){
        ClassRoom room = classRoomIsExists(classRoom.getClassName(),classRoom.getGrade());
        if (room == null){
            boolean save = classRoomService.save(classRoom);
            return ZZYResult.getInstance(200,"班级新增",save);
        }else {
            return ZZYResult.getInstance(200,"班级存在",false);
        }
    }

    //删除班级
    @DeleteMapping("/del/{id}")
    public ZZYResult del(@PathVariable("id") Integer id){
        return ZZYResult.getInstance(200,"删除科目",classRoomService.removeById(id));
    }
    //查询所有班级
    @GetMapping("/all")
    public ZZYResult all(){
        return ZZYResult.getInstance(200,"所有班级",classRoomService.list());
    }
    //按年级查询所有
    @GetMapping("/grade/{id}")
    public ZZYResult all(@PathVariable("id") Integer id){
        QueryWrapper<ClassRoom> wrapper = new QueryWrapper<>();
        wrapper.eq("grade",id);
        return ZZYResult.getInstance(200,"所有科目",classRoomService.list(wrapper));
    }
}
