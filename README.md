# Mercado Livre

## Sumário

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração](#configuração)
- - [Mosquitto](#mosquitto)
- - [Java](#java)
- [Execução](#execução)
  - [Server](#server)   
  - [Client](#client)
- [Tabelas Hash](#tabelashash)  
- [Funcionalidades](#funcionalidades)
## Tecnologias Utilizadas

- Java
- Grpc
- Mosquitto
- ...

## Configuração

1. Abra o CMD
2. Crie uma pasta
3. Navegue até a pasta criada
4. Digite no CMD: git clone https://github.com/EullerHenrique/mercado_livre

### Mosquitto

1. Abra a pasta mercado_livre
2. Abra o arquivo mosquitto-2.0.15-install-windows-x64.exe
3. Aperte next
4. Selecione visual estudio runtime e service
5. Selecione a pasta mercado_livre como local de instalação
6. Abra o CMD
7. Navegue até a pasta mercado_livre
8. Digite no CMD: mosquitto -v

### Java

1. Abra o intellij 
2. Instale o JAVA 17:  
    1. No canto superior esquerdo, aperte na aba File
    2. Clique em Project Structure
    3. Clique em Project
    4. Clique no Select do SDK
    5. Clique em Add SDK
    6. Clique em Download JDK
    7. Clique em version 
    8. Escolha a versão 17
    9. Clique em download
    10. Clique em Apply
    11. Clique em OK
4. Aperte com o botão direito do mouse na pasta mercado_libre/src/main
5. Clique em Build Module 'mercado_livre.main'

## Execução

###  Server

1. Admin
    1. Navegue até mercado_libre/server/admin/AdminServer
    2. Aperte o botão play localizado ao lado de "public class AdminServer"
    3. Digite a porta desejada (Ex: 5051)
2. Cliente
    1. Navegue até mercado_libre/server/cliente/ClienteServer
    2. Aperte o botão play localizado ao lado de "public class ClienteServer"
    3. Digite a porta desejada (Ex: 5052)
    
### Client

1. Admin
     1. Navegue até mercado_libre/client/admin/AdminClient
     2. Aperte o botão play localizado ao lado de "public class AdminClient"
     3. Digite a porta escolhida ao criar o AdminServer (Ex: 5052)

2. Cliente
    1. Navegue até mercado_libre/client/cliente/ClientCliente
    2. Aperte o botão play localizado ao lado de "public class ClientCliente"
    3. Digite a porta escolhida ao criar o ClienteServer (Ex: 5052)

## Tabelas Hash

1. Cliente
     1. Hashtable<String, String> 
     2. <CID, ClienteJson>
2. Produto
     1. Hashtable<String, String>
     2. <PID, ProdutoJson>
3. Pedido 
     1. Hashtable<String, List<Hashtable<String, List<String>>>> 
     2. <CID<List<OID, List<ProdutoJson>>>>

## Funcionalidades

1. Admin
    1. Criar Cliente
        1. AdminCliente: Digite o nome do cliente
        2. AdminCliente: Digite o email do cliente
        3. AdminCliente: Digite o telefone do cliente
        4. AdminCliente->Grpc: CriarCliente -> Realiza uma requisição por meio do protocolo rpc
        5. AdminServer->Grpc: CriarCliente -> Recebe uma requisição por meio do protocolo rpc
        6. AdminServer: Salva o cliente na tabela hash (Cliente) do servidor x
        7. AdminServer->Mosquitto: Se subscreve no tópico server/admin/cliente/criar  
        8. AdminServer->Mosquitto: Publica o cliente criado no tópico server/admin/cliente/criar 
        9. AdminServer: A subcrição realizada recebe o cliente que foi publicado 
        10. AdminServer: Se o cliente existir na tabela hash do servidor x, nada é feito
        11. AdminServer: Se o cliente não existir na tabela hash (Cliente) do servidor y, z, w, n ..., o cliente é salvo  no servidor y, z, w, n ... 
        12. AdminCliente: O cliente criado é exibido
    2. Modificar Cliente
        1. AdminCliente: Digite o nome do cliente
        2. AdminCliente: Digite o email do cliente
        3. AdminCliente: Digite o telefone do cliente
        4. AdminCliente->Grpc: ModificarCliente -> Realiza uma requisição por meio do protocolo rpc
        5. AdminServer->Grpc: ModificarCliente -> Recebe uma requisição por meio do protocolo rpc
        6. AdminServer: Se o cliente estiver presente na tabela hash (Cliente) do servidor x -> g-m
        7. AdminServer: Salva a modificação do cliente na tabela hash (Cliente) do servidor x 
        8. AdminServer->Mosquitto: Se subscreve no tópico server/admin/cliente/modificar  
        9. AdminServer->Mosquitto: Publica o cliente modificado no tópico server/admin/cliente/modificar 
        10. AdminServer: A subcrição realizada recebe o cliente que foi publicado 
        11. AdminServer: Se o cliente existir na tabela hash (Cliente) do servidor x, nada é feito
        12. AdminServer: Se o cliente não existir na tabela hash (Cliente) do servidor y, z, w, n ..., o cliente é salvo  no servidor y, z, w, n ... 
        13. AdminCliente: O cliente atualizado é exibido se ele existir
        14. AdminCliente: A mensagem "Cliente não encontrado" é exibida se ele não existir
    3. Buscar Cliente
        1. AdminCliente: Digite o CID do cliente
        2. AdminCliente->Grpc: BuscarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. AdminServer->Grpc: BuscarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. AdminServer: Realiza a busca do produto na tabela hash (Cliente)
        5. AdminCliente: O cliente buscado é exibido se ele existir
        6. AdminCliente: A mensagem "Cliente não encontrado" é exibida se ele não existir
    4. Apagar Cliente
        1. AdminCliente: Digite o CID do cliente
        2. AdminCliente->Grpc: ApagarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. AdminServer->Grpc: ApagarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. AdminServer: Se o cliente estiver presente na tabela hash (Cliente) do servidor x -> e-k
        5. AdminServer: Apaga o cliente presente na tabela hash (Cliente) do servidor x 
        6. AdminServer->Mosquitto: Se subscreve no tópico server/admin/cliente/modificar  
        7. AdminServer->Mosquitto: Publica o CID no tópico server/admin/cliente/modificar 
        8. AdminServer: A subcrição realizada recebe o CID que foi publicado 
        9. AdminServer: Se o cliente existir na tabela hash (Cliente) do servidor x, nada é feito
        10. AdminServer: Se o cliente não existir na tabela hash (Cliente) do servidor y, z, w, n ..., o cliente é salvo  no servidor y, z, w, n ... 
        11. AdminCliente: A mensagem "Cliente apagado" é exibida se ele não existir
        12. AdminCliente: A mensagem "Cliente não encontrado" é exibida se ele não existir    
    5. Criar Produto  
        1. AdminCliente: Digite o nome do produto
        2. AdminCliente: Digite a quantidade do produto
        3. AdminCliente: Digite o preço do produto
        4. AdminCliente->Grpc: CriarProduto -> Realiza uma requisição por meio do protocolo rpc
        5. AdminServer->Grpc: CriarProduto -> Recebe uma requisição por meio do protocolo rpc
        6. AdminServer: Salva o produto na tabela hash (Produto) do servidor x
        7. AdminServer->Mosquitto: Se subscreve no tópico server/admin/produto/criar  
        8. AdminServer->Mosquitto: Publica o cliente criado no tópico server/admin/produto/criar 
        9. AdminServer: A subcrição realizada recebe o produto que foi publicado 
        10. AdminServer: Se o produto existir na tabela hash (Produto) do servidor x, nada é feito
        11. AdminServer: Se o produto não existir na tabela hash (Produto) do servidor y, z, w, n ..., o produto é salvo  no servidor y, z, w, n ... 
        12. AdminCliente: O produto criado é exibido
    6. Modificar Produto
        1. AdminCliente: Digite o PID do produto
        2. AdminCliente: Digite o nome do produto
        3. AdminCliente: Digite a quantidade do produto
        4. AdminCliente: Digite o preço do produto
        5. AdminCliente->Grpc: ModificarProduto -> Realiza uma requisição por meio do protocolo rpc
        6. AdminServer->Grpc: ModificarProduto -> Recebe uma requisição por meio do protocolo rpc
        7. AdminServer: Se o produto estiver presente na tabela hash (Produto) do servidor x -> g-n
        8. AdminServer: Salva a modificação do produto na tabela hash (Produto) do servidor x 
        9. AdminServer->Mosquitto: Se subscreve no tópico server/admin/produto/modificar  
        10. AdminServer->Mosquitto: Publica o produto modificado no tópico server/admin/produto/modificar 
        11. AdminServer: A subcrição realizada recebe o produto que foi publicado 
        12. AdminServer: Se o produto existir na tabela hash (Produto) do servidor x, nada é feito
        13. AdminServer: Se o produto não existir na tabela hash (Produto) do servidor y, z, w, n ..., o produto é salvo  no servidor y, z, w, n ... 
        14. AdminCliente: O produto atualizado é exibido se ele existir
        15. AdminCliente: A mensagem "Produto não encontrado" é exibida se ele não existir
    7. Buscar Produto
        1. AdminCliente: Digite o PID do produto
        2. AdminCliente->Grpc: BuscarProduto -> Realiza uma requisição por meio do protocolo rpc
        3. AdminServer->Grpc: BuscarProduto -> Recebe uma requisição por meio do protocolo rpc
        4. AdminServer: Realiza a busca do produto na tabela hash (Produto)
        5. AdminCliente: O produto buscado é exibido se ele existir
        6. AdminCliente:A mensagem "Produto não encontrado" é exibida se ele não existir
    8. Apagar Produto
        1. AdminCliente: Digite o PID do cliente
        2. AdminCliente->Grpc: ApagarProduto -> Realiza uma requisição por meio do protocolo rpc
        3. AdminServer->Grpc: ApagarProduto -> Recebe uma requisição por meio do protocolo rpc
        4. AdminServer: Se o produto estiver presente na tabela hash (Produto) do servidor x -> e-k
        5. AdminServer: Apaga o produto presente na tabela hash (Produto) do servidor x 
        6. AdminServer->Mosquitto: Se subscreve no tópico server/admin/produto/apagar  
        7. AdminServer->Mosquitto: Publica o PID no tópico server/admin/produto/apagar 
        8. AdminServer: A subcrição realizada recebe o PID que foi publicado 
        9. AdminServer: Se o produto existir na tabela hash (Produto) do servidor x, nada é feito
        10. AdminServer: Se o produto não existir na tabela hash (Produto) do servidor y, z, w, n ..., o produto é salvo  no servidor y, z, w, n ... 
        11. AdminCliente: A mensagem "Produto apagado" é exibida se ele existir
        12. AdminCliente: A mensagem "Produto não encontrado" é exibida se ele não existir    
   
2. Cliente
    1. Criar Pedido
        1. ClientCliente: Digite o CID
        2. ClienteCliente->Grpc: VerificarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. ServerCliente->Grpc: VerificarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. ClienteServer->Mosquitto: Publica o CID no tópico server/cliente/cliente/verificar 
        5. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/cliente/verificar
        6. AdminServer-> Verifica se o cliente existe
        7. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/cliente/verificar 
        8. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/cliente/verificar
        9. ClienteServer: A subcrição realizada recebe o que foi publicado 
        10. ClientCliente->Se o clinte existir:
        11. ClientCliente: Digite o PID
        12. ClienteCliente->Grpc: BuscarProduto -> Realiza uma requisição por meio do protocolo rpc
        13. ServerCliente->Grpc: BuscarProduto -> Recebe uma requisição por meio do protocolo rpc
        14. ClienteServer->Mosquitto: Publica o PID no tópico server/cliente/produto/buscar 
        16. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/produto/buscar
        17. AdminServer-> Busca o produto
        18. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/produto/buscar
        19. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/produto/buscar
        20. ClienteServer: A subcrição realizada recebe o que foi publicado 
        21. ClienteCliente: Se o produto existir:
        22. ClientCliente: Exibe o nome do produto, quantidade disponível e preço
        23. ClientCliente: Digite a quantidade desejada (>0)
        24. ClientCliente: Multiplica a quantidade escolhida pelo preço do produto e salva no objeto ProdutoPedido
        25. ClientCliente: Você deseja adicionar mais pedidos? 
        26. ClientCLiente: Sim -> Volta até: Digite o PID... 
        27. ClientCliente: Não ->  Continua
        28. ClienteCliente->Grpc: CriarPedido -> Realiza uma requisição por meio do protocolo rpc
        29. ServerCliente->Grpc: CriarPedido -> Recebe uma requisição por meio do protocolo rpc
        30. ServerCliente: Salva o pedido na tabela hash (Pedido) do servidor x
        31. ServerClient->Mosquitto: Se subscreve no tópico server/client/pedido/criar  
        32. ServerClient->Mosquitto: Publica o pedido criado no tópico server/client/pedido/criar 
        33. ServerClient: A subcrição realizada recebe o pedido que foi publicado 
        34. ServerClient: Se o pedido existir na tabela hash do servidor x, nada é feito
        35. ServerClient: Se o pedido não existir na tabela hash (Pedido) do servidor y, z, w, n ..., o pedido é salvo  no servidor y, z, w, n ... 
        36. ClienteCliente: Solicita a modificação de cada produto presente no pedido:
        37. ClienteCliente->Grpc: ModificarProduto -> Realiza uma requisição por meio do protocolo rpc
        38. ServerCliente->Grpc: ModificarProduto -> Recebe uma requisição por meio do protocolo rpc
        39. ClienteServer->Mosquitto: Publica o produto no tópico server/cliente/produto/modificar
        40. AdminServer->Mosquitto: Se subscreve no tópico server/cliente/produto/modificar
        41. AdminServer-> Armazena a nova quantidade do produto na tabela hash (Produto)
        42. ClientCliente: O pedido criado é exibido
    2. Modificar Pedido
        1. ClientCliente: Digite o CID
        2. ClienteCliente->Grpc: VerificarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. ServerCliente->Grpc: VerificarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. ClienteServer->Mosquitto: Publica o CID no tópico server/cliente/cliente/verificar 
        6. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/cliente/verificar
        7. AdminServer-> Verifica se o cliente existe
        8. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/cliente/verificar 
        5. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/cliente/verificar
        6. ClienteServer: A subcrição realizada recebe o que foi publicado 
        7. ClientCliente->Se o clinte existir:
        8. ClienteCliente: Digite o OID
        9. ClienteCliente->Grpc: BuscarPedido -> Realiza uma requisição por meio do protocolo rpc
        10. ServerCliente->Grpc: BuscarPedido -> Recebe uma requisição por meio do protocolo rpc
        11. Se o pedido não existir: Exibe a mensagem "Pedido não encontrado. Tente novamente
        12. Se o pedido existir:
        12. ClientCliente: Digite o PID
        13. ClienteCliente->Grpc: BuscarProduto -> Realiza uma requisição por meio do protocolo rpc
        14. ServerCliente->Grpc: BuscarProduto -> Recebe uma requisição por meio do protocolo rpc
        15. ClienteServer->Mosquitto: Publica o PID no tópico server/cliente/produto/buscar 
        16. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/produto/buscar
        17. AdminServer-> Busca o produto
        18. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/produto/buscar
        19. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/produto/buscar
        20. ClienteServer: A subcrição realizada recebe o que foi publicado 
        21. ClienteCliente: Se o produto existir:
        22. ClientCliente: Exibe o nome do produto, quantidade disponível, preço, quantidade presente no pedido, preço total presente no pedido 
        24. ClientCliente: Digite a quantidade desejada (Se for 0, o produto é apagado do pedido)
        25. ClientCliente: Multiplica a quantidade escolhida pelo preço do produto e salva no objeto ProdutoPedido
        26. ClienteCliente->Grpc: ModificarPedido -> Realiza uma requisição por meio do protocolo rpc
        27. ServerCliente->Grpc: ModificarPedido -> Recebe uma requisição por meio do protocolo rpc
        28. ServerCliente: Salva o pedido atualizado na tabela hash (Pedido) do servidor x
        29. ServerClient->Mosquitto: Se subscreve no tópico server/client/pedido/modificar  
        30. ServerClient->Mosquitto: Publica o pedido atualizado no tópico server/client/pedido/modificar 
        31. ServerClient: A subcrição realizada recebe o pedido que foi publicado 
        32. ServerClient: Se o pedido atualizado existir na tabela hash do servidor x, nada é feito
        33. ServerClient: Se o pedido atualizado não existir na tabela hash (Pedido) do servidor y, z, w, n ..., o pedido é salvo  no servidor y, z, w, n ... 
        34. ClienteCliente->Grpc: ModificarProduto -> Realiza uma requisição por meio do protocolo rpc
        35. ServerCliente->Grpc: ModificarProduto -> Recebe uma requisição por meio do protocolo rpc
        36. ClienteServer->Mosquitto: Publica o produto no tópico server/cliente/produto/modificar
        37. AdminServer->Mosquitto: Se subscreve no tópico server/cliente/produto/modificar
        38. AdminServer-> Armazena a nova quantidade do produto na tabela hash (Produto)
        39. ClientCliente: O pedido modificado é exibido
    4. Buscar Pedido
        1. ClientCliente: Digite o CID
        2. ClienteCliente->Grpc: VerificarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. ServerCliente->Grpc: VerificarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. ClienteServer->Mosquitto: Publica o CID no tópico server/cliente/cliente/verificar 
        6. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/cliente/verificar
        7. AdminServer-> Verifica se o cliente existe
        8. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/cliente/verificar 
        5. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/cliente/verificar
        6. ClienteServer: A subcrição realizada recebe o que foi publicado 
        7. ClientCliente->Se o clinte existir:
        8. ClienteCliente: Digite o OID
        9. ClienteCliente->Grpc: BuscarPedido -> Realiza uma requisição por meio do protocolo rpc
        10. ServerCliente->Grpc: BuscarPedido -> Recebe uma requisição por meio do protocolo rpc
        11. AdminCliente: O pedido é exibido se ele existir
        12. AdminCliente: A mensagem "Pedido não encontrado" é exibida se ele não existir                                
    5. Buscar Pedidos
        1. ClientCliente: Digite o CID
        2. ClienteCliente->Grpc: VerificarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. ServerCliente->Grpc: VerificarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. ClienteServer->Mosquitto: Publica o CID no tópico server/cliente/cliente/verificar 
        5. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/cliente/verificar
        6. AdminServer-> Verifica se o cliente existe
        7. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/cliente/verificar 
        8. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/cliente/verificar
        9. ClienteServer: A subcrição realizada recebe o que foi publicado 
        10. ClientCliente->Se o clinte existir:
        11. ClienteCliente->Grpc: BuscarPedidos -> Realiza uma requisição por meio do protocolo rpc
        12. ServerCliente->Grpc: BuscarPedidos -> Recebe uma requisição por meio do protocolo rpc
        13. ServerServer-> Busca os pedidos do cliente, retorna cada pedido associado a soma dos produtos presentes nele
        14. ClienCliente: Os pedidos são exibidos exibida se o cliente possuir pelo menos um pedido
        15. ClienCliente: A mensagem "O cliente não possui pedidos" é exibida se o cliente não possuir pelo menos um pedido  
   6. Apagar Pedido
        1. ClientCliente: Digite o CID
        2. ClienteCliente->Grpc: VerificarCliente -> Realiza uma requisição por meio do protocolo rpc
        3. ServerCliente->Grpc: VerificarCliente -> Recebe uma requisição por meio do protocolo rpc
        4. ClienteServer->Mosquitto: Publica o CID no tópico server/cliente/cliente/verificar 
        5. AdminServer-> Mosquitto: Se subscreve no tópico server/cliente/cliente/verificar
        6. AdminServer-> Verifica se o cliente existe
        7. AdminServer->Mosquitto: Publica a resposta no tópico server/admin/cliente/verificar 
        8. ClienteServer->Mosquitto: Se subscreve no tópico server/admin/cliente/verificar
        9. ClienteServer: A subcrição realizada recebe o que foi publicado 
        10. ClientCliente->Se o clinte existir:
        11. ClienteCliente: Digite o OID
        12. ClienteCliente->Grpc: ApagarPedido -> Realiza uma requisição por meio do protocolo rpc
        13. ServerCliente->Grpc: ApagarPedido -> Recebe uma requisição por meio do protocolo rpc
        14. ServerCliente: Se o produto estiver presente na tabela hash (Peiddo) do servidor x:
        15. ServerCliente: Apaga o produto presente na tabela hash (Pedido) do servidor x 
        16. ServerCliente->Mosquitto: Se subscreve no tópico server/cliente/pedido/apagar  
        17. ServerCliente->Mosquitto: Publica pedido no tópico server/cliente/pedido/apagar 
        18. ServerCliente: A subcrição realizada recebe o pedido que foi publicado 
        19. ServerCliente: Se o pedido existir na tabela hash (Pedido) do servidor x, nada é feito
        20. ServerCliente: Se o pedido não existir na tabela hash (Produto) do servidor y, z, w, n ..., o produto é salvo  no servidor y, z, w, n ... 
        21. ClienteCliente: A mensagem "Pedido apagado" é exibida se ele existir
        22. ClienteCliente: A mensagem "Pedido não encontrado" é exibida se ele não existir    

