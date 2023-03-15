package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.CommonEntity;
import com.zzy.common.ZZYResult;
import com.zzy.entity.*;
import com.zzy.mapper.HomeworkMapper;
import com.zzy.service.IClassRoomService;
import com.zzy.service.IHomeWorkDetailService;
import com.zzy.service.IHomeworkService;
import com.zzy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/front/homework")
public class HomeworkController {

    @Autowired
    private IHomeworkService homeworkService;

    @Autowired
    private IHomeWorkDetailService homeWorkDetailService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IClassRoomService classRoomService;

    @Autowired
    private HomeworkMapper homeworkMapper;
    //新建作业
    @PostMapping("/add")
    @Transactional
    public ZZYResult add(@RequestBody Homework homework , HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("teacherInfo");
        homework.setUserId(user.getId());
        homeworkService.save(homework);
        Integer id = homework.getId();
        for (HomeWorkDetail homeWorkDetail : homework.getHomeworkDetails()) {
            homeWorkDetail.setHomeWorkId(id);
            homeWorkDetailService.save(homeWorkDetail);
        }
        return ZZYResult.getInstance(200,"新建作业",true);
    }
    //提交作业
    @PostMapping("send")
    public ZZYResult send(){
        return ZZYResult.getInstance(200,"提交作业",true);
    }



    //查看老师作业
    @GetMapping("/byTeacher")
    public ZZYResult byTeacher(HttpServletRequest request){
        List<CommonEntity> entities = new ArrayList<>();
        User teacherInfo = (User) request.getSession(false).getAttribute("teacherInfo");
        Integer id = teacherInfo.getId();
        List<Homework> list = homeworkService.list(new QueryWrapper<Homework>().eq("user_id", id));
        for (Homework homework : list) {
            Subject subject = subjectService.getById(homework.getSubjectId());
            ClassRoom classRoom = classRoomService.getById(homework.getClassRoomId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(homework);
            entity.setP2(subject);
            entity.setP3(classRoom);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"老师的所有作业",entities);
    }

    //查看学生所在班级科目作业
    @GetMapping("/byStudent/{subjectId}")
    public ZZYResult byStudentRoomSubject(HttpServletRequest request, @PathVariable("subjectId") Integer subjectId){
        User studentInfo = (User) request.getSession().getAttribute("studentInfo");
        Integer classRoom = studentInfo.getClassRoom();
        QueryWrapper<Homework> wrapper = new QueryWrapper<>();
        wrapper.eq("subject_id",subjectId);
        wrapper.eq("class_room_id",classRoom);
        List<Homework> list = homeworkService.list(wrapper);
        return ZZYResult.getInstance(200,"所有作业",list);
    }

    //删除作业
    @Transactional
    @DeleteMapping("/del/{id}")
    public ZZYResult del(@PathVariable("id") Integer id){
        boolean isDeleted = false;
        isDeleted = homeworkService.removeById(id);
        QueryWrapper<HomeWorkDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("home_work_id",id);
        isDeleted = homeWorkDetailService.remove(wrapper);
        return ZZYResult.getInstance(200,"删除作业",isDeleted);
    }

    //获取作业的所有题目
    @GetMapping("/allDetail/{id}")
    public ZZYResult allDetail(@PathVariable("id") Integer id){
        List<CommonEntity> entities = new ArrayList<>();
        Homework homework = homeworkService.getById(id);
        QueryWrapper<HomeWorkDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("home_work_id",id);
        List<HomeWorkDetail> list = homeWorkDetailService.list(wrapper);
        CommonEntity entity = new CommonEntity();
        entity.setP1(homework);
        entity.setP2(list);
        entities.add(entity);
        return ZZYResult.getInstance(200,"所有题目",entities);
    }


    //=================================================== two

    //查看老师布置的未写作业
    @GetMapping("/queryNotTodo/{subjectId}")
    public ZZYResult queryNotTodo(HttpServletRequest request,@PathVariable("subjectId") Integer subjectId){
        User userInfo = (User) request.getSession().getAttribute("studentInfo");
        Integer id = userInfo.getId();
        List<Homework> list = homeworkMapper.studentNotTodo(id,subjectId);
        return ZZYResult.getInstance(200,"获取用户未写题目",list);
    }


}
