package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.CommonEntity;
import com.zzy.common.ZZYResult;
import com.zzy.entity.*;
import com.zzy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/result")
public class HomeWorkResultController {

    @Autowired
    private IHomeWorkResultService homeWorkResultService;

    @Autowired
    private IHomeworkService homeworkService;

    @Autowired
    private IHomeWorkDetailService homeWorkDetailService;

    @Autowired
    private IHomeworkResultEndService homeworkResultEndService;

    @Autowired
    private IHomeworkResultDetailService homeworkResultDetailService;

    //查看错题
    @GetMapping("/error")
    public ZZYResult studentError(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        User studentInfo = (User) request.getSession().getAttribute("studentInfo");
        QueryWrapper<HomeWorkResult> wrapper = new QueryWrapper<>();
        wrapper.eq("is_true",false);
        wrapper.eq("user_id",studentInfo.getId());
        List<HomeWorkResult> list = homeWorkResultService.list(wrapper);
        for (HomeWorkResult homeWorkResult : list) {
            Homework homework = homeworkService.getById(homeWorkResult.getHomeworkId());
            HomeWorkDetail homeWorkDetail = homeWorkDetailService.getById(homeWorkResult.getHomeworkDetailId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(homework);
            entity.setP2(homeWorkDetail);
            entity.setP3(homeWorkResult);
            entity.setP4(studentInfo);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"所有错题",entities);
    }
    //删除
    @DeleteMapping("/del/{id}")
    public ZZYResult del(@PathVariable("id") Integer id){
        boolean b = homeWorkResultService.removeById(id);
        return ZZYResult.getInstance(200,"删除错题",b);
    }



    //查看老师已批作业
    @GetMapping("/teacherLook")
    public ZZYResult teacherLook(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("studentInfo");
        Integer userId = user.getId();
        QueryWrapper<HomeworkResultEnd> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("teacher_is_look",true);
        List<HomeworkResultEnd> list = homeworkResultEndService.list(wrapper);
        for (HomeworkResultEnd homeworkResultEnd : list) {
            Homework byId = homeworkService.getById(homeworkResultEnd.getHomeworkId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(byId);
            entity.setP2(homeworkResultEnd);
            entity.setP3(user);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"老师已批阅作业",entities);
    }
    //查看未批作业
    @GetMapping("/teacherNotLook")
    public ZZYResult teacherNotLook(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("studentInfo");
        Integer userId = user.getId();
        QueryWrapper<HomeworkResultEnd> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("teacher_is_look",false);
        List<HomeworkResultEnd> list = homeworkResultEndService.list(wrapper);
        for (HomeworkResultEnd homeworkResultEnd : list) {
            Homework byId = homeworkService.getById(homeworkResultEnd.getHomeworkId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(byId);
            entity.setP2(homeworkResultEnd);
            entity.setP3(user);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"老师已批阅作业",entities);
    }

    //userId  homeworkId  查询所有作业
    @GetMapping("/UpdataWorkDetail/{userId}/{homeworkId}")
    public ZZYResult updataWorkDetail(@PathVariable("userId") Integer userId,@PathVariable("homeworkId") Integer homeworkId){
        QueryWrapper<HomeworkResultEnd> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("homework_id",homeworkId);
        HomeworkResultEnd homeworkResultEnd = homeworkResultEndService.getOne(wrapper);
        QueryWrapper<HomeworkResultDetail> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("homework_result_id",homeworkResultEnd.getId());
        List<HomeworkResultDetail> list1 = homeworkResultDetailService.list(wrapper1);
        return ZZYResult.getInstance(200,"修改",list1);
    }

}
