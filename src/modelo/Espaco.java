package modelo;

public abstract class Espaco implements Locavel {
    private int codigo;
    private String nome;
    private int capacidade;
    protected double valorHoraBase;

    public Espaco(int codigo, String nome, int capacidade, double valorHoraBase) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código do espaço deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do espaço não pode ser vazio.");
        }
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        setValorHoraBase(valorHoraBase);

        this.codigo = codigo;
        this.nome = nome.trim();
        this.capacidade = capacidade;
    }

    public abstract double calcularTarifa(int horas);

    @Override
    public double calcularValorLocacao(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("A duração da locação deve ser de pelo menos 1 hora.");
        }
        return calcularTarifa(horas);
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getValorHoraBase() {
        return valorHoraBase;
    }

    public void setValorHoraBase(double valorHoraBase) {
        if (valorHoraBase <= 0) {
            throw new IllegalArgumentException("O valor por hora deve ser maior que zero.");
        }
        this.valorHoraBase = valorHoraBase;
    }

    @Override
    public String getDescricaoItem() {
        return "[" + codigo + "] " + nome + " (Cap.: " + capacidade + " pessoas)";
    }

    @Override
    public String toString() {
        return getDescricaoItem() + String.format(" - R$ %.2f/h", valorHoraBase);
    }
}