//package com.pratosh;
//
//import java.sql.Connection;
//import java .sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//
//public class LoginCredentials  {
//	
//	public static void main(String[] args) {
//		try(Scanner sc = new Scanner(System.in);
//			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
//			Statement st = con.createStatement();
//			){
//			String user =null,pass=null;
//			if(sc!=null) {
//				System.out.println("Enter Username : ");
//				user=sc.nextLine();
//				System.out.println("Enter Password : ");
//				pass=sc.nextLine();
//			}
//			user="'"+user+"'";
//			pass="'"+pass+"'";
//			
//			
//			String query="SELECT COUNT(*) FROM USERINFO WHERE UNAME="+user+" AND PWD="+pass;
//			System.out.println(query);
//			
//			if(st!=null) {
//				try(ResultSet rs = st.executeQuery(query)){
//					if(rs!=null) {
//						rs.next();
//						int count = rs.getInt(1);
//						if(count == 0)
//							System.out.println("INVALID CREDENTIALS");
//						else
//							System.out.println("VALID CREDENTIALS");
//					}
//				}
//				
//					
//		}
//				
//	}
//	catch(SQLException se){
//		se.printStackTrace();
//	}
//	catch(Exception e){
//		e.printStackTrace();	
//	}
//	
//	
//	
//
//}
//}
	

package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginCredentials {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "xe")) {

            String user = null, pass = null;
            if (sc != null) {
                System.out.println("Enter Username : ");
                user = sc.nextLine();
                System.out.println("Enter Password : ");
                pass = sc.nextLine();
            }

            String query = "SELECT COUNT(*) FROM USERINFO WHERE UNAME=? AND PWD=?";
            System.out.println(query);

            if (con != null) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, user);
                    ps.setString(2, pass);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs != null) {
                            rs.next();
                            int count = rs.getInt(1);
                            if (count == 0)
                                System.out.println("INVALID CREDENTIALS");
                            else
                                System.out.println("VALID CREDENTIALS");
                        }
                    }
                }

            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
