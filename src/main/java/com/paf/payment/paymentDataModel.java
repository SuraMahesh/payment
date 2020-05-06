package com.paf.payment;

import java.sql.*;

public class paymentDataModel {

	Connection con = null;

	public paymentDataModel() {
		String url = "jdbc:mysql://localhost:3306/payment2";
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

	public String createPayment(String patientId, String cardNumber, String nameOfTheCard, String validDate, String cvcCode, String amount,
			 String Password) {

		String output = "";

		System.out.println("patientId print  "+patientId);
		try {
			
			String sql = "INSERT INTO payment (`NIC`,`patientId`,`cardNumber`,`nameOfTheCard`,`validDate`,`cvcCode`,`amount`,`Password`)"
					+ " VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, 000);
			st.setString(2, patientId);
			st.setInt(3, Integer.parseInt(cardNumber));
			st.setString(4, nameOfTheCard);
			st.setString(5, validDate);
			st.setInt(6, Integer.parseInt(cvcCode));
			st.setFloat(7, Float.parseFloat(amount));
			st.setString(8, Password);
			

			st.execute();

			String newPayment = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPayment + "\"}";
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

		String sql = "SELECT * FROM payment";

		try {

			output = "<table class=\"table table-striped table-dark\"> <thead> <tr> <th scope=\"col\"> Patient Id</th><th scope=\"col\">Card Number</th>"
					+ "<th scope=\"col\">Name of the card</th><th scope=\"col\">Valid Date</th> <th scope=\"col\">Cvc Code</th> <th scope=\"col\">Amount</th> "
					+ "<th scope=\"col\">Password</th></tr> </thead>";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

		

			while (rs.next()) {

				String NIC = Integer.toString(rs.getInt("NIC"));
				String patientId = rs.getString("patientId");
				String cardNumber = Integer.toString(rs.getInt("cardNumber"));
				String nameOfTheCard = rs.getString("nameOfTheCard");
				String validDate = rs.getString("validDate");
				String cvcCode = Integer.toString(rs.getInt("cvcCode"));
				String amount = Float.toString(rs.getFloat("amount"));
				String Password = rs.getString("Password");

				
				output += "<tr>";
				output += "<td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+NIC+"'>" +patientId +  "</td>";
				output += "<td>" + cardNumber + "</td>";
				output += "<td>" + nameOfTheCard + "</td>";
				output += "<td>" + validDate + "</td>";
				output += "<td>" + cvcCode + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>";
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-patid='"+ NIC + "'></td>";
				output += "</tr>";
			}
			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading the Patients Details.";
			System.err.println("catch 1 Read " + e.getMessage());
		}

		return output;
	}


	public String updatePayment(String hidPatSave, String patientId, String cardNumber, String nameOfTheCard, String validDate, String cvcCode, String amount,
			String Password) {
		
		String output = "";
		
		String sql = "UPDATE payment set patientId=? , cardNumber=?, nameOfTheCard=?, validDate=?, cvcCode=?, amount=?, Password=? WHERE NIC=?";
		try {

			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, patientId);
			st.setInt(2, Integer.parseInt(cardNumber));
			st.setString(3, nameOfTheCard);
			st.setString(4, validDate);
			st.setInt(5, Integer.parseInt(cvcCode));
			st.setFloat(6, Float.parseFloat(amount));
			st.setString(7, Password);
			st.setInt(9, Integer.parseInt(hidPatSave));
		
			st.executeUpdate();
			
			String newPayment = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPayment + "\"}";
			System.out.println("Insert Model success Output  ::" + output);

		} catch (Exception e) {
			
			output = "{\"status\":\"error\",\"data\": \" Error while patient Details Updating.\"}";
			System.err.println("catch 4 UPDATR ::  " + e.getMessage());

		}

		return output;

	}

	public String delete(String NIC) {
		
		String output = "";
		
		String sql = "DELETE FROM payment WHERE NIC=?";
		try {

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, Integer.parseInt(NIC));
			st.execute();
			
			String newPayment = getCustomers();
			output = "{\"status\":\"success\",\"data\": \"" + newPayment + "\"}";

		} catch (Exception e) {
			
			output = "{\"status\":\"error\",\"data\": \" Error while patient Details Deleting.\"}";
			System.err.println("catch 5 :: " + e.getMessage());

		}
		return output;

	}

}