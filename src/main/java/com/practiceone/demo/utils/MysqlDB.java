package com.practiceone.demo.utils;

import com.practiceone.demo.entity.UserEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlDB {
    private static final String url="jdbc:mysql://localhost:3306/mk_lx?serverTimezone=CTT";
    private static final String un="root";
    private static final String pwd="111111";

    public static Connection getConnection()throws Exception {
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (conn == null) {
                conn = DriverManager.getConnection(url, un, pwd);
                return conn;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //用于增删改
    public static boolean executeUpdate(String sql)throws Exception {
        Connection conn =null;
        Statement stmt=null;
        int res=0;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            res = stmt.executeUpdate(sql);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt!=null) {
                    stmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return res > 0;
    }

    public static boolean executeUpdate2(String sql)throws Exception {
        Statement stmt=null;
        Connection conn=null;
        int res=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, un, pwd);
            stmt = conn.createStatement();
            res=stmt.executeUpdate(sql);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt!=null) {
                    stmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return res >0;
    }

    //用于查询
    public static List<UserEntity> executeQuery(String sql) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        List<UserEntity> resultListl=new ArrayList<UserEntity>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                UserEntity ru=new UserEntity();
                ru.setUsername(res.getString("username"));
                ru.setPassword(res.getString("password"));
                resultListl.add(ru);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(res!=null) {
                    res.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return resultListl;
    }

    public static List<UserEntity> executeQuery2(String sql)throws Exception {
        Statement stmt=null;
        Connection conn=null;
        ResultSet res=null;
        List<UserEntity> resultListl=new ArrayList<UserEntity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, un, pwd);
            stmt = conn.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()) {
                UserEntity ru=new UserEntity();
                ru.setUsername(res.getString("username"));
                ru.setPassword(res.getString("password"));
                resultListl.add(ru);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(res!=null) {
                    res.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return resultListl;
    }

    public static int executeQueryCount(String sql){
        Statement stmt=null;
        Connection conn=null;
        ResultSet res=null;
        int count =0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, un, pwd);
            stmt = conn.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()) {
                count=res.getInt(1);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(res!=null) {
                    res.close();
                }
                if(stmt!=null) {
                    stmt.close();
                }
                if(conn!=null) {
                    conn.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
