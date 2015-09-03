package edu.poo.prestamo.modelo;

import java.util.Calendar;
import java.util.Date;

public class Prestamo {

    private Calendar fechaPrestamo;
    private Calendar fechaEntrega;
    private IMaterial materialPrestado;

    public Prestamo(Date fechaPrestamo, IMaterial materialPrestado) {
        this.fechaPrestamo = Calendar.getInstance();
        this.fechaPrestamo.setTime(fechaPrestamo);
        this.materialPrestado = materialPrestado;

        //La fecha de entrega es 15 dias despu�s de la fecha del prestamo
        this.fechaEntrega = Calendar.getInstance();
        this.fechaEntrega.setTime(fechaPrestamo);
        this.fechaEntrega.add(Calendar.DAY_OF_MONTH, 15);
    }

    /**
     * M�todo que calcula los dias que tiene un material de retrazo con respecto
     * a la fecha de entrega
     *
     * @return long, dias de retraso
     */
    public long calcularDiasRetraso() {
        long diasRetraso = 0;
        long milisRetraso = Calendar.getInstance().getTimeInMillis() - fechaEntrega.getTimeInMillis();
        if (milisRetraso > 0) {
            diasRetraso = milisRetraso / (24 * 60 * 60 * 1000);
            return diasRetraso;
        } else {
            return 0;
        }
    }

    /**
     * M�todo que calcula la multa que tiene el materia, seg�n los d�as que
     * lleva de retraso y el valor la multa por dia del material
     *
     * @return float, multa que lleva el material hasta el momento.
     */
    public float calcularMulta() {
        return calcularDiasRetraso() * materialPrestado.calcularMultaXDia();
    }

    public IMaterial getMaterialPrestado() {
        return materialPrestado;
    }

    public String darDetalle() {
        return fechaPrestamo.toString() + "\n" + fechaEntrega.toString();
    }

    @Override
    public String toString() {
        return materialPrestado.toString();
    }

    public Calendar darFechaEntrega() {
        return fechaEntrega;
    }
}
