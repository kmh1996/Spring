-- test table 생성 - spring
CREATE TABLE IF NOT EXISTS tbl_member(
	userid VARCHAR(50),
    userpw VARCHAR(45),
    username VARCHAR(45),
    regdate TIMESTAMP,
    updatedate TIMESTAMP
);
DESC tbl_member;
commit;
SELECT * FROM tbl_member;

DESC tbl_board;
CREATE TABLE tbl_board(
	bno INT PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content LONGTEXT NULL,
    writer VARCHAR(50) NOT NULL,
    regdate TIMESTAMP NOT NULL DEFAULT now(),
    viewcnt INT DEFAULT 0
);

commit;

SELECT * FROM tbl_board;

INSERT INTO tbl_board(title,content,writer) 
SELECT title,content,writer FROM tbl_board;

commit;

SELECT * FROM tbl_board ORDER BY bno DESC limit 1;

CREATE TABLE tbl_user(
	uno INT PRIMARY KEY auto_increment,		-- 사용자 번호
    uid VARCHAR(50) NOT NULL UNIQUE,		-- 아이디
    upw VARCHAR(200) NOT NULL,				-- 비밀번호
    uname VARCHAR(50) NOT NULL,				-- 이름
    upoint INT NOT NULL DEFAULT 0			-- 포인트
);

CREATE TABLE tbl_message(
	mno INT PRIMARY KEY auto_increment,			-- 메세지 번호
    targetid VARCHAR(50) NOT NULL,				-- 수신자 아이디
    sender VARCHAR(50) NOT NULL,				-- 발신자 아이디
    message TEXT NOT NULL,						-- 메세지
    opendate TIMESTAMP NULL,					-- 수신확인 시간
    senddate TIMESTAMP NOT NULL default now(),  -- 발신 시간
    FOREIGN KEY(targetid) REFERENCES tbl_user(uid),
    FOREIGN KEY(sender) REFERENCES tbl_user(uid)
);

INSERT INTO tbl_user(uid,upw,uname) 
VALUES('id001','pw001','IRON MAN');

INSERT INTO tbl_user(uid,upw,uname) 
VALUES('id002','pw002','THOR');

INSERT INTO tbl_user(uid,upw,uname) 
VALUES('id003','pw003','DR.strange');

SELECT * FROM tbl_user;

ALTER TABLE smartWeb.tbl_message 
CHANGE openddate opendate TIMESTAMP NULL;

DESC tbl_message;
commit;
SELECT * FROM tbl_user;
SELECT * FROM tbl_message ORDER BY mno DESC;

CREATE TABLE ban_ip(
	ip VARCHAR(50) PRIMARY KEY,
    cnt INT DEFAULT 1,
    bandate TIMESTAMP DEFAULT now()
);

DESC ban_ip;

commit;

SELECT * FROM ban_ip;

-- 답변형 게시판 table
CREATE TABLE re_tbl_board(
	bno INT PRIMARY KEY auto_increment,
    title VARCHAR(200) NOT NULL,
    content LONGTEXT NOT NULL,
    origin INT NOT NULL default 0,
    depth INT NOT NULL default 0,
    seq INT NOT NULL default 0,
    regdate TIMESTAMP NULL DEFAULT now(),
    updatedate TIMESTAMP NULL DEFAULT now(),
    viewcnt INT NULL DEFAULT 0,
    showboard char(1) NULL DEFAULT 'Y',
    uno INT NOT NULL,
    CONSTRAINT fk_re_tbl_uno FOREIGN KEY(uno) 
    REFERENCES tbl_user(uno)
);

SELECT * FROM tbl_user WHERE uno = 1;
INSERT INTO re_tbl_board(bno,title,content,origin,uno) 
VALUES(1,'첫번째 게시글 제목','첫번째 게시글 내용',1, 1);

commit;

SELECT * FROM re_tbl_board;

SELECT B.*, U.uname AS writer 
FROM re_tbl_board AS B NATURAL JOIN tbl_user AS U 
ORDER BY B.bno DESC limit 0, 10;

SELECT B.*, U.uname AS writer 
FROM re_tbl_board AS B NATURAL JOIN tbl_user AS U 
WHERE B.bno = 2;


commit;

SELECT B.*, U.uname AS writer 
FROM re_tbl_board AS B NATURAL JOIN tbl_user AS U 
ORDER BY B.origin DESC , B.seq ASC limit 0, 10;

commit;

SELECT * FROM tbl_user;

-- 첨부파일 목록 저장 table
CREATE TABLE tbl_attach(
	fullName VARCHAR(300) NOT NULL,
    bno INT NOT NULL,
    regdate TIMESTAMP NULL DEFAULT now(),
    constraint fk_tbl_attach 
    FOREIGN KEY(bno) REFERENCES re_tbl_board(bno)
);

DESC tbl_attach;

commit;

SELECT * FROM tbl_attach;
commit;

SELECT DATE_FORMAT(regdate,'%Y-%m-%d') FROM tbl_attach;

SELECT * FROM tbl_attach;
SELECT DATE_FORMAT(
	DATE_ADD(now(), interval -1 day)
, '%Y-%m-%d');


SELECT fullName FROM tbl_attach 
WHERE regdate <= DATE_ADD(now(), interval -1 day);

SELECT fullName FROM tbl_attach
WHERE
DATE_FORMAT(regdate, '%Y-%m-%d') 
=
DATE_FORMAT(DATE_ADD(now(), interval -1 day), '%Y-%m-%d'); 

SELECT DATE_FORMAT(DATE_SUB(now(),interval 1 day),'/%Y/%m/%d/');

SELECT * FROM re_tbl_board ORDER BY bno DESC;

INSERT INTO tbl_attach
VALUE(
'/2022/02/10/2448885dcc8a4e11a420e8c53dce2d4c_googleAppPass.txt',
39,
DATE_SUB(now(),interval 1 day)); 

commit;

DELETE FROM tbl_attach WHERE bno = 39;

-- 답변형 게시판 댓글 comment
CREATE TABLE re_tbl_comment(
	cno INT PRIMARY KEY auto_increment,    -- 댓글 번호
    bno INT NOT NULL,					   -- 게시글 번호	
	commentText TEXT NOT NULL,			   -- 댓글 내용	
    regdate TIMESTAMP NOT NULL default now(), -- 작성 시간
    updatedate TIMESTAMP NOT NULL default now(), -- 수정시간
    uno INT NOT NULL default 1,				-- 댓글 작성자
    CONSTRAINT fk_re_tbl_bno FOREIGN KEY(bno) 
    REFERENCES re_tbl_board(bno),
    CONSTRAINT fk_re_tbl_comment_uno FOREIGN KEY(uno) 
    REFERENCES tbl_user(uno)
);

INSERT INTO re_tbl_comment(bno,commentText,uno) 
VALUES(2,'2번게시글 첫번째 댓글 1등',2);

commit;

SELECT * FROM re_tbl_comment;

SELECT C.*, U.uname AS commentAuth FROM 
re_tbl_comment AS C 
NATURAL JOIN 
tbl_user AS U 
WHERE bno = 2 ORDER BY cno DESC LIMIT 0, 10;

commit;

SELECT * FROM tbl_attach WHERE bno = 44;
SELECT * FROM re_tbl_comment WHERE bno = 44;




































