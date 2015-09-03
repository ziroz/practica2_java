package edu.poo.prestamo.modelo;

import java.util.Random;

public class Material {

    public Material(String nombre, boolean critico) {
        this.nombre = nombre;
        this.critico = critico;
        this.disponible = true;
        generarCodigo();
    }

    /**
     * Metodo que genera el c�digo �nico que identifica el material dentro de la
     * biblioteca
     */
    

    //
    @Override
    public String toString() {
        return nombre;
    }

}
