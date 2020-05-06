package com.paf.payment;

import java.sql.*;

public class paymentDataModel {

	Connection con = null;

	public paymentDataModel() {
		String url = "jdbc:mysql://localhost:3306/paf-project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println(" connected");
		} catch (Exception e) {
			System.out.println("not connected" + e);
		}
	}

	public String createUser(String NIC, String FName, String LName, String Email, String PhoneNum, String BirthDay,
			String Address, String Password) {

		String output = "";

		System.out.println("nic print  "+NIC);
		try {
			
			String sql = "INSERT INTO patient (`pat_id`,`pat_nic`,`FirstName`,`LastName`,`Email`,`PhoneNumber`,`Birthday`,`Address`,`Password`)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, 000);
			st.setString(2, NIC);
			st.setString(3, FName);
			st.setString(4, LName);
			st.setString(5, Email);
			st.setInt(6, Integer.parseInt(PhoneNum));
			st.setString(7, BirthDay);
			st.setString(8, Address);
			st.setString(9, Password);

			st.execute();

			String newPatient = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPatient + "\"}";
			System.out.println("Insert Model success Output  ::" + output);

		} catch (Exception e) {
			output = "{\"status\":\"error\",\"data\": \" Error while patient Details Inserting.\"}";
			System.out.println("Insert Model Error Output  ::" + output);
			System.err.println("catch 3 Insert " + e.getMessage());

		}
		return output;

	}

	public String getCustomers() {

		String output = "";

		String sql = "SELECT * FROM patient";

		try {

			output = "<table class=\"table table-striped table-dark\"> <thead> <tr> <th scope=\"col\"> Patient NIC</th><th scope=\"col\">First Name</th>"
					+ "<th scope=\"col\">Last Name</th><th scope=\"col\">Email</th> <th scope=\"col\">Phone Number</th> <th scope=\"col\">Birthday</th> "
					+ "<th scope=\"col\">Address</th> <th scope=\"col\">Password</th></tr> </thead>";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

		

			while (rs.next()) {

				String patID = Integer.toString(rs.getInt("pat_id"));
				String patNIC = rs.getString("pat_nic");
				String patFName = rs.getString("FirstName");
				String patLName = rs.getString("LastName");
				String patEmail = rs.getString("Email");
				String patPNum = Integer.toString(rs.getInt("PhoneNumber"));
				String patBday = rs.getString("Birthday");
				String patAddr = rs.getString("Address");
				String patPass = rs.getString("Password");

				//System.out.println(patID + "  " + patNIC + "  " + patFName + "  " + patLName + "  " + patEmail + "  "
					//	+ patPNum + "  " + patBday + "  " + patAddr + "  " + patPass);

				output += "<tr>";
				output += "<td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+patID+"'>" +patNIC +  "</td>";
				output += "<td>" + patFName + "</td>";
				output += "<td>" + patLName + "</td>";
				output += "<td>" + patEmail + "</td>";
				output += "<td>" + patPNum + "</td>";
				output += "<td>" + patBday + "</td>";
				output += "<td>" + patAddr + "</td>";
				output += "<td>" + patPass + "</td>";
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>";
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-patid='"+ patID + "'></td>";
				output += "</tr>";
			}
			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading the Patients Details.";
			System.err.println("catch 1 Read " + e.getMessage());
		}

		return output;
	}


	public String updateUser(String hidPatSave, String NIC, String FName, String LName, String Email, String PhoneNum, String BirthDay,
			String Address, String Password) {
		
		String output = "";
		
		String sql = "UPDATE patient set pat_nic=? , FirstName=?, LastName=?, Email=?, PhoneNumber=?, Birthday=?,Address=?, Password=? WHERE pat_id=?";
		try {

			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, NIC);
			st.setString(2, FName);
			st.setString(3, LName);
			st.setString(4, Email);
			st.setInt(5, Integer.parseInt(PhoneNum));
			st.setString(6, BirthDay);
			st.setString(7, Address);
			st.setString(8, Password);
			st.setInt(9, Integer.parseInt(hidPatSave));
		
			st.executeUpdate();
			
			String newPatient = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPatient + "\"}";
			System.out.println("Insert Model success Output  ::" + output);

		} catch (Exception e) {
			
			output = "{\"status\":\"error\",\"data\": \" Error while patient Details Updating.\"}";
			System.err.println("catch 4 UPDATR ::  " + e.getMessage());

		}

		return output;

	}

	public String delete(String ID) {
		
		String output = "";
		
		String sql = "DELETE FROM patient WHERE pat_id=?";
		try {

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, Integer.parseInt(ID));
			st.execute();
			
			String newPatient = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPatient + "\"}";

		} catch (Exception e) {
			
			output = "{\"status\":\"error\",\"data\": \" Error while patient Details Deleting.\"}";
			System.err.println("catch 5 :: " + e.getMessage());

		}
		return output;

	}

}