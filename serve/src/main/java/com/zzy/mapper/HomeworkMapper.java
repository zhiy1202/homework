package com.zzy.mapper;

import com.zzy.entity.Homework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.common.TeacherUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-03-03
 */
@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {

    @Select("select a.homework_name,b.homework_id,b.user_id from homework a join teacher_look_work b on a.id = b.homework_id where a.user_id=#{id} GROUP BY a.homework_name,b.homework_id,b.user_id")
    List<TeacherUpdate> queryWork(Integer id);

    @Select("select * from homework where id not in (select homework_id from homework_result_end where user_id = #{userId}) and subject_id = #{subjectId}")
    List<Homework> studentNotTodo(@Param("userId") Integer userId,@Param("subjectId") Integer subjectId);

}
