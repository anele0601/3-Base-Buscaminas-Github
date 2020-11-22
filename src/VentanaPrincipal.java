import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Ventana principal del Buscaminas, en ella se crean y inicializan todos los
 * componentes. También se controla el número de minas y el fin de juego.
 * 
 * @author Elena Nofuentes
 * @since 13-11-2020
 * @version 1.1
 * @see ControlJuego {@link VentanaPrincipal#inicializar()}
 * 
 */
public class VentanaPrincipal {
	/** Atributos de la clase */
	// La ventana principal, en este caso, guarda todos los componentes:
	private JFrame ventana;
	private JPanel panelImagen;
	private JPanel panelEmpezar;
	private JPanel panelPuntuacion;
	private JPanel panelJuego;
	private JCronometro cronometro;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	private JPanel[][] panelesJuego;
	private JButton[][] botonesJuego;

	private JButton botonEmpezar;
	private JTextField pantallaPuntuacion;

	/** Objetos necesarios */
	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.GREEN, Color.GRAY, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;

	/**
	 * Imágenes mostradas en el botón de empezar Sacamos la ruta mediante una URL
	 * para que se incluyan en el .jar
	 */
	URL urlImagenInicio = VentanaPrincipal.class.getClassLoader().getResource("caritafeliz.png");
	URL urlImagenFin = VentanaPrincipal.class.getClassLoader().getResource("caritatriste.png");
	ImageIcon imagenInicio = new ImageIcon(urlImagenInicio);
	ImageIcon imagenFin = new ImageIcon(urlImagenFin);

	/**
	 * Constructor por defecto. Marca el tamaño y el cierre del frame
	 */
	public VentanaPrincipal() {
		ventana = new JFrame("Buscaminas");
		ventana.setBounds(100, 100, 600, 700);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
		cronometro = new JCronometro();
	}

	/**
	 * Inicializamos todos los componentes del JFrame. Definimos el
	 * layout,incializamos los componentes, declaramos bordes y colocamos los
	 * paneles y botones.
	 */
	public void inicializarComponentes() {
		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelImagen.add(cronometro);
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));
		// Ponemos la imagen de inicio al crear el boton
		botonEmpezar = new JButton(imagenInicio);
		botonEmpezar.setFocusPainted(false);
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		// Formato del texto
		pantallaPuntuacion.setFont(new Font("Tahoma", 1, 18));

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createTitledBorder("Cronómetro"));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);
		panelesyBotones();
		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
	}

	/**
	 * Método para crear los paneles y botones del tablero.
	 */
	public void panelesyBotones() {
		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}
	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa.
	 * 
	 * {@link ActionBoton}
	 */
	public void inicializarListeners() {
		botonEmpezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelJuego.removeAll();
				panelJuego.revalidate();
				panelJuego.repaint();
				panelesyBotones();
				botonEmpezar.setIcon(imagenInicio);
				juego.setPuntuacion(0);
				actualizarPuntuacion();
				juego.inicializarPartida();
				juego.depurarTablero();
				listenerBotones();
				cronometro.comenzar();
				refrescarPantalla();
			}
		});
	}

	/**
	 * Método para inicializar los listener de los botones del tablero.
	 */
	public void listenerBotones() {
		for (int l = 0; l < botonesJuego.length; l++) {
			for (int m = 0; m < botonesJuego[l].length; m++) {
				botonesJuego[l][m].addActionListener(new ActionBoton(this, l, m));
			}
		}
	}

	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda Saca el
	 * botón que haya en la celda determinada y añade un JLabel centrado y no
	 * editable con el número de minas alrededor. Se pinta el color del texto según
	 * la siguiente correspondecia (consultar la variable correspondeciaColor): - 0
	 * : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 ó más : rojo
	 * 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		int minas = juego.getMinasAlrededor(i, j);
		panelesJuego[i][j].removeAll();
		JLabel jl = new JLabel(Integer.toString(minas));
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setForeground(correspondenciaColores[minas]);
		panelesJuego[i][j].add(jl);
		refrescarPantalla();
	}

	/**
	 * Muestra una ventana que indica el fin del juego.
	 * 
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha
	 *                     explotado una mina (true) o bien porque hemos desactivado
	 *                     todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 * 
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		if (juego.esFinJuego()) {
			cronometro.parar();
			juego.setPuntuacion(100);
			actualizarPuntuacion();
			JOptionPane.showMessageDialog(ventana, "¡Ha ganado! \n No ha explotado ninguna mina", "Partida terminada",
					JOptionPane.INFORMATION_MESSAGE);
			for (int i = 0; i < juego.LADO_TABLERO; i++) {
				for (int j = 0; j < juego.LADO_TABLERO; j++) {
					botonesJuego[i][j].setEnabled(false);
				}
			}
		}
		if (!porExplosion) {
			cronometro.parar();
			botonEmpezar.setIcon(imagenFin);
			JOptionPane.showMessageDialog(ventana,
					"¡Ha explotado una mina! \nFin de la partida. \nPuntuación : " + (juego.getPuntuacion() - 1),
					"Partida terminada", JOptionPane.INFORMATION_MESSAGE);
			for (int i = 0; i < juego.LADO_TABLERO; i++) {
				for (int j = 0; j < juego.LADO_TABLERO; j++) {
					botonesJuego[i][j].setEnabled(false);
					// Al terminar la partida, buscamos las minas en el tablero y la mostramos
					// poniendo
					// el fondo del botón en rojo.
					if (juego.getTablero()[i][j] == -1) {
						botonesJuego[i][j].setBackground(Color.RED);
					}
				}
			}
		}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(juego.getPuntuacion()));
	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}
}