insert into cozinha(nome) values ('Tailandesa');
insert into cozinha(nome) values ('Indiana');

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

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (1, 1, 'Essa sopa que tem como base os noodles de arroz, tofu, carne de porco e ovos cozidos é um dos muitos legados da presença chinesa na Tailândia. ', 'Guay Jub', 39.99, 1);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (2, 1, 'Essa é uma das saladas mais encontradas na Tailândia, servida, em geral, como acompanhamento dos pratos principais. ', 'Som tam – Salada de mamão', 41.99, 2);
insert into produto (id, ativo, descricao, nome, preco, restaurante_id) values (3, 1, 'Nessa receita, o frango é temperado com sal, garam masala ou curry, pimenta chili, açafrão e gengibre.', 'Frango butter chicken', 49.99, 3);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);