package com.zzy.controller;

import com.zzy.common.ZZYResult;
import com.zzy.entity.Native;
import com.zzy.service.INativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/native")
public class NativeController {

    @Autowired
    private INativeService nativeService;

    //获取所有公告
    @GetMapping("/all")
    public ZZYResult all(){
        List<Native> list = nativeService.list();
        return ZZYResult.getInstance(200,"所有公告",list);
    }
    //删除公告
    @DeleteMapping("/del/{id}")
    public ZZYResult delById(@PathVariable("id") Integer id){
        boolean b = nativeService.removeById(id);
        return ZZYResult.getInstance(200,"删除公告",b);
    }
    //公告详情
    @GetMapping("/get/{id}")
    public ZZYResult getByIds(@PathVariable("id") Integer id){
        Native byId = nativeService.getById(id);
        return ZZYResult.getInstance(200,"公告详情",byId);
    }
    //修改公告
    @PostMapping("/update")
    public ZZYResult updateById(Native nativeEntity){
        boolean b = nativeService.updateById(nativeEntity);
        return ZZYResult.getInstance(200,"公告更新",b);
    }
    //天机公告
    @PostMapping("/add")
    public ZZYResult add(Native nativeEntity){
        boolean save = nativeService.save(nativeEntity);
        return ZZYResult.getInstance(200,"添加公告",save);
    }

}
