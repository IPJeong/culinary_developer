CREATE TABLE mvc_board (
  num         NUMBER(5),              -- �۹�ȣ
  writer      VARCHAR2(20) NOT NULL,  -- �ۼ���
  passwd      VARCHAR2(10) NOT NULL,  -- ��й�ȣ
  subject     VARCHAR2(50) NOT NULL,  -- ������
  content     VARCHAR2(500),          -- �۳���
  readCnt     NUMBER(5)   DEFAULT 0,  -- ��ȸ��
  ref         NUMBER(5)   DEFAULT 0,  -- �׷�
  ref_step    NUMBER(5)   DEFAULT 0,  -- �׷� ����
  ref_level   NUMBER(5)   DEFAULT 0,  -- �׷� ����
  reg_date    TIMESTAMP   DEFAULT sysdate,  --�ۼ���
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
               VALUES(board_seq.nextval, '�嵿��', 1234, 'ù��', '�Խ��� �Խ�', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');

INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '��ҿ�', 1234, 'ù��', '�Խ��� �Խ�', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');
               
INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '����', 1234, 'ù��', '�Խ��� �Խ�', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');

INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '�̳���', 1234, 'ù��', '�Խ��� �Խ�', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');
               
INSERT INTO mvc_board(num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip) 
               VALUES(board_seq.nextval, '������', 1234, 'ù��', '�Խ��� �Խ�', 0, board_seq.currval, 0, 0, sysdate, '127.0.0.1');               
               
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
VALUES('p006', '�ڵ���', 'ü���2', 1000000, sysdate);

UPDATE product
   SET pname= '', pbrand= '', pprice= '', pindate= ''
 WHERE pid= '';

INSERT INTO product VALUES('p001', '������7', '����', 1200000, sysdate);

INSERT INTO product VALUES('p002', '�ƺ�����', '����', 2500000, sysdate);

INSERT INTO product VALUES('p003', '��x', '�׽���', 120000000, sysdate);

INSERT INTO product VALUES('p004', '���', '������Ƽ', 1200000, sysdate);

INSERT INTO product VALUES('p005', 'ȭ��¯����', '�Ｚ', 2400000, sysdate);
commit;