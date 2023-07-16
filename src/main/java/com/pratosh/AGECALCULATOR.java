package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;
import java.text.SimpleDateFormat;
public class AGECALCULATOR {
	private static final String ORACLE_AGE_CALCULATOR="SELECT (SYSDATE-DOB)/365.25 FROM STAFF WHERE ENO=?";

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				PreparedStatement ps = con.prepareStatement(ORACLE_AGE_CALCULATOR);
				){
			int no =0;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NUMBER : ");
				no=sc.nextInt();
			}
			if(ps!=null) {
				ps.setInt(1,no);
				
				
				try(ResultSet rs =ps.executeQuery()){
					if(rs.next()) {
						System.out.println("EMPLOYEE AGE : "+rs.getFloat(1));
					}
					else {
						System.out.println("EMPLOYEE NOT FOUND!");
					}
				}
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
