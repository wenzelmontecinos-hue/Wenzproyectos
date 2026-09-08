package com.gestion.strategy;

import com.gestion.model.RecursoHumano;
import java.util.List;

public class EstrategiaMenorCosto implements EstrategiaSeleccion {
    @Override
    public RecursoHumano seleccionarMejorCandidato(List<RecursoHumano> candidatos, int horasTurno) {
        if (candidatos == null || candidatos.isEmpty()) return null;
        
        RecursoHumano candidatoEconomico = candidatos.get(0);
        for (RecursoHumano r : candidatos) {
            if (r.calcularCostoTurno(horasTurno) < candidatoEconomico.calcularCostoTurno(horasTurno)) {
                candidatoEconomico = r;
            }
        }
        return candidatoEconomico;
    }
}