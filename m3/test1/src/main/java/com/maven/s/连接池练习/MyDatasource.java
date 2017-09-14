package com.maven.s.连接池练习;

import com.maven.s.test.JDBCUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/*
简易连接池

 */
public class MyDatasource  {
  private static LinkedList<Connection> list =new LinkedList<Connection>();
  static {
    //初始化时 放入3个连接
    for (int i=0;i<3;i++){
      try {
        Connection conn = JDBCUtil.getConn();
        list.add(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  //从连接池获取连接的方法
  public static Connection getConnetion(){
    if (list.isEmpty()){
      for (int i=0;i<2;i++){
        try {
          Connection conn = JDBCUtil.getConn();
          list.add(conn);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
      Connection conn = list.removeFirst();
      WarpConn wc = new WarpConn(conn,list);
      return wc;
  }
  //归还连接的方法
  public static void addBack(Connection conn){
    list.addLast(conn);
  }

}
