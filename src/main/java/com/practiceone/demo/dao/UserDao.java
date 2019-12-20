package com.practiceone.demo.dao;

import com.practiceone.demo.entity.UserEntity;
import com.practiceone.demo.utils.MysqlDB;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static String sql ;

    public static boolean updateUser(String username, UserEntity user) throws Exception {
        sql=" update users set username = '"+user.getUsername()+"' , password = '"+user.getPassword()+"' where username = '"+username+"'";
        return MysqlDB.executeUpdate2(sql);
    }

    public static boolean deleteUser(String username) throws Exception  {
        sql="delete from users where username = '"+username+"'";
        return MysqlDB.executeUpdate2(sql);
    }

    public static boolean selectUser(String username,String password) throws Exception {
        sql="select count(*) from users where username ='"+username+"' and password ='"+password+"'";
        boolean result = false;
        try {
            if(MysqlDB.executeQueryCount(sql) > 0) {
                result = true;
            }
            else {
                result = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean insertUser(UserEntity user) throws Exception {
        sql=" insert into users values ('"+user.getUsername()+"','"+user.getPassword()+"')";
        return MysqlDB.executeUpdate2(sql);
    }

    public static List<UserEntity> selectUser(String username)throws Exception {
        List<UserEntity> res=new ArrayList<UserEntity>();
        sql="select * from users where username = '"+username+"'";
        try {
            res = MysqlDB.executeQuery2(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<UserEntity> selectAllUser()throws Exception {
        String sql="select * from users";
        List<UserEntity> res=new ArrayList<UserEntity>();
        try {
            res = MysqlDB.executeQuery2(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
