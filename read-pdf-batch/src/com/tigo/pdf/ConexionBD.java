package com.tigo.pdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Please include Classpath  Where your DB2 Driver is located");
			e.printStackTrace();
			return;
		}
		System.out.println("DB2 driver is loaded successfully");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean found = false;
		 String url = 
			     "jdbc:db2://jdbc:db2:desadbfacturaelectronica.database.windows.net:1433/desadbfacturaelectronica" +
			     ":user=user_fe;password=user_fe;" +
			     "traceLevel=" +
			     (com.ibm.db2.jcc.DB2BaseDataSource.TRACE_ALL) + ";";
			    
		try {
			conn = DriverManager.getConnection(url);
			if (conn != null) {
				System.out.println("DB2 Database Connected");
			} else {
				System.out.println("Db2 connection Failed ");
			}
			pstmt = conn.prepareStatement("Select * from bo");
			rset = pstmt.executeQuery();
			if (rset != null) {

				while (rset.next()) {
					found = true;
					System.out.println("Class Code: " + rset.getString("clcode"));
					System.out.println("Name: " + rset.getString("name"));
				}
			}
			if (found == false) {
				System.out.println("No Information Found");
			}
		} catch (SQLException e) {
			System.out.println("DB2 Database connection Failed");
			e.printStackTrace();
			return;
		}
	}
}
