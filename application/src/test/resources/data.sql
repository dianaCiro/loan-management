CREATE TABLE IF NOT EXISTS loan(
    id VARCHAR(255) NOT NULL,
    amount DOUBLE,
    date TIMESTAMP,
    rate DOUBLE,
    term INTEGER
);

INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a1', 1000.0, TIMESTAMP '2017-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a2', 1000.0, TIMESTAMP '2018-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a3', 1000.0, TIMESTAMP '2019-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a4', 1000.0, TIMESTAMP '2020-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a5', 1000.0, TIMESTAMP '2021-01-01 14:02:00.0', 0.05, 12);