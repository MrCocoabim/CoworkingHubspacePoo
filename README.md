# CoworkingHubspacePoo
Projeto prático de software orientado a objetos  - Sobre espaços de coworking compartilhados e suas dificuldades de controlar a disponibilidade horária.

# Coworking HubSpace – Sistema de Gestão de Espaços e Reservas

## Integrantes
* Ryan Pereira Campos
* Pedro gonçalves de oliveira

## Descrição do Problema
Espaços de coworking compartilhados enfrentam dificuldades para controlar a disponibilidade horária de diferentes ambientes (estações de trabalho, salas de reunião e auditórios) e equipamentos avulsos (projetores, kits de videoconferência). O controle manual gera choques de horário, falhas no cálculo de tarifas diferenciadas por tipo de espaço e perda de histórico de locações. O sistema **HubSpace** centraliza o cadastro de clientes, espaços e equipamentos, valida conflitos de agenda e calcula automaticamente o valor total das reservas com persistência em arquivo.

## Requisitos Funcionais
* **RF01 – Cadastro de Espaços:** Cadastrar, listar, atualizar e remover estações de trabalho, salas de reunião e auditórios com código, nome, capacidade e valor por hora.

* **RF02 – Cadastro de Equipamentos:** Gerenciar equipamentos adicionais locáveis (ex.: projetor, monitor) com código, descrição e valor por hora.

* **RF03 – Cadastro de Clientes:** Registrar e consultar clientes (nome, documento e contato) responsáveis pelas reservas.

* **RF04 – Reserva por Hora sem Conflito:** Registrar reservas informando cliente, espaço, equipamentos opcionais, data, hora de início e duração em horas, impedindo sobreposição de horários para o mesmo espaço ou equipamento.

* **RF05 – Cálculo Polimórfico de Tarifas:** Calcular o valor total da reserva somando a tarifa específica do tipo de espaço (incluindo taxas próprias, como limpeza em auditórios) e os equipamentos locados.

* **RF06 – Busca de Espaços:** Permitir buscar espaços tanto pelo código numérico quanto por parte do nome (ignorando maiúsculas e minúsculas).

* **RF07 – Relatório Ordenado:** Gerar listagem ordenada de espaços por valor/hora e de reservas por data/horário.

* **RF08 – Persistência de Dados:** Salvar e carregar automaticamente os registros em arquivos texto/CSV ao encerrar e iniciar o sistema.
