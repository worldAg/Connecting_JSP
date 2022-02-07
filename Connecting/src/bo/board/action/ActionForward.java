package bo.board.action;


/*
 * ActionForward 클래스는 Action 인터페이스에서 명령을 수행하고 결과 값을 가지고 이동할 때 사용되는 클래스
 * 이 클래스는 Redirect 여부 값과 포워딩할 페이지의 위치를 갖고 있습니다.
 * 이 값들은 FrontController에서 ActionForward 클래스 타입으로 반환값을 가져오면,
 * 그 값을 확인하여 해당하는 요청 페이지로 이동합니다. 
 */
public class ActionForward {
	
	private boolean redirect = false;
	private String path = null;
	
	// property redirect의 is 메서드
	public boolean isRedirect() {
		return this.redirect;
	}
	
	// property redirect의 set 메서드
	public void setRedirect(boolean b) {
		this.redirect = b;
	}
	
	// property path의 get 메서드
	public String getPath() {
		return this.path;
	}
	
	// property path의 set 메서드
	public void setPath(String path) {
		this.path = path;
	}
	
}
