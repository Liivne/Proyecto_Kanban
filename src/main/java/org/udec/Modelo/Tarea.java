package org.udec.Modelo;

public class Tarea {
    private String mensaje;
    private String titulo;
    private EstadoTarea estado;
    public Tarea(String mensaje, String titulo){
        if (mensaje == null || mensaje.isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        this.mensaje = mensaje;
        this.titulo = titulo;
        this.estado = EstadoTarea.POR_HACER;
    }

    public void setEstado(EstadoTarea nuevoEstado){
        this.estado = nuevoEstado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public EstadoTarea getEstado() {
        return estado;
    }
}