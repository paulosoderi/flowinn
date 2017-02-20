# flowinn

Backend:
  - Springboot
  - Maven
  - hibernate
  - container Jetty
  - swagger
  - hsqldb
  - Servicos criados:
    - http://localhost:8080/api/v1/ - GET - verifica se servidor está ativo
    - http://localhost:8080/api/v1/item - GET - recupera um item
    - http://localhost:8080/api/v1/item - POST - cadastra um novo item
    - http://localhost:8080/api/v1/item/delete - POST - deleta um item
    - http://localhost:8080/api/v1/item/licitar - POST - da um lance em um item
    - http://localhost:8080/api/v1/itens - GET - recupera a lista de todos os itens cadastrados
    - http://localhost:8080/api/v1/itens/abertos - GET - recupera a lista de itens com status aberto
    - http://localhost:8080/api/v1/itens/fechados - GET  - recupera a lista de itens com status fechado
  
  Instruções:
    - Executar o método main da classe Application.java para iniciar o Jetty
    - Acessar a url na porta 8080
    - Para visualizar a documentação do serviço gerada com o swagger acessar a url: http://localhost:8080/swagger-ui.html
    

Front-end:
  - AngularJS 1.3 
  - Páginas criadas:
    - lista de itens cadastrados com os detalhes de cada item - http://localhost:3000/itens
    - cadastro de novo item - http://localhost:3000/itens/cadastro
    - lance em um item existente - http://localhost:3000/lance/
    
  Instruções:
     - executar o servidor: npm start
     - acessar a url na porta 3000
  
  
