package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //作业名
    private String homeworkName;
    //科目id
    private Integer subjectId;
    //班级id
    private Integer classRoomId;
    //用户id
    private Integer userId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;
    @Transient
    @TableField(exist = false)
    private List<HomeWorkDetail> homeworkDetails;

}
