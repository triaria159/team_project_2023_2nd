use [kakao]

CREATE TABLE MEMBER (
	MEMBER_ID	VARCHAR(30)	NOT NULL,
	MEMBER_PW	VARCHAR(200)	NOT NULL,
	MEMBER_NM	VARCHAR(30)	NOT NULL,
	MEMBER_PHONE	CHAR(13)	NOT NULL,
	ENROLL_DATE	 timestamp	NOT NULL,
	PROFILE	IMAGE	NULL,
	PROFILE_TXT	VARCHAR(50)	NULL,
	TYPE	TINYINT	NOT NULL	DEFAULT 1,
	STOP	TINYINT	NOT NULL	DEFAULT 1,
	Online	TINYINT	NOT NULL	DEFAULT 0
);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'MEMBER_ID';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원비밀번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'MEMBER_PW';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원이름' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'MEMBER_NM';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원휴대폰번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'MEMBER_PHONE';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원가입일' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'ENROLL_DATE';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'프로필' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'PROFILE';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'소개글' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'PROFILE_TXT';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'분류' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'TYPE';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'정지여부' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'STOP';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'온라인여부' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MEMBER', @level2type=N'COLUMN',@level2name=N'Online';

CREATE TABLE CHAT_ROOM (
	CHAT_ROOM_NO	VARCHAR(10)	NOT NULL,
	CM_ID	VARCHAR(30)	NOT NULL,
	TITLE	VARCHAR(200)	NOT NULL
);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'채팅방번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_ROOM', @level2type=N'COLUMN',@level2name=N'CHAT_ROOM_NO';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_ROOM', @level2type=N'COLUMN',@level2name=N'CM_ID';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'채팅방제목' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_ROOM', @level2type=N'COLUMN',@level2name=N'TITLE';

CREATE TABLE CHAT_MESSAGE (
	CM_NO	VARCHAR(10)	NOT NULL,
	C_R_NO	VARCHAR(10)	NOT NULL,
	MM_ID	VARCHAR(30)	NOT NULL,
	MESSAGE	VARCHAR(4000)	NULL,
	CREATE_DT	TIMESTAMP	NOT NULL,
	Photo	image	NULL,
	FilE_	varbinary(max)	NULL
);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'채팅메세지번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'CM_NO';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'채팅방번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'C_R_NO';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'MM_ID';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'작성한 채팅 메세지' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'MESSAGE';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'메세지 작성 시간' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'CREATE_DT';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'사진' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'Photo';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'파일' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_MESSAGE', @level2type=N'COLUMN',@level2name=N'File_';

CREATE TABLE CHAT_ROOM_JOIN (
	C_ROOM_NO	VARCHAR(10)	NOT NULL,
	MEM_ID	VARCHAR(30)	NOT NULL
);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'채팅방번호' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_ROOM_JOIN', @level2type=N'COLUMN',@level2name=N'C_ROOM_NO';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'CHAT_ROOM_JOIN', @level2type=N'COLUMN',@level2name=N'MEM_ID';

CREATE TABLE FRIEND (
	FREIND_ID	VARCHAR(30)	NOT NULL,
	M_ID	VARCHAR(30)	NOT NULL,
	Plus_Time	DATETIME	NOT NULL default getdate()
);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'친구아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'FRIEND', @level2type=N'COLUMN',@level2name=N'FREIND_ID';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'회원아이디' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'FRIEND', @level2type=N'COLUMN',@level2name=N'M_ID';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'업데이트시간' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'FRIEND', @level2type=N'COLUMN',@level2name=N'Up_Time';

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'친구추가시간' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'FRIEND', @level2type=N'COLUMN',@level2name=N'Plus_Time';

ALTER TABLE MEMBER ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	MEMBER_ID
);

ALTER TABLE CHAT_ROOM ADD CONSTRAINT PK_CHAT_ROOM PRIMARY KEY (
	CHAT_ROOM_NO
);

ALTER TABLE CHAT_MESSAGE ADD CONSTRAINT PK_CHAT_MESSAGE PRIMARY KEY (
	CM_NO
);

ALTER TABLE CHAT_ROOM_JOIN ADD CONSTRAINT PK_CHAT_ROOM_JOIN PRIMARY KEY (
	C_ROOM_NO,
	MEM_ID
);

