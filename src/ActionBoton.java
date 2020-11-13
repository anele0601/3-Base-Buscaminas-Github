import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener {
	private VentanaPrincipal ventana;

	public ActionBoton(VentanaPrincipal ventana) {
		// TODO
		this.ventana = ventana;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		ControlJuego cJuego = ventana.getJuego();

		int fila = 0, columna = 0;
		for (int i = 0; i < cJuego.LADO_TABLERO; i++) {
			for (int j = 0; j < cJuego.LADO_TABLERO; j++) {
				if (ventana.botonesJuego[i][j] == button) {
					fila = i;
					columna = j;
				}
			}
		}
		int valorCasilla = cJuego.getTablero()[fila][columna];

		if (valorCasilla == cJuego.MINA) {
			ventana.mostrarFinJuego(true);
		} else {
			ventana.mostrarNumMinasAlrededor(fila, columna);
		}

	}
}
