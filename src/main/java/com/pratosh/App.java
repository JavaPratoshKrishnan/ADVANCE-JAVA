package com.pratosh;

import java.sql.*;
import java.util.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception 
    {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Enter student name : ");
    	
        String sdnm = sc.next();
    	
    	sdnm="'"+sdnm+"'";
    	
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
    	
    	Statement st = con.createStatement();
    	
    	String query = "Select * from student where STUDENT_NAME = "+sdnm;
    	
    	System.out.println(query);
    	
    	ResultSet rs = st.executeQuery(query);
    	
    	boolean isRsEmpty=false;
    	while(rs.next()) {
    		System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
    		isRsEmpty=true;
    	}
    	
    	if(isRsEmpty) {
           System.out.println("RECORDS FOUND AND DISPLAYED");
           
    	}else {
    		System.out.println("NO RECORDS FOUND");
    		
    	}
    	
    	
    	rs.close();
    	st.close();
    	con.close();
    	sc.close();
    	
    	
    	
    	
    	
    	
    	
    	
    	
}
    
    
}