ALTER TABLE FRIEND ADD CONSTRAINT PK_FRIEND PRIMARY KEY (
	FREIND_ID, M_ID
);

ALTER TABLE CHAT_ROOM_JOIN ADD CONSTRAINT FK_CHAT_ROOM_TO_CHAT_ROOM_JOIN_1 FOREIGN KEY (
	C_ROOM_NO
)
REFERENCES CHAT_ROOM (
	CHAT_ROOM_NO
);

ALTER TABLE CHAT_ROOM_JOIN ADD CONSTRAINT FK_MEMBER_TO_CHAT_ROOM_JOIN_1 FOREIGN KEY (
	MEM_ID
)
REFERENCES MEMBER (
	MEMBER_ID
);
go
ALTER TABLE CHAT_MESSAGE
ADD CONSTRAINT FK_CHAT_MESSAGE_TO_CHAT_ROOM FOREIGN KEY (C_R_NO) REFERENCES CHAT_ROOM(CHAT_ROOM_NO);

ALTER TABLE CHAT_MESSAGE
ADD CONSTRAINT FK_CHAT_MESSAGE_TO_MEMBER FOREIGN KEY (MM_ID) REFERENCES MEMBER(MEMBER_ID);
go
ALTER TABLE CHAT_ROOM_JOIN
ADD CONSTRAINT FK_CHAT_ROOM_JOIN_TO_CHAT_ROOM FOREIGN KEY (C_ROOM_NO) REFERENCES CHAT_ROOM(CHAT_ROOM_NO);

ALTER TABLE CHAT_ROOM_JOIN
ADD CONSTRAINT FK_CHAT_ROOM_JOIN_TO_MEMBER FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEMBER_ID);
go
ALTER TABLE FRIEND
ADD CONSTRAINT FK_FRIEND_TO_MEMBER FOREIGN KEY (M_ID) REFERENCES MEMBER(MEMBER_ID);
go

create sequence increasement
	start with 1
	increment by 1
go
create sequence CHAT_MESS
	start with 1
	increment by 1
go
CREATE TRIGGER TRG_GENERATE_CHAT_ROOM_NO
ON CHAT_ROOM
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @insetedCHAT_ROOM TABLE(CHAT_ROOM_NO varchar(10),CM_ID varchar(30), title varchar(100))
    
	insert into @insetedCHAT_ROOM (CHAT_ROOM_NO, CM_ID, title)
	select chat_room_no,CM_ID, TITLE 
	from inserted;


	DECLARE @CodePrefix VARCHAR(10) = '0000000000';
    DECLARE @NextVal INT = NEXT VALUE FOR increasement;
    DECLARE @Code VARCHAR(10) = RIGHT(@CodePrefix + CAST(@NextVal AS VARCHAR(10)), 10);
	
	update @insetedCHAT_ROOM
	set CHAT_ROOM_NO = @Code
	from @insetedCHAT_ROOM
    
    INSERT INTO CHAT_ROOM (CHAT_ROOM_NO, CM_ID, title)
    SELECT CHAT_ROOM_NO, CM_ID, title    
    FROM @insetedCHAT_ROOM;
END;
go

CREATE TRIGGER TRG_GENERATE_CM_NO
ON CHAT_MESSAGE
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @insetedCHAT_MESSAGE TABLE(CM_NO varchar(10),C_R_NO VARCHAR(10), MM_ID VARCHAR(30), MESSAGE VARCHAR(4000))
    
	insert into @insetedCHAT_MESSAGE (CM_NO,C_R_NO,MM_ID,MESSAGE)
	select  CM_NO,C_R_NO,MM_ID,MESSAGE
	from inserted;

    DECLARE @CodePrefix VARCHAR(10) = '0000000000';
    DECLARE @NextVal INT = NEXT VALUE FOR CHAT_MESS;
    DECLARE @Code VARCHAR(10) = RIGHT(@CodePrefix + CAST(@NextVal AS VARCHAR(10)), 10);
	
	UPDATE @insetedCHAT_MESSAGE
	SET CM_NO = @Code
	FROM @insetedCHAT_MESSAGE
    
    INSERT INTO CHAT_MESSAGE (CM_NO, C_R_NO, MM_ID, MESSAGE)
    SELECT CM_NO,C_R_NO,MM_ID,MESSAGE
    FROM @insetedCHAT_MESSAGE;
