CREATE TABLE BOARD (
	BOARD_ID     NUMBER PRIMARY KEY, -- MAX값 가져오기, 만약 NULL이면 0번부터 시작 --
	CATEGORY     NUMBER(5) CHECK(CATEGORY IN (0, 1, 2, 3)), --0:전시회, 1:박람회, 2:버스킹, 3:연극/공연 -- 
	LOC          NUMBER(5) CHECK(LOC IN (0, 1, 2, 3, 4)), --0:서울, 1:경기/인천, 2:대전/충청/강원, 3:부산/대구/경상 4:광주/전라/제주 --
	ID           VARCHAR2(20), --MEMBER테이블의 작성자 ID--
	TITLE        VARCHAR2(50),
	HOST_NAME    VARCHAR2(10), -- 주최자의 이름 --
	ADDRESS      VARCHAR2(30), -- 상세주소 --
	START_DATE   VARCHAR2(20),
	END_DATE     VARCHAR2(20),
	START_TIME   VARCHAR2(20),
	END_TIME     VARCHAR2(20),
	WRITE_DATE   DATE DEFAULT SYSDATE,
	CONTENT      VARCHAR2(399),
	BOARD_IMG    VARCHAR2(30),
	FOREIGN KEY(ID) REFERENCES MEMBER(ID) ON DELETE CASCADE -- 회원 탈퇴 시 작성 글도 날아감 --
);