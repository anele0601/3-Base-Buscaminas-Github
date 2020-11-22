import java.awt.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Clase que simula el funcionamiento de un cronómetro. Se usa para contabilizar
 * la duración de la partida.
 * 
 * @author Elena Nofuentes
 * @since 21-11-2020
 * @version 1.0
 * @see VentanaPrincipal
 */

public class JCronometro extends JPanel implements Runnable {
    /** Atributos de la clase */
    private static final long serialVersionUID = 1L;
    private JLabel contador;
    private double tiempoTranscurrido;
    private double tiempoOriginal;
    private Thread hilo;
    private boolean iniciado;

    /** Constructor por defecto */
    public JCronometro() {
        super();
        hilo = null;
        iniciado = false;
        contador = new JLabel("00:00");
        contador.setFont(new Font("Tahoma", 1, 18));
        contador.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new GridBagLayout());
        GridBagConstraints settings = new GridBagConstraints();
        settings.insets = new Insets(40, 0, 20, 0);
        this.add(contador, settings);
    }

    /**
     * Método que incia la cuenta del cronometro.
     */
    public void comenzar() {
        iniciado = true;
        contador.setText("00:00");
        tiempoTranscurrido = 0;
        hilo = new Thread(this);
        hilo.start();
    }

    /**
     * Mëtodo que para la cuenta del cronometro
     */
    public void parar() {
        iniciado = false;

    }

    /**
     * Método que actualiza el atributo tiempoTranscurrido calculando el tiempo
     * transcurrido
     */
    private void calcularTiempoTranscurrido() {
        double tiempoActual = System.nanoTime();
        tiempoTranscurrido += tiempoActual - tiempoOriginal;
    }

    /**
     * Método que actualiza el tiempo en el cronómetro.
     */
    private void actualizarPantalla() {
        long tiempo = TimeUnit.NANOSECONDS.toSeconds((long) (tiempoTranscurrido));
        int minutos = (int) (tiempo / 60);
        int seg = (int) (tiempo % 60);
        String numMin = "";
        String numSeg = "";

        if (minutos < 10) {
            numMin = "0" + minutos;
        } else {
            numMin = String.valueOf(minutos);
        }

        if (seg < 10) {
            numSeg = "0" + String.valueOf(seg);
        } else {
            numSeg = String.valueOf(seg);
        }
        contador.setText(numMin + ":" + numSeg);
    }

    /**
     * Método sobrescrito para hacer funcionar el cronómetro
     */
    @Override
    public void run() {
        while (iniciado) {
            tiempoOriginal = System.nanoTime();
            try {
                Thread.sleep(200);
                calcularTiempoTranscurrido();
                actualizarPantalla();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}