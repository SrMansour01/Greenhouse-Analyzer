# Greenhouse Analyzer
O Analisador de Estufa é um programa em Kotlin projetado para processar e analisar dados de sensores coletados de uma estufa. Ele oferece várias funcionalidades para carregar dados de um arquivo CSV, calcular estatísticas, identificar e lidar com valores discrepantes (outliers) e exibir os resultados de forma interativa por meio de um menu de fácil utilização.

## Utilização
### Ler Dados de um Arquivo CSV

Escolha a opção 1 para ler os dados dos sensores de um arquivo CSV. Insira o caminho do arquivo CSV quando solicitado. Os dados serão carregados no programa, e você receberá uma mensagem de sucesso. Opcionalmente, você pode escolher exibir os dados carregados.

### Calcular a Média

Escolha a opção 2 para calcular o valor médio dos dados dos sensores. O programa exibirá a média calculada.

### Encontrar o Valor Máximo

Escolha a opção 3 para encontrar o valor máximo entre os dados dos sensores. O programa exibirá o valor máximo.

### Encontrar o Valor Mínimo

Escolha a opção 4 para encontrar o valor mínimo entre os dados dos sensores. O programa exibirá o valor mínimo.

### Identificar e Lidar com Valores Discrepantes

Escolha a opção 5 para identificar e lidar com valores discrepantes nos dados dos sensores usando o método do Intervalo Interquartil (IQR). O programa removerá pontos de dados que estejam abaixo do limite inferior ou acima do limite superior.

### Exibir Todas as Estatísticas

Escolha a opção 6 para exibir todas as estatísticas: média, valor máximo e valor mínimo, em um único passo.

### Sair

Escolha a opção 0 para sair do programa.

## Como Executar
Certifique-se de ter o Kotlin instalado em seu sistema.

Copie e cole o código fornecido em um arquivo de origem Kotlin (por exemplo, GreenhouseAnalyzer.kt).

Compile e execute o programa usando o compilador Kotlin ou um ambiente de desenvolvimento integrado (IDE) com suporte ao Kotlin.

Siga as instruções do menu para interagir com o programa e analisar os dados dos sensores.

## Recursos
Os dados são lidos de um arquivo CSV, onde cada linha contém um ID de sensor e um valor de leitura do sensor separados por vírgula.
O programa calcula a média, o valor máximo e o valor mínimo dos dados dos sensores.
Valores discrepantes são identificados e tratados usando o método do Intervalo Interquartil (IQR).
O usuário tem um menu interativo para escolher as ações desejadas.
## Observação
Este README fornece uma visão geral do programa Analisador de Estufa e como usá-lo. Certifique-se de ajustar os caminhos de arquivo e o tratamento de entrada/saída conforme necessário para o seu ambiente.
