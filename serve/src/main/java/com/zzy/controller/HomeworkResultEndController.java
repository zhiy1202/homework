package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzy.common.CommonEntity;
import com.zzy.common.ZZYResult;
import com.zzy.entity.*;
import com.zzy.mapper.HomeworkResultEndMapper;
import com.zzy.service.IHomeworkResultDetailService;
import com.zzy.service.IHomeworkResultEndService;
import com.zzy.service.IHomeworkService;
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
 * @since 2023-03-12
 */
@RestController
@RequestMapping("/resultEnd")
public class HomeworkResultEndController {

    @Autowired
    private IHomeworkResultEndService homeworkResultEndService;
    @Autowired
    private IHomeworkResultDetailService homeworkResultDetailService;
    @Autowired
    private HomeworkResultEndMapper homeworkResultEndMapper;
    @Autowired
    private IHomeworkService homeworkService;
    //提交作业 生成作业记录
    @Transactional
    @PostMapping("/add")
    public ZZYResult add(@RequestBody HomeworkResultEnd homeworkResultEnd , HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("studentInfo");
        Integer id = user.getId();
        QueryWrapper<HomeworkResultEnd> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        wrapper.eq("homework_id",homeworkResultEnd.getHomeworkId());
        HomeworkResultEnd one = homeworkResultEndService.getOne(wrapper);
        if (one != null){
            homeworkResultEndService.removeById(one.getId());
            UpdateWrapper<HomeworkResultDetail> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("homework_result_id",one.getId());
            homeworkResultDetailService.remove(updateWrapper);
        }
        homeworkResultEnd.setUserId(id);
        homeworkResultEnd.setTeacherIsLook(false);
        boolean save = homeworkResultEndService.save(homeworkResultEnd);
        if (save){
            for (HomeworkResultDetail homeworkResultDetail : homeworkResultEnd.getHomeworkResultDetails()) {
                homeworkResultDetail.setHomeworkResultId(homeworkResultEnd.getId());
                save = homeworkResultDetailService.save(homeworkResultDetail);
            }
        }
        return ZZYResult.getInstance(200,"保存作业记录",save);
    }

    //批改作业
    @PostMapping("/addResult")
    public ZZYResult addResult(@RequestBody List<HomeworkResultDetail> homeworkResultDetails){
        Integer homeworkResultId = homeworkResultDetails.get(0).getHomeworkResultId();
        HomeworkResultEnd byId = homeworkResultEndService.getById(homeworkResultId);
        byId.setTeacherIsLook(true);
        int allScore = 0;
        int myAllScore = 0;
        for (HomeworkResultDetail homeworkResultDetail : homeworkResultDetails) {
            allScore += homeworkResultDetail.getScore();
            myAllScore += homeworkResultDetail.getMyScore();
            homeworkResultDetailService.updateById(homeworkResultDetail);
        }
        byId.setScore(myAllScore);
        if (myAllScore > allScore /2){
            byId.setIsPass(true);
        }else{
            byId.setIsPass(false);
        }
        boolean b = homeworkResultEndService.updateById(byId);
        return ZZYResult.getInstance(200,"批改作业",b);
    }

    //获取用户已做题目
    @GetMapping("/allResultDetail/{id}")
    public ZZYResult allResultDetail(@PathVariable("id") Integer id , HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("studentInfo");
        Integer userId = user.getId();
        QueryWrapper<HomeworkResultEnd> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("homework_id",id);
        wrapper.eq("teacher_is_look",true);
        HomeworkResultEnd one = homeworkResultEndService.getOne(wrapper);

        QueryWrapper<HomeworkResultDetail> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("homework_result_id",one.getId());
        List<HomeworkResultDetail> list = homeworkResultDetailService.list(wrapper1);
        return ZZYResult.getInstance(200,"所有题目",list);
    }

//      及格人数
    @GetMapping("/isPass")
    public ZZYResult isPass(){
        List<CommonEntity> entities = new ArrayList<>();
        List<DataInfo> dataInfo = homeworkResultEndMapper.getDataInfo();
        for (DataInfo info : dataInfo) {
            Homework byId = homeworkService.getById(info.getHomeworkId());
            CommonEntity entity = new CommonEntity();
            entity.setP1(byId);
            entity.setP2(info);
            entities.add(entity);
        }
        return ZZYResult.getInstance(200,"及格人数",entities);
    }

}
