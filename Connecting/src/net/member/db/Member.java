package net.member.db;
//MemberBean
public class Member {
	private String ID ;
	private String PASSWORD;
	private String NAME;
	private String EMAIL;
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}
}
