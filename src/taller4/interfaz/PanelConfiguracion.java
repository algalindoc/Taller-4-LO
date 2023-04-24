package taller4.interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class PanelConfiguracion extends JPanel implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InterfazLightsOut principal;
    private JLabel dificultad;
    private JComboBox<String> tamanios;
    private JRadioButton facil, medio, dificil;
    private ButtonGroup grupoBotones;
    int d;
    
    public PanelConfiguracion(InterfazLightsOut pPrincipal) {
        principal = pPrincipal;
		inicializar();   
    }

    public void inicializar(){
        String[] elementos = {"3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9"};

        setLayout(new FlowLayout());

        JLabel tamanio = new JLabel("Tamanio: ");
        tamanios = new JComboBox<>(elementos);
        add(tamanio);
        add(tamanios);
        tamanios.addActionListener(this);

        dificultad = new JLabel("Dificultad: ");
        add(dificultad);

        facil = new JRadioButton("Facil", true);
        medio = new JRadioButton("Medio");
        dificil = new JRadioButton("Dificil");

        grupoBotones = new ButtonGroup();
        grupoBotones.add(facil);
        grupoBotones.add(medio);
        grupoBotones.add(dificil);

        add(facil);
        add(medio);
        add(dificil);

        facil.addActionListener(this);
        medio.addActionListener(this);
        dificil.addActionListener(this); 
    }

    public void actionPerformed( ActionEvent pEvento )
    {   
        String comando = (String) tamanios.getSelectedItem();
        principal.setTamanio(Character.getNumericValue(comando.charAt(0)));


        if (facil.isSelected())
            d = 1;
        else if (medio.isSelected()) 
            d = 5;
        else if (dificil.isSelected())
            d = 9;

        principal.setDificultad(d);
    }    
}
