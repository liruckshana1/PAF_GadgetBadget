package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Researcher {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbudget", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<ResearcherModel> readJsonResearcher() {
		List<ResearcherModel> researchList = new ArrayList<ResearcherModel>();
		try {
			Connection con = connect();
			if (con == null) {
				throw new RuntimeException("Error while connecting to the database for reading.");
			}

			String query = "select * from reseacher3";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String researcherID = Integer.toString(rs.getInt("researcherID"));
				String researcherName = rs.getString("researcherName");
				String researcherPhone = Integer.toString(rs.getInt("researcherPhone"));
				String researcherUniversity = rs.getString("researcherUniversity");
				researchList
						.add(new ResearcherModel(researcherID, researcherName, researcherPhone, researcherUniversity));

			}
			con.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return researchList;
	}

	public String insertResearcher(String name, int phone, String university) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into reseacher3(`researcherId`,`researcherName`,`researcherPhone`,`researcherUniversity`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setInt(3, phone);
			preparedStmt.setString(4, university);
			// execute the statement3
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readResearcher() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>researcher Id</th>" + "<th>researcher Name</th>"
					+ "<th>researcher phone</th>" + "<th>researcher university</th></tr>";

			String query = "select * from reseacher3";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String researcherID = Integer.toString(rs.getInt("researcherID"));
				String researcherName = rs.getString("researcherName");
				String researcherPhone = Integer.toString(rs.getInt("researcherPhone"));
				String researcherUniversity = rs.getString("researcherUniversity");

				// Add into the html table
				output += "<tr><td>" + researcherID + "</td>";
				output += "<td>" + researcherName + "</td>";
				output += "<td>" + researcherPhone + "</td>";
				output += "<td>" + researcherUniversity + "</td>";

				// buttons
				/*
				 * output +=
				 * "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 * + "<td><form method='post' action='researcher.jsp'>" +
				 * "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 * + "<input name='researcherID' type='hidden' value='" + researcherID + "'>" +
				 * "</form></td></tr>";
				 */
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the researchers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String ID, String name, String phone, String university) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE reseacher3 SET researcherName=?, researcherPhone=?,researcherUniversity=? WHERE researcherID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, Integer.parseInt(phone));
			preparedStmt.setString(3, university);
			preparedStmt.setInt(4, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteResearcher(String researcherID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from reseacher3 where researcherID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researcherID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
