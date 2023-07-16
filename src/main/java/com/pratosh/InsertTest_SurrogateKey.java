package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class InsertTest_SurrogateKey {
	private static final String INSERT_STUDENT_QUERY = "INSERT INTO STUDENT(ROLL_NO,STUDENT_NAME,MARKS) VALUES(EID_SEQ.NEXTVAL,?,?,?)"; 

	public static void main(String[] args) {
		try(Scanner sc =new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_QUERY);){
			String name=null;
			float no = 0.0f,marks=0.0f;
			if(sc!=null) {
				System.out.println("ENTER THE STUDENT_NAME : ");
				name=sc.next();
				System.out.println("ENTER THE ROLL_NO OF THE STUDENT : ");
				no=sc.nextFloat();
				System.out.println("ENTER THE TOTAL MARKS OF THE STUDENT ( /100) :");
				marks=sc.nextFloat();
				
			}
			if(ps!=null) {
				ps.setFloat(1, no);
				ps.setString(2,name);
				ps.setFloat(3,marks);
				
				int result =ps.executeUpdate();
				
				if(result==0)
					System.out.println("RECORD NOT INSERTED. ");
				else
					System.out.println("RECORD INSERTED.");
				
		}
			
		}
		catch(SQLException se ) {
			se.printStackTrace();
		}
		catch(Exception e ) {
			e.printStackTrace();
		}

	}

}
