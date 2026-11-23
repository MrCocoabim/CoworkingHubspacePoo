package modelo;

public class Auditorio extends Espaco {
    private double taxaLimpeza;

    public Auditorio(int codigo, String nome, int capacidade, double valorHoraBase, double taxaLimpeza) {
        super(codigo, nome, capacidade, valorHoraBase);
        if (taxaLimpeza < 0) {
            throw new IllegalArgumentException("A taxa de limpeza não pode ser negativa.");
        }
        this.taxaLimpeza = taxaLimpeza;
    }

    public double getTaxaLimpeza() {
        return taxaLimpeza;
    }

    @Override
    public double calcularTarifa(int horas) {
        return (valorHoraBase * horas) + taxaLimpeza;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Auditório (Taxa limpeza: R$ %.2f)", taxaLimpeza);
    }
}