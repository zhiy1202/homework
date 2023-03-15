package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
//查找用户未作题目
//select id from homework where id not in (select homeworkId from homework_result where user_id = 4)
//查找用户已做题目
//select * from homework_result where user_id = 4
//查找用户已做未批阅
//select * from homework_result where teacherIsLook = false
//查找用户已批阅
//select * from homework_result where teacherIsLook = true
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "homework_result_end")
public class HomeworkResultEnd {
    @Id
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer homeworkId;
    private Integer userId;
    //总得分
    private Integer score;
    //老师是否查看
    private Boolean teacherIsLook;
    //是否及格
    private Boolean isPass;

    @Transient
    @TableField(exist = false)
    private List<HomeworkResultDetail> homeworkResultDetails;
}