END;
GO

ALTER TABLE FRIEND
ALTER COLUMN PLUS_TIME DATETIME NULL 


ALTER TABLE FRIEND
ADD PLUS_TIME DATETIME DEFAULT GETDATE();

GO

ALTER TABLE MEMBER
ADD DEFAULT 'C:\Users\바부팅이\Desktop\다운로드.jpg' FOR PROFILE;
go

insert into member(MEMBER_ID,MEMBER_PW,MEMBER_NM,MEMBER_PHONE,PROFILE_TXT) values('wlals7744','159159','지민','010-2789-8086','test')
insert into member(MEMBER_ID,MEMBER_PW,MEMBER_NM,MEMBER_PHONE,PROFILE_TXT) values('test2','159159','테스트2','010-2789-8852','상메2')
insert into member(MEMBER_ID,MEMBER_PW,MEMBER_NM,MEMBER_PHONE,PROFILE_TXT) values('test3','159','테스트4','010-2759-8852','상메4')
insert into member(MEMBER_ID,MEMBER_PW,MEMBER_NM,MEMBER_PHONE,PROFILE_TXT) values('test4','1234','테스트3','010-2589-8852','상메3')
insert into FRIEND(FREIND_ID, M_ID) values('test2','wlals7744')
insert into FRIEND(FREIND_ID, M_ID) values('test3','wlals7744')
insert into FRIEND(FREIND_ID, M_ID) values('wlals7744','test2')
insert into FRIEND(FREIND_ID, M_ID) values('test3','test2')
insert into CHAT_ROOM(CM_ID,TITLE) values ('wlals7744','test1')
insert into CHAT_ROOM(CM_ID,TITLE) values ('wlals7744','test2')
INSERT INTO CHAT_ROOM_JOIN values('0000000002','test2')
INSERT INTO CHAT_ROOM_JOIN values('0000000002','test3')
INSERT INTO CHAT_ROOM_JOIN values('0000000002','wlals7744')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000002','wlals7744','방1테스트메세지1')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000002','wlals7744','방1테스트메세지2')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000002','test3','방1테스트메세지3')

INSERT INTO CHAT_ROOM_JOIN values('0000000003','test2')
INSERT INTO CHAT_ROOM_JOIN values('0000000003','test4')
INSERT INTO CHAT_ROOM_JOIN values('0000000003','wlals7744')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000003','test2','방2테스트메세지1')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000003','test4','방2테스트메세지2')
insert into CHAT_MESSAGE(C_R_NO,MM_ID,MESSAGE) values('0000000003','test2','방2테스트메세지3')
SELECT *
FROM FRIEND

SELECT *
FROM CHAT_ROOM_JOIN

DELETE FROM CHAT_MESSAGE

DELETE FROM CHAT_ROOM where CHAT_ROOM_NO = '0000000018'
DELETE FROM CHAT_ROOM_JOIN where C_ROOM_NO = '0000000018'

SELECT CR.CHAT_ROOM_NO, CR.TITLE, STRING_AGG(CRJ.MEM_ID, ',') AS JOINED_USERS 
FROM CHAT_ROOM CR LEFT JOIN CHAT_ROOM_JOIN CRJ ON CR.CHAT_ROOM_NO = CRJ.C_ROOM_NO 
GROUP BY CR.CHAT_ROOM_NO, CR.TITLE

SELECT CR.CHAT_ROOM_NO, CR.TITLE, STRING_AGG(CRJ.MEM_ID, ',') AS JOINED_USERS 
FROM CHAT_ROOM CR INNER JOIN CHAT_ROOM_JOIN CRJ ON CR.CHAT_ROOM_NO = CRJ.C_ROOM_NO
WHERE CRJ.MEM_ID = 'wlals7744'
GROUP BY CR.CHAT_ROOM_NO, CR.TITLE

SELECT CHAT_ROOM_NO, TITLE FROM CHAT_ROOM WHERE CM_ID = 'wlals7744'
SELECT * FROM CHAT_ROOM

select *
from CHAT_MESSAGE

SELECT *
FROM CHAT_MESSAGE
where C_R_NO=0000000012

--DELETE from CHAT_ROOM where CHAT_ROOM_NO = '0000000011'