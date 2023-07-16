package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQL_SelectTest {
	private static final String FETCH_ALL_PRODS_QUERY="SELECT PID,PNAME,PRICE,QTY FROM PRODUCT";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:postgresql:///NTAJ","postgres","sql");
				PreparedStatement ps = con.prepareStatement(FETCH_ALL_PRODS_QUERY);
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				boolean isRsEmpty=true;
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					isRsEmpty=false;
					
					
				}
				if(isRsEmpty)
					System.out.println("NO RECORDS FOUND");
				else
					System.out.println("RECORDS FOUND AND DISPLAYED");
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
