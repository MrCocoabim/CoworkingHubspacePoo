package modelo;

public class SalaReuniao extends Espaco {
    private boolean possuiVideoconferencia;

    public SalaReuniao(int codigo, String nome, int capacidade, double valorHoraBase, boolean possuiVideoconferencia) {
        super(codigo, nome, capacidade, valorHoraBase);
        this.possuiVideoconferencia = possuiVideoconferencia;
    }

    public boolean isPossuiVideoconferencia() {
        return possuiVideoconferencia;
    }

    @Override
    public double calcularTarifa(int horas) {
        double fator = possuiVideoconferencia ? 1.15 : 1.0; // +15% se tiver kit videoconferência
        return (valorHoraBase * horas) * fator;
    }

    @Override
    public String toString() {
        return super.toString() + " | Sala de Reunião" + (possuiVideoconferencia ? " (c/ Videoconferência)" : "");
    }
}