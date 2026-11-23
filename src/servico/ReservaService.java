package servico;

import excecao.ConflitoHorarioException;
import excecao.ItemNaoEncontradoException;
import excecao.RegraNegocioException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelo.FormaPagamento;
import modelo.Locavel;
import modelo.Reserva;

public class ReservaService {
    private List<Reserva> reservas;

    public ReservaService() {
        this.reservas = new ArrayList<>();
    }

    public void registrarReserva(Reserva novaReserva) throws RegraNegocioException {
        if (novaReserva == null) {
            throw new RegraNegocioException("Reserva inválida.");
        }
        if (novaReserva.getItensLocados().isEmpty()) {
            throw new RegraNegocioException("A reserva deve conter pelo menos um espaço ou equipamento.");
        }
        for (Reserva existente : reservas) {
            if (existente.getId() == novaReserva.getId()) {
                throw new RegraNegocioException("Já existe uma reserva com o ID " + novaReserva.getId() + ".");
            }
            if (existente.conflitaCom(novaReserva)) {
                Locavel itemConflitante = existente.itemEmComum(novaReserva);
                throw new ConflitoHorarioException(itemConflitante, novaReserva.getData(), novaReserva.getHoraInicio());
            }
        }
        reservas.add(novaReserva);
    }

    public void cancelarReserva(int id) throws RegraNegocioException {
        Reserva reserva = buscarReserva(id);
        if (reserva.estaPaga()) {
            throw new RegraNegocioException("Não é possível cancelar uma reserva que já foi paga.");
        }
        reservas.remove(reserva);
    }

    public void pagarReserva(int id, FormaPagamento forma, double valorPago) throws RegraNegocioException {
        Reserva reserva = buscarReserva(id);
        reserva.registrarPagamento(forma, valorPago);
    }

    public Reserva buscarReserva(int id) throws ItemNaoEncontradoException {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        throw new ItemNaoEncontradoException(id);
    }

    // Relatório ordenado por data e hora de início (R7)
    public List<Reserva> listarReservasOrdenadas() {
        List<Reserva> ordenadas = new ArrayList<>(reservas);
        ordenadas.sort(Comparator.comparing(Reserva::getData).thenComparing(Reserva::getHoraInicio));
        return ordenadas;
    }

    public double calcularFaturamento() {
        double total = 0.0;
        for (Reserva r : reservas) {
            if (r.estaPaga()) {
                total += r.calcularValorTotal();
            }
        }
        return total;
    }
}