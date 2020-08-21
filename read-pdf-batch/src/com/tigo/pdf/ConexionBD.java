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
			//git clone https://bitbucket.org/yperez_altiuz/tigotransferenciaxmlsftp/src/master
			//Class.forName("COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
			/** Estado: Fallo:Fallo de la prueba: [jcc][t4][2030][11211][3.59.81] A communication error occurred during operations on the connection's underlying socket, socket input stream, 
or socket output stream.  Error location: Reply.fill() - insufficient data (-1).  Message: Insufficient data. ERRORCODE=-4499, SQLSTATE=08001**/
			
			/** [jcc][t4][2030][11211][4.25.13] A communication error occurred during operations on the connection's underlying socket, socket input stream, 
or socket output stream.  Error location: Reply.fill() - insufficient data (-1).  Message: Insufficient data. ERRORCODE=-4499, SQLSTATE=08001**/
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
			     "jdbc:db2://desadbfacturaelectronica.database.windows.net:1433/desadbfacturaelectronica" +
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
