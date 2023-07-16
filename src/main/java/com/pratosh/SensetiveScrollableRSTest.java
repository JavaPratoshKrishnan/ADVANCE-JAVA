package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensetiveScrollableRSTest {

	public static void main(String[] args) {
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT ID,NAME,DEPARTMENT,SALARY FROM EMPLOYEES");
				){
			System.out.println("Records (top-bottom)");
			
			int count = 1;
			while(rs.next()) {
				
				if(count == 1) {
					Thread.sleep(20000);
				}
				rs.refreshRow();
				
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				count++;
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
