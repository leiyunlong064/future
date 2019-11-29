CREATE SEQUENCE T_ROLE_ID_SEQ INCREMENT BY 1 MINVALUE 1000 NO MAXVALUE START WITH 1000;
CREATE TABLE T_ROLE
(
    ROLE_ID     BIGINT      NOT NULL DEFAULT NEXTVAL('T_ROLE_ID_SEQ') PRIMARY KEY,
    ROLE_NAME   VARCHAR(64) NOT NULL,
    ROLE_TYPE   VARCHAR(64) NOT NULL,
    STATUS      VARCHAR(64) NOT NULL,
    CREATE_TIME TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UPDATE_TIME TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE (ROLE_NAME)
);