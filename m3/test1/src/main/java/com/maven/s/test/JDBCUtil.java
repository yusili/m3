package com.maven.s.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
  private static final String URL = "jdbc:mysql://localhost:3306/test";
  private static final String PSW = "123qwe";
  private static final String USER = "root";


  public static Connection getConn() throws SQLException {

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return DriverManager.getConnection(URL,USER,PSW);

  }

  public static void insert(String name,String tel,String addr,String pho){//String pho 是照片地址
    Connection conn = null;
    PreparedStatement statement = null;

    try {
      conn = JDBCUtil.getConn();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      statement = conn.prepareStatement("inser into homework VALUES(?,?,?,?)");

      statement.setObject(1,name);
      statement.setObject(2,tel);
      statement.setObject(3,addr);
      statement.setObject(4,new FileInputStream(pho));

      statement.execute();

      System.out.println("添加成功");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }finally {
      close(statement,conn);
    }

  }


  public static void select(Object s1){
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    List<Person> list = new ArrayList<Person>();
    Class cl = Person.class;
    Person p1 = null;


    try {
      conn = JDBCUtil.getConn();
      statement = conn.prepareStatement("select * FROM person WHERE name = ?");
      statement.setObject(1,s1);

      rs  = statement.executeQuery();

      while (rs.next()){
        String name = rs.getString(1);
        Integer id = rs.getInt(2);
        Integer age = rs.getInt(3);
        Constructor<Person> cs = cl.getConstructor(String.class,Integer.class,Integer.class);
        p1 = cs.newInstance(name,id,age);
        list.add(p1);
      }

      System.out.println(list.get(0).getName());

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } finally {
      close(statement,conn);
    }

  }

  public static void close(ResultSet rs,Statement s,Connection conn){
    if (rs != null){
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (s != null){
      try {
        s.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null){
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  public static void close(Statement s,Connection conn){
    if (s != null){
      try {
        s.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (conn != null){
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  public static void close(Connection conn){
    if (conn != null){
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
