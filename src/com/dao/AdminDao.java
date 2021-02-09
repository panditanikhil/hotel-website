package com.dao;
import com.bean.*;
import DBUtil.*;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class AdminDao {
	Connection con=null;
	PreparedStatement pst=null;
	PreparedStatement pst1=null;
	PreparedStatement pst2=null;
	PreparedStatement pst3=null;
	PreparedStatement pst4=null;
	PreparedStatement pst5=null;
	PreparedStatement pst6=null;
	PreparedStatement pst7=null;
	PreparedStatement pst8=null;
	PreparedStatement pst9=null;
	PreparedStatement pst10=null;
	PreparedStatement pst11=null;
	PreparedStatement pst12=null;
	PreparedStatement pst13=null;
	PreparedStatement pst14=null;
	
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	ResultSet rs3=null;
	ResultSet rs4=null;
	ResultSet rs5=null;
	ResultSet rs6=null;
	ResultSet rs7=null;
	ResultSet rs8=null;
	ResultSet rs9=null;
	ResultSet rs10=null;
	ResultSet rs11=null;

	public ArrayList<RequestaRoom> viewRoomRequestPending(){
		ArrayList<RequestaRoom> list=new ArrayList<RequestaRoom>();
		try
		{
			con=DB_util.getConnection();
			pst3=con.prepareStatement("select * from RoomRequest where status=?");
			pst3.setString(1,"Pending");
			rs3=pst3.executeQuery();
			while(rs3.next()){
				RequestaRoom rr = new RequestaRoom(rs3.getInt(1),rs3.getInt(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getInt(6), rs3.getString(7), rs3.getString(8), rs3.getString(9), rs3.getInt(10), rs3.getString(11));
				list.add(rr);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
			
		return list;	
	}
	public ArrayList<RequestaRoom> viewRoomRequestAlloted(){
		ArrayList<RequestaRoom> list=new ArrayList<RequestaRoom>();
		try
		{
			con=DB_util.getConnection();
			pst3=con.prepareStatement("select * from RoomRequest where status=?");
			pst3.setString(1,"Alloted");
			rs3=pst3.executeQuery();
			while(rs3.next()){
				RequestaRoom rr = new RequestaRoom(rs3.getInt(1),rs3.getInt(2), rs3.getString(3), rs3.getString(4), rs3.getString(5), rs3.getInt(6), rs3.getString(7), rs3.getString(8), rs3.getString(9), rs3.getInt(10), rs3.getString(11));
				list.add(rr);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
			
		return list;	
	}
	public ArrayList<Room> viewRoomsOccupied(){
		ArrayList<Room> roomlist=new ArrayList<Room>();
		try
		{
			con=DB_util.getConnection();
			pst4=con.prepareStatement("select * from Room where status=?");
			pst4.setString(1,"Occupied");
			rs4=pst4.executeQuery();
			while(rs4.next()){
				Room r = new Room(rs4.getInt(1),rs4.getInt(2), rs4.getString(3),rs4.getString(4),rs4.getString(5), rs4.getDouble(6),  rs4.getInt(7),rs4.getString(8));
				roomlist.add(r);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
			
		return roomlist;	
	}
	public ArrayList<Room> viewRoomsVacant(){
		ArrayList<Room> roomlist=new ArrayList<Room>();
		try
		{
			con=DB_util.getConnection();
			pst5=con.prepareStatement("select * from Room where status=?");
			pst5.setString(1,"Vacant");
			rs5=pst5.executeQuery();
			while(rs5.next()){
				Room r = new Room(rs5.getInt(1),rs5.getInt(2), rs5.getString(3),rs5.getString(4),rs5.getString(5),rs5.getDouble(6),  rs5.getInt(7),rs5.getString(8));
				roomlist.add(r);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
			
		return roomlist;	
	}
	public ArrayList<Room> viewRoomsByCid(int cid){
		ArrayList<Room> roomsofCid=new ArrayList<Room>();
		try
		{
			con=DB_util.getConnection();
			pst6=con.prepareStatement("select r.Room_Number,r.Floor_Number,r.Room_Type,r.Conditioner,r.Category,r.PricePerDay,r.Request_Id,r.Status from Room r,RoomRequest rr where rr.Request_Id=r.Request_Id and rr.Customer_Id=?");
			pst6.setInt(1, cid);
			rs6=pst6.executeQuery();
			while(rs6.next()){
				Room r1=new Room(rs6.getInt(1),rs6.getInt(2), rs6.getString(3),rs6.getString(4),rs6.getString(5),rs6.getDouble(6),  rs6.getInt(7),rs6.getString(8));
				roomsofCid.add(r1);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return roomsofCid;
	}
	public ArrayList<RequestaRoom> viewCustomerByRoomno(int rno){
		ArrayList<RequestaRoom> cust=new ArrayList<RequestaRoom>();
		try
		{
			con=DB_util.getConnection();
			pst7=con.prepareStatement("select rr.Request_Id,rr.Customer_Id,rr.Cust_Name,rr.Check_In_Date,rr.Check_Out_Date,rr.Rooms_Required,rr.Room_Type,rr.Conditioner,rr.Category,rr.Advance_Payment,rr.Status from RoomRequest rr,Room r where rr.Request_Id=r.Request_Id and r.Room_Number=?");
			pst7.setInt(1, rno);
			rs7=pst7.executeQuery();
			while(rs7.next()){
				RequestaRoom rr1=new RequestaRoom(rs7.getInt(1),rs7.getInt(2), rs7.getString(3), rs7.getString(4), rs7.getString(5), rs7.getInt(6), rs7.getString(7), rs7.getString(8), rs7.getString(9), rs7.getInt(10), rs7.getString(11));
				cust.add(rr1);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cust;
	}
	public int rejectRequestforNonAdvance(int rid){
		int rej=-1,a=0,b=0;
		try
		{
			con=DB_util.getConnection();
			pst8=con.prepareStatement("select Advance_Payment,Status from RoomRequest where Request_Id=?");
			pst8.setInt(1,rid);
			rs8=pst8.executeQuery();
			while(rs8.next()){
				if(rs8.getInt(1)==0){
					if(rs8.getString(2).equalsIgnoreCase("Allocated")){
						pst10=con.prepareStatement("delete from PreBooking where Request_Id=?");
						pst10.setInt(1,rid);
						b=pst10.executeUpdate();
					}
					else{
						pst9=con.prepareStatement("update RoomRequest set Status=? where Request_Id=?");
						pst9.setString(1,"Rejected");
						pst9.setInt(2,rid);
						a=pst9.executeUpdate();
					}	
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
	
	public int rejectRequestforAdvance(int rid){
		int rejadv=-1,a=0,b=0;
		try
		{
			con=DB_util.getConnection();
			pst11=con.prepareStatement("select Advance_Payment,Status from RoomRequest where Request_Id=?");
			pst11.setInt(1,rid);
			rs9=pst11.executeQuery();
			while(rs9.next()){
				if(rs9.getInt(1)>0 && rs9.getString(2).equalsIgnoreCase("Pending")){
					pst11=con.prepareStatement("update RoomRequest set Status=? where Request_Id=?");
					pst11.setString(1,"Rejected");
					pst11.setInt(2,rid);
					a=pst11.executeUpdate();
					pst13=con.prepareStatement("select * from Payment where Request_Id=?");
					pst13.setInt(1,rid);
					rs10=pst13.executeQuery();
					while(rs10.next()){
						pst14=con.prepareStatement("Insert into Refund (Payment_Id,Refund_Amount) values(?,?)");
						pst14.setInt(1,rs10.getInt("Payment_Id"));
						pst14.setDouble(2, rs10.getDouble("Advance_Amount"));
						b=pst14.executeUpdate();
					}
				}
			}
			if(a>0 && b>0){
				rejadv=1;
			}	
		}
		catch(Exception e){
			System.out.println(e);
		}
		return rejadv;
	}
	public int rejectRequest(int rid){
		int reject=0;
		try
		{
			con=DB_util.getConnection();
			pst12=con.prepareStatement("select Advance_Payment,Status from RoomRequest where Request_Id=?");
			pst12.setInt(1,rid);
			rs11=pst12.executeQuery();
			if(rs11.getInt(1)>0){
				reject=new AdminDao().rejectRequestforAdvance(rid); 
			}
			else{
				reject=new AdminDao().rejectRequestforNonAdvance(rid); 
			}
			
	    }
		catch(Exception e){
			System.out.println(e);
		}
		return reject;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminDao dao= new AdminDao();
		System.out.println(dao.rejectRequestforNonAdvance(4));
	}

}
