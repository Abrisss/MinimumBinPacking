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
    private JButton instantlyBtn;

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

        instantlyBtn = new JButton("Instantly draw");
        instantlyBtn.setEnabled(false);
        instantlyBtn.addActionListener(this);
        add(instantlyBtn);

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
                        instantlyBtn.setEnabled(true);
                    } else {
                        firstBinAlgoBtn.setEnabled(false);
                        lastBinAlgoBtn.setEnabled(false);
                        instantlyBtn.setEnabled(false);
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
                init();
//                firstBinAlgorithm();
                stopBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
                break;
            case "LastBinAlgorithm":
                init();
                lastBinAlgorithm();
                stopBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
                break;
            case "Stop":
                resumeBtn.setEnabled(true);
                stopBtn.setEnabled(false);
//                board.timer.stop();
                break;
            case "Resume":
                resumeBtn.setEnabled(false);
                stopBtn.setEnabled(true);
//                board.timer.start();
                break;
            case "Instantly draw":
//                startAlgorithm("instantly");
                resumeBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                break;
        }
    }

    private void init() {
        depo.init(thingsReader.parseThings(fileName), thingsReader.parseBinsNumber(fileName));
        board.init(depo);
    }

    private void lastBinAlgorithm() {
        board.runLastBinAlgorithm();
//        depo.runLastBinAlgorithm();
    }

//    private void startAlgorithm(String type) {
//        if (type.equals("normal")) {
//            sjtAlgorithm = new SJTAlgorithm(maxNumber);
//            sjtAlgorithm.doAlgorithm();
//            board.drawBoard(maxNumber, factorial(maxNumber));
//        } else if (type.equals("instantly")) {
//            if (sjtAlgorithm == null || sjtAlgorithm.getMaxNumber() != maxNumber) {
//                sjtAlgorithm = new SJTAlgorithm(maxNumber);
//                sjtAlgorithm.doAlgorithm();
//            }
//            board.drawInstantlyBoard(maxNumber, factorial(maxNumber));
//        }
//    }

}
