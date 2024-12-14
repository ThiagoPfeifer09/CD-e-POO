import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

# Definir os algoritmos que queremos comparar
algoritmos = ["Dijkstra Lista", "Dijkstra Matriz"]

# Inicializar lista para armazenar os dados dos gráficos
dados_boxplot = []

# Loop pelos arquivos de arq1.csv até arq5.csv
for i in range(11, 16):  # Para arq1 até arq5
    arquivo = f"tempos/arq{i}.csv"
    try:
        # Carregar o arquivo CSV
        df = pd.read_csv(arquivo, sep=",")  # Ajustado para separador de vírgula

        # Para cada algoritmo, obter os valores correspondentes no arquivo
        for algoritmo in algoritmos:
            valores = df.loc[df['Algoritmo utilizado'] == algoritmo, df.columns[1:]]  # Filtra os valores do algoritmo
            if not valores.empty:
                for valor in valores.values.flatten():
                    dados_boxplot.append({'Arquivo': f'arq{i}', 'Algoritmo': algoritmo, 'Valores': valor})
    except Exception as e:
        print(f"Erro ao processar {arquivo}: {e}")

# Verifica se há dados suficientes para o boxplot
if dados_boxplot:
    # Criar um DataFrame com os dados coletados
    df_boxplot = pd.DataFrame(dados_boxplot)

    # Plotar os boxplots lado a lado
    plt.figure(figsize=(16, 8))  # Ajuste do tamanho do gráfico

    # Gerar o boxplot com Seaborn, dividindo por Algoritmo e por Arquivo
    sns.boxplot(data=df_boxplot, x="Arquivo", y="Valores", hue="Algoritmo", showfliers=False)

    # Personalizar o gráfico
    plt.title("Comparação de Algoritmos Dijkstra (Lista vs Matriz)")
    plt.ylabel("Tempos de Execução (em milissegundos)")
    plt.xlabel("Arquivos")
    plt.xticks(rotation=45)  # Rotação das etiquetas no eixo X
    plt.legend(title="Algoritmo", loc='upper left')
    plt.tight_layout()

    # Salvar o gráfico como arquivo PNG
    output_filename = "graficos/Dijkstra_comparacao500.png"
    plt.savefig(output_filename)

    # Mostrar o gráfico (opcional)
    plt.show()

else:
    print("Nenhum dado encontrado para os algoritmos Bellman-Ford Lista ou Matriz.")
