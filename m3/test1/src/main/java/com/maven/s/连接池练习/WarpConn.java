package com.maven.s.连接池练习;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class WarpConn extends AdaptiveConn {
  private Connection conn;
  private LinkedList<Connection> list;

  public WarpConn(Connection conn,LinkedList<Connection> list) {
    this.conn = conn;
    this.list = list;
  }

  @Override
  public Statement createStatement() throws SQLException {
    return conn.createStatement();
  }

  @Override
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return conn.prepareStatement(sql);
  }

  @Override
  public void close() throws SQLException {
    list.addLast(conn);
  }

}
