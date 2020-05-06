package com.paf.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class paymentAPI extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		
		
		String patientId      =   request.getParameter("patientId");        
		String cardNumber	=	request.getParameter("cardNumber");
		String nameOfTheCard	=	request.getParameter("nameOfTheCard");
		String validDate 	=	request.getParameter("validDate");
		String cvcCode 	= 	request.getParameter("cvcCode");
		String amount	=	request.getParameter("amount");
		String PASS		=	request.getParameter("pass");
		
		System.out.println("API CALL  :  " +patientId);
		
		paymentDataModel item = new paymentDataModel();
		
		String output = item.createPayment(patientId, cardNumber, nameOfTheCard, validDate, cvcCode, amount, PASS);
		
		
		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		paymentDataModel item = new paymentDataModel();
		Map paras = getParasMap(request);
		System.out.println(paras + "  :: paras PUT");
		String output = item.updatePayment(paras.get("hidPatSave").toString(), paras.get("patientId").toString(),
				paras.get("cardNumber").toString(), paras.get("nameOfTheCard").toString(), paras.get("validDate").toString(),
			    paras.get("cvcCode").toString(), paras.get("amount").toString(), paras.get("password").toString()
				);
		
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Map paras = getParasMap(request);
		paymentDataModel item = new paymentDataModel();
		
		String output = item.delete(paras.get("NIC").toString());
		
		System.out.println(output + "Delete NIC");
		
		response.getWriter().write(output);
		
		
	}
	
	private static Map getParasMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
            String queryString = scanner.hasNext() ?
                    scanner.useDelimiter("\\A").next() : "";
            scanner.close();
            String[] params = queryString.split("&");
            for (String param : params) {
                String[] p = param.split("=");
                map.put(p[0], p[0]);
            }
            
            System.out.println(map + "Map ***");
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return map;
        
    }

}