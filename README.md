# livraria

projeto de entrevista da IBM;

Este é um sistema basico para armazenamento de informações de livros;
é uma API REST, escrita em java:11 utilizando SPRINGBOOT, JPA, banco de dados H2;
O sistema pode ser acessado, na porta 8080, utilizando o endpoint /books;
Este endpoint aceita requisições do tipo GET, POST, UPDATE e DELETE.

# INSTRUÇÕES DE USO

Este sistema pode ser utlizado com docker ou spring.

os dados utlizados para a criação de novos livros na base de dados são os seguintes:
{
"sbn": string,
"name": string,
"description": string,
"author": string,
"amount": int
}

## INSTALAÇÃO

recomento que seja utilizado o maven wapper que vem no Springboot:

instalar as dependencias do projeto, execute o seguinte em um terminal na raíz do projeto:

linux:

> ./mwnw install

para inciar o servidor sem docker:

> ./mwnv spring-boot:run

acesse http://localhost:8080/books em seu browser preferido.

Para roda com o docker, é necessário seguir o seguinte passo a passo:
execute o seguinte comando para criar a imagem docker:

> docker build -t livraria .

em seguida, execute o seguinte comando para inciar o servidor:

> docker run -p 8080:8080 livraria

acesse http://localhost:8080/books em seu browser preferido.
