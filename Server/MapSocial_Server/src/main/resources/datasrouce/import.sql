INSERT INTO user (id, username, password, nick) VALUES (1, 'ganyue', 'ganyue', 'ganyue');
INSERT INTO user (id, username, password, nick) VALUES (2, 'samgan', 'samgan', 'samgan');

INSERT INTO authority (id, name) VALUES (10, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 10);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
