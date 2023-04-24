package taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;
import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class InterfazLightsOut extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamanio = 3;
	public Tablero tablero = new Tablero(tamanio);
	private Top10 top10;
	private PanelOpciones pOpciones;
	private PanelBoard pBoard;
	private PanelConfiguracion pConfiguracion;
	private PanelEstado pEstado;
	public String nombre;
	private int dificultad = 1;
	
	
	public InterfazLightsOut() {
		{
			try
			{
				tablero = new Tablero(tamanio);
				top10 = new Top10();
				top10.cargarRecords(new File("data/top10.csv"));
			}
			catch( Exception e )
			{
				JOptionPane.showMessageDialog( this, "Error al cargar el estado inicial " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
			}
	
			setTitle( "LightsOut" );
			setSize( 800, 800 );
			setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			setResizable( false );
			setLocationRelativeTo( null );
	
			setLayout( new BorderLayout( ) );
			pOpciones = new PanelOpciones(this);
			pOpciones.setBackground(new Color(240, 249, 255));
			add( pOpciones, BorderLayout.EAST );
	
			pBoard = new PanelBoard(this);
			pBoard.setBackground(Color.WHITE);
			add( pBoard, BorderLayout.CENTER );
	
			pConfiguracion = new PanelConfiguracion( this);
			pConfiguracion.setBackground(new Color(72, 136, 189));
			add( pConfiguracion, BorderLayout.NORTH );
			
			pEstado = new PanelEstado(this);
			pEstado.setBackground(new Color(240, 249, 255));
			add( pEstado, BorderLayout.SOUTH );
			
			this.setVisible( true );

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						top10.salvarRecords(new File("data/top10.csv"));
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			int margen = 10;
			int espacio = 5;
			pOpciones.setBorder(BorderFactory.createEmptyBorder(margen, margen, margen, margen));
			pConfiguracion.setBorder(BorderFactory.createEmptyBorder(margen, margen, espacio, margen));
			pEstado.setBorder(BorderFactory.createEmptyBorder(espacio, margen, margen, margen));

			pOpciones.setPreferredSize(new Dimension(180, 0));
			pBoard.setPreferredSize(new Dimension(620, 620));

		}
	}

	public Collection<RegistroTop10> cargarRegistrosTop() {
		return top10.darRegistros();
	}
	
	public void setTamanio(int c){
		tamanio = c;
		pBoard.actualizarTamanio(tamanio);
		reiniciar();
   		}

    public void setDificultad(int d) {
		dificultad = d;
		reiniciar();
	}

	public void jugar(int i, int j) {
		tablero.jugar(j, i);
		pBoard.repaint();
		if (tablero.tableroIluminado()){
			int puntaje = tablero.calcularPuntaje();
			if (top10.esTop10(puntaje)){
				JOptionPane.showMessageDialog(this, "Felicidades, has entrado al top 10");
				top10.agregarRegistro(nombre, puntaje);
			}
			else{
				JOptionPane.showMessageDialog(this, "Ganaste pero no has entrado al top 10");
			}
		}
		pEstado.actualizarJugadas(tablero, nombre);
	}

	public void iniciar() {
		nombre = JOptionPane.showInputDialog( this, "Ingrese su nombre", "LightsOut", JOptionPane.QUESTION_MESSAGE );
		tablero = new Tablero(tamanio);
		tablero.desordenar(dificultad);
		pBoard.repaint();
		pEstado.actualizarJugadas(tablero, nombre);
	}

	public void reiniciar(){
		tablero = new Tablero(tamanio);
		tablero.desordenar(dificultad);
		pBoard.repaint();
		pEstado.actualizarJugadas(tablero, nombre);
	}

	public void top(){
		new Scoreboard(this);
	}

	public void cambiar(){
		iniciar();
	}

	public int darTamano() {
		return tamanio;
	}
	
	public static void main(String[] args) {
		new InterfazLightsOut();
	}	

}
