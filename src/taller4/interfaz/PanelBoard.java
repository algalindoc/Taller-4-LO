package taller4.interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBoard extends JPanel implements MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InterfazLightsOut principal;
    private int tamano;

    public PanelBoard(InterfazLightsOut pPrincipal) {
        principal = pPrincipal;
        tamano = principal.darTamano();
        this.addMouseListener(this);
		repaint();
    }

    public void actualizarTamanio(int pTamano) {
        tamano = pTamano;
        removeAll();
    }

    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();
        String[] coordenada = comando.split(",");
        int i = Integer.parseInt(coordenada[0]);
        int j = Integer.parseInt(coordenada[1]);
        principal.jugar(i, j);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e)  {
    if (principal.nombre != null){
       int click_x = e.getX();
       int click_y = e.getY();   
       int[] casilla = convertirCoordenadasACasilla(click_x, click_y);   
       principal.jugar(casilla[0], casilla[1]);      
       repaint();  }
       else{
        JOptionPane.showMessageDialog( this, "debes iniciar el juego e ingresar tu nombre para poder jugar" );
       }
    }   

    private int[] convertirCoordenadasACasilla(int x, int y)  {   
        int ladoTablero = tamano;   
        int altoPanelTablero = getHeight();   
        int anchoPanelTablero = getWidth();      
        int altoCasilla = altoPanelTablero / ladoTablero;   
        int anchoCasilla = anchoPanelTablero / ladoTablero;   
        int fila = (int) (y / altoCasilla);   
        int columna = (int) (x / anchoCasilla);      
        return new int[] { fila, columna };  
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

	@Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 255, 0));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(new Color(0, 0, 0));
        boolean[][] casillas = principal.tablero.darTablero();

        Image image = Toolkit.getDefaultToolkit().getImage("data/luz.png");
        int anchoImagen = image.getWidth(this);
        int altoImagen = image.getHeight(this);

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {

                int anchoCasilla = this.getWidth() / tamano;
                int altoCasilla = this.getHeight() / tamano;

                g2d.drawRect(anchoCasilla * i, altoCasilla * j, anchoCasilla, altoCasilla);
                if (!casillas[i][j]) {
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fillRect((anchoCasilla) * i, (altoCasilla) * j, anchoCasilla, altoCasilla);
                }
                g2d.setColor(new Color(0, 0, 0));
                g2d.drawImage(image, (anchoCasilla) * i + (anchoCasilla - anchoImagen) / 2, altoCasilla * j + (altoCasilla - altoImagen) / 2, this);
            }
        }
    }

}


