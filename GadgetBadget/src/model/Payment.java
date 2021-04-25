package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbudget", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	
	
	
	
	//insert
	public String insertPayment(String name,String cType, String cNo, String cvv, String exp_mon, String exp_year, String amt, String Date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment( PaymentID , Name , CardType , CardNo , cvv , EXP_Month , EXP_Year , Amount , PaymentDate)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, cType);
	 preparedStmt.setString(4, cNo);
	 preparedStmt.setString(5, cvv);
	 preparedStmt.setString(6, exp_mon);
	 preparedStmt.setString(7, exp_year);
	 preparedStmt.setDouble(8, Double.parseDouble(amt));
	 preparedStmt.setString(9, Date);
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	
	
	
	     //read
	
	public String readPayment()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>Name</th>"
	 		+ "<th>Card Type</th><th>Card No</th>" + "<th>CVV</th>" + "<th>EXP_Month</th>" + "<th>EXP_Year</th>" +
	 		"<th>Amount</th>"+"<th>Date</th>"+"<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query); 
	 while (rs.next())
	 {
	 String PaymentID = Integer.toString(rs.getInt("PaymentID"));
	 String Name = rs.getString("Name");
	 String CardType = rs.getString("CardType");
	 String CardNo = rs.getString("CardNo");
	 String CVV = Double.toString(rs.getDouble("CVV"));
	 String EXP_Month = rs.getString("EXP_Month");
	 String EXP_Year = rs.getString("EXP_Year");
	 String Amount = Double.toString(rs.getDouble("Amount")); 
	 String PaymentDate = rs.getString("PaymentDate");
	 // Add into the html table
	 output += "<tr><td>" + PaymentID + "</td>";
	 output +="<td>" + Name + "</td>";
	 output +="<td>" + CardType + "</td>";
	 output += "<td>" + CardNo + "</td>";
	 output += "<td>" + CVV + "</td>";
	 output += "<td>" + EXP_Month + "</td>";
	 output += "<td>" + EXP_Year + "</td>";
	 output += "<td>" + Amount + "</td>";
	 output += "<td>" + PaymentDate + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='ProjectID' type='hidden' value='" + PaymentID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)

	 {
	 output = "Error while reading the payment details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
	
	
	
	 
		
	
	
	
	//update
	public String updatePayment(String PaymentID, String name, String cType,String cNo, String cvv, String exp_mon, String exp_year, String amt, String Date)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payment SET Name=?,CardType=?,CardNo=?,cvv=?,EXP_Month=?,EXP_Year=?,Amount=?,PaymentDate=? WHERE PaymentId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, cType);
	 preparedStmt.setString(3, cNo);
	 preparedStmt.setString(4, cvv);
	 preparedStmt.setString(5, exp_mon);
	 preparedStmt.setString(6, exp_year);
	 preparedStmt.setDouble(7, Double.parseDouble(amt));
	 preparedStmt.setString(8, Date);
	 preparedStmt.setInt(9, Integer.parseInt(PaymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Payment details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	
	
	
	
	
	
	
	
	   //delete
	
	public String deletePayment(String PaymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payment where PaymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PaymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Payment details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
}
