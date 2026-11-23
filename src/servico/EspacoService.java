package servico;

import excecao.ItemNaoEncontradoException;
import excecao.RegraNegocioException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.Espaco;

public class EspacoService {
    private List<Espaco> espacos;

    public EspacoService() {
        this.espacos = new ArrayList<>();
    }

    public void cadastrar(Espaco espaco) throws RegraNegocioException {
        if (espaco == null) {
            throw new RegraNegocioException("Espaço inválido.");
        }
        for (Espaco existente : espacos) {
            if (existente.getCodigo() == espaco.getCodigo()) {
                throw new RegraNegocioException("Já existe um espaço cadastrado com o código " + espaco.getCodigo() + ".");
            }
        }
        espacos.add(espaco);
    }

    public List<Espaco> listar() {
        return Collections.unmodifiableList(espacos);
    }

    // Sobrecarga 1: busca por código exato (R2)
    public Espaco buscarEspaco(int codigo) throws ItemNaoEncontradoException {
        for (Espaco e : espacos) {
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        throw new ItemNaoEncontradoException(codigo);
    }

    // Sobrecarga 2: busca por nome ignorando maiúsculas/minúsculas (R2 e R7)
    public List<Espaco> buscarEspaco(String nome) {
        List<Espaco> encontrados = new ArrayList<>();
        if (nome == null || nome.trim().isEmpty()) {
            return encontrados;
        }
        String termo = nome.trim().toLowerCase();
        for (Espaco e : espacos) {
            if (e.getNome().toLowerCase().contains(termo)) {
                encontrados.add(e);
            }
        }
        return encontrados;
    }

    public void alterarValorHora(int codigo, double novoValor) throws ItemNaoEncontradoException {
        Espaco espaco = buscarEspaco(codigo);
        espaco.setValorHoraBase(novoValor);
    }

    public void remover(int codigo) throws ItemNaoEncontradoException {
        Espaco espaco = buscarEspaco(codigo);
        espacos.remove(espaco);
    }

    // Polimorfismo limpo (R6): invoca calcularValorLocacao sem instanceof ou cast
    public String gerarTabelaPrecos(int horas) {
        StringBuilder sb = new StringBuilder("=== TABELA DE PREÇOS (" + horas + "h) ===\n");
        for (Espaco e : espacos) {
            sb.append(e.getDescricaoItem())
              .append(String.format(" -> Total: R$ %.2f\n", e.calcularValorLocacao(horas)));
        }
        return sb.toString();
    }
}