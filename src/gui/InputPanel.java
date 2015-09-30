package gui;

import depository.Depository;
import utility.ThingsReader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class InputPanel extends JPanel implements ActionListener {

    private Depository depo;
    private Board board;
    private ThingsReader thingsReader;
    private String fileName;

    private JButton firstBinAlgoBtn;
    private JButton lastBinAlgoBtn;
    private JButton stopBtn;
    private JButton resumeBtn;
    private JButton instantlyFirstBinBtn;
    private JButton instantlyLastBinBtn;

    public InputPanel(Depository depo, ThingsReader thingsReader, Board board) {
        this.depo = depo;
        this.thingsReader = thingsReader;
        this.board = board;
        setBorder(BorderFactory.createEtchedBorder());
        addButtons();
        addInputField();
    }

    private void addButtons() {
        firstBinAlgoBtn = new JButton("FirstBinAlgorithm");
        firstBinAlgoBtn.setEnabled(false);
        firstBinAlgoBtn.addActionListener(this);
        add(firstBinAlgoBtn);

        lastBinAlgoBtn = new JButton("LastBinAlgorithm");
        lastBinAlgoBtn.setEnabled(false);
        lastBinAlgoBtn.addActionListener(this);
        add(lastBinAlgoBtn);

        stopBtn = new JButton("Stop");
        stopBtn.setEnabled(false);
        stopBtn.addActionListener(this);
        add(stopBtn);

        resumeBtn = new JButton("Resume");
        resumeBtn.setEnabled(false);
        resumeBtn.addActionListener(this);
        add(resumeBtn);

        instantlyFirstBinBtn = new JButton("Instantly draw FirstBinAlgorithm");
        instantlyFirstBinBtn.setEnabled(false);
        instantlyFirstBinBtn.addActionListener(this);
        add(instantlyFirstBinBtn);

        instantlyLastBinBtn = new JButton("Instantly draw LastBinAlgorithm");
        instantlyLastBinBtn.setEnabled(false);
        instantlyLastBinBtn.addActionListener(this);
        add(instantlyLastBinBtn);

    }

    private void addInputField() {
        JTextField fileNameField = new JFormattedTextField();
        fileNameField.getDocument().addDocumentListener(new DocumentListener() {
            void checkDocument(DocumentEvent e) {
                try {
                    fileName = e.getDocument().getText(0,
                            e.getDocument().getLength());
                    if (Pattern.matches("\\w+\\w*", fileName)) {
                        firstBinAlgoBtn.setEnabled(true);
                        lastBinAlgoBtn.setEnabled(true);
                        instantlyFirstBinBtn.setEnabled(true);
                        instantlyLastBinBtn.setEnabled(true);
                    } else {
                        firstBinAlgoBtn.setEnabled(false);
                        lastBinAlgoBtn.setEnabled(false);
                        instantlyFirstBinBtn.setEnabled(false);
                        instantlyLastBinBtn.setEnabled(false);
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(InputPanel.class.getName()).
                            log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkDocument(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkDocument(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkDocument(e);
            }
        });
        fileNameField.setColumns(10);
        add(fileNameField);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getText()) {
            case "FirstBinAlgorithm":
                initFirstBinAlgorithm(false);
                board.runBinAlgorithm(true);
                stopBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
                break;
            case "LastBinAlgorithm":
                initLastBinAlgorithm(false);
                board.runBinAlgorithm(false);
                stopBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
                break;
            case "Stop":
                resumeBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                board.timer.stop();
                break;
            case "Resume":
                resumeBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                board.timer.start();
                break;
            case "Instantly draw FirstBinAlgorithm":
                initFirstBinAlgorithm(true);
                board.runBinAlgorithm(true);
                resumeBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                break;
            case "Instantly draw LastBinAlgorithm":
                initLastBinAlgorithm(true);
                board.runBinAlgorithm(false);
                resumeBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                break;
        }
    }

    private void initLastBinAlgorithm(boolean isInstantlyPaint) {
        depo.init(thingsReader.parseThings(fileName), thingsReader.parseBinsNumber(fileName));
        board.init(depo, isInstantlyPaint);
    }

    private void initFirstBinAlgorithm(boolean isInstantlyPaint) {
        depo.init(thingsReader.parseThings(fileName), thingsReader.parseBinsNumber(fileName));
        depo.sort();
        board.init(depo, isInstantlyPaint);
    }
}
