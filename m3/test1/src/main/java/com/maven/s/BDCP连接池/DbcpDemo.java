package com.maven.s.BDCP连接池;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class DbcpDemo {

  @Test
  public void testmain() throws Exception {
    //配置文件
    Properties pro = new Properties();
    InputStream is = DbcpDemo.class.getClassLoader().getResourceAsStream("jdbcPro.properties");
    pro.load(is);
    String url = pro.getProperty("url");
    System.out.println(url);
    //创建连接池
    DataSource ds = BasicDataSourceFactory.createDataSource(pro);
    Connection conn = ds.getConnection();

    conn.close();
  }


}
