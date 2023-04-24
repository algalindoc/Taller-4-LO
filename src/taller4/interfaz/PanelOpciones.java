package taller4.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class PanelOpciones extends JPanel implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InterfazLightsOut principal;
    JButton nuevo;
    JButton reiniciar;
    JButton TOP;
    JButton cambiar;

    public PanelOpciones(InterfazLightsOut pPrincipal) {
        principal = pPrincipal;
        inicializar();
    }

    public void inicializar(){
        Color azul = (new Color(72, 136, 189));
        Color claro = (new Color(240, 249, 255));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Component spacer = Box.createRigidArea(new Dimension(0, 200));
        add(spacer);

        Component spacerN = Box.createRigidArea(new Dimension(0, 30));
        nuevo = new JButton("Nuevo");
        nuevo.addActionListener( this );
        nuevo.setBackground(azul);
        nuevo.setActionCommand("Nuevo");
        nuevo.setForeground(claro);
        nuevo.setAlignmentX(CENTER_ALIGNMENT);
        nuevo.setAlignmentY(CENTER_ALIGNMENT);
        add(spacerN);
        add(nuevo);
        

        Component spacerR = Box.createRigidArea(new Dimension(0, 30));
        reiniciar = new JButton("Reiniciar");
        reiniciar.addActionListener( this );
        reiniciar.setBackground(azul);
        reiniciar.setForeground(claro);
        reiniciar.setActionCommand("Reiniciar");
        reiniciar.setAlignmentX(CENTER_ALIGNMENT);
        reiniciar.setAlignmentY(CENTER_ALIGNMENT);
        add(spacerR);
        add(reiniciar);

        Component spacerT = Box.createRigidArea(new Dimension(0, 30));
        TOP = new JButton("TOP");
        TOP.addActionListener( this );
        TOP.setBackground(azul);
        TOP.setForeground(claro);
        TOP.setActionCommand("TOP");
        TOP.setAlignmentX(CENTER_ALIGNMENT);
        TOP.setAlignmentY(CENTER_ALIGNMENT);
        add(spacerT);
        add(TOP);

        Component spacerC = Box.createRigidArea(new Dimension(0, 30));
        cambiar = new JButton("Cambiar");
        cambiar.addActionListener( this );
        cambiar.setBackground(azul);
        cambiar.setForeground(claro);
        cambiar.setActionCommand("Cambiar");
        cambiar.setAlignmentX(CENTER_ALIGNMENT);
        cambiar.setAlignmentY(CENTER_ALIGNMENT);
        add(spacerC);
        add(cambiar);
    }
    

    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( "Nuevo" ) )
        {
            principal.iniciar();
        }
        else if( comando.equals( "Reiniciar" ) )
        {
            principal.reiniciar();
        }
        else if( comando.equals( "TOP" ) )
        {
            principal.top();
        }
        else if( comando.equals( "Cambiar" ) )
        {
            principal.cambiar();
        }
    }
}