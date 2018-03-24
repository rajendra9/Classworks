package com.htc

import java.sql.Connection
import java.io.FileInputStream
import java.util.Properties
import java.sql.DriverManager
import java.sql.ResultSet

class DBConnector {
	var connectionProperties = new Properties();
	
	//Creating a method to save Connection Object
	def getConnection() :Connection={
  var connection:Connection = null
		connectionProperties.load(new FileInputStream("Connection.Properties"));
		var driver = connectionProperties.getProperty("driver")
		var url = connectionProperties.getProperty("url")
		var username = connectionProperties.getProperty("user")
		var password = connectionProperties.getProperty("password")
		Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    return connection
	}
}

