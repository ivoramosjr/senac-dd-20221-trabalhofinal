package petshop.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HorarioJaMarcadoException extends Exception{

    public HorarioJaMarcadoException(String message){
        super(message);
    }

    public HorarioJaMarcadoException(LocalDateTime data){
        super("Já possui atendimento com essa data: "+data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
