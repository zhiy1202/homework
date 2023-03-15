package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzy.common.CommonEntity;
import com.zzy.common.ZZYResult;
import com.zzy.entity.*;
import com.zzy.entity.common.TeacherUpdate;
import com.zzy.mapper.HomeworkMapper;
import com.zzy.service.IHomeWorkDetailService;
import com.zzy.service.IHomeWorkResultService;
import com.zzy.service.IHomeworkService;
import com.zzy.service.ITeacherLookWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/lookWork")
public class TeacherLookWorkController {
    @Autowired
    private ITeacherLookWorkService teacherLookWorkService;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private IHomeworkService homeworkService;

    @Autowired
    private IHomeWorkDetailService homeWorkDetailService;

    @Autowired
    private IHomeWorkResultService homeWorkResultService;
    //提交答案
    @Transactional
    @PostMapping("/add")
    public ZZYResult add(@RequestBody List<TeacherLookWork> teacherLookWorks, HttpServletRequest request){
        User studentInfo = (User) request.getSession().getAttribute("studentInfo");
        for (TeacherLookWork teacherLookWork : teacherLookWorks) {
            Integer id = studentInfo.getId();
            teacherLookWork.setUserId(id);
            teacherLookWork.setClassRoomId(studentInfo.getClassRoom());
            teacherLookWorkService.save(teacherLookWork);
        }
        return ZZYResult.getInstance(200,"提交作业",true);
    }

    //教师需要查看的作业
    @GetMapping("/allByTeacher")
    public ZZYResult allByTeacher(HttpServletRequest request){
        User teacher = (User) request.getSession().getAttribute("teacherInfo");
        List<TeacherUpdate> teacherUpdates = homeworkMapper.queryWork(teacher.getId());
        return ZZYResult.getInstance(200,"教师待修改",teacherUpdates);
    }
    //userId  homeworkId  查询所有作业
    @GetMapping("/UpdataWorkDetail/{userId}/{homeworkId}")
    public ZZYResult updataWorkDetail(@PathVariable("userId") Integer userId,@PathVariable("homeworkId") Integer homeworkId){
        List<CommonEntity> entities = new ArrayList<>();
        QueryWrapper<TeacherLookWork> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("homework_id",homeworkId);
        List<TeacherLookWork> list = teacherLookWorkService.list(wrapper);
        for (TeacherLookWork teacherLookWork : list) {
            Homework homework = homeworkService.getById(teacherLookWork.getHomeworkId());
            QueryWrapper<HomeWorkDetail> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("home_work_id",teacherLookWork.getHomeworkId());
            HomeWorkDetail homeWorkDetail = homeWorkDetailService.getById(teacherLookWork.getHomeworkDetailId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(homework);
            entity.setP2(homeWorkDetail);
            entity.setP3(teacherLookWork);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"修改",entities);
    }
    //保存修改
    @Transactional
    @PostMapping("/addResult")
    public ZZYResult addResult(@RequestBody List<HomeWorkResult> homeWorkResults){

        for (HomeWorkResult homeWorkResult : homeWorkResults) {
            UpdateWrapper<TeacherLookWork> wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id",homeWorkResult.getUserId()).eq("homework_id",homeWorkResult.getHomeworkId());
            teacherLookWorkService.remove(wrapper);
        }
        homeWorkResultService.saveBatch(homeWorkResults);
        return ZZYResult.getInstance(200,"修改成功",true);
    }

}
