package edu.poo.prestamo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<IMaterial> listaMaterial;
    private List<Prestamo> listaPrestamos;

    public Biblioteca() {
        listaMaterial = new ArrayList<IMaterial>();
        listaPrestamos = new ArrayList<Prestamo>();
    }

    /**
     * Metodo que calcula la multa total de todos los materiales que tengan
     * retraso en la biblioteca
     *
     * @return float, multa total del materias retrasado de la biblioteca
     */
   public float calcularTotalMulta(){
       return 0;
   }

    //
    public void anadirMaterial(IMaterial material) {
        listaMaterial.add(material);
    }

    public void anadirPrestamo(Prestamo prestamo) {
        prestamo.getMaterialPrestado().setDisponible(false);
        listaPrestamos.add(prestamo);
    }

    public List<IMaterial> getListaMaterial() {
        return listaMaterial;
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public IMaterial[] toArrayListaMaterial() {
        IMaterial[] materiales = new IMaterial[listaMaterial.size() + 1];
        materiales[0] = null;
        for (int j = 1; j < listaMaterial.size() + 1; j++) {
            materiales[j] = listaMaterial.get(j - 1);
        }
        return materiales;
    }

    public Prestamo[] toArrayListaPrestamo() {
        Prestamo[] prestamos = new Prestamo[listaPrestamos.size() + 1];
        prestamos[0] = null;
        for (int j = 1; j < listaPrestamos.size() + 1; j++) {
            prestamos[j] = listaPrestamos.get(j - 1);
        }
        return prestamos;
    }

}
