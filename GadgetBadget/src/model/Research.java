package model;

import java.sql.*; 

public class Research {
	
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
	
	public String insertResearch(String code,String name, String type, String price)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into products( ProjectID , ProjectCode , ProjectName , ProjectType , Price )"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, type);
	 preparedStmt.setDouble(5, Double.parseDouble(price));
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	
	
	
	
	     //read
	
	public String readResearch()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Project ID</th><th>Project Code</th>"
	 		+ "<th>Project Name</th><th>Project Type</th>" + "<th>Price</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query); 
	 while (rs.next())
	 {
	 String ProjectID = Integer.toString(rs.getInt("ProjectID"));
	 String ProjectCode = rs.getString("ProjectCode");
	 String ProjectName = rs.getString("ProjectName");
	 String ProjectType = rs.getString("ProjectType");
	 String Price = Double.toString(rs.getDouble("Price"));
	 // Add into the html table
	 output += "<tr><td>" + ProjectID + "</td>";
	 output +="<td>" + ProjectCode + "</td>";
	 output +="<td>" + ProjectName + "</td>";
	 output += "<td>" + ProjectType + "</td>";
	 output += "<td>" + Price + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Removeclass'btn btn-danger'>"
	 + "<input name='ProjectID' type='hidden' value='" + ProjectID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)

	 {
	 output = "Error while reading the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
	
	
	
	
	
	
	
	//update
	public String updateResearch(String ProjectID, String code, String name,String type, String price)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE products SET ProjectCode=?,ProjectName=?,ProjectType=?,Price=? WHERE ProjectID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, type);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setInt(5, Integer.parseInt(ProjectID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	
	
	
	
	
	
	
	
	   //delete
	
	public String deleteResearch(String ProjectID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from products where ProjectID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ProjectID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
}
