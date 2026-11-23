import excecao.RegraNegocioException;
import java.time.LocalDate;
import java.time.LocalTime;
import modelo.*;
import servico.EspacoService;
import servico.ReservaService;

public class Main {
    public static void main(String[] args) {
        EspacoService espacoService = new EspacoService();
        ReservaService reservaService = new ReservaService();

        try {
            // 1. Cadastro de Espaços (Herança + Classe Abstrata)
            Espaco estacao1 = new EstacaoTrabalho(101, "Estação Alpha", 1, 25.0, true);
            Espaco sala1 = new SalaReuniao(201, "Sala Inovação", 8, 80.0, true);
            Espaco auditorio1 = new Auditorio(301, "Auditório Principal", 50, 200.0, 150.0);

            espacoService.cadastrar(estacao1);
            espacoService.cadastrar(sala1);
            espacoService.cadastrar(auditorio1);

            // 2. Equipamentos (Segunda hierarquia implementando Locavel)
            Equipamento projetor = new Equipamento(501, "Projetor 4K", 30.0);

            // 3. Demonstração de Polimorfismo (R6)
            System.out.println(espacoService.gerarTabelaPrecos(3));

            // 4. Demonstração de Sobrecarga e Busca Case-Insensitive (R2 e R7)
            System.out.println("=== BUSCA POR NOME ('sala') ===");
            for (Espaco e : espacoService.buscarEspaco("sala")) {
                System.out.println(e);
            }

            // 5. Criação de Clientes e Reservas
            Cliente c1 = new Cliente("111.222.333-44", "Ryan Campos", "ryan@email.com");
            Cliente c2 = new Cliente("555.666.777-88", "Ana Silva", "ana@email.com");

            LocalDate hoje = LocalDate.now();

            Reserva r1 = new Reserva(1, hoje, LocalTime.of(14, 0), 2, c1);
            r1.adicionarItem(sala1);
            r1.adicionarItem(projetor);
            reservaService.registrarReserva(r1);

            Reserva r2 = new Reserva(2, hoje, LocalTime.of(9, 0), 3, c2);
            r2.adicionarItem(estacao1);
            reservaService.registrarReserva(r2);

            // 6. Teste de Exceção Personalizada: Conflito de Horário (R8)
            System.out.println("\n=== TESTE DE CONFLITO DE HORÁRIO ===");
            try {
                Reserva rConflito = new Reserva(3, hoje, LocalTime.of(15, 0), 1, c2);
                rConflito.adicionarItem(sala1); // Sala 201 já ocupada das 14h às 16h!
                reservaService.registrarReserva(rConflito);
            } catch (RegraNegocioException ex) {
                System.out.println("Exceção capturada com sucesso: " + ex.getMessage());
            }

            // 7. Pagamento e Relatório Ordenado (R7)
            reservaService.pagarReserva(1, FormaPagamento.PIX, r1.calcularValorTotal());

            System.out.println("\n=== RESERVAS ORDENADAS POR HORÁRIO ===");
            for (Reserva r : reservaService.listarReservasOrdenadas()) {
                System.out.println(r);
            }

            System.out.printf("\nFaturamento confirmado: R$ %.2f\n", reservaService.calcularFaturamento());

        } catch (RegraNegocioException | IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        }
    }
}