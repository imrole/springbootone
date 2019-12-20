package com.practiceone.demo.controller;

import com.mysql.cj.Session;
import com.practiceone.demo.dao.UserDao;
import com.practiceone.demo.entity.UserEntity;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/check")
    public String checkUser(@RequestParam("uname")String userName, @RequestParam("upwd")String passWord, Model model) throws Exception{
        if(UserDao.selectUser(userName,passWord)) {
            AddNeirong(model);
            return "home";
        }
        else
        {
            return "../static/index";
        }
    }

    public void AddNeirong(Model model) throws Exception{
        List<UserEntity> userlist = new ArrayList<UserEntity>();
        userlist = UserDao.selectAllUser();
        model.addAttribute("allUser",userlist);
    }

    @RequestMapping("/insert")
    public String insertUser(@RequestParam("uname")String userName,@RequestParam("upwd")String passWord, Model model) throws Exception {
        UserEntity user=new UserEntity();
        user.setUsername(userName);
        user.setPassword(passWord);
        if(UserDao.insertUser(user)) {
            AddNeirong(model);
            return "home";
        }
        else {
            return "../static/index";
        }
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("username")String userName,Model model)throws Exception{
        if(userName!=null) {
            if(UserDao.deleteUser(userName)) {
                AddNeirong(model);
                return "home";
            }
            else {
                return "../static/index";
            }
        }
        else {
            return null;
        }
    }

    @RequestMapping("/update")
    public String updateUser(@RequestParam("username")String userName,@RequestParam("password")String password,Model model)throws Exception{
        model.addAttribute("oldusername",userName);
        model.addAttribute("oldpassword",password);
        return "update";
    }

    @RequestMapping("/select")
    public String selectUser(@RequestParam("username")String userName,Model model)throws Exception{
        if(userName!=null) {
            List<UserEntity> userlist = new ArrayList<UserEntity>();
            userlist = UserDao.selectUser(userName);
            model.addAttribute("allUser",userlist);
            return "home";
        }
        else {
            return null;
        }
    }

    @RequestMapping("/tz")
    public String tzinsert() {
        return "insert";
    }
}
