package com.dao;
import com.bean.*;
import DBUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

	Connection con=null;
	PreparedStatement pst=null;
	PreparedStatement pst1=null;
	PreparedStatement pst2=null;
	PreparedStatement pst3=null;
	PreparedStatement pst4=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs3=null;
	ResultSet rs4=null;
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

	public int checklogin(int customerId, String password) {
		int i=0;
		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("select Customer_Id,Password from CustomerRegistration where Customer_Id=?");
			pst.setInt(1, customerId);
			
			rs=pst.executeQuery();
			while(rs.next())
			{
				if(rs.getString(2).equals(password))
				{
					i=1;
				}				
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return i;
	}

	public int requestRoom(RequestaRoom requestRoom) {
		
		int i=0;
		int id=-1;
		try
		{
			con=DB_util.getConnection();
			pst=con.prepareStatement("insert into RoomRequest (Customer_Id,Cust_Name,Check_In_Date,Check_Out_Date,Rooms_Required,Room_Type,Conditioner,Category,Advance_Payment,Status) values(?,?,?,?,?,?,?,?,?,?)" );
			
			pst.setInt(1, requestRoom.getCustomerId());
			pst.setString(2, requestRoom.getCustomerName());
			pst.setString(3, requestRoom.getCheckInDate());
			pst.setString(4, requestRoom.getCheckOutDate());
			pst.setInt(5, requestRoom.getRoomsRequired());
			pst.setString(6, requestRoom.getRoomType());
			pst.setString(7, requestRoom.getConditioner());
			pst.setString(8, requestRoom.getCategory());
			pst.setInt(9, requestRoom.getAdvancePayment());
			pst.setString(10, requestRoom.getStatus());
			i=pst.executeUpdate();
			if(i>0)
			{
				pst2=con.prepareStatement("select max(Request_Id) from RoomRequest");
				rs1 =pst2.executeQuery();
				while(rs1.next())
				{
					id=rs1.getInt(1);
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return id;
	}
	
	public int AddBankDetails(BankDetails bank)
	{
		int i=0;
		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("insert into BankDetails values(?,?,?,?,?,?)");
			pst.setInt(1, bank.getCID());
			pst.setLong(2, bank.getAccountNo());
			pst.setString(3, bank.getBName());
			pst.setString(4, bank.getIFSC());
			pst.setString(5, bank.getAccountType());
			pst.setString(6, bank.getAccountHolderName());
			i=pst.executeUpdate();
						
		}
		
	catch (SQLException e) {
			e.printStackTrace();
		}			
		
		return i;
	}
	public int UpdateBankDetails(BankDetails bank){
		int i=0;
		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("update BankDetails set Account_Number=?,Bank_Name=?,Ifsc_Code=?,Account_Type=?,Account_Holder_Name=? where Customer_Id=?");
			pst.setLong(1, bank.getAccountNo());
			pst.setString(2, bank.getBName());
			pst.setString(3, bank.getIFSC());
			pst.setString(4, bank.getAccountType());
			pst.setString(5, bank.getAccountHolderName());
			pst.setInt(6, bank.getCID());
			i=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}			
		return i;
	}
	
	public RequestaRoom ViewRoomRequest(int requestId)
	{
		RequestaRoom requestaRoom=null;

		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("select * from RoomRequest where Request_Id=?");
			rs=pst.executeQuery();
			requestaRoom=new RequestaRoom(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11));

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return requestaRoom;
	}
	
	public Room ViewRoom(int requestId)
	{
		Room room=null;

		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("select * from Room where Request_Id=?");
			rs=pst.executeQuery();
			room=new Room(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7), rs.getString(8));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}
	
	public int ModifyRoomRequest(RequestaRoom requestRoom,int requestId)
	{
		int i=0,id=-1;
		try{
				con=DB_util.getConnection();
				pst=con.prepareStatement("update RoomRequest set Customer_id=?,Cust_Name=?,Check_In_Date=?,Check_Out_Date=?,Rooms_Required=?,Room_Type=?,Conditioner=?,Category=?,Advance_Payment=?,Status=? where Request_Id=?");
				pst.setInt(1, requestRoom.getCustomerId());
				pst.setString(2, requestRoom.getCustomerName());
				pst.setString(3, requestRoom.getCheckInDate());
				pst.setString(4, requestRoom.getCheckOutDate());
				pst.setInt(5, requestRoom.getRoomsRequired());
				pst.setString(6, requestRoom.getRoomType());
				pst.setString(7, requestRoom.getConditioner());
				pst.setString(8, requestRoom.getCategory());
				pst.setInt(9, requestRoom.getAdvancePayment());
				pst.setString(10, requestRoom.getStatus());
				pst.setInt(11, requestId);
				i=pst.executeUpdate();
				/*if(i>0)
				{
					pst2=con.prepareStatement("select max(Request_Id) from RoomRequest");
					rs1 =pst2.executeQuery();
					while(rs1.next())
					{
						id=rs1.getInt(1);
					}
				}*/
			}
		catch (SQLException e) 
			{
				e.printStackTrace();
			}

		return i;
	}
	public int updateAdvancePayment(int amount,int requestId)
	{
		try{
			con=DB_util.getConnection();
			pst=con.prepareStatement("update RoomRequest set Advance_Payment=? where Request_Id=?");
			pst.setInt(1, amount);
			pst.setInt(2, requestId);
		}
		catch (SQLException e) 
			{
			e.printStackTrace();
			}

	return 1;
	
		
		
	}
	
	public int cancelRequestforNonAdvance(int rid){
		int rej=-1,a=0,b=0;
		try
		{
			con=DB_util.getConnection();
			pst1=con.prepareStatement("select Status from RoomRequest where Request_Id=?");
			pst1.setInt(1,rid);
			rs1=pst1.executeQuery();
			while(rs1.next()){
				if(rs1.getString(1).equals("Allocated")){
					pst2=con.prepareStatement("delete from PreBooking where Request_Id=?");
					pst2.setInt(1,rid);
					b=pst2.executeUpdate();
				}
				else{
					pst2=con.prepareStatement("update RoomRequest set Status=? where Request_Id=?");
					pst2.setString(1,"Cancelled");
					pst2.setInt(2,rid);
					a=pst2.executeUpdate();
				}
			}
			if(a>0 || b>0){
				rej=1;
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return rej;
	}

	public int cancelRequestforAdvance(int rid){
		int rejadv=-1,a=0,b=0;
		try
		{
			con=DB_util.getConnection();
			pst1=con.prepareStatement("select Status from RequestaRoom where Request_Id=?");
			pst1.setInt(1,rid);
			rs1=pst1.executeQuery();
			while(rs1.next()){
				if(rs1.getString(1).equals("Allocated")){
					pst2=con.prepareStatement("delete from PreBooking where Request_Id=?");
					pst2.setInt(1,rid);
					b=pst2.executeUpdate();
				}
				pst2=con.prepareStatement("update RoomRequest set Status=? where Request_Id=?");
				pst2.setString(1,"Cancelled");
				pst2.setInt(2,rid);
				a=pst2.executeUpdate();
					
				pst3=con.prepareStatement("select * from Payment where Request_Id=?");
				pst3.setInt(1,rid);
				rs3=pst3.executeQuery();
				while(rs3.next()){
					pst4=con.prepareStatement("Insert into Refund (Payment_Id,Refund_Amount) values(?,?)");
					pst4.setInt(1,rs3.getInt("Payment_Id"));
					pst4.setDouble(2, 0.9*rs3.getDouble("Advance_Amount"));
					b=pst4.executeUpdate();
				}
			}
			if(a>0 && b>0){
				rejadv=1;
			}   
		}catch(Exception e){
			System.out.println(e);
		}
		return rejadv;
	}
	
public int cancelRequest(int rid){
		    int reject=0;
		    try
		    {
		        con=DB_util.getConnection();
		        pst1=con.prepareStatement("select Advance_Payment,Status from RoomRequest where Request_Id=?");
		        pst1.setInt(1,rid);
		        rs1=pst1.executeQuery();
		        if(rs1.getInt(1)>0){
		            reject=new CustomerDao().cancelRequestforAdvance(rid); 
		        }
		        else{
		            reject=new CustomerDao().cancelRequestforNonAdvance(rid); 
		        }

		    }
		    catch(Exception e){
		        System.out.println(e);
		    }
		    return reject;
}
	
public int GeneratePaymentId(int rId,long accno,double amount)
	{	int i=0,id=0;
		

		try{
				con=DB_util.getConnection();
				pst=con.prepareStatement("insert into Payment values(?,?,?)");
				pst.setInt(1, rId);
				pst.setLong(2, accno);
				pst.setDouble(3, amount);
				i=pst.executeUpdate();
				if(i>0)
				{
					pst2=con.prepareStatement("select max(Payment_Id) from Payment");
					rs1 =pst2.executeQuery();
					while(rs1.next())
					{
						id=rs1.getInt(1);
					}
				}

			}
		catch (SQLException e) 
			{
				e.printStackTrace();
			}

		return id;
		
	}
	
	public static void main(String args[]){
		Customer cust = new Customer("xys","Shail",23,"Male","Allahabad","shailv07@gmai.com",888565544);
		Customer cust1 = new Customer("xys","Ravi",23,"Male","Allahabad","shailv07@gmai.com",888565544);
		Customer cust2 = new Customer("xys","Rajni",23,"Male","Allahabad","shailv07@gmai.com",888565544);
		Customer cust3 = new Customer("xys","Mohit",23,"Male","Allahabad","shailv07@gmai.com",888565544);
		System.out.println(new CustomerDao().customerRegistration(cust));
		System.out.println(new CustomerDao().customerRegistration(cust1));
		System.out.println(new CustomerDao().customerRegistration(cust2));
		System.out.println(new CustomerDao().customerRegistration(cust3));
		
	}


}
