import java.awt.EventQueue;

/**
 * Clase principal del Buscaminas. Inicia el proceso.
 * 
 * @author Elena Nofuentes
 * @since 11-11-2020
 * @version 1.0
 * @see ControlJuego {@link VentanaPrincipal#inicializar()}
 *
 */
public class Principal {

	/**
	 * Método main
	 * 
	 * @param args : Cadenas de parámetros del main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
