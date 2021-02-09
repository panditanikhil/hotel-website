package com.dao;
import com.bean.*;
import DBUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class RoomAllotmentDao {
	Connection con=null;
	
	int AllotRoom(int rid){
		con = DB_util.getConnection();
		int flag=0,flag2=0;
		PreparedStatement ps1;
		try {
			RequestaRoom req = new RequestaRoom();
			ps1 = con.prepareStatement("select * from RoomRequest where Request_Id=?");
			ps1.setInt(1, rid);
			ResultSet rs = ps1.executeQuery();
			req.setRoomsRequired(rs.getInt("Rooms_Required"));
			req.setCheckInDate(rs.getString("Check_In_Date"));
			req.setCheckOutDate(rs.getString("Check_Out_Date"));
			req.setRoomType(rs.getString("Room_Type"));
			req.setConditioner(rs.getString("Conditioner"));
			req.setCategory(rs.getString("Category"));
			flag = new RoomAllotmentDao().RoomCanAllot(req);
			
			if(flag==0){
				new AdminDao().rejectRequest(req.getRequestId());
				flag2=1;
			}
			else if(flag==1){
				new RoomAllotmentDao().AllotVacantRoom(req);
				new RoomAllotmentDao().updateStatusAllocated(req.getRequestId());
				flag2=1;
			}
			else if(flag==2){
				new RoomAllotmentDao().AllotOccupiedRoom(req);
				new RoomAllotmentDao().updateStatusAllocated(req.getRequestId());
				flag2=1;
			}
			else if(flag==3){
				int a = new RoomAllotmentDao().AllotVacantRoom(req);
				int roomReq = req.getRoomsRequired();
				req.setRoomsRequired(roomReq-a);
				new RoomAllotmentDao().AllotOccupiedRoom(req);
				new RoomAllotmentDao().updateStatusAllocated(req.getRequestId());
				flag2=1;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		
		return flag;
	}
	
	void updateStatusAllocated(int rid){
		con = DB_util.getConnection();
		try{
		PreparedStatement ps2 = con.prepareStatement("update RoomRequest set Status=? where Request_Id=?");
		ps2.setString(1, "Allocated");
		ps2.setInt(2, rid);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	int AllotVacantRoom(RequestaRoom req){
		int n=0;
		con = DB_util.getConnection();
		try{
		PreparedStatement ps2 = con.prepareStatement("select * from Room where Status =? and Room_Type=? and Conditioner=? and Category=? LIMIT ?");
		ps2.setString(1, "Vacant");
		ps2.setString(2,req.getRoomType());
		ps2.setString(3,req.getConditioner());
		ps2.setString(4,req.getCategory());
		ps2.setInt(5,req.getRoomsRequired());
		ResultSet rs2 = ps2.executeQuery();
		while(rs2.next()){
			PreparedStatement ps3 = con.prepareStatement("insert into PreBooking values(?,?,?,?)");
			ps3.setInt(1,req.getRequestId());
			ps3.setInt(2, rs2.getInt("Room_Number"));
			ps3.setString(3,req.getCheckInDate());
			ps3.setString(4, req.getCheckOutDate());
			n++;
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return n;
	}
	
	int AllotOccupiedRoom(RequestaRoom req){
//		int n=0;
		int b=0;
		con = DB_util.getConnection();
		try{
		PreparedStatement ps3 = con.prepareStatement("select Room_Number from Room where Room_Type=? and Conditioner=? and Category=?");
		ps3.setString(1,req.getRoomType());
		ps3.setString(2,req.getConditioner());
		ps3.setString(3,req.getCategory());
		ResultSet rs3 = ps3.executeQuery();
		while(rs3.next()) {
			int c=0;
	
			PreparedStatement ps5 = con.prepareStatement("select count(*) from PreBooking where Room_Number=?");
			ps5.setInt(1, rs3.getInt("Room_Number"));
			ResultSet rs5 = ps5.executeQuery();
			int count = rs5.getInt(1)>0?rs5.getInt(1):-1;
	
			PreparedStatement ps4 = con.prepareStatement("select * from PreBooking where Room_Number=?");
			ps4.setInt(1, rs3.getInt("Room_Number"));
			ResultSet rs4 = ps4.executeQuery();
//			System.out.println("RNo:"+rs3.getInt("Room_Number"));
			while(rs4.next()) {
//				System.out.println(" Rid:"+rs4.getInt("Request_Id"));
				String cin = rs4.getString("Check_In_Date");
				String cout = rs4.getString("Check_Out_Date");
//				System.out.println(cin+" "+cout);
				if((dateDiff(Date.valueOf(cin),Date.valueOf(req.getCheckInDate()))>1 ) || (dateDiff(Date.valueOf(req.getCheckOutDate()),Date.valueOf(cout))>1))
					c++;
//				count++;
//				System.out.println("c:"+c+" count:"+count);
			}
			if(count==c) {
				PreparedStatement ps2 = con.prepareStatement("insert into Prebooking values(?,?,?,?)");
				ps2.setInt(1,req.getRequestId());
				ps2.setInt(2, rs3.getInt("Room_Number"));
				ps2.setString(3,req.getCheckInDate());
				ps2.setString(4, req.getCheckOutDate());
				b++;
			}
//			System.out.println("b:"+b+"count:"+count);
		}
		}catch(Exception e){
			System.out.println(e);
		}
		return b;
	}
	
	int RoomCanAllot(RequestaRoom req) {
		int flag=0,a=0,b=0;
		a = new RoomAllotmentDao().check1(req);
		b = new RoomAllotmentDao().check2(req);
		if(req.getRequestId()<=a)
			flag=1;
		else if(req.getRequestId()<=b)
			flag=2;
		else if(req.getRequestId()<=a+b)
			flag=3;
		return flag;
	}
	
	public int check1(RequestaRoom req){
		int a=0;
		con = DB_util.getConnection();
		PreparedStatement ps1;
		try {
			PreparedStatement ps2 = con.prepareStatement("select count(*) from Room where Status =? and Room_Type=? and Conditioner=? and Category=?");
			ps2.setString(1, "Vacant");
			ps2.setString(2,req.getRoomType());
			ps2.setString(3,req.getConditioner());
			ps2.setString(4,req.getCategory());
			ResultSet rs2 = ps2.executeQuery();
			a = rs2.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public int check2(RequestaRoom req){
		con = DB_util.getConnection();
		int b=0;
		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement("select * from RoomRequest where Request_Id=?");
			ps1.setInt(1,req.getRequestId());
			ResultSet rs = ps1.executeQuery();
			int roomReq = rs.getInt("Rooms_Required");
			
			PreparedStatement ps3 = con.prepareStatement("select Room_Number from Room where Room_Type=? and Conditioner=? and Category=?");
			ps3.setString(2,req.getRoomType());
			ps3.setString(3,req.getConditioner());
			ps3.setString(4,req.getCategory());
			ResultSet rs3 = ps3.executeQuery();
			while(rs3.next()) {
				int c=0;
		
				PreparedStatement ps5 = con.prepareStatement("select count(*) from PreBooking where Room_Number=?");
				ps5.setInt(1, rs3.getInt("Room_Number"));
				ResultSet rs5 = ps5.executeQuery();
				int count = rs5.getInt(1)>0?rs5.getInt(1):-1;
		
				PreparedStatement ps4 = con.prepareStatement("select * from PreBooking where Room_Number=?");
				ps4.setInt(1, rs3.getInt("Room_Number"));
				ResultSet rs4 = ps4.executeQuery();
//				System.out.println("RNo:"+rs3.getInt("Room_Number"));
				while(rs4.next()) {
//					System.out.println(" Rid:"+rs4.getInt("Request_Id"));
					String cin = rs4.getString("Check_In_Date");
					String cout = rs4.getString("Check_Out_Date");
//					System.out.println(cin+" "+cout);
					if((dateDiff(Date.valueOf(cin),Date.valueOf(req.getCheckInDate()))>1 ) || (dateDiff(Date.valueOf(req.getCheckOutDate()),Date.valueOf(cout))>1))
						c++;
//					count++;
//					System.out.println("c:"+c+" count:"+count);
				}
				if(count==c) 
					b++;
//				System.out.println("b:"+b+"count:"+count);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	private static long dateDiff(Date one, Date two) {
//		System.out.println("one:"+one+" tw0:"+two);
        long difference =  (one.getTime()-two.getTime())/86400000;
//        System.out.println("diff:"+Math.abs(difference));
        return Math.abs(difference);
    }
	
	public static void main(String args[]) throws SQLException {
//			System.out.println("Ans:"+new RoomAllotmentDao().RoomCanAllot(5));
		System.out.println(dateDiff(Date.valueOf("2018-01-02"),Date.valueOf("2018-01-01")));
	}
	
}
