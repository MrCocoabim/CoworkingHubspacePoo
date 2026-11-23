package excecao;

public class PagamentoInvalidoException extends RegraNegocioException {
    public PagamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}