package com.vitily.member.api.controller;

import com.vitily.common.module.Result;
import com.vitily.member.module.search.TsMemGroup;
import com.vitily.member.server.service.MemBaseService;
import com.vitily.member.server.service.MemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mem")
@RestController
public class MemberTestController {
    @Autowired
    MemGroupService memGroupService;
    @Autowired
    MemBaseService memBaseService;
    @GetMapping("get/{id}")
    public Result getApiToken(@PathVariable Integer id){
        return Result.success(memBaseService.selectByPrimaryKey(id));
    }
    @GetMapping("list")
    public Result list(TsMemGroup query){
        return Result.success(memGroupService.getPagingList(query));
    }
    @PostMapping("update")
    public Result update(@RequestBody TsMemGroup query){
        return Result.success(memGroupService.update(query));
    }
}
