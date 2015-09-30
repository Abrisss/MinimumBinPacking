package gui;

import depository.Bin;
import depository.Depository;
import depository.Thing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    Timer timer;
    final int size = 77;
    final int speed = 1000;
    final int pause = 1000;
    Depository depo;
    //utolsó 2 sor a bin-eké, az első 8 a thing-eké
    int rowNumber;
    int columnNumber;
    boolean isInstantlyPaint;
    boolean isFirstBinAlgorithm;

    public Board() {
        setBorder(BorderFactory.createEtchedBorder());
        timer = new Timer(speed, this);
        timer.setInitialDelay(pause);
        rowNumber = 7;
        isInstantlyPaint = false;
        isFirstBinAlgorithm = true;
    }

    public void init(Depository depo, boolean isInstantlyPaint) {
        this.depo = depo;
        this.isInstantlyPaint = isInstantlyPaint;
        columnNumber = (depo.getBins().size() / 2 + 1 > depo.getThings().size() / 4 + 1) ? depo.getBins().size() / 2 + 1 : depo.getThings().size() / 4 + 1;
    }

    public void runBinAlgorithm(boolean isFirstBinAlgorithm) {
        this.isFirstBinAlgorithm = isFirstBinAlgorithm;
        timer.start();
        drawBoard();
    }

    public void drawBoard() {
        setPreferredSize(new Dimension(
                columnNumber * (size + 10), (rowNumber) * (size + 10)));
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (depo != null && depo.isInit()) {
            Graphics2D g2d = (Graphics2D) g.create();
            paintBins(g2d);
            paintThings(g2d);
        }
    }

    private void paintThings(Graphics2D g2d) {
        List<Thing> things = depo.getThings();
        for (int i = 0; i < things.size(); i++) {
            Thing actThing = things.get(i);

            setThingColor(g2d, i);

            int actBinX = i % columnNumber;
            int actBinY = i / columnNumber + 1;

            fillThing(g2d, actThing, actBinX, actBinY);
        }
    }

    private void setThingColor(Graphics2D g2d, int i) {
        if (i == 0) {
            g2d.setColor(Color.orange);
        } else {
            g2d.setColor(Color.darkGray);
        }
    }

    private void fillThing(Graphics2D g2d, Thing actThing, int actBinX, int actBinY) {
        g2d.fillRect(actBinX * (size + 10) + 10,
                actBinY * (size + 10), size, (int) (size * actThing.getWeight()));
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
        g2d.drawRect(actBinX * (size + 10) + 10,
                actBinY * (size + 10), size, size);
    }

    private void fillBin(Graphics2D g2d, Bin actBin, int actBinX, int actBinY) {
        g2d.fillRect(actBinX * (size + 10) + 10,
                actBinY * (size + 10), size, size);

        g2d.setColor(Color.darkGray);
        g2d.fillRect(actBinX * (size + 10) + 10,
                actBinY * (size + 10), size, (int) (size * actBin.getWeight()));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (isFirstBinAlgorithm && !isInstantlyPaint) {
            if (depo.runFirstBinAlgorithmOneStep()) {
                drawBoard();
            } else {
                timer.stop();
            }
            repaint();
        } else if (isFirstBinAlgorithm && isInstantlyPaint) {
            while (depo.runFirstBinAlgorithmOneStep()) {
                drawBoard();
            }
            timer.stop();
            repaint();
        } else if (!isFirstBinAlgorithm && !isInstantlyPaint) {
            if (depo.runLastBinAlgorithmOneStep()) {
                drawBoard();
            } else {
                timer.stop();
            }
            repaint();
        } else if (!isFirstBinAlgorithm && isInstantlyPaint) {
            while (depo.runLastBinAlgorithmOneStep()) {
                drawBoard();
            }
            timer.stop();
            repaint();
        }
    }
}
