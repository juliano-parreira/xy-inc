-- MySQL Script
-- Tue Jan 20 15:43:37 2015
-- Model: ZUP XY-INC DB    Version: 1.0


-- -----------------------------------------------------
-- Usuário de criação de estruturas do BD
-- -----------------------------------------------------
-- DROP USER 'xyinco'@'localhost';
CREATE USER 'xyinco'@'localhost' IDENTIFIED BY 'xyinc#527@EPI';
GRANT
	ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE VIEW, DROP, EVENT, INDEX, REFERENCES, TRIGGER , SHOW VIEW
	ON xyincdb.* TO 'xyinco'@'localhost';

-- -----------------------------------------------------
-- Usuáiro de conexão com o BD -- USADO PELO WEBSERVICE
-- -----------------------------------------------------
-- DROP USER 'xyincc'@'localhost';
CREATE USER 'xyincc'@'localhost' IDENTIFIED BY 'xyinc#731$EPI';
GRANT
	SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW 
	ON xyincdb.* TO 'xyincc'@'localhost';

FLUSH PRIVILEGES;

COMMIT;

