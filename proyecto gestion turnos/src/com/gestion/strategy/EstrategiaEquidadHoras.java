package com.gestion.strategy;

import com.gestion.model.RecursoHumano;
import java.util.List;

public class EstrategiaEquidadHoras implements EstrategiaSeleccion {
    @Override
    public RecursoHumano seleccionarMejorCandidato(List<RecursoHumano> candidatos, int horasTurno) {
        if (candidatos == null || candidatos.isEmpty()) return null;
        
        RecursoHumano candidatoEquitativo = candidatos.get(0);
        for (RecursoHumano r : candidatos) {
            if (r.getHorasAcumuladas() < candidatoEquitativo.getHorasAcumuladas()) {
                candidatoEquitativo = r;
            }
        }
        return candidatoEquitativo;
    }
}