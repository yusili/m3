package com.maven.s.test;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

//测试时间处理（java.sql.date,time,timestamp）
public class Demo04 {
  private static final String PSW = "123qwe";
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String USER = "root";
  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PSW);


      statement = conn.prepareStatement("insert into test1 values(3,?,?)");

      Date date = new java.sql.Date(0,0,0);
      Timestamp stamp = new Timestamp(System.currentTimeMillis());

      statement.setObject(1,date);
      statement.setObject(2,stamp);

      statement.execute();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } finally{
      if (rs != null){
        try {
          rs.close();
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
