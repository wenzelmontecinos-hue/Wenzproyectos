package com.gestion.strategy;

import com.gestion.model.RecursoHumano;
import java.util.List;

public interface EstrategiaSeleccion {
    RecursoHumano seleccionarMejorCandidato(List<RecursoHumano> candidatos, int horasTurno);
}