package edu.poo.prestamo.modelo;

import java.util.Random;

public abstract class Material implements IMaterial{

    private String codigo;
    private String nombre;
    protected boolean critico;
    private boolean disponible;
    
    public Material(String nombre, boolean critico) {
        this.nombre = nombre;
        this.critico = critico;
        this.disponible = true;
        this.generarCodigo();
    }
    
    @Override
    public String darNombre(){
        return this.nombre;
    }
    
    @Override
    public String darCodigo(){
        return this.codigo;
    }
    
    public void setDisponible(boolean disponible){
    this.disponible = disponible;
    }

    
    public boolean darDisponible(){
        return this.disponible;
    }
    /**
     * Metodo que genera el c�digo �nico que identifica el material dentro de la
     * biblioteca
     */
    @Override
    public void generarCodigo(){
        Random rand = new Random();
        
        this.codigo = this.nombre.substring(0,3) + rand.nextInt();
    }

    //
    @Override
    public String toString() {
        return nombre;
    }

}
