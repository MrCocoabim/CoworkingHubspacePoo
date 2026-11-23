package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pagamento {
    private double valor;
    private FormaPagamento forma;
    private LocalDateTime dataHora;

    public Pagamento(double valor, FormaPagamento forma) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        if (forma == null) {
            throw new IllegalArgumentException("A forma de pagamento é obrigatória.");
        }
        this.valor = valor;
        this.forma = forma;
        this.dataHora = LocalDateTime.now();
    }

    public double getValor() {
        return valor;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("R$ %.2f via %s em %s", valor, forma, dataHora.format(fmt));
    }
}