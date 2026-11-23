package modelo;

public interface Locavel {
    int getCodigo();
    double calcularValorLocacao(int horas);
    String getDescricaoItem();
}