package gui;

import algorithm.FirstBinAlgorithm;
import algorithm.LastBinAlgorithm;
import depository.Depository;
import utility.ThingsReader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

    JPanel container;
    Board board;
    InputPanel inputPanel;
    JScrollPane pane;

    public MainFrame(Depository depo, ThingsReader thingsReader) {
        setTitle("Minimum Bin Packing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        container = new JPanel();
        container.setLayout(new BorderLayout());

        inputPanel = new InputPanel(depo, thingsReader);
        container.add(inputPanel, BorderLayout.SOUTH);

//        pane = new JScrollPane(board);
//        pane.setPreferredSize(new Dimension(700, 0));
//        container.add(pane, BorderLayout.CENTER);

        add(container);

        setVisible(true);
    }
}
