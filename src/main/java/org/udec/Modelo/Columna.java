package org.udec.Modelo;
//hay 4 columnas en total, cada una representa un estado

import java.util.List;

public class Columna {
    private List<Tarea> tareas;
    private String nombreColumna;
    public void eliminarTarea(Tarea t){}
    public void agregarTarea(Tarea t){}
    public int contarTareas(List<Tarea> trs){
        int total = 0 ;
        for(org.udec.Modelo.Tarea Tarea: trs){total++;};
        return total;
    }
}
