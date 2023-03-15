package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author zzy
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "home_work_result")
public class HomeWorkResult {
    @Id
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //作答人
    private Integer userId;
    //作业id
    private Integer homeworkId;
    //题目id
    private Integer homeworkDetailId;
    //回答答案
    private String answer;
    //老师评语
    private String comment;
    //分数
    private Integer score;
    //题目是否做对
    private Boolean isTrue;
}
