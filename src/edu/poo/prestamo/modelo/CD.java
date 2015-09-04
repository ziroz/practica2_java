package edu.poo.prestamo.modelo;

public class CD extends Material{

    //Tamanio del Cd en MB
    private int megabytes;
    //
    private static final float VALOR_MULTA = 1500;

    
    public CD(String nombre, boolean critico, int megabytes) {
        super(nombre, critico);
        this.megabytes = megabytes;
    }

    @Override
    public String darDetalle() {
         String detalle = "NOMBRE:" + "\t" + darNombre() + "\n"
                + "CODIGO:" + "\t" + darCodigo() + "\n"
                + "CRITICO:" + "\t" + (critico ? "CRITICO" : "NORMAL" + "\n"
                        + "MEGABYTES:" + "\t" + megabytes);
        return detalle;
    }
    
    /**
     * Metodo que calcula la multa por dia de retreso, Tiendo en cuenta el
     * tamaño en MegaByte del CD, es decir, por cada 50 MB que tenga el CD debe
     * pagar 1500 pesos diarios.
     *
     * @return float, valor de la multa por d�a de retraso
     */
    
    @Override
    public float calcularMultaXDia() {
        return this.megabytes / 50 * VALOR_MULTA;
    }
}
