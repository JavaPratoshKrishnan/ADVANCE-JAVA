package com.pratosh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CS_FunctionCallTest {
	
	private static final String CALL_FUNCTION_QUERY="{?=call FX_GET_STUD_DETAILS_BY_NO(?,?,?)}"; 

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				CallableStatement cs = con.prepareCall(CALL_FUNCTION_QUERY);
				){
				int no = 0;
				if(sc!=null) {
					System.out.println("ENTER THE STUDENT NUMBER :");
					no=sc.nextInt();
				}
				if(cs!=null) {
					cs.registerOutParameter(1,Types.VARCHAR);
					cs.registerOutParameter(3,Types.VARCHAR);
					cs.registerOutParameter(4,Types.FLOAT);
					
					cs.setInt(2,no);
					
					cs.execute();
					
					String addrs=cs.getString(1);
					String name=cs.getString(3);
					float avg = cs.getFloat(4);
					System.out.println("STUDENT NAME :: "+name);
					System.out.println("STUDENT ADDRESS :: "+addrs);
					System.out.println("STUDENT AVERAGE :: "+avg);
					
					
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
