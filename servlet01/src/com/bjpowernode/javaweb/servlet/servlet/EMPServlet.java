package com.bjpowernode.javaweb.servlet.servlet;
import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class EMPServlet implements Servlet{


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        //设置响应的内容类型
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        //连接数据库
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","0753");

            //获取预编译的数据库对象连接
            String sql="select * from emp";
            ps= conn.prepareStatement(sql);
            //连接数据库
            rs=ps.executeQuery();
            //处理查询结果集
            while(rs.next()){
                String empno=rs.getString("empno");
                String ename=rs.getString("ename");
                String sal=rs.getString("sal");
                String job=rs.getString("job");
                out.print(job+"<br>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
