package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUIGridBagLayout extends JFrame {

    private Header headerProject;
    private JLabel palabraPrueba;
    private JPanel inicio, mostrarNiveles, panelMostrarPalabras, seleccionarPalabras;
    private JButton mostrarPalabras, jugar, ayuda, salir, botonSi, botonNo;
    private Timer timer;
    private Escucha escucha;
    private Palabras palabras;
    private ArrayList<String> diccionario;
    private boolean flagTimer;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout() {
        initGUIGridBagLayout();

        //Default JFrame configuration
        this.setTitle("I KNOW THAT WORD!!");
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUIGridBagLayout() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.setUndecorated(true);
        //Create Listener Object and Control Object
        palabras = new Palabras();
        escucha = new Escucha();
        //Set up JComponents

        headerProject = new Header("I KNOW THAT WORD!!", Color.BLACK);
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
        this.add(headerProject, constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(salir, constraints);

        /**
        palabraPrueba = new JLabel();
        palabraPrueba.setText("No tengo palabras");

        inicio = new JPanel();
        inicio.setPreferredSize(new Dimension(460, 500));
         */

        jugar = new JButton("Jugar");
        jugar.addActionListener(escucha);

        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;

        this.add(jugar, constraints);

        inicio = new JPanel();

        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(inicio, constraints);



        mostrarPalabras = new JButton("Mostrar palabras");

        //mostrarPalabras.addActionListener(escucha);
        //mostrarPalabras.setVisible(false);
        mostrarPalabras.setEnabled(true);

        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

//        this.add(mostrarPalabras, constraints);

        timer = new Timer(1000, escucha);
        //timer.stop();
        flagTimer = false;

    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        private Random random;
        private int counter;

        public Escucha() {
            random = new Random();
            counter = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==salir){
                System.exit(0);
            }

            diccionario = palabras.getPalabras();

            if (e.getSource() == timer) {
                if (counter <= 10) {
                    palabraPrueba.setText(diccionario.get(counter));
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Ya se mostraron todas las palabras del txt");
                    flagTimer = false;
                }
                counter++;
            }

            if (e.getSource() == mostrarPalabras) {
                if (flagTimer == true) {

                } else if (flagTimer == false) {
                    timer.start();
                    flagTimer = true;
                }
            }

//            if(e.getSource()==timer){
//                counter++;
//                if(counter<=7){
//                    squareColor.setBackground(new Color(random.nextInt(256),
//                            random.nextInt(256),
//                            random.nextInt(256)));
//                }else{
//                    timer.stop();
//                    //mostrarPalabras.setVisible(true);
//                    mostrarPalabras.setEnabled(true);
//                    mostrarPalabras.addActionListener(escucha);
//                }
//            }else{
//                timer.start();
//                counter=0;
//                //mostrarPalabras.setVisible(false);
//                mostrarPalabras.setEnabled(false);
//                mostrarPalabras.removeActionListener(escucha);
//            }
        }
    }
}
