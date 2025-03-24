package org.udec.Modelo;
//hay 3 columnas en total, cada una representa un estado

import java.util.ArrayList;
import java.util.List;

public class Columna {
    private List<Tarea> tareas;

    public Columna(EstadoTarea estado){
        this.tareas = new ArrayList<>();
    }
    public void eliminarTarea(Tarea t){tareas.remove(t);}
    public void agregarTarea(Tarea t){tareas.add(t);}
    public int contarTareas(){
        int total = 0 ;
        for(Tarea tarea: tareas){total++;};
        return total;
    }
}
