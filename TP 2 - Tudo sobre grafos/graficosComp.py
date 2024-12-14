import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

# Função para gerar o gráfico de comparação por arquivo e salvar o gráfico
def gerar_boxplot(arquivo):
    # Carrega o arquivo CSV
    df = pd.read_csv(arquivo, sep=",")  # Ajuste para separador de vírgula

    # Imprime os algoritmos únicos encontrados no arquivo para diagnóstico
    print(f"Algoritmos encontrados no arquivo {arquivo}:")
    print(df['Algoritmo utilizado'].unique())  # Mostra os nomes dos algoritmos

    # Lista de algoritmos que queremos comparar
    algoritmos = ["Bellman-Ford Lista", "Bellman-Ford Matriz", "Dijkstra Lista", "Dijkstra Matriz"]

    # Lista para armazenar os dados
    boxplot_data = []

    # Loop para filtrar e armazenar os valores de cada algoritmo
    for algoritmo in algoritmos:
        # Filtra os valores do algoritmo
        valores = df.loc[df['Algoritmo utilizado'] == algoritmo, df.columns[1:]]  # Exclui a coluna do nome do algoritmo
        for valor in valores.values.flatten():
            boxplot_data.append({'Algoritmo': algoritmo, 'Valores': valor})

    # Criar DataFrame para os dados coletados
    boxplot_df = pd.DataFrame(boxplot_data)

    # Plotar o boxplot
    plt.figure(figsize=(10, 6))
    sns.boxplot(data=boxplot_df, x="Algoritmo", y="Valores")
    plt.title(f"Box Plot para os Algoritmos no Arquivo: {arquivo}")
    plt.yscale("log")
    plt.ylabel("Valores")
    plt.xlabel("Algoritmos")
    plt.xticks(rotation=45)
    plt.tight_layout()

    # Salvar o gráfico como arquivo PNG
    output_filename = arquivo.replace(".csv", "_boxplot.png").replace("tempos/", "graficos/")
    plt.savefig(output_filename)

    # Mostrar o gráfico (opcional)
    plt.show()

# Gerar os gráficos para os arquivos especificados e salvar os gráficos
for arquivo in ['tempos/arq5.csv', 'tempos/arq10.csv', 'tempos/arq15.csv']:
    gerar_boxplot(arquivo)

