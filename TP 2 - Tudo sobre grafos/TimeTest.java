package tools;

import grafo.*;
import java.util.*;
import java.io.*;

public class TimeTest {

    public static void main(String[] args) {
        // args[0] grafo para teste (1-15)
        // args[1] quantidade de testes

        List<Long> Bellman_Ford_Lista = new ArrayList<>();
        List<Long> Bellman_Ford_Matriz = new ArrayList<>();
        List<Long> Dijkstral_Lista = new ArrayList<>();
        List<Long> Dijkstral_Matriz = new ArrayList<>();

        Graph graph, graphM;
        graph = new Graph(new In(new File("Grafos de Entrada/sample100-1980.gr")), false);
        graphM = new Graph(new In(new File("Grafos de Entrada/sample100-1980.gr")), true);

        switch (Integer.parseInt(args[0])) {

            case 1:
                graph = new Graph(new In(new File("Grafos de Entrada/sample100-1980.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample100-1980.gr")), true);
                break;
            case 2:
                graph = new Graph(new In(new File("Grafos de Entrada/sample100-3960.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample100-3960.gr")), true);
                break;
            case 3:
                graph = new Graph(new In(new File("Grafos de Entrada/sample100-5940.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample100-5940.gr")), true);
                break;
            case 4:
                graph = new Graph(new In(new File("Grafos de Entrada/sample100-7920.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample100-7920.gr")), true);
                break;
            case 5:
                graph = new Graph(new In(new File("Grafos de Entrada/sample100-9900.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample100-9900.gr")), true);
                break;
            case 6:
                graph = new Graph(new In(new File("Grafos de Entrada/sample200-7960.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample200-7960.gr")), true);
                break;
            case 7:
                graph = new Graph(new In(new File("Grafos de Entrada/sample200-15920.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample200-15920.gr")), true);
                break;
            case 8:
                graph = new Graph(new In(new File("Grafos de Entrada/sample200-23880.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample200-23880.gr")), true);
                break;
            case 9:
                graph = new Graph(new In(new File("Grafos de Entrada/sample200-31840.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample200-31840.gr")), true);
                break;
            case 10:
                graph = new Graph(new In(new File("Grafos de Entrada/sample200-39800.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample200-39800.gr")), true);
                break;
            case 11:
                graph = new Graph(new In(new File("Grafos de Entrada/sample500-49900.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample500-49900.gr")), true);
                break;
            case 12:
                graph = new Graph(new In(new File("Grafos de Entrada/sample500-99800.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample500-99800.gr")), true);
                break;
            case 13:
                graph = new Graph(new In(new File("Grafos de Entrada/sample500-149700.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample500-149700.gr")), true);
                break;
            case 14:
                graph = new Graph(new In(new File("Grafos de Entrada/sample500-199600.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample500-199600.gr")), true);
                break;
            case 15:
                graph = new Graph(new In(new File("Grafos de Entrada/sample500-249500.gr")), false);
                graphM = new Graph(new In(new File("Grafos de Entrada/sample500-249500.gr")), true);
                break;
        }


        int k = 0;

        while (k < Integer.parseInt(args[1])) {

            long t_ini, t_fim;

            // Bellman-Ford_Lista
            t_ini = System.nanoTime();
            graph.Bellman_Ford_Lista(1);
            t_fim = System.nanoTime();
            Bellman_Ford_Lista.add(t_fim - t_ini);

            // Bellman-Ford_Matriz
            t_ini = System.nanoTime();
            graphM.Bellman_Ford_Matriz(1);
            t_fim = System.nanoTime();
            Bellman_Ford_Matriz.add(t_fim - t_ini);

            // Dijkstral_Lista
            t_ini = System.nanoTime();
            graph.Dijkstra_Lista(1);
            t_fim = System.nanoTime();
            Dijkstral_Lista.add(t_fim - t_ini);

            // Dijkstral_Matriz
            t_ini = System.nanoTime();
            graphM.Dijkstra_Matriz(1);
            t_fim = System.nanoTime();
            Dijkstral_Matriz.add(t_fim - t_ini);

            k++;

        }

        String nomeArquivoCSV = "tempos/arq" + args[0] + ".csv";

        try (PrintWriter writer = new PrintWriter(new File(nomeArquivoCSV))) {

            writer.print("Algoritmo utilizado");
            for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                writer.print(",Teste " + (i + 1));
            }
            writer.println();

            writer.print("Bellman-Ford Lista");
            for (Long tempo : Bellman_Ford_Lista)
                writer.print("," + tempo);
            writer.println();

            writer.print("Bellman-Ford Matriz");
            for (Long tempo : Bellman_Ford_Matriz)
                writer.print("," + tempo);
            writer.println();

            writer.print("Dijkstra Lista");
            for (Long tempo : Dijkstral_Lista)
                writer.print("," + tempo);
            writer.println();

            writer.print("Dijkstra Matriz");
            for (Long tempo : Dijkstral_Matriz)
                writer.print("," + tempo);
            writer.println();

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }
    }
}
