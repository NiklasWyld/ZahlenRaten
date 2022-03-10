import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static Integer myNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    static Integer tries = 0;
    static JLabel text = new JLabel("Gebe eine Zahl zwischen 0 und 100 ein!");
    static JTextField textField = new JTextField();
    static JLabel texttries = new JLabel("Versuche: " + tries);
    static JLabel bestscore = new JLabel("Best-Score: " + bestscore_score);
    static JButton button = new JButton("Raten!");
    static JButton a_but = new JButton("Aufloesen!");
    static JButton n_but = new JButton("Neue Runde!");
    static JButton e_but = new JButton("Beenden!");

    public static void main(String[] args) {
        openUI();
    }

    public static void openUI(){
        JFrame frame = new JFrame("Rate die Zahl");
        frame.setSize(340, 400);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                    "Bist du sicher, dass du das Spiel beenden willst?", "Spiel beenden?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        texttries.setBounds(240, 1, 200, 40);

        text.setBounds(50, 50, 300, 30);

        bestscore.setBounds(10, 1, 200, 40);

        textField.setBounds(50, 150, 225, 30);

        button.setBounds(50, 200, 225, 30);

        a_but.setBounds(50, 240, 225, 30);

        n_but.setBounds(50, 280, 225, 30);

        e_but.setBounds(50, 320, 225, 30);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String textFromTextfield = textField.getText();
                    Integer number = Integer.parseInt(textFromTextfield);
                    guess(number);
                } catch (Exception error) {
                    text.setText("Bitte gebe eine Zahl ein!");
                }
            }
        });

        a_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dissolve();
            }
        });

        n_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(true);
                a_but.setEnabled(true);
                tries = 0;
                texttries.setText("Versuche: " + tries);
                myNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            }
        });

        e_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Bist du sicher, dass du das Spiel beenden willst?", "Spiel beenden?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        Image icon = new ImageIcon("icon.png").getImage();

        frame.setLayout(null);
        frame.setIconImage(icon);
        frame.add(texttries);
        frame.add(bestscore);
        frame.add(button);
        frame.add(textField);
        frame.add(text);
        frame.add(e_but);
        frame.add(a_but);
        frame.add(n_but);
        frame.setVisible(true);
    }


    public static void guess(Integer number) {
        if(Objects.equals(number, myNumber)) {
            text.setText("Richtig geraten! Mit " + tries + " Versuchen.");
            button.setEnabled(false);
            a_but.setEnabled(false);
        } else {
            tries = tries + 1;
            texttries.setText("Versuche: " + tries);
            if(number < myNumber) {
                text.setText("Falsch geraten! Deine Zahl ist zu klein! ");
                textField.setText("");
            } else {
                text.setText("Falsch geraten! Deine Zahl ist zu gross! ");
                textField.setText("");
            }
        }
    }


    public static void dissolve() {
        text.setText(myNumber.toString());
        button.setEnabled(false);
        a_but.setEnabled(false);
    }

}
