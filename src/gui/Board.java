package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    Timer timer;
    final int blockSize = 40;
    final int speed = 1000;
    final int pause = 1000;
    int rowNumber;
    int columnNumber;
    int actArrayNumber;

    public Board() {
        setBorder(BorderFactory.createEtchedBorder());
        actArrayNumber = 0;
        timer = new Timer(speed, this);
        timer.setInitialDelay(pause);
    }

    public void drawBoard(int rowNumber, int columnNumber) {
        setPreferredSize(new Dimension(
                columnNumber * (blockSize + 10), (rowNumber + 1) * (blockSize + 10)));
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        actArrayNumber = 0;
        timer.start();
        revalidate();
        repaint();
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

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//
//        for (int i = 0; i < actArrayNumber; i++) {
//            Number actNumber = SJTAlgorithm.allNumbers.
//                    get(i);
//            if (actNumber.isMoved()) {
//                g2d.setColor(Color.red);
//            } else {
//                g2d.setColor(Color.black);
//            }
//            int actNumberColumn = i / rowNumber;
//            int actNumberRow = i % rowNumber;
//            g2d.drawRect(actNumberColumn * (blockSize + 10) + 10,
//                    actNumberRow * (blockSize + 10) + 40, blockSize, blockSize);
//            g2d.setFont(new Font("", Font.PLAIN, 26));
//            g2d.drawString(Integer.toString(actNumber.getNumber()),
//                    actNumberColumn * (blockSize + 10) + 22,
//                    (actNumberRow + 1) * (blockSize + 10) + 20);
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        actArrayNumber++;
        repaint();
        if (actArrayNumber >= rowNumber * columnNumber) {
            actArrayNumber = rowNumber * columnNumber;
            timer.stop();
        }
    }
}
