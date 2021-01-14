# eclipse-java

Versão JDK 14
Banco MySQL versão 8.0
Tomcat versão 9

A Conexão com o banco está configurada com usuário (daniela) e senha (123456) na classe Conexão no pacote br.com.utils 
O script para criar a base e tabelas está na pasta SQL.

Para listar as pessoas cadastradas no banco, fazer o chamado:
GET
http://localhost:8080/paytrack3/pessoa/listarPessoas

Exemplo de retorno:
{
	"celular": "55999789789",
	"idEnderecoPessoa": 1,
	"idPessoa": 7,
	"idade": 18,
	"nome": "Antonio Pereira",
	"sexo": "M"
}
Para buscar uma pessoa por Id, fazer o chamado:
GET
http://localhost:8080/paytrack3/pessoa/buscarPessoaPorId/{numero do id}
Exemplo de retorno
{
    "celular": "5598745678",
    "idEnderecoPessoa": 3,
    "idPessoa": 9,
    "idade": 35,
    "nome": "Ana Paula",
    "sexo": "F"
}

Para adicionar uma pessoa, fazer o chamado:
POST
http://localhost:8080/paytrack3/pessoa/adicionarPessoa
Exemplo:
{
    "nome":"João da Silva",
    "idade":24,
    "celular":"47999456789",
    "sexo":"M",
    "idEnderecoPessoa":2
}
É necessário informar todos os campos e um idEnderecoPessoa já cadastrado no banco.

Para alterar uma pessoa, fazer o chamado:
PUT
http://localhost:8080/paytrack3/pessoa/alterarPessoa/3
{
    "nome":"João Gonçãlves",
    "idade":55,
    "celular":"55999789789",
    "sexo":"F",
    "idEnderecoPessoa":2
}
É necessário informar todos os campos e um idEnderecoPessoa já cadastrado no banco.

Para aexcluir uma pessoa, fazer o chamado:
DELETE
http://localhost:8080/paytrack3/pessoa/removerPessoa/{numero do id}
Quando a pessoa é removida a mensagem será "Pessoa removida com sucesso!"


Para listar os endereços cadastrados no banco, fazer o chamado:
GET
http://localhost:8080/paytrack3/pessoaEndereco/listarEnderecos
Exemplo de retorno:
{
	"bairro": "Ponta aguda",
	"cep": "89010050",
	"cidade": "Blumenau",
	"idEnderecoPessoa": 1,
	"numero": "85",
	"rua": "Rua Francisco Louro"
}

Para buscar um endereço por Id, fazer o chamado:
GET
http://localhost:8080/paytrack3/pessoaEndereco/buscarEnderecoPorId/{numero do id}
Exemplo de retorno:
{
    "bairro": "Legal",
    "cep": "89155222",
    "cidade": "Disney",
    "idEnderecoPessoa": 7,
    "numero": "450",
    "rua": "Das 12"
}

Para adicionar um endereço, fazer o chamado:
POST
http://localhost:8080/paytrack3/pessoaEndereco/adicionarEnderecoPessoa
Exemplo:
{
    "cidade":"Blumenau",
    "bairro":"Ponta aguda",
    "rua":"Rua Francisco",
    "numero":"85",
    "cep":"89010050"
}
É necessário informar todos os campos.

Para alterar um endereço, fazer o chamado:
PUT
http://localhost:8080/paytrack3/pessoaEndereco/alterarEndereco/1
{
    "cidade":"Blumenau",
    "bairro":"Ponta aguda",
    "rua":"Rua Francisco Louro",
    "numero":"85",
    "cep":"89010050"
}
É necessário informar todos os campos.

Para remover um endereço, fazer o chamado:
DELETE
http://localhost:8080/paytrack3/pessoaEndereco/removerEndereco/{numero do id}
Quando o endereço é removido a mensagem será "Endereço removido com sucesso!"












