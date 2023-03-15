package com.zzy.mapper;

import com.zzy.entity.DataInfo;
import com.zzy.entity.HomeworkResultEnd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzy
 * @since 2023-03-12
 */
@Mapper
public interface HomeworkResultEndMapper extends BaseMapper<HomeworkResultEnd> {

    //数据统计及格
    @Select("select homework_id,sum(is_pass=0) notPass, sum(is_pass=1) pass from homework_result_end group by homework_id")
    List<DataInfo> getDataInfo();

}
