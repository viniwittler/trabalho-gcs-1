
package dados;

import java.util.List;
import java.util.stream.Collectors;

public class EstatisticasAdministrador {

    public static void pedidosTotaisStatus(List<Pedido> pedidos) {
        long totalAprovados = pedidos.stream().filter(p -> p.getStatus() == Pedido.Status.APROVADO).count();
        long totalReprovados = pedidos.stream().filter(p -> p.getStatus() == Pedido.Status.REPROVADO).count();
        long totalPedidos = pedidos.size();

        double percentualAprovados = (totalPedidos > 0) ? (double) totalAprovados / totalPedidos * 100 : 0;
        double percentualReprovados = (totalPedidos > 0) ? (double) totalReprovados / totalPedidos * 100 : 0;

        System.out.println("Total de Pedidos: " + totalPedidos);
        System.out.println("Aprovados: " + totalAprovados + " (" + percentualAprovados + "%)");
        System.out.println("Reprovados: " + totalReprovados + " (" + percentualReprovados + "%)");
    }

    public static void pedidosUltimos30Dias(List<Pedido> pedidos, String dataAtual) {
        List<Pedido> pedidosUltimos30Dias = pedidos.stream()
            .filter(p -> isDentroDosUltimos30Dias(p.getDataPedido(), dataAtual))
            .collect(Collectors.toList());

        double valorMedio = pedidosUltimos30Dias.stream()
            .mapToDouble(Pedido::getValorTotal)
            .average()
            .orElse(0.0);

        System.out.println("Pedidos nos últimos 30 dias: " + pedidosUltimos30Dias.size());
        System.out.println("Valor médio: " + valorMedio);
    }
    
    private static boolean isDentroDosUltimos30Dias(String dataPedido, String dataAtual) {
        return true; // Substituir por lógica real de comparação de datas
    }

    public static void valorTotalPorCategoria(List<Pedido> pedidos, String dataAtual) {
        pedidos.stream()
            .filter(p -> isDentroDosUltimos30Dias(p.getDataPedido(), dataAtual))
            .collect(Collectors.groupingBy(p -> p.getDepartamento().getNome(), Collectors.summingDouble(Pedido::getValorTotal)))
            .forEach((departamento, valorTotal) -> {
                System.out.println("Departamento: " + departamento + " | Valor total: " + valorTotal);
            });
    }

    public static void pedidoMaiorValorAberto(List<Pedido> pedidos) {
        Pedido maiorPedidoAberto = pedidos.stream()
            .filter(p -> p.getStatus() == Pedido.Status.ABERTO)
            .max((p1, p2) -> Double.compare(p1.getValorTotal(), p2.getValorTotal()))
            .orElse(null);

        if (maiorPedidoAberto != null) {
            System.out.println("Pedido de maior valor aberto:");
            System.out.println(maiorPedidoAberto);
        } else {
            System.out.println("Não há pedidos abertos.");
        }
    }
}
