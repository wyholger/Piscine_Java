package edu.school21.chat.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class InitConnection
{
	public static Connection init_connection(String db_url, String user, String pass)
	{
		HikariConfig config = new HikariConfig();
		HikariDataSource data_source;
		Connection connection = null;

		config.setJdbcUrl(db_url);
		config.setUsername(user);
		config.setPassword(pass);
		config.addDataSourceProperty( "cachePrepStmts" , "true" );
		config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		data_source = new HikariDataSource(config);
		try
		{
			connection = data_source.getConnection();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}
