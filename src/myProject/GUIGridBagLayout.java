package myProject;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIGridBagLayout extends JFrame {

    private Header headerProject;
    private JTextPane resultado;
    private JLabel estadisticas, palabraAMostrar, palabraSeleccionar, usuario, mostrarNombre, mostrarNivelActual, mostrarAciertos;
    private JPanel superior, inicio, nombreJugadorSu, nombreJugadorCe, nombreJugadorIn, panelMostrarPalabrasSu, panelMostrarPalabrasCe, panelMostrarPalabrasIn, estadoActual, seleccionarPalabrasSu, seleccionarPalabrasCe, seleccionarPalabrasIn, pantallaResultadosSu, pantallaResultadosCe, pantallaResultadosIn;
    private JTextField nombre;
    private JButton mostrarPalabras, jugar, ayuda, salir, botonSi, botonNo, aceptar, continuar, reiniciarNivel, avanzar;
    private Timer timerMostrar, timerSeleccionar;
    private Jugador elJugador;
    private ModelIKnowThatWord elModelo;
    private Escucha escucha;
    private Palabras palabras;
    private ArrayList<String> palabrasNivel, palabrasMemorizar;
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
        this.setBackground(Color.CYAN);
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

        //PANEL SUPERIOR
        {
            superior = new JPanel();
            superior.setBackground(Color.CYAN);
            superior.setPreferredSize(new Dimension(500, 40));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 3;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.LINE_START;

            ayuda = new JButton("?");
            ayuda.setPreferredSize(new Dimension(65, 30));

            ayuda.addActionListener(escucha);
            superior.add(ayuda);

            headerProject = new Header("I KNOW THAT WORD!!", Color.BLACK);
            headerProject.setPreferredSize(new Dimension(350, 30));
            superior.add(headerProject);

            salir = new JButton("Salir");
            salir.setPreferredSize(new Dimension(65, 30));
            salir.addActionListener(escucha);
            superior.add(salir);

            this.add(superior, constraints);
        }

        //PANEL INICIO
        {
            inicio = new JPanel();
            inicio.setBackground(Color.CYAN);
            inicio.setPreferredSize(new Dimension(500, 360));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 3;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.anchor = GridBagConstraints.LINE_START;
            imageInicio = new ImageIcon(this.getClass().getResource("/myProject/archivos/imagenInicio.png"));
            JLabel picLabel = new JLabel(imageInicio);
            inicio.add(picLabel);

            jugar = new JButton("Jugar");
            jugar.addActionListener(escucha);
            inicio.add(jugar);

            this.add(inicio, constraints);
        }

        //PANEL NOMBRE USUARIO
        {
            nombreJugadorSu = new JPanel();
            nombreJugadorSu.setBackground(Color.CYAN);
            nombreJugadorSu.setPreferredSize(new Dimension(500, 120));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 4;
            constraints.anchor = GridBagConstraints.PAGE_START;

            this.add(nombreJugadorSu, constraints);

            nombreJugadorCe = new JPanel();
            nombreJugadorCe.setBackground(Color.CYAN);
            nombreJugadorCe.setPreferredSize(new Dimension(500, 120));
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 4;
            constraints.anchor = GridBagConstraints.CENTER;

            this.add(nombreJugadorCe, constraints);

            usuario = new JLabel("Ingresa tu nombre");

            nombre = new JTextField(30);

            nombreJugadorCe.add(usuario);
            nombreJugadorCe.add(nombre);

            nombreJugadorIn = new JPanel();
            nombreJugadorIn.setBackground(Color.CYAN);
            nombreJugadorIn.setPreferredSize(new Dimension(500, 120));
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 4;
            constraints.anchor = GridBagConstraints.PAGE_END;

            this.add(nombreJugadorIn, constraints);

            aceptar = new JButton("Aceptar");
            aceptar.addActionListener(escucha);

            nombreJugadorIn.add(aceptar);

        }

        //PANEL MOSTRAR ESTADO
        {
            estadoActual = new JPanel();
            estadoActual.setBackground(Color.CYAN);
            estadoActual.setPreferredSize(new Dimension(500, 30));
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 3;

            mostrarNombre = new JLabel("Usuario: " + nombreUsuario);
            mostrarNombre.setSize(new Dimension(100, 30));
            mostrarNombre.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

            mostrarNivelActual = new JLabel("Nivel actual: " + nivelActual);
            mostrarNivelActual.setSize(new Dimension(100, 30));
            mostrarNivelActual.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

            mostrarAciertos = new JLabel("Aciertos: " + elModelo.aciertos);
            mostrarAciertos.setSize(new Dimension(100, 30));
            mostrarAciertos.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

            //estadoActual.add(ayuda);
            estadoActual.add(mostrarNombre);
            estadoActual.add(mostrarNivelActual);
            estadoActual.add(mostrarAciertos);
            //estadoActual.add(salir);

            this.add(estadoActual, constraints);
        }

        //PANEL MOSTRAR PALABRAS
        {
            panelMostrarPalabrasSu = new JPanel();
            panelMostrarPalabrasSu.setBackground(Color.CYAN);
            panelMostrarPalabrasSu.setPreferredSize(new Dimension(500, 80));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 3;

            this.add(panelMostrarPalabrasSu, constraints);

            panelMostrarPalabrasCe = new JPanel();
            panelMostrarPalabrasCe.setBackground(Color.ORANGE);
            panelMostrarPalabrasCe.setPreferredSize(new Dimension(500, 150));
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 3;

            this.add(panelMostrarPalabrasCe, constraints);

            palabraAMostrar = new JLabel("                                                     ");
            palabraAMostrar.setSize(500, 150);
            palabraAMostrar.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
            palabraAMostrar.setSize(500, 500);
            panelMostrarPalabrasCe.add(palabraAMostrar);

            palabraAMostrar = new JLabel("Aquí se mostrarán las palabras");
            palabraAMostrar.setSize(500, 150);
            palabraAMostrar.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
            palabraAMostrar.setSize(500, 500);
            panelMostrarPalabrasCe.add(palabraAMostrar);

            panelMostrarPalabrasIn = new JPanel();
            panelMostrarPalabrasIn.setBackground(Color.CYAN);
            panelMostrarPalabrasIn.setPreferredSize(new Dimension(500, 100));
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 3;

            this.add(panelMostrarPalabrasIn, constraints);

            continuar = new JButton("Continuar");
            continuar.addActionListener(escucha);
            continuar.setEnabled(false);

            panelMostrarPalabrasIn.add(continuar);

            mostrarPalabras = new JButton("Iniciar");
            mostrarPalabras.addActionListener(escucha);

            panelMostrarPalabrasIn.add(mostrarPalabras);

        }

        //PANEL SELECCIONAR PALABRAS
        {
            seleccionarPalabrasSu = new JPanel();
            seleccionarPalabrasSu.setBackground(Color.CYAN);
            seleccionarPalabrasSu.setPreferredSize(new Dimension(500, 110));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 3;

            this.add(seleccionarPalabrasSu, constraints);

            seleccionarPalabrasCe = new JPanel();
            seleccionarPalabrasCe.setBackground(Color.CYAN);
            seleccionarPalabrasCe.setPreferredSize(new Dimension(500, 110));
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 3;

            this.add(seleccionarPalabrasCe, constraints);

            palabraSeleccionar = new JLabel("¡Buena suerte!");
            palabraSeleccionar.setFont(new Font(Font.DIALOG, Font.BOLD, 30));

            seleccionarPalabrasCe.add(palabraSeleccionar);

            seleccionarPalabrasIn = new JPanel();
            seleccionarPalabrasIn.setBackground(Color.CYAN);
            seleccionarPalabrasIn.setPreferredSize(new Dimension(500, 110));
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 3;

            this.add(seleccionarPalabrasIn, constraints);

            botonSi = new JButton("Sí");
            botonSi.setEnabled(false);
            botonSi.setPreferredSize(new Dimension(100, 30));
            botonSi.addActionListener(escucha);

            botonNo = new JButton("No");
            botonNo.setEnabled(false);
            botonNo.setPreferredSize(new Dimension(100, 30));
            botonNo.addActionListener(escucha);

            seleccionarPalabrasIn.add(botonSi);
            seleccionarPalabrasIn.add(botonNo);

        }

        //PANEL RESULTADOS
        {
            pantallaResultadosSu = new JPanel();
            pantallaResultadosSu.setBackground(Color.CYAN);
            pantallaResultadosSu.setPreferredSize(new Dimension(500, 200));
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 3;

            this.add(pantallaResultadosSu, constraints);

            pantallaResultadosCe = new JPanel();
            pantallaResultadosCe.setBackground(Color.CYAN);
            pantallaResultadosCe.setPreferredSize(new Dimension(500, 65));
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 3;

            this.add(pantallaResultadosCe, constraints);

            pantallaResultadosIn = new JPanel();
            pantallaResultadosSu.setAlignmentX(Component.CENTER_ALIGNMENT);
            pantallaResultadosIn.setBackground(Color.CYAN);
            pantallaResultadosIn.setPreferredSize(new Dimension(500, 65));
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 3;

            this.add(pantallaResultadosIn, constraints);

            estadisticas = new JLabel("Aquí se mostrarán tus estadisticas");
            estadisticas.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

            resultado = new JTextPane();
            resultado.setText("Aquí se mostrará tu resultado");
            resultado.setPreferredSize(new Dimension(400, 200));

            StyledDocument doc = resultado.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            resultado.setEditable(false);
            resultado.setOpaque(false);
            resultado.setFont(new Font(Font.DIALOG, Font.BOLD, 30));

            reiniciarNivel = new JButton("Reiniciar nivel");
            reiniciarNivel.addActionListener(escucha);
            reiniciarNivel.setPreferredSize(new Dimension(100, 30));

            reiniciarNivel.setVisible(false);

            avanzar = new JButton("Siguiente nivel");
            avanzar.addActionListener(escucha);
            avanzar.setVisible(false);

            pantallaResultadosSu.add(estadisticas);
            pantallaResultadosSu.add(resultado);
            pantallaResultadosCe.add(avanzar);
            pantallaResultadosCe.add(reiniciarNivel);
        }

        superior.setVisible(true);
        inicio.setVisible(true);
        estadoActual.setVisible(false);
        nombreJugadorSu.setVisible(false);
        nombreJugadorCe.setVisible(false);
        nombreJugadorIn.setVisible(false);
        panelMostrarPalabrasSu.setVisible(false);
        panelMostrarPalabrasCe.setVisible(false);
        panelMostrarPalabrasIn.setVisible(false);
        seleccionarPalabrasSu.setVisible(false);
        seleccionarPalabrasCe.setVisible(false);
        seleccionarPalabrasIn.setVisible(false);
        pantallaResultadosSu.setVisible(false);
        pantallaResultadosCe.setVisible(false);
        pantallaResultadosIn.setVisible(false);

        timerMostrar = new Timer(5000, escucha);
        timerSeleccionar = new Timer(7000, escucha);
    }

    /**
     * Dependiendo del porcentaje de aciertos, lanza el mensaje de resultados
     */

    public void resultados() {
        if (elModelo.superoNivel()) {
            if (nivelActual == 10) {
                resultado.setText("Has superado el nivel máximo, podrás repetirlo si deseas.");
                estadisticas.setText("Porcentaje aciertos: " + elModelo.porcentaje + "%");
                reiniciarNivel.setVisible(true);
                nivelMaximoSuperado = nivelActual;
            } else {
                resultado.setText("Has pasado este nivel, podrás avanzar al siguiente nivel.");
                estadisticas.setText("Porcentaje aciertos: " + elModelo.porcentaje + "%");
                avanzar.setVisible(true);
                nivelMaximoSuperado = nivelActual;
                nivelActual = nivelActual + 1;
            }
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
                if (elJugador.nombreVacio(nombreUsuario) == true) {
                    System.exit(0);
                } else {
                    if (elJugador.estaRegistrado(nombreUsuario) == true) {
                        elJugador.actualizarUsuario(nombreUsuario, nivelMaximoSuperado);
                    } else {
                        elJugador.registrarJugador(nombreUsuario, nivelMaximoSuperado);
                    }
                    System.exit(0);
                }
            }

            if (e.getSource() == jugar) {
                inicio.setVisible(false);
                nombreJugadorSu.setVisible(true);
                nombreJugadorCe.setVisible(true);
                nombreJugadorIn.setVisible(true);
            }

            if (e.getSource() == aceptar) {

                System.out.println(nivelActual);

                nombreUsuario = nombre.getText().replaceAll("\\s+", "");

                if (elJugador.nombreVacio(nombreUsuario) == false) {
                    if (elJugador.estaRegistrado(nombreUsuario) == true) {
                        nivelMaximoSuperado = elJugador.getNivel();

                        if (nivelMaximoSuperado < 0 || nivelMaximoSuperado > 10) {
                            JOptionPane.showMessageDialog(null, "Error de sistema.");
                            System.exit(0);
                        }

                        if (nivelMaximoSuperado == 10) {
                            nivelActual = 10;
                        } else {
                            nivelActual = nivelMaximoSuperado + 1;
                        }
                        JOptionPane.showMessageDialog(null, "Usted ya se encuentra registrado, su nivel máximo superado es: " + nivelMaximoSuperado);
                    } else {
                        nivelActual = 1;
                        nivelMaximoSuperado = 0;
                    }

                    mostrarNombre.setText("Usuario: " + nombreUsuario);
                    mostrarNivelActual.setText("Nivel actual: " + nivelActual);
                    mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);

                    nombreJugadorSu.setVisible(false);
                    nombreJugadorCe.setVisible(false);
                    nombreJugadorIn.setVisible(false);

                    estadoActual.setVisible(true);
                    panelMostrarPalabrasSu.setVisible(true);
                    panelMostrarPalabrasCe.setVisible(true);
                    panelMostrarPalabrasIn.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre es muy corto o está vacío");
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
                panelMostrarPalabrasSu.setVisible(false);
                panelMostrarPalabrasCe.setVisible(false);
                panelMostrarPalabrasIn.setVisible(false);
                seleccionarPalabrasSu.setVisible(true);
                seleccionarPalabrasCe.setVisible(true);
                seleccionarPalabrasIn.setVisible(true);
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
                pantallaResultadosSu.setVisible(false);
                pantallaResultadosCe.setVisible(false);
                pantallaResultadosIn.setVisible(false);
                panelMostrarPalabrasSu.setVisible(true);
                panelMostrarPalabrasCe.setVisible(true);
                panelMostrarPalabrasIn.setVisible(true);
                palabraAMostrar.setText("Aquí se mostrarán las palabras");
                mostrarPalabras.setEnabled(true);
                continuar.setEnabled(false);
                elModelo.aciertos = 0;
                mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);
                mostrarNivelActual.setText("Nivel actual: " + nivelActual);
                elModelo.arrayPalabrasNivel.clear();
                elModelo.arrayPalabrasMemorizar.clear();
            }

            if (e.getSource() == reiniciarNivel) {
                pantallaResultadosSu.setVisible(false);
                pantallaResultadosCe.setVisible(false);
                pantallaResultadosIn.setVisible(false);
                panelMostrarPalabrasSu.setVisible(true);
                panelMostrarPalabrasCe.setVisible(true);
                panelMostrarPalabrasIn.setVisible(true);
                palabraAMostrar.setText("Aquí se mostrarán las palabras");
                mostrarPalabras.setEnabled(true);
                continuar.setEnabled(false);
                elModelo.aciertos = 0;
                mostrarAciertos.setText("Aciertos: " + elModelo.aciertos);
                mostrarNivelActual.setText("Nivel actual: " + nivelActual);
                elModelo.arrayPalabrasNivel.clear();
                elModelo.arrayPalabrasMemorizar.clear();
            }

            if (e.getSource() == timerMostrar) {

                if (counter < elModelo.palabrasMemorizar) {
                    palabraAMostrar.setText(palabrasMemorizar.get(counter));
                    counter++;
                } else {
                    timerMostrar.stop();
                    palabraAMostrar.setText("¡Buena suerte!");
                    continuar.setEnabled(true);
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
                    porcentajeAciertos = elModelo.calcularPorcentaje();
                    elModelo.estado = 0;
                    counter = 0;
                    seleccionarPalabrasSu.setVisible(false);
                    seleccionarPalabrasCe.setVisible(false);
                    seleccionarPalabrasIn.setVisible(false);
                    pantallaResultadosSu.setVisible(true);
                    pantallaResultadosCe.setVisible(true);
                    pantallaResultadosIn.setVisible(true);
                    resultados();
                }
            }

            revalidate();
            repaint();
        }
    }
}
