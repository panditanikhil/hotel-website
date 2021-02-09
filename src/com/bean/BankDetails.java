package com.bean;



public class BankDetails {
	
	private int CID;
	private long AccountNo;
	private String BName;
	private String IFSC;
	private String AccountType;
	private String AccountHolderName;
	public BankDetails() {
		super();
	}
	public BankDetails(int cID, long accountNo, String bName, String iFSC,
			String accountType, String accountHolderName) {
		super();
		CID = cID;
		AccountNo = accountNo;
		BName = bName;
		IFSC = iFSC;
		AccountType = accountType;
		AccountHolderName = accountHolderName;
	}
	public int getCID() {
		return CID;
	}
	public void setCID(int cID) {
		CID = cID;
	}
	public long getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(long accountNo) {
		AccountNo = accountNo;
	}
	public String getBName() {
		return BName;
	}
	public void setBName(String bName) {
		BName = bName;
	}
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getAccountHolderName() {
		return AccountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}
	@Override
	public String toString() {
		return "BankDetails [CID=" + CID + ", AccountNo=" + AccountNo
				+ ", BName=" + BName + ", IFSC=" + IFSC + ", AccountType="
				+ AccountType + ", AccountHolderName=" + AccountHolderName
				+ "]";
	}
	
	
	

}
