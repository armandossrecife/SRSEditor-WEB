# SRSEditor-WEB
Gerador de Documento de requisitos na plataforma Web - UFPI 2017.1

A aplicação foi criada usando a plataforma Eclipse JavaEE [1] com esquema de configuração via classes de configuração na própria aplicação. Na camada de visão foi usado JSF [2]/Primeface[3]. Na camada de acesso aos dados foi usado o padrão JPA[4] integrado com Hibernate.

A aplicação é estrtuturada no padrão Maven[5] para aplicações Web.

Atualmente o projeto tem as seguintes funcionalidades:

    1. Controle de sessão
    2. login de usuário

Para rodar a aplicação:

    1. Baixe o projeto via clone da URL https://github.com/lucas-china/SRSEditor-WEB.git
    2. Configure o projeto como MAVEN
    3. Atualize as dependências via Maven Update Project.
    4. Configure o container Java para rodar no Tom Cat[6].
    5. Crei um banco mysql[7] com o nome srs
    6. Crie um usuário para acessar o banco de dados. OBS: o nome do usuário deve ser editor.
    7. De privilegio para esse usuário sobre o banco srs.
    8. Atualize seu JDk para o 1.8

Referências:

[1] Eclipse JavaEE - Java Enterprise Edition - http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2

[2] JSF -  Java Server Faces - http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html

[3] PrimaFaces - framework para Java EE - https://www.primefaces.org

[4] JPA - Java Persistence API - http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html

[5] Maven - Gestão de Builds e Dependências - https://maven.apache.org

[6] Tom Cat - Container Java Web - https://tomcat.apache.org/

[7] Mysql 5 - Sistema de Gerenciamento de Banco de Dados - https://dev.mysql.com/downloads/mysql/

Dúvidas, sugestões ou críticas entre em contato via e-mail lucasbrito_9@hotmail.com
