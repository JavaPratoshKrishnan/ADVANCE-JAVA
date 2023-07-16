package com.pratosh;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_LOBS_Oracle {
    private static final String INSERT_EMPLOYEE_LOBS_QUERY = "INSERT INTO E_LOBS(ENO,ENAME,DESG,PHOTO,RESUME) VALUES(EID_SEQ1.NEXTVAL,?,?,?,?)";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "xe")) {
            String name, desg, photoPath, resumePath;

            System.out.println("ENTER THE EMPLOYEE NAME :");
            name = sc.nextLine();
            System.out.println("ENTER THE EMPLOYEE'S DESIGNATION :");
            desg = sc.nextLine();
            System.out.println("ENTER THE EMPLOYEE PHOTO PATH :");
            photoPath = sc.nextLine();
            System.out.println("ENTER THE EMPLOYEE RESUME PATH :");
            resumePath = sc.nextLine();

            try (InputStream is = new FileInputStream(photoPath);
                 Reader reader = new FileReader(resumePath);
                 PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_LOBS_QUERY)) {

                ps.setString(1, name);
                ps.setString(2, desg);
                ps.setBinaryStream(3, is);
                ps.setCharacterStream(4, reader);

                int count = ps.executeUpdate();

                if (count == 0) {
                    System.out.println("RECORD NOT INSERTED..");
                } else {
                    System.out.println("RECORD INSERTED..");
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
