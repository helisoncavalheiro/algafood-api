insert into cozinha(nome) values ('Tailandesa');
insert into cozinha(nome) values ('Indiana');

insert into restaurante(nome, taxa_frete, cozinha_id) values("Thai Gourmet", 9.5, 2);
insert into restaurante(nome, taxa_frete, cozinha_id) values("Thai Delivery", 10, 2);
insert into restaurante(nome, taxa_frete, cozinha_id) values("Tuk Tuk Delivery", 10, 1);

insert into estado(nome) values ("Paraná");
insert into estado(nome) values ("Santa Catarina");
insert into estado(nome) values ("Rio Grande do Sul");

insert into cidade(nome, estado_id) values ("Curitiba", 1);
insert into cidade(nome, estado_id) values ("Almirante Tamandaré", 1);
insert into cidade(nome, estado_id) values ("Florianópolis", 2);
insert into cidade(nome, estado_id) values ("Itapema", 2);
insert into cidade(nome, estado_id) values ("Porto Alegre", 3);
insert into cidade(nome, estado_id) values ("Bagé", 3);

insert into forma_pagamento(descricao) values ("Dinheiro");
insert into forma_pagamento(descricao) values ("Cartão de Débito");
insert into forma_pagamento(descricao) values ("Cartão de Crédito");
insert into forma_pagamento(descricao) values ("PIX");

insert into permissao(nome, descricao) values ("cliente", "Cliente do restaurante");
insert into permissao(nome, descricao) values ("atendente", "Atendente do restaurante");
insert into permissao(nome, descricao) values ("dono", "Dono do restaurante");