package taller4.interfaz;

import javax.swing.*;

import uniandes.dpoo.taller4.modelo.Tablero;

import java.awt.*;

public class PanelEstado extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jugador;
    private JLabel jugadas;
    private JTextField NJugadas;
    private JTextField NJugador;

    public PanelEstado(InterfazLightsOut pPrincipal) {
        inicializar();
        
    }

    public void inicializar(){
        setLayout(new GridLayout(1,4, 10, 10));
        jugador = new JLabel("Jugador: ");
        add(jugador);

        NJugador = new JTextField("");
        add(NJugador);

        jugadas = new JLabel("Jugadas: ");
        add(jugadas);

        NJugadas = new JTextField("0");
        add(NJugadas);

    }

    public void actualizarJugadas(Tablero t, String nombre) {
        int jugadas = t.darJugadas();
        NJugadas.setText(jugadas + "");
        NJugador.setText(nombre);
    }
}

