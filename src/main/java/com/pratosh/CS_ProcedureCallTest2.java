package com.pratosh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import java.sql.ResultSet;
import java.util.Scanner;

public class CS_ProcedureCallTest2 {
	private static final String CALL_PROCEDURE_QUERY="{CALL GET_PRODS_BY_PRICE_RANGE(?,?)}";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE_QUERY);
				){
			float start=0.0f,end=0.0f;
			if(sc!=null) {
				System.out.println("ENTER START PRICE :: ");
				start=sc.nextFloat();
				System.out.println("ENTER END PRICE :: ");
				end=sc.nextFloat();
			}
			if(cs!=null) {
				
				cs.setFloat(1,start);
				cs.setFloat(2,end);
				cs.execute();
				
				try(ResultSet rs = cs.getResultSet()){
					if(rs!=null) {
						System.out.println("PRODUCT DETAILS OF PRICE RANGE :: "+start+"  -  "+end);
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						}
					}
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
