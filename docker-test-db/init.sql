CREATE TABLE MEMBER(
    USER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(255),
    PASSWORD VARCHAR(255),
    ROLE VARCHAR(255)
);

CREATE TABLE IP_ADDR (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    IP_ADDR VARCHAR(255)
);