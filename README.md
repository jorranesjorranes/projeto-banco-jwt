# projeto-banco-jwt
Projeto com Java de um banco simples, utilizando a IDE Eclipse, Spring Boot, JPA, JWT e banco de dados MySQL;

Configurações iniciais:

Entre no arquivo.properties do projeto, que está dentro da pasta "src/main/resources", pasta essa que está abaixo de "src/main/java";
O arquivo.properties estará abaixo da pasta static e, embaixo também, da pasta templates, entitulado de "application.properties";

Uma vez dentro do arquivo.properties, você verá essa linha de código:
"spring.datasource.url=jdbc:mysql://localhost:3306/springboot";
O nome "springboot", nessa linha de código é o nome do schema que eu criei no mysql, antes de rodar o programa, então:
Crie, no MySQL, um novo schema, e, coloque o nome desse schema no lugar de "springboot";
Ou, deixe o nome "springboot", mesmo, na linha de código do arquivo.properties, e crie um schema também com o nome "springboot" no MySQL;
Rode a aplicação.

Como usar:

Em "BancoProjetoApplication.java", onde está o método main, haverá uma senha padrão que será encriptada quando você rodar o programa;
Altere-a, caso queira;
Rode o programa uma vez;
No console a senha que você colocou na String, ou a senha padrão que você deixou, mesmo, aparecerá encriptada;
Copie-a;
Stoppe o programa;
Utilizando o MySQL workbench, ou outra interface para o MySQL, ou mesmo através do terminal, insira os dados de administrador ou de user, através de Linguagem MySQL, ou clicando nos campos da interface.<br> 
No campo de password cole a senha encriptada;<br>
Modelo:
"
insert into tb_user values("id", "senhaEncriptada", "email", "saldo", "nomeDeUsuarioAdministrador");
insert into tb_user values("id", "senhaEncriptada", "email", "saldo", "nomeDeUsuarioUser");

insert into tb_role values("1", "ROLE_ADMIN");
insert into tb_role values("2", "ROLE_USER");

insert into tb_users_roles values("1", "1");
insert into tb_users_roles values("2", "2");
"
Neste modelo colocamos um Usuário Administrador e um Usuário User;
Rode o programa novamente;
Utilize o programa.
