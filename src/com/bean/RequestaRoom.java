package com.bean;

import java.sql.Date;
public class RequestaRoom {

private int RequestId;
private int CustomerId;
private String CustomerName;
String CheckInDate;
String CheckOutDate;
int RoomsRequired;
String RoomType;
String Conditioner;
String Category;
int AdvancePayment;
String Status;
public RequestaRoom() {
	super();
}
public RequestaRoom(int requestId, int customerId, String customerName,
		String checkInDate, String checkOutDate, int roomsRequired,
		String roomType, String conditioner, String category,
		int advancePayment, String status) {
	super();
	RequestId = requestId;
	CustomerId = customerId;
	CustomerName = customerName;
	CheckInDate = checkInDate;
	CheckOutDate = checkOutDate;
	RoomsRequired = roomsRequired;
	RoomType = roomType;
	Conditioner = conditioner;
	Category = category;
	AdvancePayment = advancePayment;
	Status = status;
}
public int getRequestId() {
	return RequestId;
}
public void setRequestId(int requestId) {
	RequestId = requestId;
}
public int getCustomerId() {
	return CustomerId;
}
public void setCustomerId(int customerId) {
	CustomerId = customerId;
}
public String getCustomerName() {
	return CustomerName;
}
public void setCustomerName(String customerName) {
	CustomerName = customerName;
}
public String getCheckInDate() {
	return CheckInDate;
}
public void setCheckInDate(String checkInDate) {
	CheckInDate = checkInDate;
}
public String getCheckOutDate() {
	return CheckOutDate;
}
public void setCheckOutDate(String checkOutDate) {
	CheckOutDate = checkOutDate;
}
public int getRoomsRequired() {
	return RoomsRequired;
}
public void setRoomsRequired(int roomsRequired) {
	RoomsRequired = roomsRequired;
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
public int getAdvancePayment() {
	return AdvancePayment;
}
public void setAdvancePayment(int advancePayment) {
	AdvancePayment = advancePayment;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
@Override
public String toString() {
	return "RequestaRoom [RequestId=" + RequestId + ", CustomerId="
			+ CustomerId + ", CustomerName=" + CustomerName + ", CheckInDate="
			+ CheckInDate + ", CheckOutDate=" + CheckOutDate
			+ ", RoomsRequired=" + RoomsRequired + ", RoomType=" + RoomType
			+ ", Conditioner=" + Conditioner + ", Category=" + Category
			+ ", AdvancePayment=" + AdvancePayment + ", Status=" + Status + "]";
}


}

