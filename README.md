# SRSEditor-WEB
Gerador de Documento de requisitos na plataforma Web - UFPI 2017.1

A aplicação foi criada usando JSF[1]/Primeface[2] na camada de visão. Na camada de acesso aos dados foi usado o padrão JPA[3] integrado com Hibernate.

A aplicação é estrtuturada no padrão Maven[4] para aplicações Web.

Atualmente o projeto tem as seguintes funcionalidades:

    1. Controle de sessão
    2. login de usuário

Para rodar a aplicação:

    1. Baixe o projeto via clone da URL https://github.com/lucas-china/SRSEditor-WEB.git
    2. Configure o projeto como MAVEN
    3. Atualize as dependências via Maven Update Project.
    4. Configure o container Java para rodar no Tom Cat[5].
    5. Crei um banco mysql[6] com o nome srseditorweb
    6. Crie a tabela usuario com os campos userid, username e pwd.
    7. Cadastre pelo menos um usuário na tabela usuario.
    8. No arquivo persistence.xml altere o user e o password do banco de dados.
    9. Atualize seu JDk para o 1.8

Referências:

[1] JSF -  Java Server Faces - http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html

[2] PrimaFaces - framework para Java EE - https://www.primefaces.org

[3] JPA - Java Persistence API - http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html

[4] Maven - Gestão de Builds e Dependências - https://maven.apache.org

[5] Tom Cat - Container Java Web - https://tomcat.apache.org/

[6] Mysql 5 - Sistema de Gerenciamento de Banco de Dados - https://dev.mysql.com/downloads/mysql/

Dúvidas, sugestões ou críticas entre em contato via e-mail lucasbrito_9@hotmail.com
