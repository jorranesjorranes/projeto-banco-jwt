# projeto-banco-jwt
Projeto com Java de um banco simples, utilizando a IDE Eclipse, Spring Boot, JPA, JWT e banco de dados MySQL;<br>

<P>

Configurações iniciais:<br>

<P>

Entre no arquivo.properties do projeto, que está dentro da pasta "src/main/resources", pasta essa que está abaixo de "src/main/java";<br>
O arquivo.properties estará abaixo da pasta static e, embaixo também, da pasta templates, entitulado de "application.properties";<br>

<P>

Uma vez dentro do arquivo.properties, você verá essa linha de código:<br>
"spring.datasource.url=jdbc:mysql://localhost:3306/springboot";<br>
O nome "springboot", nessa linha de código é o nome do schema que eu criei no mysql, antes de rodar o programa, então:<br>
Crie, no MySQL, um novo schema, e, coloque o nome desse schema no lugar de "springboot";<br>
Ou, deixe o nome "springboot", mesmo, na linha de código do arquivo.properties, e crie um schema também com o nome "springboot" no MySQL;<br>
Rode a aplicação.<br>

<P>

Como usar:<br>

<P>

Em "BancoProjetoApplication.java", onde está o método main, haverá uma senha padrão que será encriptada quando você rodar o programa;<br>
Altere-a, caso queira;<br>
Rode o programa uma vez;<br>
No console a senha que você colocou na String, ou a senha padrão que você deixou, mesmo, aparecerá encriptada;<br>
Copie-a;<br>
Stoppe o programa;<br>
Utilizando o MySQL workbench, ou outra interface para o MySQL, ou mesmo através do terminal, insira os dados de administrador ou de user, através de linguagem MySQL, ou clicando nos campos da interface.<br> 
No campo de password cole a senha encriptada;<br>
Modelo:<br>
"<br>
insert into tb_user values("id", "senhaEncriptada", "email", "saldo", "nomeDeUsuarioAdministrador");<br>
insert into tb_user values("id", "senhaEncriptada", "email", "saldo", "nomeDeUsuarioUser");<br>

<P>

insert into tb_role values("1", "ROLE_ADMIN");<br>
insert into tb_role values("2", "ROLE_USER");<br>

<P>

insert into tb_users_roles values("1", "1");<br>
insert into tb_users_roles values("2", "2");<br>
"<br>
Neste modelo colocamos um Usuário Administrador e um Usuário User;<br>
Rode o programa novamente;<br>
Utilize o programa.<br>

<P>
