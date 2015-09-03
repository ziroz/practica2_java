package edu.poo.prestamo.modelo;

public class Libro extends Material {

    private int numeroPaginas;
    private static final float VALOR_MULTA = 1000;

    public Libro(String nombre, boolean critico, int numeroPaginas) {
        super(nombre, critico);
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Metodo que calcula la multa por dia de retreso, Tiendo en cuenta el
     * numero de paginas del libro, es decir, por cada 100 paginas que tenga el
     * libro debe pagar 1000 pesos diarios.
     *
     * @return float, valor de la multa por d�a de retraso
     */
    
    
    //
    @Override
    public String darDetalle() {
        String detalle = "NOMBRE:" + "\t" + darNombre() + "\n"
                + "CODIGO:" + "\t" + darCodigo() + "\n"
                + "MATERIAL:" + "\t" + (critico ? "CRITICO" : "NORMAL" + "\n"
                        + "No. Paginas:" + "\t" + numeroPaginas);
        return detalle;
    }
}
