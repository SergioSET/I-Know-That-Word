package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class GUIGridBagLayout<string> extends JFrame {

    private Header headerProject;
    private JLabel resultado, palabraAMostrar, palabraSeleccionar, usuario, mostrarNombre, mostrarNivelActual, mostrarAciertos;
    private JPanel inicio, nombreJugador, panelMostrarPalabras, estadoActual, seleccionarPalabras, pantallaResultados;
    private JTextField nombre;
    private JButton mostrarPalabras, jugar, ayuda, salir, botonSi, botonNo, aceptar, continuar, reiniciarNivel, avanzar;
    private Timer timerMostrar, timerSeleccionar;
    private Jugador elJugador;
    private ModelIKnowThatWord elModelo;
    private Escucha escucha;
    private Palabras palabras;
    private ArrayList<String> palabrasNivel, palabrasMemorizar;
    private boolean flagTimer, usuarioRegistrado;
    private ImageIcon imageInicio;
    private String nombreUsuario = null;
    private int nivelMaximoSuperado = 0;
    private int nivelActual = 1;
    private int porcentajeAciertos = 0;
    private String palabra;

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
        elJugador = new Jugador();
        elModelo = new ModelIKnowThatWord();
        //Set up JComponents

        headerProject = new Header("I KNOW THAT WORD!!", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.anchor = GridBagConstraints.NORTHWEST;
        this.add(headerProject, constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.anchor = GridBagConstraints.NORTHEAST ;
        this.add(ayuda, constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        //constraints.anchor = GridBagConstraints.NORTHEAST ;
        this.add(salir, constraints);

        //PANTALLA INICIO
        {
            inicio = new JPanel();
            inicio.setPreferredSize(new Dimension(500, 360));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 5;
            constraints.anchor = GridBagConstraints.LINE_START;
            imageInicio = new ImageIcon(this.getClass().getResource("/myProject/archivos/imagenInicio.png"));
            JLabel picLabel = new JLabel(imageInicio);
            inicio.add(picLabel);

            jugar = new JButton("Jugar");
            jugar.addActionListener(escucha);
            inicio.add(jugar);

            this.add(inicio, constraints);
        }

        //PANTALLA NOMBRE USUARIO
        {
            nombreJugador = new JPanel();
            nombreJugador.setPreferredSize(new Dimension(500, 360));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 5;

            usuario = new JLabel("Ingresa tu nombre");

            nombre = new JTextField(20);

            aceptar = new JButton("Aceptar");
            aceptar.addActionListener(escucha);

            nombreJugador.add(usuario);
            nombreJugador.add(nombre);
            nombreJugador.add(aceptar);

            this.add(nombreJugador, constraints);
        }


        //PANTALLA MOSTRAR ESTADO
        {
            estadoActual = new JPanel();
            estadoActual.setPreferredSize(new Dimension(500, 20));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 5;

            mostrarNombre = new JLabel("Usuario: " + nombreUsuario);
            mostrarNivelActual = new JLabel("Nivel actual: " + nivelActual);
            mostrarAciertos = new JLabel("Aciertos: " + elModelo.aciertos);

            estadoActual.add(mostrarNombre);
            estadoActual.add(mostrarNivelActual);
            estadoActual.add(mostrarAciertos);

            this.add(estadoActual, constraints);
        }

        //PANTALLA MOSTRAR PALABRAS
        {
            panelMostrarPalabras = new JPanel();
            panelMostrarPalabras.setPreferredSize(new Dimension(500, 320));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;

            palabraAMostrar = new JLabel("Aquí se mostrarán las palabras");

            continuar = new JButton("Continuar");
            continuar.addActionListener(escucha);
            continuar.setEnabled(false);

            mostrarPalabras = new JButton("Iniciar");
            mostrarPalabras.addActionListener(escucha);

            panelMostrarPalabras.add(palabraAMostrar);
            panelMostrarPalabras.add(mostrarPalabras);
            panelMostrarPalabras.add(continuar);


            this.add(panelMostrarPalabras, constraints);
        }

        //PANTALLA SELECCIONAR PALABRAS
        {
            seleccionarPalabras = new JPanel();
            seleccionarPalabras.setPreferredSize(new Dimension(500, 320));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;

            palabraSeleccionar = new JLabel("\"¡Buena suerte!\"");

            botonSi = new JButton("Sí");
            botonSi.addActionListener(escucha);

            botonNo = new JButton("No");
            botonNo.addActionListener(escucha);

            seleccionarPalabras.add(palabraSeleccionar);
            seleccionarPalabras.add(botonSi);
            seleccionarPalabras.add(botonNo);

            this.add(seleccionarPalabras, constraints);
        }

        //PANTALLA RESULTADOS
        {
            pantallaResultados = new JPanel();
            pantallaResultados.setPreferredSize(new Dimension(500, 320));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;

            resultado = new JLabel("Aquí se mostrará tu resultado");

            reiniciarNivel = new JButton("Reiniciar nivel");
            reiniciarNivel.addActionListener(escucha);
            reiniciarNivel.setVisible(false);

            avanzar = new JButton("Siguiente nivel");
            avanzar.addActionListener(escucha);
            avanzar.setVisible(false);

            pantallaResultados.add(resultado);
            pantallaResultados.add(avanzar);
            pantallaResultados.add(reiniciarNivel);

            this.add(pantallaResultados, constraints);
        }

        inicio.setVisible(true);
        nombreJugador.setVisible(false);
        estadoActual.setVisible(false);
        panelMostrarPalabras.setVisible(false);
        seleccionarPalabras.setVisible(false);
        pantallaResultados.setVisible(false);

        timerMostrar = new Timer(5000, escucha);
        timerSeleccionar = new Timer(7000, escucha);

    }

    public void resultados() {
        if (elModelo.superoNivel()) {
            resultado.setText("Has pasado este nivel, podrás avanzar al siguiente nivel.");
            avanzar.setVisible(true);
            nivelMaximoSuperado = nivelActual;
            nivelActual = nivelActual + 1;
        } else {
            resultado.setText("Has fallado este nivel, no podrás avanzar al siguiente nivel.");
            reiniciarNivel.setVisible(true);
        }
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
        private int counter;

        public Escucha() {
            counter = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == ayuda) {
                JOptionPane.showMessageDialog(null, "Este juego consiste en recordar las palabras mostradas y determinar si están en la siguiente aparición");
            }

            if (e.getSource() == salir) {
//                if (usuarioRegistrado) {
//                    elJugador.actualizarNivel(nombreUsuario, nivelMaximoSuperado);
//                } else {
//                    elJugador.registrarJugador(nombreUsuario, nivelMaximoSuperado);
//                }
                System.exit(0);
            }

            if (e.getSource() == jugar) {
                inicio.setVisible(false);
                nombreJugador.setVisible(true);
            }

            if (e.getSource() == aceptar) {

                nombreUsuario = nombre.getText();

                if (nombreUsuario != "" || nombreUsuario != null) {
                    JOptionPane.showMessageDialog(null, "Su nombre de usuario es: " + nombreUsuario);

                    if (elJugador.estaRegistrado(nombreUsuario)) {
                        nivelMaximoSuperado = elJugador.getNivel();
                        nivelActual = nivelMaximoSuperado + 1;
                        JOptionPane.showMessageDialog(null, "Usted ya se encuentra registrado, su nivel máximo superado es: " + nivelMaximoSuperado);
                    } else {
                        nivelActual = 1;
                        nivelMaximoSuperado = 0;
                    }

                    mostrarNombre.setText("Usuario: " + nombreUsuario);
                    mostrarNivelActual.setText("Nivel actual: " + nivelActual);
                    mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);

                    nombreJugador.setVisible(false);
                    estadoActual.setVisible(true);
                    panelMostrarPalabras.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre está vacío");
                }
            }

            if (e.getSource() == mostrarPalabras) {
                mostrarPalabras.setEnabled(false);
                elModelo.determinarPalabrasNivel(nivelActual);
                palabrasNivel = elModelo.getArrayPalabrasNivel();
                palabrasMemorizar = elModelo.getArrayPalabrasMemorizar();
                System.out.println(palabrasNivel + "" + palabrasNivel.toArray().length);
                System.out.println(palabrasMemorizar + "" + palabrasMemorizar.toArray().length);
                counter = 0;
                timerMostrar.start();
            }

            if (e.getSource() == continuar) {
                panelMostrarPalabras.setVisible(false);
                seleccionarPalabras.setVisible(true);
                counter = 0;
                timerSeleccionar.start();
            }

            if (e.getSource() == botonSi) {
                botonSi.setEnabled(false);
                botonNo.setEnabled(false);
                if (elModelo.palabraEstaEnNivel(palabra) == true) {
                    elModelo.aciertos = elModelo.aciertos + 1;
                }
                mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);
            }

            if (e.getSource() == botonNo) {
                botonNo.setEnabled(false);
                botonSi.setEnabled(false);
                if (elModelo.palabraEstaEnNivel(palabra) == false) {
                    elModelo.aciertos = elModelo.aciertos + 1;
                }
                mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);
            }

            if (e.getSource() == avanzar) {
                pantallaResultados.setVisible(false);
                panelMostrarPalabras.setVisible(true);
                palabraAMostrar.setText("Aquí se mostrarán las palabras");
                mostrarPalabras.setEnabled(true);
                continuar.setEnabled(false);
                elModelo.aciertos = 0;
                mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);
                mostrarNivelActual.setText("Nivel actual: " + nivelActual);
                elModelo.arrayPalabrasNivel.clear();
                elModelo.arrayPalabrasMemorizar.clear();
                System.out.println(nivelActual);
            }

            if (e.getSource() == timerMostrar) {

                if (counter < elModelo.palabrasMemorizar) {
                    palabraAMostrar.setText(palabrasMemorizar.get(counter));
                    counter++;
                } else {
                    timerMostrar.stop();
                    palabraAMostrar.setText("¡Buena suerte!");
                    continuar.setEnabled(true);
                    flagTimer = false;
                    counter = 0;
                    elModelo.estado = 1;
                }
            }

            if (e.getSource() == timerSeleccionar) {
                botonSi.setEnabled(true);
                botonNo.setEnabled(true);
                if (counter < elModelo.palabrasNivel) {
                    palabraSeleccionar.setText(palabrasNivel.get(counter));
                    palabra = palabrasNivel.get(counter);
                    System.out.println(elModelo.palabraEstaEnNivel(palabra));
                    counter++;
                } else {
                    timerSeleccionar.stop();
                    avanzar.setVisible(false);
                    reiniciarNivel.setVisible(false);
                    porcentajeAciertos = elModelo.porcentaje();
                    flagTimer = false;
                    elModelo.estado = 0;
                    counter = 0;
                    seleccionarPalabras.setVisible(false);
                    pantallaResultados.setVisible(true);
                    resultados();
                }
            }

            revalidate();
            repaint();
        }
    }
}
