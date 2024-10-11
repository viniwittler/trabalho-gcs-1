package dados;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    enum Status {
        ABERTO, APROVADO, REPROVADO, CONCLUIDO
    }

    private Funcionario solicitante;
    private Departamento departamento;
    private String dataPedido;
    private String dataConclusao;
    private Status status;
    private List<Item> itens;
    private double valorTotal;

    public Pedido(Funcionario solicitante, String dataPedido) {
        this.solicitante = solicitante;
        this.departamento = solicitante.getDepartamento();
        this.dataPedido = dataPedido;
        this.status = Status.ABERTO;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        valorTotal = itens.stream().mapToDouble(Item::getValorTotal).sum();
    }

    public Funcionario getSolicitante() {
        return solicitante;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setStatus(Status status) throws Exception {
        if (this.status != Status.ABERTO) {
            throw new Exception("Não é possível alterar o status do pedido.");
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nStatus: " + status);
        sb.append("\nData do Pedido: " + dataPedido);
        sb.append("\nSolicitante: " + solicitante.getNome());
        sb.append("\nDepartamento: " + departamento.getNome());

        if (dataConclusao != null) {
            sb.append("Data de Conclusão: " + dataConclusao);
        }
        return sb.toString();
    }
}
