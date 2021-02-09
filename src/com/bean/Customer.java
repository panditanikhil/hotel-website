package com.bean;

public class Customer {
	int customerId;
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [password=" + password + ", customerName="
				+ customerName + ", customerAge=" + customerAge
				+ ", customerGender=" + customerGender + ", customerLocation="
				+ customerLocation + ", customerEmail=" + customerEmail
				+ ", customerContactNumber=" + customerContactNumber + "]";
	}
	String password;
String customerName;
int customerAge;
String customerGender;
String customerLocation;
String customerEmail;
long customerContactNumber;

public Customer(String password,String customerName, int customerAge, String customerGender,
		String customerLocation, String customerEmail,
		long customerContactNumber) {
	super();
	
	this.password = password;
	this.customerName = customerName;
	this.customerAge = customerAge;
	this.customerGender = customerGender;
	this.customerLocation = customerLocation;
	this.customerEmail = customerEmail;
	this.customerContactNumber = customerContactNumber;
}

public Customer() {
	super();
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public int getCustomerAge() {
	return customerAge;
}
public void setCustomerAge(int customerAge) {
	this.customerAge = customerAge;
}
public String getCustomerGender() {
	return customerGender;
}
public void setCustomerGender(String customerGender) {
	this.customerGender = customerGender;
}
public String getCustomerLocation() {
	return customerLocation;
}
public void setCustomerLocation(String customerLocation) {
	this.customerLocation = customerLocation;
}
public String getCustomerEmail() {
	return customerEmail;
}
public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}
public long getCustomerContactNumber() {
	return customerContactNumber;
}
public void setCustomerContactNumber(long customerContactNumber) {
	this.customerContactNumber = customerContactNumber;
}

}
