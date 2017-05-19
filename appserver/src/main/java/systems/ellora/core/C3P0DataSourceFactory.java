package systems.ellora.core;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/*
 * Provides to set certain properties 
 * for Data Source in C3PO Connection Pool
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {

  private ComboPooledDataSource dataSource = null;

  public C3P0DataSourceFactory() {
    this.dataSource = new ComboPooledDataSource();
  }

  public void setProperties(Properties properties) {
    this.dataSource.setPassword(properties.getProperty("password"));
    this.dataSource.setUser(properties.getProperty("user"));
    this.dataSource.setJdbcUrl(properties.getProperty("url"));

    try {

      this.dataSource.setDriverClass(properties.getProperty("driver"));

    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }
  }

  public DataSource getDataSource() {
    return (this.dataSource);
  }
}