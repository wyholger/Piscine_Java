INSERT INTO chat.users(login, password) VALUES ('Bob', 'bob_pass');
INSERT INTO chat.users(login, password) VALUES ('Carl', 'carl_pass');
INSERT INTO chat.users(login, password) VALUES ('Susan', 'susan_pass');
INSERT INTO chat.users(login, password) VALUES ('Amely', 'amely_pass');
INSERT INTO chat.users(login, password) VALUES ('Viktor', 'viktor_pass');
INSERT INTO chat.users(login, password) VALUES ('Sasha', 'sasha_pass');
INSERT INTO chat.users(login, password) VALUES ('Andrey', 'andrey_pass');
INSERT INTO chat.users(login, password) VALUES ('Georgiy', 'georgiy_pass');
INSERT INTO chat.users(login, password) VALUES ('Vi', 'vi_pass');
INSERT INTO chat.users(login, password) VALUES ('Nikita', 'nikita_pass');
INSERT INTO chat.users(login, password) VALUES ('Valeriy', 'valeriy_pass');
INSERT INTO chat.users(login, password) VALUES ('Slava', 'slava_pass');
INSERT INTO chat.users(login, password) VALUES ('Inna', 'inna_pass');
INSERT INTO chat.users(login, password) VALUES ('Kay', 'kay_pass');
INSERT INTO chat.users(login, password) VALUES ('Dan', 'dan_pass');
INSERT INTO chat.users(login, password) VALUES ('Masha', 'masha_pass');
INSERT INTO chat.users(login, password) VALUES ('Igor', 'igor_pass');

INSERT INTO chat.rooms(name, owner) VALUES ('Work_chat', 1);
INSERT INTO chat.rooms(name, owner) VALUES ('Home_chat', 2);
INSERT INTO chat.rooms(name, owner) VALUES ('Fun_chat', 3);
INSERT INTO chat.rooms(name, owner) VALUES ('School_chat', 4);
INSERT INTO chat.rooms(name, owner) VALUES ('Friends_chat', 5);

INSERT INTO chat.messages(author, room, text, timestamp) VALUES (1, 1, 'Hello', '1997-01-13 00:00:01');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (2, 1, 'Hi', '1997-01-13 00:00:21');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (1, 1, '<Work question>?', '1997-01-13 00:01:21');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (2, 1, '<Work answer>!', '1997-01-13 00:02:21');

INSERT INTO chat.messages(author, room, text, timestamp) VALUES (2, 2, 'Hay', '1997-01-13 00:10:01');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (3, 2, 'Hay!', '1997-01-13 00:10:21');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (2, 2, '<Home question>?', '1997-01-13 00:11:21');
INSERT INTO chat.messages(author, room, text, timestamp) VALUES (3, 2, '<Home answer>!', '1997-01-13 00:12:21');

SELECT * FROM chat.users WHERE id=2;



