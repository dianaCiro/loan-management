DROP TABLE IF EXISTS loan;
CREATE TABLE loan(
    id VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    date TIMESTAMP NOT NULL,
    rate DOUBLE NOT NULL,
    term INTEGER NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payment;
CREATE TABLE payment(
    id BIGINT AUTO_INCREMENT NOT NULL,
    amount DOUBLE NOT NULL,
    date TIMESTAMP NOT NULL,
    payment_type VARCHAR(255) NOT NULL,
    loan_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE payment ADD CONSTRAINT IF NOT EXISTS fk_loan_id FOREIGN KEY(loan_id) REFERENCES loan(id) NOCHECK;

INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a1', 2000.0, TIMESTAMP '2017-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a2', 1000.0, TIMESTAMP '2018-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a3', 1000.0, TIMESTAMP '2019-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a4', 1000.0, TIMESTAMP '2020-01-01 14:02:00.0', 0.05, 12);
INSERT INTO loan(id, amount, date, rate, term) VALUES('90e4dd94-e042-4d55-8473-7f8f27ca82a5', 1000.0, TIMESTAMP '2021-01-01 14:02:00.0', 0.05, 12);


INSERT INTO payment(id, amount, date, payment_type, loan_id) VALUES
(1, 100.0, TIMESTAMP '2017-07-05 02:18:00.0', 'made', '90e4dd94-e042-4d55-8473-7f8f27ca82a1');
INSERT INTO payment(id, amount, date, payment_type, loan_id) VALUES
(2, 100.0, TIMESTAMP '2017-08-05 02:18:00.0', 'made', '90e4dd94-e042-4d55-8473-7f8f27ca82a1');
INSERT INTO payment(id, amount, date, payment_type, loan_id) VALUES
(3, 100.0, TIMESTAMP '2017-09-05 02:18:00.0', 'made', '90e4dd94-e042-4d55-8473-7f8f27ca82a1');
INSERT INTO payment(id, amount, date, payment_type, loan_id) VALUES
(4, 100.0, TIMESTAMP '2017-10-05 02:18:00.0', 'missed', '90e4dd94-e042-4d55-8473-7f8f27ca82a1');