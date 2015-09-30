package gui;

import depository.Bin;
import depository.Depository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    Timer timer;
    final int binSize = 100;
    final int thingSize = 50;
    final int speed = 1000;
    final int pause = 1000;
    Depository depo;
    //utolsó 2 sor a bin-eké, az első 8 a thing-eké
    int rowNumber;
    int columnNumber;

    public Board() {
        setBorder(BorderFactory.createEtchedBorder());
        timer = new Timer(speed, this);
        timer.setInitialDelay(pause);
        rowNumber = 10;
    }

    public void init(Depository depo) {
        this.depo = depo;
        columnNumber = (depo.getBins().size() / 2 + 1 > depo.getThings().size() / 8 + 1) ? depo.getBins().size() / 2 + 1 : depo.getThings().size() / 8 + 1;
    }

    public void runLastBinAlgorithm() {
        timer.start();
        drawBoard();
    }

    public void drawBoard() {
        setPreferredSize(new Dimension(
                columnNumber * (binSize + 10), (rowNumber) * (binSize + 10)));
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (depo != null && depo.isInit()) {
            Graphics2D g2d = (Graphics2D) g.create();
            paintBins(g2d);
        }
    }

    private void paintThings(Graphics2D g2d){


    }

    private void paintBins(Graphics2D g2d) {
        List<Bin> bins = depo.getBins();
        for (int i = 0; i < bins.size(); i++) {
            Bin actBin = bins.get(i);
            setBinColor(g2d, actBin);

            int actBinX = i % (columnNumber);
            int actBinY = (i / columnNumber == 0) ? rowNumber - 2 : rowNumber - 1;

            paintBin(g2d, actBinX, actBinY);
            fillBin(g2d, actBin, actBinX, actBinY);
        }
    }

    private void setBinColor(Graphics2D g2d, Bin actBin) {
        if (actBin.isFull()) {
            g2d.setColor(Color.green);
        } else if (!actBin.isOpen()) {
            g2d.setColor(Color.red);
        } else {
            g2d.setColor(Color.black);
        }
    }

    private void paintBin(Graphics2D g2d, int actBinX, int actBinY) {
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRect(actBinX * (binSize + 10) + 10,
                actBinY * (binSize + 10) , binSize, binSize);
    }

    private void fillBin(Graphics2D g2d, Bin actBin, int actBinX, int actBinY) {
        g2d.fillRect(actBinX * (binSize + 10) + 10,
                actBinY * (binSize + 10) , binSize, binSize);

        g2d.setColor(Color.darkGray);
        g2d.fillRect(actBinX * (binSize + 10) + 10,
                actBinY * (binSize + 10) , binSize, (int)(binSize * actBin.getWeight()));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (depo.runLastBinAlgorithmOneStep()) {
            drawBoard();
        } else {
            timer.stop();
        }
        repaint();
    }
}
