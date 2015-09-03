package edu.poo.prestamo.interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.poo.prestamo.modelo.Biblioteca;

public class PanelBiblioteca extends JPanel implements ActionListener{
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
     * El comando para asignar nota a cada una de las evaluaciones
     */
	public static final String CALCULAR_MULTA = "CALCULAR_MULTA";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private Biblioteca biblioteca;
	
	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------
	private JLabel multaBiblioteca;
	private JButton botonCalcularMulta;

    /**
     * Construye de biblioteca
     */
	public PanelBiblioteca(Biblioteca biblioteca){
		this.biblioteca = biblioteca;
		iniciarComponentes();
	}
	
	/**
    * M�todo que construye los elementos graficos que tiene el panel de biblioteca
    */
	private void iniciarComponentes() {
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(new EmptyBorder(0, 0, 2, 0),new TitledBorder("Biblioteca")));
		
		GridBagConstraints gbc;
		
		botonCalcularMulta = new JButton("CALCULAR MULTA TOTAL");
		gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		add(botonCalcularMulta, gbc);
		botonCalcularMulta.setActionCommand(PanelBiblioteca.CALCULAR_MULTA);
		botonCalcularMulta.addActionListener(this);
		
		multaBiblioteca = new JLabel("");
		gbc = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);
		add(multaBiblioteca, gbc);
	}

	/**
     * Este m�todo se llama cuando se presiona uno de los botones. <br>
     * <b>post: </b> Se ejecut� la acci�n correspondiente al bot�n presionado. <br>
     * @param evento El evento del click en el bot�n. evento != null.
     */
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		
		if (comando.equals(PanelBiblioteca.CALCULAR_MULTA)) {
			float multa = biblioteca.calcularTotalMulta();
			DecimalFormat formato = new DecimalFormat("#,###.##");
			multaBiblioteca.setText("MULTA TOTAL: $"+formato.format(multa));
			if(multa>0){
				multaBiblioteca.setForeground(Color.red);
			}else{
				multaBiblioteca.setForeground(Color.black);
			}
		}
	}

}
