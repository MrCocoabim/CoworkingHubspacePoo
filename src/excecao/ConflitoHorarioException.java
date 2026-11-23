package excecao;

import java.time.LocalDate;
import java.time.LocalTime;
import modelo.Locavel;

public class ConflitoHorarioException extends RegraNegocioException {
    public ConflitoHorarioException(Locavel item, LocalDate data, LocalTime horaInicio) {
        super("Conflito de horário para o item '" + item.getDescricaoItem() 
              + "' na data " + data + " às " + horaInicio + ".");
    }
}