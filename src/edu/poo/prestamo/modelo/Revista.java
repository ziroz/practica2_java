package edu.poo.prestamo.modelo;

public class Revista extends Material {

    private String categoria;
    private static final float VALOR_MULTA = 500;

    //Categoirias de las revistas
    public static final String CIENCIA = "CIENCIA";
    public static final String DEPORTE = "DEPORTE";
    public static final String SALUD = "SALUD";

    public Revista(String nombre, boolean critico, String categoria) {
        super(nombre, critico);
        this.categoria = categoria;
    }

    /**
     * Metodo que calcula la multa por dia de retreso, Tiendo en cuenta la
     * categoria de la revista, es decir, si es categoria CIENCIA 1000 pesos,
     * DEPORTE 1500 pesos, SALUD 2000 pesos, cualquier otra 500 por dia
     *
     * @return float, valor de la multa por d�a de retraso
     */
    @Override
    public float calcularMultaXDia() {
        float multa = VALOR_MULTA;
        if (categoria.equals(CIENCIA)) {
            multa = 2 * VALOR_MULTA;
        }
        if (categoria.equals(DEPORTE)) {
            multa = 3 * VALOR_MULTA;
        }
        if (categoria.equals(SALUD)) {
            multa = 4 * VALOR_MULTA;
        }
        return multa;
    }

    @Override
    public String darDetalle() {
        String detalle = "NOMBRE:" + "\t" + darNombre() + "\n"
                + "CODIGO:" + "\t" + darCodigo() + "\n"
                + "MATERIAL:" + "\t" + (super.critico ? "CRITICO" : "NORMAL" + "\n"
                        + "CATEROGIA:" + "\t" + categoria);
        return detalle;
    }

}
