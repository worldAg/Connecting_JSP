DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER (

	ID VARCHAR2(20) PRIMARY KEY,
	PASSWORD VARCHAR2(20) NOT NULL,
	EMAIL VARCHAR2(20) NOT NULL,
	NAME VARCHAR2(10) NOT NULL,
	REG_DATE DATE DEFAULT SYSDATE,
	PROFILE_IMG VARCHAR2(20) 
);

insert into member (id, password, email,name,profile_img) 
values('hh','1234','hhyy@naver.com','hy',null);

select * from MEMBER;


CREATE TABLE HEART ( -- 사용자가 하트 버튼 클릭한 게시물을 모아주는 테이블 --
	ID VARCHAR2(20),
	HEART VARCHAR2(30), -- 하트 버튼 누른 게시물의 BOARD_ID (컴마로 구분) 100,200,300 --
	FOREIGN KEY(ID) REFERENCES MEMBER(ID)
);


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

insert into board (board_id,category,loc,id,title,
start_date,end_date) 
values(3,1,0,'hh','2월박람회','2022-02-01','2022-02-31');

select * from board;


CREATE TABLE NOTICE (
	NOTICE_ID NUMBER(20) PRIMARY KEY,
	TITLE VARCHAR2(50),
	CONTENT VARCHAR2(399),
	WRITE_DATE DATE DEFAULT SYSDATE
);




CREATE TABLE BOARD_HEART (
	BOARD_ID NUMBER,
	HEART_NUM NUMBER
);
