/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.izquierdofp.retrovault.modelo;

/**
 *
 * @author 2DAM
 */
public class Juego {
    private int id;
    private String titulo;
    private String consola;
    private String genero;
    private int anio;
    private int puntuacion;
    private String estado;

    public Juego(int id, String titulo, String consola, String genero, int anio, int puntuacion, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.consola = consola;
        this.genero = genero;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.estado = estado;
    }

    public Juego() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

@Override
public String toString() {
    return titulo + " (" + consola + ")";
}
    
    
}
