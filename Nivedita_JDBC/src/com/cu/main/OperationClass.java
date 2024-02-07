package com.cu.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OperationClass {
	Scanner sc = new Scanner(System.in);
	Connection con = null;
	ConnectionClass conObject = new ConnectionClass();
	int count = 0;
	
// 1. Insert Record
	public void insertRecord() throws ClassNotFoundException, SQLException{
		System.out.println("Enter student UID:");
		String studentUID=sc.next();
		System.out.println("Enter student name:");
		String studentName=sc.next();
		System.out.println("Enter student Couse:");
		String studentCourse=sc.next();
		System.out.println("Enter student Batch:");
		int studentBatch=sc.nextInt();
		if(con==null) {
			con = conObject.getConnection();
		}
		String countStatus = "select max(idstudent) from student";
		PreparedStatement psmt1 = con.prepareStatement(countStatus);
		ResultSet rs = psmt1.executeQuery();
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		String insertQuery = "insert into student values(?,?,?,?,?)";
		con = conObject.getConnection();
		PreparedStatement psmt2 = con.prepareStatement(insertQuery);
		count+=1;
		psmt2.setInt(1, count);
		psmt2.setString(2, studentUID);
		psmt2.setString(3, studentName);
		psmt2.setString(4, studentCourse);
		psmt2.setInt(5, studentBatch);
		
		int status = psmt2.executeUpdate();
		if(status>0) {
			System.out.println("Record insert successully");
		}
		else {
			System.out.println("Record not inserted");
		}
		psmt1.close();
		psmt2.close();
//		con.close();
	}

// 2. Update Record
	public void update() throws ClassNotFoundException, SQLException {
		System.out.println("Enter students UID");
		String studentUID = sc.next();
		
		String searchQuery = "Select *from student where studentUID=?";
		con = conObject.getConnection();
		PreparedStatement psmt1 = con.prepareStatement(searchQuery);
		psmt1.setString(1, studentUID);
		
		ResultSet rs = psmt1.executeQuery();
		if(rs.next()) {
			System.out.println("Student UID: " + rs.getString(2));
			System.out.println("Student Name: " + rs.getString(3));
			System.out.println("Student Cource: " + rs.getString(4));
			System.out.println("Student Batch: " + rs.getString(5));
		}
		else {
			System.out.println("Record not found");
		}
		System.out.println("Enter updated cource: ");
		String updatedCource = sc.next();
		
		String updatedQuery = "update student set studentCource=? where studentUID=?";
		
		PreparedStatement psmt2 = con.prepareStatement(updatedQuery);
		psmt2.setString(1, updatedCource);
		psmt2.setString(2, studentUID);
		
		int status = psmt2.executeUpdate();
		if(status>0) {
			System.out.println("Record update sucessfully");
		}
		psmt1.close();
		psmt2.close();
		con.close();	
	}

// 3. Delete Record
	
	public void delete() throws ClassNotFoundException, SQLException {
	    System.out.println("Enter student UID: ");
	    String studentUID = sc.next(); 
	    String searchQuery = "SELECT * FROM student WHERE studentUID=?";
	    con = conObject.getConnection();
	    PreparedStatement psmt1 = con.prepareStatement(searchQuery);
	    psmt1.setString(1, studentUID);

	    ResultSet rs = psmt1.executeQuery();
	    if (rs.next()) {
	        System.out.println("Student UID: " + rs.getString(2));
	        System.out.println("Student Name: " + rs.getString(3));
	        System.out.println("Student Course: " + rs.getString(4));
	        System.out.println("Student Batch: " + rs.getString(5));
	        System.out.println("Do you confirm for delete record? 1/Yes, 0/No");
		    int reply = sc.nextInt();
		    if (reply == 1) {
		        String deleteQuery = "DELETE FROM student WHERE studentUID=?"; 
		        PreparedStatement psmt2 = con.prepareStatement(deleteQuery);
		        psmt2.setString(1, studentUID);
		        int status1 = psmt2.executeUpdate();
		        if (status1 > 0) {
		            System.out.println("Record deleted successfully");
		        } else {
		            System.out.println("No record deleted");
		        }
		    }
	    } else {
	        System.out.println("Record not found");
	    }
	}
	
// 4. Display One record
	
	public void disply_one() throws ClassNotFoundException, SQLException {
		System.out.println("Enter student UID: ");
	    String studentUID = sc.next();
	    String searchQuery = "SELECT * FROM student WHERE studentUID=?";
	    con = conObject.getConnection();
	    PreparedStatement psmt1 = con.prepareStatement(searchQuery);
	    psmt1.setString(1, studentUID);
	    ResultSet rs = psmt1.executeQuery();
	    if (rs.next()) {
	        System.out.println("Student UID: " + rs.getString(2));
	        System.out.println("Student Name: " + rs.getString(3));
	        System.out.println("Student Course: " + rs.getString(4)); 
	        System.out.println("Student Batch: " + rs.getString(5));
	        System.out.println("Do you confirm for delete record? 1/Yes, 0/No");
		        }
	    else {
	    	System.out.println("No record deleted");
	    	}
	    }

// 5. Display all record
	
	 public void display_All() throws ClassNotFoundException, SQLException {
		 String searchQuery = "SELECT * FROM student";
		    con = conObject.getConnection();
		    PreparedStatement psmt1 = con.prepareStatement(searchQuery);
		    
		    ResultSet rs = psmt1.executeQuery();
		    while (rs.next()) {
		        System.out.println("Student UID: " + rs.getString(2));
		        System.out.println("Student Name: " + rs.getString(3));
		        System.out.println("Student Course: " + rs.getString(4)); 
		        System.out.println("Student Batch: " + rs.getString(5));
		        System.out.println("Do you confirm for delete record? 1/Yes, 0/No");
			        }
	 }
}