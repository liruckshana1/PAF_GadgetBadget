package model;

import java.sql.*;

public class Funders
{	 //A common method to connect to the DB
	private Connection connect()
	{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, user name, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbudget", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
	}
	public String insertFunder(String ID, String name, String address, String phno, String email, String amount, String date)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into funders (`funderID`,`funderName`,`funderAddress`,`funderPhno`,`FunderEmail`,`Amount`,`Date`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setDouble(4, Double.parseDouble(phno));
			preparedStmt.setString(5, email);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(5, date);
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the funder.";
			System.err.println(e.getMessage());
		}
		return output;
}
public String readFunder()
{
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Funder ID</th><th>Funder Name</th>" +
				"<th>Funder Address</th>" +
				"<th>Funder PhoneNo</th>" +
				"<th>Funder Email</th>" +
				"<th>Amount</th>" +
				"<th>Date</th>" +
				"<th>Update</th><th>Remove</th></tr>";

		String query = "select * from funders";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
			String funderID = Integer.toString(rs.getInt("funderID"));
			String funderName = rs.getString("funderName");
			String funderAddress = rs.getString("funderAddress");
			String funderPhno = Double.toString(rs.getInt("funderPhno"));
			String funderEmail = rs.getString("funderEmail");
			String amount = rs.getString("amount");
			String date = rs.getString("date");
			// Add into the html table
			output += "<tr><td>" + funderID + "</td>";
			output += "<td>" + funderName + "</td>";
			output += "<td>" + funderAddress + "</td>";
			output += "<td>" + funderPhno + "</td>";
			output += "<td>" + funderEmail + "</td>";
			output += "<td>" + amount + "</td>";
			output += "<td>" + date + "</td>";
			// buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='funders.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
									+ "<input name='itemID' type='hidden' value='" + funderID
									+ "'>" + "</form></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the funders.";
		System.err.println(e.getMessage());
	}
	return output;
 }
public String updateFunder(String ID, String name, String address, String phno, String email, String amount, String date)

 {
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		// create a prepared statement
		String query = "UPDATE funders SET funderName=?,funderAddress=?,funderPhno=?,funderEmail=?, amount=?, date=? WHERE funderID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, address);
		preparedStmt.setDouble(4, Double.parseDouble(phno));
		preparedStmt.setString(5, email);
		preparedStmt.setDouble(4, Double.parseDouble(amount));
		preparedStmt.setString(5, date);
		preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the funder.";
		System.err.println(e.getMessage());
	}
	return output;
 }


public String deleteFunder(String funderID)
 {
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting."; }
		// create a prepared statement
		String query = "delete from funders where funderID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(funderID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
	}
	catch (Exception e)
	{
		output = "Error while deleting the funder.";
		System.err.println(e.getMessage());
	}
	return output;
 }
}
