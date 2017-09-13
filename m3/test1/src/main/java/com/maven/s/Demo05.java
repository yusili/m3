package com.maven.s;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//测试时间处理（java.sql.date,time,timestamp）
public class Demo05 {
  private static final String PSW = "123qwe";
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String USER = "root";

  public static Long strToDate(String dateStr){
//    将字符串代表的日期转为long数字
    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    try {
      return format.parse(dateStr).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static  void main(String[] args) {
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(URL,USER,PSW);


      statement = conn.prepareStatement("select * from test1 where time between ? and ?");

      Date date1 = new java.sql.Date(strToDate("2016-1-1"));
      Date date2 = new Date(strToDate("2018-1-1"));

      statement.setObject(1,date1);
      statement.setObject(2,date2);

      rs = statement.executeQuery();

      while(rs.next()){
        Object one = rs.getObject(1);
        Object tow = rs.getObject(2);
        Object three = rs.getObject(3);
        System.out.println(""+one+" "+tow+three);
      }

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
