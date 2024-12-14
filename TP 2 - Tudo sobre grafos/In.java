package tools; // Ou o pacote onde a classe está definida
import java.util.*;
import java.io.*;

// Classe para leitura de arquivos
public class In {
    Scanner in;

    public In(File file) { // Construtor também precisa ser public
        try {
            this.in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        }
    }

    // Torne o método público
    public boolean hasNextLine() {
        return in.hasNextLine();
    }

    // Torne o método público
    public String nextLine() {
        return in.nextLine();
    }
}
