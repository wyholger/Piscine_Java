INSERT INTO chat.users(login, password) VALUES ('Bob', 'bob_pass');
INSERT INTO chat.users(login, password) VALUES ('Carl', 'carl_pass');
INSERT INTO chat.users(login, password) VALUES ('Susan', 'susan_pass');
INSERT INTO chat.users(login, password) VALUES ('Amely', 'amely_pass');
INSERT INTO chat.users(login, password) VALUES ('Viktor', 'viktor_pass');

INSERT INTO chat.rooms(name, owner) VALUES ('Work_chat', 1);
INSERT INTO chat.rooms(name, owner) VALUES ('Home_chat', 2);
INSERT INTO chat.rooms(name, owner) VALUES ('Fun_chat', 3);
INSERT INTO chat.rooms(name, owner) VALUES ('School_chat', 4);
INSERT INTO chat.rooms(name, owner) VALUES ('Friends_chat', 5);

INSERT INTO chat.massages(author, room, text, timestamp) VALUES (1, 1, 'Hello', '1997-01-13 00:00:01');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (2, 1, 'Hi', '1997-01-13 00:00:21');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (1, 1, '<Work question>?', '1997-01-13 00:01:21');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (2, 1, '<Work answer>!', '1997-01-13 00:02:21');

INSERT INTO chat.massages(author, room, text, timestamp) VALUES (2, 2, 'Hay', '1997-01-13 00:10:01');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (3, 2, 'Hay!', '1997-01-13 00:10:21');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (2, 2, '<Home question>?', '1997-01-13 00:11:21');
INSERT INTO chat.massages(author, room, text, timestamp) VALUES (3, 2, '<Home answer>!', '1997-01-13 00:12:21');




