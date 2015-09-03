package edu.poo.prestamo.interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.poo.prestamo.modelo.Biblioteca;
import edu.poo.prestamo.modelo.Prestamo;

public class PanelPrestamo extends JPanel implements ActionListener {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private Biblioteca biblioteca;
    private DefaultComboBoxModel model;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------
    private JComboBox<Prestamo> comboPrestamos;
    private JLabel detallePrestamo;
    private JLabel multaPrestamo;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * Construye el panel de prestamos
     */
    public PanelPrestamo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        iniciarComponentes();
    }

    /**
     * M�todo que construye los elementos graficos que tiene el panel prestamo
     */
    private void iniciarComponentes() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 2, 0), new TitledBorder("Material Prestado")));

        GridBagConstraints gbc;

        comboPrestamos = new JComboBox<Prestamo>((Prestamo[]) biblioteca.toArrayListaPrestamo());
        gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(comboPrestamos, gbc);
        comboPrestamos.addActionListener(this);

        detallePrestamo = new JLabel("");
        gbc = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(detallePrestamo, gbc);

        multaPrestamo = new JLabel("");
        multaPrestamo.setForeground(Color.red);
        gbc = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(multaPrestamo, gbc);
    }

    /**
     * Este m�todo se llama cuando se presiona uno de los botones. <br>
     * <b>post: </b> Se ejecut� la acci�n correspondiente al bot�n presionado.
     * <br>
     *
     * @param evento El evento del click en el bot�n. evento != null.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() instanceof JComboBox) {
            Prestamo prestamo = (Prestamo) comboPrestamos.getSelectedItem();
            if (prestamo != null) {
                if (prestamo.calcularMulta() > 0) {
                    DecimalFormat formato = new DecimalFormat("#,###.##");
                    detallePrestamo.setText("El material tiene un retrasado de: " + prestamo.calcularDiasRetraso() + " dia(s).");
                    multaPrestamo.setText("MULTA ACOMULADA: $" + formato.format(prestamo.calcularMulta()));
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                    detallePrestamo.setText("El material se debe entregar el: " + sdf.format(prestamo.darFechaEntrega().getTime()));
                    multaPrestamo.setText("");
                }
            } else {
                multaPrestamo.setText("");
                detallePrestamo.setText("");
            }
        }
    }

    public void actualizarPrestamos(Prestamo prestamo) {
        biblioteca.anadirPrestamo(prestamo);
        comboPrestamos.addItem(prestamo);
    }

}
