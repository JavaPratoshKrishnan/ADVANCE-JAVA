package com.pratosh;
import java.util.Scanner;

public class BANK_CALCULATOR {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int op;
		while(true) {
			System.out.println("ENTER 1 TO FIND THE AMOUNT TO BE PAID :");
			System.out.println("ENTER 2 TO FIND THE TIME(IN YRS) IT SHOULD BE PAID AT :");
			System.out.println("ENTER 3 TO FIND THE RATE OF INTEREST :");
			System.out.println("ENTER 4 TO FIND THE PRINCIPAL AMOUNT :");
			System.out.println("ENTER 5 TO EXIT THE APPLICATION.");
			op=sc.nextInt();
			
			
			switch(op) {
			 case 1:
				 int principal;
				 int time;
				 int ri;
				 int si;
				 int amt;
				 
				 System.out.println("ENTER THE PRINCIPAL AMOUNT :");
				 principal=sc.nextInt();
				 System.out.println("ENTER THE TIME(IN YRS) :");
				 time=sc.nextInt();
				 System.out.println("ENTER THR RATE OF INTEREST :");
				 ri=sc.nextInt();
				 
				 si=(ri*time*principal)/100;
				 amt=si+principal;
				 System.out.println("THE AMOUNT TO BE PAID IN "+time+"YRS"+" IS "+amt+"rs.");
				 
				 break;
			 case 2:
				 int principal1;
				 int time1;
				 int ri1;
				 int si1;
				 
				 
				 System.out.println("ENTER THE PRINCIPAL AMOUNT :");
				 principal1=sc.nextInt();
				 System.out.println("ENTER THE RATE OF INTEREST :");
				 ri1=sc.nextInt();
				 System.out.println("ENTER THE SIMPLE INTEREST :");
				 si1=sc.nextInt();
				 
				 time1=(100*si1)/(principal1*ri1);
				 System.out.println("THE TIME  YOU HAVE TO PAY "+(si1+principal1)+"rs IS "+time1+"YRS...");
				 
				 break;
	 
			 case 5:
				 System.out.println("THANK YOU FOR VISITING OUR APPLICATION , HOPE YOU VISIT AGAIN.");
				 System.out.println("APPLICATION EXITED..");
				 System.exit(0);
				 break;
			 default:
				 System.out.println("INAVLID OPTION. PLEASE ENTER A VALID ONE...");
				 
				 
			}
			
		}
		
	}

}
