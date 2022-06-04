package petshop.exceptions;

public class RegistroNaoEncontradoException extends Exception{

    public RegistroNaoEncontradoException(String message){
        super(message);
    }

    public RegistroNaoEncontradoException(){
        super("Registro não encontrado!");
    }
}
