package edu.poo.prestamo.modelo;

public interface IMaterial {

    public void generarCodigo();

    public String darNombre();

    public float calcularMultaXDia();

    public String darCodigo();

    public String darDetalle();

    public void setDisponible(boolean disponible);

    public boolean darDisponible();
}
