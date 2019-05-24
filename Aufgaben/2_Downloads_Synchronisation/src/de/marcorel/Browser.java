package de.marcorel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import javax.swing.*;

public class Browser extends JFrame implements ActionListener {

    private int downloads;
    private JProgressBar[] balken;
    private JButton startButton;

    // Deklaration Ihrer Synchronisations-Hilfsklassen hier:
    private final CountDownLatch start, stop;

    public Browser(int downloads) {
        super("Mein Download-Browser");
        this.downloads = downloads;


        // Initialisierung Ihrer Synchronisations-Hilfsklassen hier:
        this.start = new CountDownLatch(downloads);
        this.stop = new CountDownLatch(1);

        // Aufbau der GUI-Elemente:
        balken = new JProgressBar[downloads];
        JPanel zeilen = new JPanel(new GridLayout(downloads, 2));

        for(int i=0; i<downloads; i++) {
            JPanel reihe = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); //gaps => padding top and sides.
            JPanel reihe2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
            reihe2.setPreferredSize(new Dimension(100,20));
            balken[i] = new JProgressBar(0, 100); //0 min, 100 max
            balken[i].setPreferredSize(new Dimension(500, 20)); //
            reihe.add(balken[i]);
            JTextPane jtext = new JTextPane();
            jtext.setPreferredSize(new Dimension(100,20));
            reihe2.add(jtext);
            zeilen.add(reihe);
            //zeilen.add(reihe2);



            // neue Download-Threads erzeugen und starten
            // ggf. müssen Synchronisations-Objekte im Konstruktor übergeben werden!!
            // balken ist ebenfalls zu übergeben!
            new Download(start, stop, balken[i]);
        }

        startButton = new JButton("Downloads starten");
        startButton.addActionListener(this);

        this.add(zeilen, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);


        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws InterruptedException {
        new Browser(5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Blockierte Threads jetzt laufen lassen:
        start.countDown();
        startButton.setEnabled(false);
        startButton.setSelected(false);
        startButton.setText("Marc's Downloads laufen...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stop.await();
                    startButton.setText("ENDE");
                }catch(InterruptedException er){}
            }
        }).start();














    }

}
