import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    //se crea la variable boton
    private JButton btnescalera_suma;
    private JPanel pnlJugador1;
    //el JPanle sirve para colocar una mini ventana dentro de la ventana principal
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    public FrmJuego() {
        //se le asugna la variable como tal al boton
        btnescalera_suma = new JButton();
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        // este comando se utiliza para poner un panel que va a contener pestañas
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        //se establece el tamaño de la ventana
        setTitle("Juego de Cartas");
        // el titulo de la pantalla
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //se establece que conncerrar la ventana tambien se cierra el programa

        pnlJugador1.setBackground(new Color(153, 255, 51));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        //el setBackround modifica el color del tema de este 
        // el setLaout desactiva la pantalla para manejo
        pnlJugador2.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        btnescalera_suma.setBounds(240,10,140,25);
        // el setBounds sirve para dar le la posicion al boton y su tamaño
        btnescalera_suma.setText("escaleras y suma");
        //el setText sirve para dar le un texto adentro del boton
        btnescalera_suma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnescalera_sumaCLick(evt);
            }
        });
        /*el addActionListener sirve para interactuar con su accion predeterminada en este caso se llama a la funcion btnescalera_sumaCLick
        para interactuar con este se hace de esta manera para que sea mas limpip el codigo y no se vea tan feo
        pero tambien se puede hacer de forma directa
        */

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);
        // este sirve para agregar el boton en el panel para que sea visible y sirva
        getContentPane().add(btnescalera_suma);

    }

    private void btnRepartirClick(ActionEvent evt) {

        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);

    }


    private void btnVerificarClick(ActionEvent evt) {
        int pestañaEscogida = tpJugadores.getSelectedIndex();
        switch (pestañaEscogida) {

            case 0:
            //crea una apntalla que esta ayuda a mostrar un mensaje que se llama desde jugador y se le adiociona el componete gerGrupos
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;
        }
    }

    // creamos el private para verificarlo y hacer que se accione 

    private void btnescalera_sumaCLick(ActionEvent evt)
    {
        
       
        int pestañaEscogida = tpJugadores.getSelectedIndex();
        switch (pestañaEscogida) 
        {
            case 0:
            //crea una apntalla que esta ayuda a mostrar un mensaje que se llama desde jugador y se le adiociona el componete gerGrupos
                JOptionPane.showMessageDialog(null, jugador1.detectarEscaleras());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.detectarEscaleras());
                break;
        }
        /* 
        int pestañaEscogida = tpJugadores.getSelectedIndex();
        switch (pestañaEscogida) {

            case 0:
            //crea una apntalla que esta ayuda a mostrar un mensaje que se llama desde jugador y se le adiociona el componete gerGrupos
                JOptionPane.showMessageDialog(null, jugador1.escalera());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.escalera());
                break;
                
        }
                */
    }

}
