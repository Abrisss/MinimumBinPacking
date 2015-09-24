package gui;

import depository.Depository;
import utility.ThingsReader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    JPanel container;
    Board board;
    InputPanel inputPanel;
    JScrollPane pane;

    public MainFrame(Depository depo, ThingsReader thingsReader) {
        setTitle("Minimum Bin Packing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        container = new JPanel();
        container.setLayout(new BorderLayout());

        board = new Board(depo);
        inputPanel = new InputPanel(depo, thingsReader, board);
        container.add(inputPanel, BorderLayout.SOUTH);

        pane = new JScrollPane(board);
        pane.setPreferredSize(new Dimension(700, 0));
        container.add(pane, BorderLayout.CENTER);

        add(container);

        setVisible(true);
    }
}
