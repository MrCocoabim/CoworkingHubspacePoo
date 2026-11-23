package modelo;

import excecao.PagamentoInvalidoException;
import excecao.RegraNegocioException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reserva {
    private int id;
    private LocalDate data;
    private LocalTime horaInicio;
    private int duracaoHoras;
    private Cliente cliente;
    private List<Locavel> itensLocados;
    private Pagamento pagamento;

    public Reserva(int id, LocalDate data, LocalTime horaInicio, int duracaoHoras, Cliente cliente) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID da reserva deve ser maior que zero.");
        }
        if (data == null || horaInicio == null) {
            throw new IllegalArgumentException("Data e hora de início são obrigatórias.");
        }
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("A duração da reserva deve ser de pelo menos 1 hora.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("A reserva deve estar vinculada a um cliente.");
        }
        this.id = id;
        this.data = data;
        this.horaInicio = horaInicio;
        this.duracaoHoras = duracaoHoras;
        this.cliente = cliente;
        this.itensLocados = new ArrayList<>();
    }

    public void adicionarItem(Locavel item) throws RegraNegocioException {
        if (item == null) {
            throw new RegraNegocioException("Item locável inválido.");
        }
        for (Locavel existente : itensLocados) {
            if (existente.getCodigo() == item.getCodigo() && existente.getClass().equals(item.getClass())) {
                throw new RegraNegocioException("O item '" + item.getDescricaoItem() + "' já foi adicionado a esta reserva.");
            }
        }
        itensLocados.add(item);
    }

    // Polimorfismo puro (R6): sem instanceof, sem getClass() e sem cast
    public double calcularValorTotal() {
        double total = 0.0;
        for (Locavel item : itensLocados) {
            total += item.calcularValorLocacao(duracaoHoras);
        }
        return total;
    }

    public LocalTime getHoraFim() {
        return horaInicio.plusHours(duracaoHoras);
    }

    public Locavel itemEmComum(Reserva outra) {
        for (Locavel meuItem : this.itensLocados) {
            for (Locavel outroItem : outra.itensLocados) {
                if (meuItem.getCodigo() == outroItem.getCodigo()
                        && meuItem.getDescricaoItem().equals(outroItem.getDescricaoItem())) {
                    return meuItem;
                }
            }
        }
        return null;
    }

    public boolean conflitaCom(Reserva outra) {
        if (!this.data.equals(outra.data)) {
            return false;
        }
        if (itemEmComum(outra) == null) {
            return false;
        }
        // Verifica sobreposição de intervalos: [inicio1, fim1) e [inicio2, fim2)
        return this.horaInicio.isBefore(outra.getHoraFim()) && outra.horaInicio.isBefore(this.getHoraFim());
    }

    public void registrarPagamento(FormaPagamento forma, double valorPago) throws PagamentoInvalidoException {
        if (estaPaga()) {
            throw new PagamentoInvalidoException("Esta reserva já está quitada.");
        }
        double total = calcularValorTotal();
        if (valorPago < total) {
            throw new PagamentoInvalidoException(
                String.format("Valor insuficiente. Total da reserva: R$ %.2f | Valor informado: R$ %.2f", total, valorPago)
            );
        }
        // Composição (R3): Pagamento é instanciado e pertence exclusivamente à Reserva
        this.pagamento = new Pagamento(total, forma);
    }

    public boolean estaPaga() {
        return pagamento != null;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Locavel> getItensLocados() {
        return Collections.unmodifiableList(itensLocados);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    @Override
    public String toString() {
        String status = estaPaga() ? "PAGA (" + pagamento + ")" : "PENDENTE";
        return String.format("Reserva #%d | %s (%s às %s) | Cliente: %s | Total: R$ %.2f | Status: %s",
                id, data, horaInicio, getHoraFim(), cliente.getNome(), calcularValorTotal(), status);
    }
}