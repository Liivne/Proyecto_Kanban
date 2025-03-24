package org.udec.Modelo;
//hay 3 columnas en total, cada una representa un estado

import java.util.List;

public class Columna {
    private List<Tarea> tareas;
    private EstadoTarea estado;
    public Columna(){}
    public void eliminarTarea(Tarea t){tareas.remove(t);}
    public void agregarTarea(Tarea t){tareas.add(t);}
    public int contarTareas(List<Tarea> trs){
        int total = 0 ;
        for(org.udec.Modelo.Tarea Tarea: trs){total++;};
        return total;
    }
}
