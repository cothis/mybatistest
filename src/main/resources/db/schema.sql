DROP TABLE IF EXISTS MEMBER;

CREATE TABLE MEMBER
(
    id        BIGINT AUTO_INCREMENT,
    name      VARCHAR(50),
    nick_name VARCHAR(50),
    amount    NUMERIC(19, 4),
    reg_date  DATETIME DEFAULT NOW(),
    PRIMARY KEY (id)
);