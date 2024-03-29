create table estado (
    id BIGINT NOT NULL auto_increment,
    nome VARCHAR(80) NOT NULL,
    primary key (id)
) engine = InnoDB default charset = utf8;
INSERT into estado (nome)
select distinct nome_estado
from cidade;
ALTER TABLE cidade
ADD COLUMN estado_id BIGINT NOT NULL;
UPDATE cidade c
SET c.estado_id = (
        SELECT e.id
        FROM estado e
        WHERE e.nome = c.nome_estado
    );
ALTER TABLE cidade
ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado (id);
ALTER TABLE cidade DROP COLUMN nome_estado;
ALTER TABLE cidade CHANGE nome_cidade nome VARCHAR(80) NOT NULL;