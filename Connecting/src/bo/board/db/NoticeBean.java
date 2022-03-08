package bo.board.db;

public class NoticeBean {

	private int notice_id;
	private String title;
	private String content;
	private String write_date;

	public int getNotice_id() {
		return notice_id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

}
