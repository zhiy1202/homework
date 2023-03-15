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
@Table(name = "homework_result_detail")
public class HomeworkResultDetail {
    @Id
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //作业id
    private Integer homeworkResultId;
    //题目类型 1 选择 2 填空
    private Integer type;
    //题目名
    private String title;
    //答案
    private String answer;
    //选项
    private String p1;
    //选项
    private String p2;
    //选项
    private String p3;
    //选项
    private String p4;
    //题目分数
    private Integer score;
    //我的得分
    private Integer myScore;
    //老师评语
    private String comment;
    //我的答案
    private String myAnswer;
    //是否作对
    private Boolean isTrue;

}
