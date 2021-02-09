package com.HotelService;

import com.HotelDao.HotelDao;
import com.HotelUtil.JsonUtil;
import com.bean.Customer;
import com.bean.RequestaRoom;

public class HotelService {
	public int customerRegistration(Customer customer)
	{
		HotelDao hotelDao=new HotelDao();
		return hotelDao.customerRegistration(customer);
		
	}

	public String checkLogin(int customerId, String password) {
		
		HotelDao hotelDao=new HotelDao();
		String jsonCustomerDetails=JsonUtil.convertJavaToJson(hotelDao.checklogin(customerId,password));
		return jsonCustomerDetails;
		
	}

	public String requestRoom(RequestaRoom requestRoom) {
	
		HotelDao hotelDao=new HotelDao();
		String requestId=JsonUtil.convertJavaToJson(hotelDao.requestRoom(requestRoom));
		return requestId;
	}

}
