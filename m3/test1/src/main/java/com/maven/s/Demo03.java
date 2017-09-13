package com.maven.s;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//测试事务
public class Demo03 {
  private static final String PSW = "123qwe";
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String USER = "root";
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement statement = null,statement1 = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PSW);

      conn.setAutoCommit(false);//JDBC中默认自动提交事务

      statement = conn.prepareStatement("insert into a values(2,?)");

      statement.setString(1,"a");

      statement.execute();

      System.out.println("ok,已插入一个用户");

      Thread.sleep(3000);

      statement1 = conn.prepareStatement("insert into a values(3 ,?,?)");

      statement1.setString(1,"a");

      statement1.execute();
      conn.commit();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally{
      if (rs != null){
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (statement1 != null){
        try {
          statement1.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (statement != null){
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn!=null){
        try {
          conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
    }

  }


}
