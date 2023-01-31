insert into "CONFERENCE" ( CONFERENCE_ID, BEGIN_DATE ,DESCRIPTION , END_DATE ,MAX_SEATS ,TITLE )values (1, timestamp'2023-07-23 02:00:00','test', timestamp'2023-07-23 02:00:00', 200, 'test')
insert into "PARTICIPANT" ( PARTICIPANT_ID, EMAIL, FULL_NAME, PHONE_NUMBER)  values ( 2,'test@tes','test name','234567')
insert into "CONFERENCE_PARTICIPANT" (CONFERENCE_ID, PARTICIPANT_ID) values (1, 2)