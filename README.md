🚀 Microsserviço de Notificações Assíncronas
Este é um microsserviço especializado no processamento e disparo de e-mails, desenvolvido com Java 17 e Spring Boot 3. O projeto foi desenhado seguindo princípios de Arquitetura em Camadas e utiliza Mensageria (RabbitMQ) para garantir o desacoplamento e a escalabilidade do sistema.

📋 Sobre o Projeto
O objetivo deste serviço é atuar como um "trabalhador em segundo plano" (worker). Ele consome pedidos de notificação de uma fila, realiza o envio via protocolo SMTP e mantém um histórico rigoroso de cada tentativa no banco de dados.

Fluxo de Funcionamento (Arquitetura)
O diagrama abaixo ilustra como o dado percorre a aplicação, desde o evento externo até a persistência final:
<img width="1600" height="691" alt="image" src="https://github.com/user-attachments/assets/e6a07b6b-6367-4ed0-823c-788d287b157e" />



🛠️ Tecnologias Utilizadas
Linguagem: Java 17
Framework: Spring Boot 3.x
Mensageria: RabbitMQ (Broker de mensagens)
Banco de Dados: PostgreSQL
Migrações: Flyway (Versionamento de Schema)
Persistência: Spring Data JPA / Hibernate
Produtividade: Lombok & Java Records (DTOs)
Serviço de E-mail: Java Mail Sender (Testado com Mailtrap)

<img width="1600" height="868" alt="image" src="https://github.com/user-attachments/assets/e4e9fd23-842f-42c5-89f2-2422097fb272" />


🏗️ Estrutura do Código
A aplicação está dividida de forma semântica para facilitar a manutenção:
config/: Configurações do Broker RabbitMQ e Beans do Spring.
consumer/: Ouvintes (Listeners) que capturam mensagens da fila de forma assíncrona.
dto/: Records utilizados para o transporte seguro e imutável de dados (Payload).
service/: Camada de inteligência onde reside a lógica de negócio e integração SMTP.
model/: Entidades JPA que representam a estrutura da tabela no banco de dados.
repository/: Interfaces de abstração para operações de banco de dados.

🚦 Como Rodar o Projeto
Pré-requisitos
Java 17 instalado.
Instância do PostgreSQL rodando (Porta 5432).
Instância do RabbitMQ rodando (Porta 5672).

Clone o repositório:
git clone https://github.com/seu-usuario/layered_architecture.git

Configure suas credenciais no arquivo src/main/resources/application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/db_notificacoes
spring.rabbitmq.host=localhost
spring.mail.host=sandbox.smtp.mailtrap.io

Execute a aplicação via terminal ou sua IDE favorita:
./mvnw spring-boot:run
<img width="1600" height="870" alt="image" src="https://github.com/user-attachments/assets/d12ca3ca-2dec-4474-bd0f-7064a5879b86" />

📈 Melhorias Futuras (Roadmap)
[ ] Implementar Dead Letter Queue (DLQ) para tratamento de mensagens com falha.

[ ] Adicionar suporte a Templates HTML com Thymeleaf.

[ ] Criar uma tarefa agendada (@Scheduled) para reenvio automático de e-mails com erro.

[ ] Dockerização completa com docker-compose.

Desenvolvido por [Keven Nogueira da Silva]
Estudante de Desenvolvimento de Software com foco em Backend e Arquiteturas Distribuídas.
