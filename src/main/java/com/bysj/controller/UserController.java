package com.bysj.controller;

import com.bysj.model.User;
import com.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by cz1996 on 2018/4/15.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String goTo() {
//        return "test";
//    }

    //    登录查询
    @GetMapping("/login")
    public List<User> getUser(HttpServletRequest request, String userName, String userPassword) {
        List<User> test = userService.getUserByNameAndPsd(userName, userPassword);
        if (test.size()>0) {
            HttpSession httpSession = request.getSession();
            int currId = test.get(0).getUserId();
            String currName = test.get(0).getUserName();
            httpSession.setAttribute("name", currName);
            httpSession.setAttribute("id", currId);
            return test;
        } else {
            return null;
        }
    }

    //    注册查询
    @GetMapping("/register")
    public String registerUser(String userName, String userPassword) {
        List<User> user = userService.getUserByNameAndPsd(userName, userPassword);
        if (user.size() > 0) {
            return "3";
        } else {
            boolean insert = userService.setUserByNameAndPsd(userName, userPassword);
            if (insert) {
                return "1";
            } else {
                return "0";
            }
        }
    }

    //    Session查询
    @GetMapping("/session")
    public String getUserSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null){
            return "0";
        }else{
            String currName = (String) httpSession.getAttribute("name");
            return currName;
        }
    }

    //    登出查询
    @GetMapping("/loginOut")
    public String loginOutSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if(httpSession != null){
            httpSession.invalidate();
            return "1";
        }else{
            return "0";
        }
    }

    //    修改密码
    @GetMapping("/changePsd")
    public String changePaswd(HttpServletRequest request, String userPasswordNew){
        HttpSession httpSession = request.getSession();
        int currId = (int) httpSession.getAttribute("id");
        boolean update = userService.changeUserPassword(currId,userPasswordNew);
        if (update) {
            return "1";
        } else {
            return "0";
        }
    }
}