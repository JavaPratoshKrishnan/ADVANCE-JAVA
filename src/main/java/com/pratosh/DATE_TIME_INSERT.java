package com.pratosh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DATE_TIME_INSERT {
	private static final String INSERT_EMP_DATES_QUERY="INSERT INTO STAFF VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				PreparedStatement ps = con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			int no =0;
			String name = null,dob=null,doj=null;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NUMBER : ");
				no=sc.nextInt();
				System.out.println("ENTER EMPLOYEE NAME : ");
				name=sc.next();
				System.out.println("ENTER EMPLOYEE DOB (dd-MM-yyyy) : ");
				dob=sc.next();
				System.out.println("ENTER EMPLOYE DOJ (yyyy-MM-dd hh:mm:ss) : ");
				sc.nextLine();
				doj=sc.nextLine();
				
				
			}
			
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf.parse(dob);
			
			long ms =udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			
			java.sql.Timestamp sqdoj = java.sql.Timestamp.valueOf(doj);
			
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3,sqdob);
				ps.setTimestamp(4,sqdoj);
				
				int count =ps.executeUpdate();
				if(count==0)
					System.out.println("RECORD NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
				
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
