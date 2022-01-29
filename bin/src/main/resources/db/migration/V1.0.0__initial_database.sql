CREATE TABLE restaurante (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    taxa_frete DECIMAL(15,2) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    status TINYINT NOT NULL,
    res_idf INT NOT NULL,
    CONSTRAINT `fk_usuario_restaurante`
        FOREIGN KEY (`res_idf`)
        REFERENCES `restaurante` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `delivery`.`restaurante` (`id`, `nome`, `taxa_frete`, `uuid`) VALUES ('1', 'Restaurante do Adriano', '10', '00b8fd02-eba0-48ed-8806-8d88176f9c42');

INSERT INTO `delivery`.`usuario` (`id`, `email`, `nome`, `password`, `status`, `username`, `uuid`, `res_idf`) VALUES ('1', 'adriano@gmail.com', 'Adriano', '$2a$12$QRrkslCMml.aGFqJjE0tp.uijS30hHIF6RMdOCL7oHxpvIYstxRDK', 1, 'adriano', '7e9779f6-15da-4457-b7ee-e026329cb605', 1);
