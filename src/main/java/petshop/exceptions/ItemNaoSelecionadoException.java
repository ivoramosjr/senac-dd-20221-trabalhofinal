package petshop.exceptions;

public class ItemNaoSelecionadoException extends NullPointerException {

    public ItemNaoSelecionadoException(String message) {
        super(message);
    }

    public ItemNaoSelecionadoException() {
        super("Item não selecionado.");
    }

}
