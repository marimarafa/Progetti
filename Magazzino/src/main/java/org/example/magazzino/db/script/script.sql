-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema magazzino
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema magazzino
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS magazzino DEFAULT CHARACTER SET utf8mb3 ;
USE magazzino ;

-- -----------------------------------------------------
-- Table magazzino.categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.categoria (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   nome VARCHAR(45) NOT NULL,
                                                   PRIMARY KEY (id))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.cliente (
                                                 id INT NOT NULL AUTO_INCREMENT,
                                                 nome VARCHAR(45) NOT NULL,
                                                 cognome VARCHAR(45) NOT NULL,
                                                 indirizzo VARCHAR(45) NULL DEFAULT NULL,
                                                 codice_fiscale VARCHAR(45) NOT NULL,
                                                 email VARCHAR(45) NULL DEFAULT NULL,
                                                 telefono VARCHAR(45) NULL DEFAULT NULL,
                                                 numero_carta VARCHAR(45) NOT NULL,
                                                 partita_iva VARCHAR(45) NULL DEFAULT NULL,
                                                 PRIMARY KEY (id),
                                                 UNIQUE INDEX codice_fiscale_UNIQUE (codice_fiscale ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.sottocategoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.sottocategoria (
                                                        id INT NOT NULL AUTO_INCREMENT,
                                                        nome VARCHAR(45) NOT NULL,
                                                        categoria_id INT NOT NULL,
                                                        PRIMARY KEY (id),
                                                        INDEX fk_sottoCategoria_categoria1_idx (categoria_id ASC) VISIBLE,
                                                        CONSTRAINT fk_sottoCategoria_categoria1
                                                            FOREIGN KEY (categoria_id)
                                                                REFERENCES magazzino.categoria (id)
                                                                ON DELETE CASCADE
                                                                ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.unitamisura
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.unitamisura (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     descrizione VARCHAR(255) NOT NULL,
                                                     PRIMARY KEY (id))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.prodotto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.prodotto (
                                                  id INT NOT NULL AUTO_INCREMENT,
                                                  nome VARCHAR(45) NOT NULL,
                                                  prezzo DECIMAL(10,2) NOT NULL,
                                                  descrizione VARCHAR(255) NOT NULL,
                                                  quantita DECIMAL(10,2) NOT NULL,
                                                  disponibilita TINYINT NOT NULL DEFAULT '1',
                                                  UnitaMisura_id INT NOT NULL,
                                                  sottoCategoria_id INT NOT NULL,
                                                  PRIMARY KEY (id),
                                                  INDEX fk_Prodotto_UnitaMisura_idx (UnitaMisura_id ASC) VISIBLE,
                                                  INDEX fk_prodotto_sottoCategoria1_idx (sottoCategoria_id ASC) VISIBLE,
                                                  CONSTRAINT fk_prodotto_sottoCategoria1
                                                      FOREIGN KEY (sottoCategoria_id)
                                                          REFERENCES magazzino.sottocategoria (id)
                                                          ON DELETE CASCADE
                                                          ON UPDATE CASCADE,
                                                  CONSTRAINT fk_Prodotto_UnitaMisura
                                                      FOREIGN KEY (UnitaMisura_id)
                                                          REFERENCES magazzino.unitamisura (id)
                                                          ON DELETE CASCADE
                                                          ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.ordine
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.ordine (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                quantita INT NOT NULL,
                                                data_ora TIMESTAMP NOT NULL,
                                                prezzo_totale DECIMAL(10,2) NOT NULL,
                                                sospeso TINYINT NOT NULL,
                                                Cliente_id INT NOT NULL,
                                                Prodotto_id INT NOT NULL,
                                                PRIMARY KEY (id),
                                                INDEX fk_Ordine_Cliente1_idx (Cliente_id ASC) VISIBLE,
                                                INDEX fk_Ordine_Prodotto1_idx (Prodotto_id ASC) VISIBLE,
                                                CONSTRAINT fk_Ordine_Cliente1
                                                    FOREIGN KEY (Cliente_id)
                                                        REFERENCES magazzino.cliente (id)
                                                        ON DELETE CASCADE
                                                        ON UPDATE CASCADE,
                                                CONSTRAINT fk_Ordine_Prodotto1
                                                    FOREIGN KEY (Prodotto_id)
                                                        REFERENCES magazzino.prodotto (id)
                                                        ON DELETE CASCADE
                                                        ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table magazzino.movimento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS magazzino.movimento (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   tipo VARCHAR(45) NOT NULL,
                                                   data_ora TIMESTAMP NOT NULL,
                                                   descrizione VARCHAR(45) NOT NULL,
                                                   ordine_id INT NOT NULL,
                                                   PRIMARY KEY (id),
                                                   INDEX fk_movimento_ordine1_idx (ordine_id ASC) VISIBLE,
                                                   CONSTRAINT fk_movimento_ordine1
                                                       FOREIGN KEY (ordine_id)
                                                           REFERENCES magazzino.ordine (id)
                                                           ON DELETE CASCADE
                                                           ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;