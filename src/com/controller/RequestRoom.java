package com.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HotelService.HotelService;
import com.bean.RequestaRoom;

/**
 * Servlet implementation class RequestRoom
 */
public class RequestRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int CustomerId=Integer.parseInt(request.getParameter("customerId"));
		String CustomerName=request.getParameter("customerName");
		Date CheckInDate=Date.valueOf(request.getParameter("CheckInDate"));
		Date CheckOutDate=Date.valueOf(request.getParameter("CheckOutDate"));
		int RoomsRequired=Integer.parseInt(request.getParameter("RoomsRequired"));
		String RoomType=request.getParameter("RoomType");
		String Type=request.getParameter("Type");
		String Category=request.getParameter("Category");
		RequestaRoom requestRoom=new RequestaRoom(CustomerId,CustomerName,CheckInDate,CheckOutDate,RoomsRequired,RoomType,Type,Category);
		HotelService hotelService=new HotelService();
		String requestId=hotelService.requestRoom(requestRoom);
		 response.setContentType("application/json");
		 response.getWriter().write(requestId);
		
	}

}
