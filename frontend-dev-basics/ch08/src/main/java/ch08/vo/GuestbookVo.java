package ch08.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String content;
	private String password;
	private String regDate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", content=" + content + ", password=" + password
				+ ", regDate=" + regDate + ", getNo()=" + getNo() + ", getName()=" + getName() + ", getcontent()="
				+ getContent() + ", getPassword()=" + getPassword() + ", getRegDate()=" + getRegDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
