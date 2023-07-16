package com.pratosh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest_MySql {
	private static final String CALL_PROCEDURE_QUERY="{CALL GET_PROD_DETAILS_BY_PID(?,?,?,?)}";
  
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE_QUERY);
				Scanner sc = new Scanner(System.in)){
			int id = 0;
			if(sc!=null) {
				System.out.println("ENTER THE PRODUCT ID : ");
				id=sc.nextInt();
			}
			if(cs!=null) {
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.FLOAT);
				cs.registerOutParameter(4,Types.FLOAT);
				
				cs.setInt(1, id);
				
				cs.execute();
				
				String name = cs.getString(2);
				float price = cs.getFloat(3);
				float qty   = cs.getFloat(4);
				System.out.println("PRODUCT NAME : "+name);
				System.out.println("PRODUCT PRICE : "+price);
				System.out.println("PRODUCT QTY : "+qty);
				
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
