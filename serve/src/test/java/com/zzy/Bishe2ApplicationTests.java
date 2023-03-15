package com.zzy;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class Bishe2ApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/homework?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true",
                        "root",
                        "333")
                        .globalConfig(builder -> {
                            builder.author("zzy") // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("E:\\code\\java\\java\\homework\\serve\\bishe2\\src\\main\\java"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com") // 设置父包名
                                    .moduleName("zzy") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\code\\java\\java\\homework\\serve\\bishe2\\src\\main\\resources\\mapper"));
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("user","subject","home_work_result","home_work_detail","homework","class_room","teacher_look_work","homework_result_end","homework_result_detail","native") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_")
                                    .enableCapitalMode()
                                    .entityBuilder()
                                    .enableLombok()
                                    .logicDeleteColumnName("is_del")
                                    .logicDeletePropertyName("isDel")
                                    .mapperBuilder()
                                    .superClass(BaseMapper.class)
                                    .enableMapperAnnotation();
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }

}
