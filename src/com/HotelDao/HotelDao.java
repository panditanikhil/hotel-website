package com.HotelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.HotelUtil.DB_util;
import com.bean.Customer;
import com.bean.RequestaRoom;

public class HotelDao {

	Connection con=null;
	PreparedStatement pst=null;
	PreparedStatement pst1=null;
	PreparedStatement pst2=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	public int customerRegistration(Customer c) {
		
		int i=0;
		int id=-1;
		try
		{
			con=DB_util.getConnection();
			pst=con.prepareStatement("insert into CustomerRegistration (Password,Customer_Name,Age,Gender,Location,Email,Contact) values(?,?,?,?,?,?,?)");
			pst.setString(1,c.getPassword());
			pst.setString(2,c.getCustomerName());
			pst.setInt(3,c.getCustomerAge());
			pst.setString(4,c.getCustomerGender());
			pst.setString(5,c.getCustomerLocation());
			pst.setString(6,c.getCustomerEmail());
			pst.setLong(7,c.getCustomerContactNumber());
			i=pst.executeUpdate();	
			pst2=con.prepareStatement("select max(Customer_Id) from CustomerRegistration");
			rs1 =pst2.executeQuery();
			while(rs1.next())
			{
				id=rs1.getInt(1);
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}

	public String checklogin(int customerId, String password) {
		
		return null;
	}

	public int requestRoom(RequestaRoom requestRoom) {
		
		return 0;
	}

}
