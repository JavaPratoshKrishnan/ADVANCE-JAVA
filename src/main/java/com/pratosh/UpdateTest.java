package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner; 

public class UpdateTest {

	public static void main(String[] args) throws SQLException {
		
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
				Statement st = con.createStatement()){
			int newRoll=0;
			String newName=null;
			char newMark=0;
			
			if(sc!=null) {
				System.out.println("Enter Student's Roll No :  ");
				newRoll=sc.nextInt();
				System.out.println("Enter Student's Name : ");
				newName= sc.next();
				System.out.println("Enter Student's Mark : ");
				newMark=(char) sc.nextInt();
				
			}
			
			newName="'"+newName+"'";
			
			String query = "UPDATE STUDENT SET STUDENT_NAME="+newName+",MARKS="+newMark+"WHERE ROLL_NO="+newRoll;
			System.out.println(query);
			
			int count =0;
			if(st!=null) 
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("Record not found for updation ");
			else
				System.out.println(count+" no.of record found and deleted");
						
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
				
		

	}

}
