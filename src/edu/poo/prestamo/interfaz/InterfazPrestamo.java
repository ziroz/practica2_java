package edu.poo.prestamo.interfaz;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.poo.prestamo.modelo.Biblioteca;
import edu.poo.prestamo.modelo.CD;
import edu.poo.prestamo.modelo.IMaterial;
import edu.poo.prestamo.modelo.Libro;
import edu.poo.prestamo.modelo.Prestamo;
import edu.poo.prestamo.modelo.Revista;

/**
 * Esta es la ventana principal de la aplicaci�n. Contiene a los paneles que tienen los botones y muestran la informaci�n del 
 * material de la biblioteca que se puede prestar
 * @author Universidad de Medellin
 */
public class InterfazPrestamo extends JFrame {
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
    * Es el panel que contiene los elementos para mostrar del material disponible a prestar
    */
	private PanelMaterial panelMaterial;
	
	/**
     * Es el panel que contiene los elementos prestados y calcular sus multas
     */
	private PanelPrestamo panelPrestamo;
	
	/**
     * Es el panel que contiene los elementos para culcular las multas totales de la 
	*/
	private PanelBiblioteca panelBiblioteca ;
	
	private Biblioteca biblioteca;
	
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	/**
     * Construye una nueva interfaz inicializada .
     */
	public InterfazPrestamo(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
		iniciarComponentes();
	}
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
	
	/**
	 * M�todo que sirva para incializar los componentes graficos de la ventana 
	 */
	private void iniciarComponentes() {
		setTitle("Prestamos Biblioteca");
		
		// construir los paneles
		panelMaterial = new PanelMaterial(this,biblioteca);
		panelPrestamo = new PanelPrestamo(biblioteca);
		panelBiblioteca = new PanelBiblioteca(biblioteca);
		
		// organizar el panel principal
		getContentPane().add(panelMaterial, BorderLayout.NORTH);
		getContentPane().add(panelPrestamo, BorderLayout.CENTER);
		getContentPane().add(panelBiblioteca, BorderLayout.SOUTH);

		setSize(420, 380);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evento) {
				cerrarAplicacion();
			}
		});
	}
	
	/**
	 * M�todo que cierra la aplicaci�n
	 */
	public void cerrarAplicacion() {
		int respuesta = JOptionPane.showOptionDialog(this,
				"Esta seguro que desea cerrar la aplicacion?",
				"Terminar Programa", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null,
				new String[] { "Si", "No" }, "default");

		if (respuesta == JOptionPane.OK_OPTION)
			dispose();// Método heredado de la clase Window que libera los
						// recursos asignados por el SO a una ventana y sus
						// componentes
	}
	
	public void actualizarPrestamos(Prestamo prestamo) {
		panelPrestamo.actualizarPrestamos(prestamo);
		
	}
	
	//-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args Los argumentos no son utilizados.
     */
	public static void main(String[] args) {
		
		Biblioteca biblioteca = new Biblioteca();
		
		IMaterial libro = new Libro("Cien años de soledad", true,100);
		IMaterial libro1 = new Libro("Programacion Orientada a Objetos", false,500);
		IMaterial libro2 = new Libro("Fisica Mecanica", true,350);
		
		IMaterial revista = new Revista("Revista Facultad de Ingenieria", false, Revista.CIENCIA);
		IMaterial revista1 = new Revista("ESPN", true, Revista.DEPORTE);
		IMaterial revista2 = new Revista("Muy Saludable", false, Revista.SALUD);
		
		IMaterial cd = new CD("Fundamentos de Programacion", true, 500);
		IMaterial cd1 = new CD("Windows 8.0", false, 350);
		IMaterial cd2 = new CD("Oracle 10g", true, 600);
		
		biblioteca.anadirMaterial(libro);
		biblioteca.anadirMaterial(libro1);
		biblioteca.anadirMaterial(libro2);
		biblioteca.anadirMaterial(revista);
		biblioteca.anadirMaterial(revista1);
		biblioteca.anadirMaterial(revista2);
		biblioteca.anadirMaterial(cd);
		biblioteca.anadirMaterial(cd1);
		biblioteca.anadirMaterial(cd2);
		
		
		biblioteca.anadirPrestamo(new Prestamo(new Date(115,0,1),libro));
		biblioteca.anadirPrestamo(new Prestamo(new Date(),revista));
		biblioteca.anadirPrestamo(new Prestamo(new Date(115,4,15),cd));
		
		InterfazPrestamo ventana = new InterfazPrestamo(biblioteca);
		ventana.setVisible(true);
	}

		
}
