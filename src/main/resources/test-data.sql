INSERT INTO account(ACCOUNTID, NUMBER, TYPE ,BALANCE,DATECREATION, ISACTIVE) VALUES (1, '01000251215', 'SAVING' ,4210.42,'2003-01-22 08:39:40', true);
INSERT INTO account(ACCOUNTID, NUMBER, TYPE ,BALANCE,DATECREATION, ISACTIVE) VALUES (2, '01000251216', 'CURRENT' ,25.12,'2003-01-22 08:39:40',false);


INSERT INTO transaction(ID, NUMBER , BALANCE, ACCOUNTID) VALUES (1, '12151885120', 42.12 , 1);
INSERT INTO transaction(ID, NUMBER , BALANCE, ACCOUNTID) VALUES (2, '12151885121', 456.00 , 1);
INSERT INTO transaction(ID, NUMBER , BALANCE, ACCOUNTID) VALUES (3, '12151885122', -12.12 , 1);
INSERT INTO transaction(ID, NUMBER , BALANCE, ACCOUNTID) VALUES (4, '12151885123', -12.12 , 1)