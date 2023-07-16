package com.pratosh;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5TWR1 {

	public static void main(String[] args) throws SQLException {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe")){
			
			if(con!=null)
				try(Statement st = con.createStatement()){
					
					if(st!=null)
						try(ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Student")){
		                    
							if(rs!=null) {
								rs.next();
								System.out.println("EMP db table records count::"+rs.getInt(1));
							}
								
						}
						
						
					
				}
			
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e ) {
				e.printStackTrace();
			}
		
			
		}
				
		

	}

}
