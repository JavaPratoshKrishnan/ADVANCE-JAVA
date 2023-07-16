package com.pratosh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest1 {
	private static final String CALL_QUERY="{CALL P_FIRST(?,?,?)}";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				CallableStatement cs = con.prepareCall(CALL_QUERY);
				Scanner sc = new Scanner(System.in)){
			int first = 0 ,second = 0;
			if(sc!=null) {
				System.out.println("ENTER THE FIRST VALUE");
				first=sc.nextInt();
				System.out.println("ENTER THE SECOND VALUE");
				second =sc.nextInt();
			}
			if(cs!=null) {
				cs.registerOutParameter(3,Types.INTEGER);
				
				cs.setInt(1,first);
				cs.setInt(2, second);
				
				cs.execute();
				
				int result = cs.getInt(3);
				System.out.println("RESULT (SUM OF "+first+","+second+"values) is :: "+result);
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
