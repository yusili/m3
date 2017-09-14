package com.maven.s.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
  private static final String PSW = "123qwe";
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String USER = "root";

  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet set = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PSW);

      String sql= "select * from a where s=1";
      statement = conn.prepareStatement(sql);
      set = statement.executeQuery();

      while(set.next()){
        System.out.println(set.getInt(1)+"=="+set.getString(2));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      if (set != null){
        try {
          set.close();
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
