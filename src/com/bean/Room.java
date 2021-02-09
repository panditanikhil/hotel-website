package com.bean;

public class Room {
	
	private int RoomNo;
	private int FloorNo;
	private String RoomType;
	private String Conditioner;
	private String Category;
	private double Price;
	private int ReqId;
	private String Status;
	public Room() {
		super();
	}
	@Override
	public String toString() {
		return "Room [RoomNo=" + RoomNo + ", FloorNo=" + FloorNo
				+ ", RoomType=" + RoomType + ", Conditioner=" + Conditioner
				+ ", Category=" + Category + ", Price=" + Price + ", ReqId="
				+ ReqId + ", Status=" + Status + "]";
	}
	public int getRoomNo() {
		return RoomNo;
	}
	public void setRoomNo(int roomNo) {
		RoomNo = roomNo;
	}
	public int getFloorNo() {
		return FloorNo;
	}
	public void setFloorNo(int floorNo) {
		FloorNo = floorNo;
	}
	public String getRoomType() {
		return RoomType;
	}
	public void setRoomType(String roomType) {
		RoomType = roomType;
	}
	public String getConditioner() {
		return Conditioner;
	}
	public void setConditioner(String conditioner) {
		Conditioner = conditioner;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public int getReqId() {
		return ReqId;
	}
	public void setReqId(int reqId) {
		ReqId = reqId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Room(int roomNo, int floorNo, String roomType, String conditioner,
			String category, double price, int reqId, String status) {
		super();
		RoomNo = roomNo;
		FloorNo = floorNo;
		RoomType = roomType;
		Conditioner = conditioner;
		Category = category;
		Price = price;
		ReqId = reqId;
		Status = status;
	}
}