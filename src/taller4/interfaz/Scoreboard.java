package taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collection;
import javax.swing.*;
import javax.swing.event.*;
import uniandes.dpoo.taller4.modelo.RegistroTop10;


public class Scoreboard extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InterfazLightsOut principal;
    JLabel labelNombreCompleto = new JLabel();

    public Scoreboard(InterfazLightsOut Pprincipal) {
        super("Scoreboard");
        principal = Pprincipal;
        inicializar();
    }

    public void inicializar() {
        
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        Collection<RegistroTop10> registros = principal.cargarRegistrosTop();
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (RegistroTop10 registro : registros) {
            modeloLista.addElement(registro.darNombre() + " - " + registro.darPuntos());
        }
                
        JList<String> listaRegistros = new JList<>(modeloLista);

        //nombrearriba
        JLabel nombre = new JLabel(" ");
        nombre.setForeground(Color.WHITE);
        nombre.setOpaque(true);
        nombre.setBorder(BorderFactory.createEmptyBorder(8, 130, 8, 30));
        nombre.setBackground(new Color(72, 136, 189));
        add(nombre, BorderLayout.NORTH);
        listaRegistros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String registro = listaRegistros.getSelectedValue();
                    String[] datos = registro.split(" - ");
                    nombre.setText(datos[0]);
                }
            }
        });

        //arreglar para que se vea bien
        listaRegistros.setCellRenderer(new ListaModificada());     
        add(new JScrollPane(listaRegistros));

        setSize(300, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ListaModificada extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
    
            JPanel panel = new JPanel(new BorderLayout());

            String registro = (String) value;
            String[] datos = registro.split(" - ");

            JLabel numeroRegistro = new JLabel(Integer.toString(index + 1));
            numeroRegistro.setFont(numeroRegistro.getFont().deriveFont(Font.BOLD));
            numeroRegistro.setBorder(BorderFactory.createEmptyBorder(5, 60, 5, 0));
            panel.add(numeroRegistro, BorderLayout.WEST);

            JLabel nombre = new JLabel(datos[0]);
            nombre.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
            panel.add(nombre, BorderLayout.CENTER);

            JLabel puntaje = new JLabel(datos[1]);
            puntaje.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 30));
            panel.add(puntaje, BorderLayout.EAST);


            if (index < 3) {
                numeroRegistro.setForeground(new Color(72, 136, 189));
                nombre.setForeground(new Color(72, 136, 189));
                puntaje.setForeground(new Color(72, 136, 189));
            } else if (index < 6) {
                panel.setForeground(Color.BLACK);
            }

            panel.add(new JSeparator(), BorderLayout.SOUTH);

            if (isSelected)
                panel.setBackground(list.getSelectionBackground());
            else
                panel.setBackground(list.getBackground());

            
                return panel;
            }
    }
}
