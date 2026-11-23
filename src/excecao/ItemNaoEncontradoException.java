package excecao;

public class ItemNaoEncontradoException extends RegraNegocioException {
    public ItemNaoEncontradoException(int codigo) {
        super("Nenhum registro encontrado com o código: " + codigo);
    }
}