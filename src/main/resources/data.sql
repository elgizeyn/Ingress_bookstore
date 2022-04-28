
INSERT INTO people (person_id, name, password, email, roles)
VALUES
(1, 'Bertelsmann', '1234', 'bert@gmail.com', 'PUBLISHER'),
(2, 'HarperCollins', '5678', 'harper@gmail.com', 'PUBLISHER'),
(3, 'User', '4321', 'user@gmail.com', 'USER');



INSERT INTO books (book_id, person_id, author_name, book_name, price)
VALUES
(1, 1, 'Leo Tolstoy', 'Anna Karenina', 50.0),
(2, 1, 'Dostoevsky', 'Crime and Punishment', 150.0),
(3, 2, 'Karl Marx', 'Das Kapital', 200.0);