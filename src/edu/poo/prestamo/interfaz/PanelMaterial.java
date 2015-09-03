package edu.poo.prestamo.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.poo.prestamo.modelo.Biblioteca;
import edu.poo.prestamo.modelo.IMaterial;
import edu.poo.prestamo.modelo.Prestamo;

public class PanelMaterial extends JPanel implements ActionListener {

	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    /**
     * El comando para asignar nota a cada una de las evaluaciones
     */
    public static final String PRESTAR = "PRESTAR";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private Biblioteca biblioteca;
    private PanelCalendario calendario;
    private InterfazPrestamo principal;
    private IMaterial materialSeleccionado;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------
    private JComboBox<IMaterial> comboMaterial;
    private JTextArea textAreaMaterial;
    private JButton botonPrestar;

	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * Construye el panel de material de la biblioteca
     *
     * @param interfazPrestamo
     * @param biblioteca
     */
    public PanelMaterial(InterfazPrestamo interfazPrestamo, Biblioteca biblioteca) {
        this.principal = interfazPrestamo;
        this.biblioteca = biblioteca;
        iniciarComponentes();
    }

    /**
     * M�todo que construye los elementos graficos que tiene el panel material
     */
    private void iniciarComponentes() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 2, 0), new TitledBorder("Material de la Biblioteca")));

        GridBagConstraints gbc;

        comboMaterial = new JComboBox<IMaterial>((IMaterial[]) biblioteca.toArrayListaMaterial());
        gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(comboMaterial, gbc);
        comboMaterial.addActionListener(this);

        textAreaMaterial = new JTextArea("");
        textAreaMaterial.setEditable(false);
        textAreaMaterial.setAutoscrolls(true);
        textAreaMaterial.setVisible(true);
        gbc = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(textAreaMaterial, gbc);

        botonPrestar = new JButton("PRESTAR");
        gbc = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        add(botonPrestar, gbc);
        botonPrestar.setActionCommand(PanelMaterial.PRESTAR);
        botonPrestar.addActionListener(this);
        botonPrestar.setVisible(false);

        calendario = new PanelCalendario(this);
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
        String comando = evento.getActionCommand();

        if (comando.equals(PanelMaterial.PRESTAR)) {
            calendario.setVisible(true);
        }
        if (evento.getSource() instanceof JComboBox) {
            materialSeleccionado = (IMaterial) comboMaterial.getSelectedItem();
            if (materialSeleccionado != null) {
                String descripcionMaterial = materialSeleccionado.darDetalle();
                textAreaMaterial.setText(descripcionMaterial);

                if (materialSeleccionado.darDisponible()) {
                    botonPrestar.setVisible(true);
                } else {
                    botonPrestar.setVisible(false);
                }

            } else {
                textAreaMaterial.setText("");
                botonPrestar.setVisible(false);
            }
        }
    }

    public void acutalizarPrestamos() {
        Date fechaPrestamo = calendario.gerFechaPrestamo();
        if (fechaPrestamo != null && materialSeleccionado != null) {
            Prestamo prestamo = new Prestamo(fechaPrestamo, materialSeleccionado);
            botonPrestar.setVisible(false);
            principal.actualizarPrestamos(prestamo);
        }
    }

}
