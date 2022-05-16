package my.mypage.db;

public class Board {
	private int		board_id;
	private String  user_id;
	private String	title;	
	private String 	board_img;
	private int		category;		
	private int		loc;		
	private String	host;		
	private String	address;	
	private String	start_time;
	private String	end_time;
	private String	start_date;	
	private String	end_date;	
	private String 	write_date;
	private String 	content;
	private int 	heart_count;
	
	
	public int getBoard_id() {
		return board_id;
	}
	
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBoard_img() {
		return board_img;
	}
	
	public void setBoard_img(String board_img) {
		this.board_img = board_img;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getLoc() {
		return loc;
	}
	
	public void setLoc(int loc) {
		this.loc = loc;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getStart_time() {
		return start_time;
	}
	
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getStart_date() {
		return start_date;
	}
	
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	public String getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	public String getWrite_date() {
		return write_date;
	}
	
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getHeart_count() {
		return heart_count;
	}
	
	public void setHeart_count(int heart_count) {
		this.heart_count = heart_count;
	}
	
}
