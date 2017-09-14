package com.maven.s.test;
//批处理
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
  private static final String PSW = "123qwe";
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String USER = "root";
  public static void main(String[] args) {
    Connection conn = null;
    Statement statement = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PSW);

      conn.setAutoCommit(false);//设为手动提交

      statement = conn.createStatement();
      for (int i = 0;i<2000;i++){
        statement.addBatch("INSERT into a (s,c) values(6+"+i+",'i"+i+"') ");
      }
      statement.executeBatch();
      conn.commit();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
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
