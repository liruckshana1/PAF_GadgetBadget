package model;

import java.sql.*;

public class Customer {
	// A common method to connect to the DB

		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbudget", "root", "AKAI1997");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertCustomer(String name, int age, String address, String email) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into customer(`customerID`,`customerName`,`customerAge`,`customerAddress`,`customerEmail`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setInt(3, age);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, email);
				// execute the statement3
				preparedStmt.execute();
				con.close();
				output = "successfully Registered";
			} catch (Exception e) {
				output = "Error while registering the customer.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readCustomer() 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Customer Name</th>" +
			 "<th>Customer Age</th>" + 
			 "<th>Customer Address</th>" +
			 "<th>Customer Email</th></tr>"; 
			 
			 String query = "select * from customer"; 
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String customerID = Integer.toString(rs.getInt("customerID")); 
			 String customerName = rs.getString("customerName"); 
			 String customerAge = Integer.toString(rs.getInt("customerAge")); 
			 String customerAddress = rs.getString("customerAddress"); 
			 String customerEmail = rs.getString("customerEmail"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + customerName + "</td>"; 
			 output += "<td>" + customerAge + "</td>"; 
			 output += "<td>" + customerAddress + "</td>";
			 output += "<td>" + customerEmail + "</td>";
			 
			 // buttons
			 /*output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='customers.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					 + "<input name='customerID' type='hidden' value='" + customerID 
					 + "'>" + "</form></td></tr>"; */
			 } 
			 con.close(); 
			 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while reading the customers."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }

		public String updateCustomer(String ID, String name, String age, String address, String email) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE customer SET customerName=?, customerAge=?,customerAddress=?, customerEmail=? WHERE customerID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setInt(2, Integer.parseInt(age));
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, email);
				preparedStmt.setInt(5, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating customer.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deleteCustomer(String customerID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from customer where customerID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(customerID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting customer.";
				System.err.println(e.getMessage());
			}
			return output;
		}
}
