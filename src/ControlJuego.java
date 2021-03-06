import java.util.Random;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posición guarda el número -1 Si no hay una
 * mina, se guarda cuántas minas hay alrededor. Almacena la puntuación de la
 * partida
 * 
 * @author jesusredondogarcia
 * @author Elena Nofuentes
 * @since 13-11-2020
 * @version 1.1
 *
 */
public class ControlJuego {
	/** Atributos de la clase */
	private final int MINA = -1;
	private final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int[][] tablero;
	private int puntuacion;

	/** Constructor por defecto */
	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		// Inicializamos una nueva partida
		inicializarPartida();
	}

	/**
	 * Método para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque
	 *        la variable MINAS_INICIALES. El resto de posiciones que no son minas
	 *        guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {
		// Repartir minas e inicializar puntación. Si hubiese un tablero anterior
		// lo pongo todo a cero para inicializarlo.
		int horizontal = 0, vertical = 0, minas = MINAS_INICIALES;
		Random rd = new Random();
		for (int l = 0; l < tablero.length; l++) {
			for (int m = 0; m < tablero[l].length; m++) {
				tablero[l][m] = 0;
			}
		}
		puntuacion = 0;
		while (minas > 0) {
			vertical = rd.nextInt(LADO_TABLERO);
			horizontal = rd.nextInt(LADO_TABLERO);
			if (tablero[vertical][horizontal] != -1) {
				tablero[vertical][horizontal] = MINA;
				minas--;
			}
		}
		// Al final del método hay que guardar el número de minas para las casillas que
		// no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
	}

	/**
	 * Cálculo de las minas adjuntas: Para calcular el número de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdrán LADO_TABLERO-1. Por lo tanto, como poco la i y la j
	 * valdrán 0.
	 * 
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/

	private int calculoMinasAdjuntas(int i, int j) {
		int iInicial = Math.max(0, i - 1);
		int iFinal = Math.min(LADO_TABLERO - 1, i + 1);
		int jInicial = Math.max(0, j - 1);
		int jfinal = Math.min(LADO_TABLERO - 1, j + 1);
		int minas = 0;

		for (int vertical = iInicial; vertical <= iFinal; vertical++) {
			for (int horizontal = jInicial; horizontal <= jfinal; horizontal++) {
				if (vertical >= 0 && vertical < LADO_TABLERO && horizontal >= 0 && horizontal < LADO_TABLERO) {
					if (tablero[vertical][horizontal] == MINA) {
						minas++;
					}
				}
			}
		}
		return minas;
	}

	/**
	 * Método que nos permite comprobar si al pulsar una casilla hay una mina.
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {
		boolean resultado = true;
		if (tablero[i][j] == MINA) {
			resultado = false;
		}
		puntuacion++;
		return resultado;
	}

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {
		return (LADO_TABLERO * LADO_TABLERO) - MINAS_INICIALES == puntuacion ? true : false;
	}

	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: " + puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return calculoMinasAdjuntas(i, j);
	}

	/**
	 * Método que devuelve la puntuación actual
	 * 
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Método que establece la puntuación
	 */

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * Método que devuelve el tablero
	 * 
	 * @return Un array bidimensional de enteros
	 */
	public int[][] getTablero() {
		return tablero;
	}

}