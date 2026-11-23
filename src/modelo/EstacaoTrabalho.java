package modelo;

public class EstacaoTrabalho extends Espaco {
    private boolean possuiMonitorExtra;

    public EstacaoTrabalho(int codigo, String nome, int capacidade, double valorHoraBase, boolean possuiMonitorExtra) {
        super(codigo, nome, capacidade, valorHoraBase);
        this.possuiMonitorExtra = possuiMonitorExtra;
    }

    public boolean isPossuiMonitorExtra() {
        return possuiMonitorExtra;
    }

    @Override
    public double calcularTarifa(int horas) {
        double adicional = possuiMonitorExtra ? 5.0 * horas : 0.0;
        return (valorHoraBase * horas) + adicional;
    }

    @Override
    public String toString() {
        return super.toString() + " | Estação de Trabalho" + (possuiMonitorExtra ? " (c/ Monitor Extra)" : "");
    }
}