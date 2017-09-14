package com.maven.s.C3P0连接池;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

public class C3p0test {

  @Test
  public void testmain(){
    ComboPooledDataSource cds = new ComboPooledDataSource();
    Connection conn = null;
    try {
      conn=cds.getConnection();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }


}
