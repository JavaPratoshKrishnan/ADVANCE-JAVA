package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertTest {
	
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "xe");
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_QUERY)){
			int count =0;
			if(sc!=null) {
				System.out.println("Enter Students count : ");
				count=sc.nextInt();			
		     }
			if(sc!=null && ps!=null) {
				for(int i = 1;i<=count;++i) {
					System.out.println("Enter "+ i +" student details");
					System.out.println("Enter Roll_No : ");
					int no =sc.nextInt();
					System.out.println("Enter Student Name : ");
					String name=sc.next();
					System.out.println("Enter Student's Mark : ");
					int mark=sc.nextInt();
					
					ps.setInt(1, no);ps.setString(2, name);ps.setInt(3, mark);
                    int result = ps.executeUpdate();
                    
                    if(result==0)
                    	System.out.println(i+" STUDENT RECORD NOT INSERTED.");
                    else
                    	System.out.println(i+" STUDENT RECORD INSERTED.");
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
