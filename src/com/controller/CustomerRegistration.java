package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HotelService.HotelService;
import com.bean.Customer;

/**
 * Servlet implementation class CustomerRegistration
 */
public class CustomerRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }
    static String geek_Password(int len) 
    { 
        System.out.println("Generating password using random() : "); 
        System.out.print("Your new password is : "); 
  
        // A strong password has Cap_chars, Lower_chars, 
        // numeric value and symbols. So we are using all of 
        // them to generate our password 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
                String symbols = "@*_=+-/.)"; 
  
  
        String values = Capital_chars + Small_chars + 
                        numbers + symbols; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] password = new char[len]; 
        String finals="";
  
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            password[i] = 
              values.charAt(rndm_method.nextInt(values.length())); 
  
        } 
        for(char c : password)
        {
        	finals=finals+c;
        }
        return finals; 
    } 
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		System.out.println("entering");
		String customerName=request.getParameter("customerName");
		int customerAge=Integer.parseInt(request.getParameter("customerAge"));
		String customerGender=request.getParameter("customerGender");
		String customerLocation=request.getParameter("customerLocation");
		String customerEmail=request.getParameter("customerEmail");
		long customerContactNumber=Long.parseLong(request.getParameter("customerContactNumber"));
		String password=geek_Password(10); 
		System.out.println(password);
		Customer customer=new Customer(password,customerName, customerAge, customerGender, customerLocation, customerEmail, customerContactNumber);
		System.out.println(customer);
		HotelService hotelService=new HotelService();
		int id = hotelService.customerRegistration(customer);
		if(id>0)
		{
			System.out.println("Successful");
			out.print(id);
			
		}
		else{
			System.out.println("Failed to Regsiter");
		}
		
		
	}

}
