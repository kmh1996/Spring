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













