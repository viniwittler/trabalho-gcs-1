package dados;

public class Departamento {

    private String nome;
    private double valorMaximoPedido;

    public Departamento(String nome, double valorMaximoPedido) {
        this.nome = nome;
        this.valorMaximoPedido = valorMaximoPedido;
    }

    public String getNome() {
        return nome;
    }

    public double getValorMaximoPedido() {
        return valorMaximoPedido;
    }
}
