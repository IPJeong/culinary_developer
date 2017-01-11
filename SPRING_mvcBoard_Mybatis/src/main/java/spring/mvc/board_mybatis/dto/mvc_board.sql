CREATE TABLE mvc_board (
  num         NUMBER(5),              -- 글번호
  writer      VARCHAR2(20) NOT NULL,  -- 작성자
  passwd      VARCHAR2(10) NOT NULL,  -- 비밀번호
  subject     VARCHAR2(50) NOT NULL,  -- 글제목
  content     VARCHAR2(500),          -- 글내용
  readCnt     NUMBER(5)   DEFAULT 0,  -- 조회수
  ref         NUMBER(5)   DEFAULT 0,  -- 그룹
  ref_step    NUMBER(5)   DEFAULT 0,  -- 그룹 스텝
  ref_level   NUMBER(5)   DEFAULT 0,  -- 그룹 레벨
  reg_date    TIMESTAMP   DEFAULT sysdate,  --작성일
  ip          VARCHAR2(15),           -- IP
  CONSTRAINT mvc_board_num_pk PRIMARY KEY(num)
);

DESC mvc_board;

DROP TABLE mvc_board;

DROP SEQUENCE board_seq;
CREATE SEQUENCE board_seq
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 99999;

INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '장동건', 1234, '첫글', '게시판 게시', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');

INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '고소영', 1234, '첫글', '게시판 게시', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');
               
INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '원빈', 1234, '첫글', '게시판 게시', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');

INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '이나영', 1234, '첫글', '게시판 게시', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');
               
INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '전지현', 1234, '첫글', '게시판 게시', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');               
               
COMMIT; 

SELECT * FROM mvc_board;

CREATE TABLE product (
  pid VARCHAR2(10),
  pname VARCHAR2(20) CONSTRAINT product_pname_nn NOT NULL,
  pbrand VARCHAR2(20) CONSTRAINT product_pbrand_nn NOT NULL,
  pprice NUMBER(10) CONSTRAINT procuct_pprice_nn NOT NULL,
  pindate DATE DEFAULT sysdate,
  CONSTRAINT product_pid_pk PRIMARY KEY(pid)
);

SELECT * FROM product;

INSERT INTO product(pid, pname, pbrand, pprice, pindate)
VALUES('p006', '자동차', '체어맨2', 1000000, sysdate);

UPDATE product
   SET pname= '', pbrand= '', pprice= '', pindate= ''
 WHERE pid= '';

INSERT INTO product VALUES('p001', '아이폰7', '애플', 1200000, sysdate);

INSERT INTO product VALUES('p002', '맥북프로', '애플', 2500000, sysdate);

INSERT INTO product VALUES('p003', '모델x', '테슬라', 120000000, sysdate);

INSERT INTO product VALUES('p004', '기블리', '마세라티', 1200000, sysdate);

INSERT INTO product VALUES('p005', '화질짱좋은', '삼성', 2400000, sysdate);
commit;