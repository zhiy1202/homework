package com.zzy.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherUpdate {
    private String homeworkName;
    private Integer userId;
    private Integer homeworkId;
}
