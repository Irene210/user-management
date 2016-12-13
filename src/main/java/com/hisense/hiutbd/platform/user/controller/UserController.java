package com.hisense.hiutbd.platform.user.controller;

import com.hisense.hiutbd.platform.base.domain.Department;
import com.hisense.hiutbd.platform.base.domain.User;
import com.hisense.hiutbd.platform.config.ReplyVO;
import com.hisense.hiutbd.platform.user.dto.DepartmentDTO;
import com.hisense.hiutbd.platform.user.qo.DepartmentQO;
import com.hisense.hiutbd.platform.user.service.UserService;
import com.hisense.hiutbd.platform.user.vo.DepartmentVO;
import javaslang.collection.List;
import javaslang.control.Either;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/user/*")
public class UserController {

	@Autowired
    private UserService userService;

	@RequestMapping(value = "getList")
    @ResponseBody
    public List<User> getList(DepartmentQO qo) {
        return userService.findAll();
    }

    @RequestMapping(value = "getUserInfo")
     @ResponseBody
     public User getUserInfo(String id) {
        return userService.find(id).getOrElse(new User());
    }


}
