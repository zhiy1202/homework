package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.ZZYResult;
import com.zzy.entity.Subject;
import com.zzy.service.ISubjectService;
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
@RequestMapping("/admin/subject")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    private Subject subjectIsExists(String subjectName){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("subject_name",subjectName);
        return subjectService.getOne(wrapper);
    }
    //新增科目
    @PostMapping("/add")
    public ZZYResult add(Subject subject){
        Subject subject1 = subjectIsExists(subject.getSubjectName());
        if (subject1 == null){
            boolean save = subjectService.save(subject);
            return ZZYResult.getInstance(200,"科目新增",save);
        }else {
            return ZZYResult.getInstance(200,"科目存在",false);
        }
    }

    //删除科目
    @DeleteMapping("/del/{id}")
    public ZZYResult del(@PathVariable("id") Integer id){
        return ZZYResult.getInstance(200,"删除科目",subjectService.removeById(id));
    }
    //查询所有科目
    @GetMapping("/all")
    public ZZYResult all(){
        return ZZYResult.getInstance(200,"所有科目",subjectService.list());
    }
}
