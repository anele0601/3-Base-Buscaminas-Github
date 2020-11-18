import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 * @author Elena Nofuentes
 * @since 13-11-2020
 * @version 1.1
 * @see ControlJuego
 **
 */
public class ActionBoton implements ActionListener {
	/** Atributos de la clase */
	private VentanaPrincipal ventana;
	private int i, j;

	/**
	 * Constructor parametrizado
	 * 
	 * @param ventana objeto VentanaPrincipal
	 * @param i       entero que representa la fila
	 * @param j       entero que representa la columna
	 */
	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 * 
	 * Dependiendo del contenido de la casilla (mina o no), mostraremos el numero de
	 * minas alrededor, actualizaremos la puntuación y/o comprobaremos si el juego
	 * ha terminado.
	 * 
	 * Link al método abrir casilla de la clase Control Juego.
	 * {@link ControlJuego#abrirCasilla(int, int)}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean comprobar = ventana.getJuego().abrirCasilla(i, j);
		if (comprobar) {
			ventana.mostrarNumMinasAlrededor(i, j);
			ventana.actualizarPuntuacion();
		}
		ventana.mostrarFinJuego(comprobar);
	}
}