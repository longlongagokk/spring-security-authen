package com.vitily.member.api.controller.auth;

import com.vitily.common.module.Result;
import com.vitily.member.api.util.HttpUtil;
import com.vitily.member.module.req.TqMemLogin;
import com.vitily.member.module.resp.UserInfo;
import com.vitily.member.server.service.MemBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("auth")
@Controller
public class UserAuthController {
    @Autowired
    MemBaseService memBaseService;

    @RequestMapping("getToken")
    @ResponseBody
    public Result loginForToken(TqMemLogin req, HttpServletRequest request)throws Exception{
        return Result.success(memBaseService.getTokenByLogin(req));
    }
}
