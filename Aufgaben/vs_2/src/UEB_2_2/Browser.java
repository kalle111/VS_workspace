package UEB_2_2;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Browser extends JFrame implements ActionListener {

    private int downloads;
    private JProgressBar[] balken;
    private JButton startButton;

    // Deklaration Ihrer Synchronisations-Hilfsklassen hier:
    private final CountDownLatch start, stop;

    public Browser(int downloads) {
        super("Mein Download-Browser");
        this.downloads = downloads;

        start = new CountDownLatch(1);
        stop = new CountDownLatch(downloads);
        // Initialisierung Ihrer Synchronisations-Hilfsklassen hier:


        // Aufbau der GUI-Elemente:
        balken = new JProgressBar[downloads];
        JPanel zeilen = new JPanel(new GridLayout(downloads, 1));

        for (int i = 0; i < downloads; i++) {
            JPanel reihe = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
            balken[i] = new JProgressBar(0, 100);
            balken[i].setPreferredSize(new Dimension(500, 20));
            reihe.add(balken[i]);
            zeilen.add(reihe);


            // neue Download-Threads erzeugen und starten
            // ggf. müsseen Synchronisations-Objkte im Konstruktor übergeben werden!!
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
        System.out.println(start.getCount());
        startButton.setEnabled(false);
        startButton.setSelected(false);
        startButton.setText("Downloads laufen...");

        // Auf Ende aller Download-Threads warten ... erst dann die Beschriftung ändern
        // Achtung, damit die Oberflaeche "reaktiv" bleibt dies in einem eigenen Runnable ausfuehren!
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stop.await();
                    startButton.setText("ENDE");
                } catch (InterruptedException e1) {
                }
            }
        }).start();
    }
}