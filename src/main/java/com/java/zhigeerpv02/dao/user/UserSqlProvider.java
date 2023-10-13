package com.java.zhigeerpv02.dao.user;

import com.java.zhigeerpv02.entity.User;

@SuppressWarnings("unused")
public class UserSqlProvider {
    private String sql = "";

    public String findByUserName(String name) {
        sql = "select * from user where uname= '" + name + "';";
        return sql;
    }

    public String addUser(User user) {
        StringBuffer sql1 = new StringBuffer();
        sql1.append("insert into user(uname, password, status, tel) values( ");
        sql1.append("'" + user.getUname() + "'");
        sql1.append(", '" + user.getPassword() + "'");
        sql1.append(", '" + 2 + "'");
        sql1.append(", '" + user.getTel() + "'");
        sql1.append(") ;");
        return sql1.toString();
    }

    public String loginUser(String name, String pwd) {
        sql = "select * from user where uname= '" + name + "'" + " and " + "password ='" + pwd + "' ;";
        System.out.println(sql);
        return sql;
    }
}
