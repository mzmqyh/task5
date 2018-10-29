package com.jnshu.myutils;


import com.jnshu.entity.User1;

import java.util.ArrayList;

public class SessionUtil {
    //工具类，通过session在链表中找到相应的用户对象

    public static User1 getUserBySessionID(ArrayList<User1> userlist, String sessionID) {
        for (int i = 0; i < userlist.size(); i++) {
            User1 user=userlist.get(i);
            if (user.getSessionID().equals(sessionID)){
                return user;
            }
        }
        return null;
    }
}
