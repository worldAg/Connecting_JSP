package bo.board.db;

public class BoardBean {

	private int board_id;
	private int category;
	private int loc;

	private String id;
	private String title;
	private String host_name;
	private String address;
	private String start_date;
	private String end_date;
	private String start_time;
	private String end_time;

	private String write_date;
	private String content;
	private String board_img;
	
	private int heart_num; // 하트수

	public int getBoard_id() {
		return board_id;
	}

	public int getCategory() {
		return category;
	}

	public int getLoc() {
		return loc;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getHost_name() {
		return host_name;
	}

	public String getAddress() {
		return address;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public String getWrite_date() {
		return write_date;
	}

	public String getContent() {
		return content;
	}

	public String getBoard_img() {
		return board_img;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBoard_img(String board_img) {
		this.board_img = board_img;
	}

	public int getHeart_num() {
		return heart_num;
	}

	public void setHeart_num(int heart_num) {
		this.heart_num = heart_num;
	}

}
