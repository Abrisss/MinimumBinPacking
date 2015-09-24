package gui;

import depository.Bin;
import depository.Depository;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    Timer timer;
    final int blockSize = 40;
    final int speed = 1000;
    final int pause = 1000;
    Depository depo;
    //utolsó 2 sor a bin-eké, az elsõ 8 a thing-eké
    int rowNumber;
    int columnNumber;
    int actArrayNumber;

    public Board(Depository depo) {
        setBorder(BorderFactory.createEtchedBorder());
        timer = new Timer(speed, this);
        timer.setInitialDelay(pause);
        rowNumber = 11;
        this.depo = depo;
    }

    public void init(Depository depo){
        this.depo = depo;
        columnNumber = (depo.getBins().size()/2+1 > depo.getThings().size()/8+1) ? depo.getBins().size()/2+1 : depo.getThings().size()/8+1;
    }

    public void runLastBinAlgorithm() {
        drawBoard();
        while(depo.runLastBinAlgorithmOneStep()){
            drawBoard();
        }
    }

    public void drawBoard() {
        setPreferredSize(new Dimension(
                columnNumber * (blockSize + 10), (rowNumber) * (blockSize + 10)));
        actArrayNumber = 0;
        timer.start();
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(depo.isInit()) {
            Graphics2D g2d = (Graphics2D) g.create();
            paintBins(g2d);
        }
    }

    private void paintBins(Graphics2D g2d) {
        List<Bin> bins = depo.getBins();
        for (int i = 0; i < bins.size(); i++) {
            Bin actBin = bins.get(i);
            if (actBin.isFull()) {
                g2d.setColor(Color.green);
            } else if(!actBin.isOpen()){
                g2d.setColor(Color.red);
            } else{
                g2d.setColor(Color.black);
            }

            int actNumberColumn = i / rowNumber;
            int actNumberRow = i % rowNumber;
            g2d.drawRect(actNumberColumn * (blockSize + 10) + 10,
                    actNumberRow * (blockSize + 10) + 40, blockSize, blockSize);
            g2d.setFont(new Font("", Font.PLAIN, 26));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        actArrayNumber++;
        repaint();
        if (actArrayNumber >= rowNumber * columnNumber) {
            actArrayNumber = rowNumber * columnNumber;
            timer.stop();
        }
    }


    public void drawInstantlyBoard(int rowNumber, int columnNumber) {
        setPreferredSize(new Dimension(
                columnNumber * (blockSize + 10), (rowNumber + 1) * (blockSize + 10)));
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        actArrayNumber = rowNumber * columnNumber;
        timer.start();
        revalidate();
        repaint();
    }


}
