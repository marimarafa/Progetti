-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MAGAZZINO
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS MAGAZZINO DEFAULT CHARACTER SET utf8mb3 ;
USE MAGAZZINO ;

-- -----------------------------------------------------
-- Table MAGAZZINO.CATEGORIA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CATEGORIA (
                                         ID INT NOT NULL AUTO_INCREMENT,
                                         NOME VARCHAR(45) NOT NULL,
                                         PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.CLIENTE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CLIENTE (
                                       ID INT NOT NULL AUTO_INCREMENT,
                                       NOME VARCHAR(45) NOT NULL,
                                       COGNOME VARCHAR(45) NOT NULL,
                                       INDIRIZZO VARCHAR(45) NULL DEFAULT NULL,
                                       CODICE_FISCALE VARCHAR(45) NOT NULL,
                                       EMAIL VARCHAR(45) NULL DEFAULT NULL,
                                       TELEFONO VARCHAR(45) NULL DEFAULT NULL,
                                       NUMERO_CARTA VARCHAR(45) NOT NULL,
                                       PARTITA_IVA VARCHAR(45) NULL DEFAULT NULL,
                                       PRIMARY KEY (ID),
                                       UNIQUE INDEX CODICE_FISCALE_UNIQUE (CODICE_FISCALE ASC) VISIBLE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.SOTTOCATEGORIA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SOTTOCATEGORIA (
                                              ID INT NOT NULL AUTO_INCREMENT,
                                              NOME VARCHAR(45) NOT NULL,
                                              CATEGORIA_ID INT NOT NULL,
                                              PRIMARY KEY (ID),
                                              INDEX IDX_SOTTOCATEGORIA_CATEGORIA_ID (CATEGORIA_ID ASC),
                                              CONSTRAINT FK_SOTTOCATEGORIA_CATEGORIA
                                                  FOREIGN KEY (CATEGORIA_ID)
                                                      REFERENCES CATEGORIA (ID)
                                                      ON DELETE CASCADE
                                                      ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.UNITAMISURA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS UNITAMISURA (
                                           ID INT NOT NULL AUTO_INCREMENT,
                                           DESCRIZIONE VARCHAR(255) NOT NULL,
                                           PRIMARY KEY (ID)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.PRODOTTO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS PRODOTTO (
                                        ID INT NOT NULL AUTO_INCREMENT,
                                        NOME VARCHAR(45) NOT NULL,
                                        PREZZO DECIMAL(10,2) NOT NULL,
                                        DESCRIZIONE VARCHAR(255) NOT NULL,
                                        QUANTITA DECIMAL(10,2) NOT NULL,
                                        DISPONIBILITA TINYINT NOT NULL DEFAULT '1',
                                        UNITAMISURA_ID INT NOT NULL,
                                        SOTTOCATEGORIA_ID INT NOT NULL,
                                        PRIMARY KEY (ID),
                                        INDEX IDX_PRODOTTO_UNITAMISURA_ID (UNITAMISURA_ID ASC),
                                        INDEX IDX_PRODOTTO_SOTTOCATEGORIA_ID (SOTTOCATEGORIA_ID ASC),
                                        CONSTRAINT FK_PRODOTTO_UNITAMISURA
                                            FOREIGN KEY (UNITAMISURA_ID)
                                                REFERENCES UNITAMISURA (ID)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE,
                                        CONSTRAINT FK_PRODOTTO_SOTTOCATEGORIA
                                            FOREIGN KEY (SOTTOCATEGORIA_ID)
                                                REFERENCES SOTTOCATEGORIA (ID)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.ORDINE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ORDINE (
                                      ID INT NOT NULL AUTO_INCREMENT,
                                      QUANTITA INT NOT NULL,
                                      DATA_ORA TIMESTAMP NOT NULL,
                                      PREZZO_TOTALE DECIMAL(10,2) NOT NULL,
                                      SOSPESO TINYINT NOT NULL,
                                      CLIENTE_ID INT NOT NULL,
                                      PRIMARY KEY (ID),
                                      INDEX IDX_ORDINE_CLIENTE_ID (CLIENTE_ID ASC),
                                      CONSTRAINT FK_ORDINE_CLIENTE
                                          FOREIGN KEY (CLIENTE_ID)
                                              REFERENCES CLIENTE (ID)
                                              ON DELETE CASCADE
                                              ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.ORDINEREFPRODOTTO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ORDINEREFPRODOTTO (
                                                 ORDINE_ID INT NOT NULL,
                                                 PRODOTTO_ID INT NOT NULL,
                                                 PRIMARY KEY (ORDINE_ID, PRODOTTO_ID),
                                                 CONSTRAINT FK_ORDINEREFPRODOTTO_ORDINE
                                                     FOREIGN KEY (ORDINE_ID)
                                                         REFERENCES ORDINE (ID)
                                                         ON DELETE CASCADE
                                                         ON UPDATE CASCADE,
                                                 CONSTRAINT FK_ORDINEREFPRODOTTO_PRODOTTO
                                                     FOREIGN KEY (PRODOTTO_ID)
                                                         REFERENCES PRODOTTO (ID)
                                                         ON DELETE CASCADE
                                                         ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table MAGAZZINO.MOVIMENTO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS MOVIMENTO (
                                         ID INT NOT NULL AUTO_INCREMENT,
                                         TIPO VARCHAR(45) NOT NULL,
                                         DATA_ORA TIMESTAMP NOT NULL,
                                         DESCRIZIONE VARCHAR(45) NOT NULL,
                                         ORDINE_ID INT NOT NULL,
                                         PRIMARY KEY (ID),
                                         INDEX IDX_MOVIMENTO_ORDINE_ID (ORDINE_ID ASC),
                                         CONSTRAINT FK_MOVIMENTO_ORDINE
                                             FOREIGN KEY (ORDINE_ID)
                                                 REFERENCES ORDINE (ID)
                                                 ON DELETE CASCADE
                                                 ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Restore settings
-- -----------------------------------------------------
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
