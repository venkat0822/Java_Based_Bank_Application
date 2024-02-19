package com.venkat.bank.data.bank;

import java.util.Date;

public class HelpSupport {
	private String complaint;
    private String userName;
    private Date date;
    private Boolean resolved=false;
    private String adminUserName;
    private Date dateResolved;
  
	public HelpSupport(String complaint, String userName, Date date, String adminUserName) {
		
		this.complaint = complaint;
		this.userName = userName;
		this.adminUserName = adminUserName;
		this.date=date;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	public Date getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(Date dateResolved) {
		this.dateResolved = dateResolved;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
    
    
	
}