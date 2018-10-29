package com.jnshu.myutils;

import com.jnshu.entity.User;
import com.jnshu.mapper.UserMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


@Component
public class Login {
   Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private UserMapper userMapper;
    public String login(User user1, DESUtil desUtil, HttpServletResponse response) throws UnsupportedEncodingException {
        String view = " ";
        logger.info(user1);

        User user2 = userMapper.findByName(user1.getUsername());
        logger.info(user1.getUsername()+user1.getPassword());
        logger.info("user2======\n"+user2+"user2.getUsername()==========="+user2.getUsername());
        if(user2.getUsername() ==null){
            logger.info("用户不存在，请重新输入");
            view = "login";
        }else if(user2.getPassword().equals(MD5Util.md5(MD5Util.md5(user1.getUsername()+user1.getPassword())))){
            //对cookie进行加密
            long time = System.currentTimeMillis();
            user2.setUpdateAt(time);
            String str1 = desUtil.encryptFromLong(time);
            String str2 = desUtil.encryptFromLong(user2.getId());
            //在相加时，用户名前后有“|”。这个是为了后面验证cookie时可以直接解密提出用户名。
            String token = desUtil.encrypt(str1+"|"+user2.getUsername()+"|"+str2);
            Cookie nameCookie = new Cookie("name",user2.getUsername());
            nameCookie.setMaxAge(30*60);
            nameCookie.setPath("/");
            Cookie tokenCookie = new Cookie("token",token);
            tokenCookie.setMaxAge(30*60);
            tokenCookie.setPath("/");
            response.addCookie(nameCookie);
            response.addCookie(tokenCookie);
            view = "redirect:/u/task-93";
        }else{
            logger.info((MD5Util.md5(user1.getUsername()+user1.getPassword())));
            logger.info("登陆失败");
            view = "login";
        }
        return view;
    }
}
