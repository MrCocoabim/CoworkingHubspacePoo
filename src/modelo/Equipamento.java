package modelo;

public class Equipamento implements Locavel {
    private int codigo;
    private String nome;
    private double valorHora;

    public Equipamento(int codigo, String nome, double valorHora) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código do equipamento deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do equipamento não pode ser vazio.");
        }
        if (valorHora <= 0) {
            throw new IllegalArgumentException("O valor por hora do equipamento deve ser maior que zero.");
        }
        this.codigo = codigo;
        this.nome = nome.trim();
        this.valorHora = valorHora;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getValorHora() {
        return valorHora;
    }

    @Override
    public double calcularValorLocacao(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("A duração deve ser de pelo menos 1 hora.");
        }
        return valorHora * horas;
    }

    @Override
    public String getDescricaoItem() {
        return "[" + codigo + "] Equipamento: " + nome;
    }

    @Override
    public String toString() {
        return getDescricaoItem() + String.format(" - R$ %.2f/h", valorHora);
    }
}