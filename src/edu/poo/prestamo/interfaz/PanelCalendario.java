package edu.poo.prestamo.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class PanelCalendario extends JFrame implements ActionListener {

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * El comando para asignar nota a cada una de las evaluaciones
     */
    public static final String ACEPTAR = "ACEPTAR";

    private Date fechaPrestamo;
    private PanelMaterial panelMaterial;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------
    private JTextField fechalabel;
    private JButton botonCalcularMulta;
    private JPanel panel;
    private JDateChooser dateChooser;

    public PanelCalendario(PanelMaterial panelMaterial) {
        this.panelMaterial = panelMaterial;
        panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        panel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 2, 0), new TitledBorder("Ingrese la fecha de inicio del prestamo")));

        GridBagConstraints gbc;

        dateChooser = new JDateChooser();
        dateChooser.setBounds(20, 20, 200, 100);
        gbc = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        panel.add(dateChooser, gbc);

        botonCalcularMulta = new JButton("ACEPTAR");
        gbc = new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
        panel.add(botonCalcularMulta, gbc);
        botonCalcularMulta.setActionCommand(PanelCalendario.ACEPTAR);
        botonCalcularMulta.addActionListener(this);
        botonCalcularMulta.setVisible(true);

        getContentPane().add(panel, BorderLayout.NORTH);

        setTitle("Fecha Prestamo");
        setSize(300, 120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evento) {
                dispose();
            }
        });
    }

    public Date gerFechaPrestamo() {
        return fechaPrestamo;
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

        if (comando.equals(PanelCalendario.ACEPTAR)) {
            fechaPrestamo = dateChooser.getDate();
            panelMaterial.acutalizarPrestamos();
            dispose();
        }
    }
}
