import java.util.ArrayList;
import java.util.Scanner;

// Classe Produto
class Produto {
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void reduzirEstoque(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return nome + " - Preço: R$ " + preco + " - Estoque: " + quantidadeEstoque;
    }
}

// Classe ItemPedido
class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - Quantidade: " + quantidade + " - Subtotal: R$ " + calcularSubtotal();
    }
}

// Classe Cliente
class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " - CPF: " + cpf;
    }
}

// Classe Pedido
class Pedido {
    private Cliente cliente;
    private ArrayList<ItemPedido> itens;
    private boolean pago;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.pago = false;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void realizarPagamento(String formaPagamento) {
        System.out.println("Total do pedido: R$ " + calcularTotal());
        System.out.println("Forma de pagamento: " + formaPagamento);
        this.pago = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cliente.toString()).append("\n");
        for (ItemPedido item : itens) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: R$ ").append(calcularTotal()).append("\n");
        return sb.toString();
    }

    public boolean isPago() {
        return pago;
    }
}

// Classe principal
public class Supermercado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando produtos para o supermercado
        Produto arroz = new Produto("Arroz", 20.00, 100);
        Produto feijao = new Produto("Feijão", 10.00, 50);
        Produto farinha = new Produto("Farinha", 5.00, 80);
        Produto leite = new Produto("Leite", 3.50, 200);

        // Lista de produtos disponíveis
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(arroz);
        produtos.add(feijao);
        produtos.add(farinha);
        produtos.add(leite);

        Pedido pedidoAtual = null;

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1) Novo pedido");
            System.out.println("2) Realizar pagamento");
            System.out.println("0) Sair da aplicação");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1: // Novo pedido
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpfCliente = scanner.nextLine();

                    Cliente cliente = new Cliente(nomeCliente, cpfCliente);
                    pedidoAtual = new Pedido(cliente);

                    boolean adicionandoItens = true;
                    while (adicionandoItens) {
                        System.out.println("\nProdutos disponíveis:");
                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println(i + 1 + ") " + produtos.get(i));
                        }
                        System.out.print("Escolha o número do produto ou 0 para finalizar: ");
                        int numeroProduto = scanner.nextInt();
                        if (numeroProduto == 0) {
                            adicionandoItens = false;
                        } else if (numeroProduto > 0 && numeroProduto <= produtos.size()) {
                            Produto produtoEscolhido = produtos.get(numeroProduto - 1);
                            System.out.print("Quantidade: ");
                            int quantidade = scanner.nextInt();

                            if (quantidade <= produtoEscolhido.getQuantidadeEstoque()) {
                                produtoEscolhido.reduzirEstoque(quantidade);
                                ItemPedido item = new ItemPedido(produtoEscolhido, quantidade);
                                pedidoAtual.adicionarItem(item);
                                System.out.println("Item adicionado ao pedido.");
                            } else {
                                System.out.println("Quantidade indisponível em estoque.");
                            }
                        } else {
                            System.out.println("Produto inválido.");
                        }
                    }
                    System.out.println("\nResumo do pedido:");
                    System.out.println(pedidoAtual);
                    break;

                case 2: // Realizar pagamento
                    if (pedidoAtual != null && !pedidoAtual.isPago()) {
                        System.out.println("\nFormas de pagamento disponíveis: DINHEIRO, CHEQUE, CARTÃO");
                        System.out.print("Escolha a forma de pagamento: ");
                        String formaPagamento = scanner.nextLine().toUpperCase();
                        pedidoAtual.realizarPagamento(formaPagamento);
                    } else if (pedidoAtual == null) {
                        System.out.println("Nenhum pedido foi criado.");
                    } else {
                        System.out.println("Pedido já foi pago.");
                    }
                    
                    break;

                case 0: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
