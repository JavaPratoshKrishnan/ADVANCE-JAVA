package com.pratosh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

public class CLIENT_PROJECT {
    private static final String ORACLE_AGE_CALCULATOR="SELECT (SYSDATE-DOB)/365.25 FROM STAFF WHERE ENO=?";
    private static final String INSERT_EMP_DATES_QUERY="INSERT INTO STAFF VALUES(?,?,?,?)";


    public static void main(String[] args) throws NoSuchElementException {
        try(Scanner sc = new Scanner(System.in)) {
        int op;
            while (true) {
                System.out.println("ENTER OPTION 1 TO INSERT EMPLOYEE DETAILS. :");
                System.out.println("ENTER OPTION 2 TO CALCULATE THE AGE OF THE EMPLOYEE :");
                System.out.println("ENTER OPTION 3 TO EXIT :");
               
                	op = sc.nextInt();	
               
                
                	
                

                switch(op) {
                    case 1:
                        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
                            
                            PreparedStatement ps = con.prepareStatement(INSERT_EMP_DATES_QUERY);
                        ){
                            int no =0;
                            String name = null,dob=null,doj=null;
                            if(sc!=null) {
                                System.out.println("ENTER EMPLOYEE NUMBER : ");
                                if(sc.hasNextInt()) {
                                    no=sc.nextInt();
                                } 
                                else {
                                    System.out.println("Please enter a valid employee number.");
                                    sc.nextLine();
                                    continue;
                                }
                                System.out.println("ENTER EMPLOYEE NAME : ");
                                name=sc.next();
                                System.out.println("ENTER EMPLOYEE DOB (dd-MM-yyyy) : ");
                                dob=sc.next();
                                System.out.println("ENTER EMPLOYE DOJ (yyyy-MM-dd hh:mm:ss) : ");
                                sc.nextLine();
                                doj=sc.nextLine();
                            }
                            if (dob != null && doj != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
                        }
                        catch(SQLException se) {
                            se.printStackTrace();
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                    	try(Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","xe");
                				
                				PreparedStatement ps2 = con2.prepareStatement(ORACLE_AGE_CALCULATOR);
                				){
                			int no =0;
                			if(sc!=null) {
                				System.out.println("ENTER EMPLOYEE NUMBER : ");
                				if(sc.hasNextInt()) {
                                        no=sc.nextInt();
                                    } else {
                                        System.out.println("Please enter a valid employee number.");
                                        sc.nextLine();
                                        continue;
                                    }
                			}
                			if(ps2!=null) {
                				ps2.setInt(1,no);
                				
                				try(ResultSet rs =ps2.executeQuery()){
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
                    	break;
                    case 3:
                    	System.out.println("EXITED");
                    	System.exit(0);
                    	
                    default:
                        System.out.println("INVALID OPTION. PLEASE ENTER A VALID OPTION (1,2,3)");
                        
                }
            }
        }
    }
}

                				
                				
