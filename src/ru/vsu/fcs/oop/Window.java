package ru.vsu.fcs.oop;

import javax.swing.*;
import java.awt.*;

public class Window implements PlayerStrategy {
    public static final Factory FACTORY = new Factory() {
        @Override
        public PlayerStrategy create() {
            return new Window();
        }
    };

    private JFrame frame;
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton submitMoveButton;
    private MoveMaker moveMaker;
    private PlayerBoard playerBoard;
    private boolean waitingForMove = true;

    public Window () {

        frame = new JFrame("XO ru.vsu.fcs.oop.Game - ru.vsu.fcs.oop.Window Strategy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());


        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);


        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        submitMoveButton = new JButton("Сделать ход");
        inputPanel.add(new JLabel("Введите координаты (x,y): "), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitMoveButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);


        submitMoveButton.addActionListener(e -> handleMoveInput());


        frame.setVisible(true);
    }

    @Override
    public void makeMove(PlayerBoard pb, MoveMaker mm) {

        this.playerBoard = pb;
        this.moveMaker = mm;

        inputField.setEnabled(true);

        updateGameFieldDisplay();
    }

    private void handleMoveInput() {

        String input = inputField.getText().trim();
        inputField.setText("");

        try {

            String[] parts = input.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Введите координаты в формате x,y");
            }

            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());


            if (moveMaker != null)
                moveMaker.makeMove(new Point(x, y));
            moveMaker = null;

            inputField.setEnabled(false);
            inputField.setText("");
            outputArea.setText("ZHDEMS......");



            //waitingForMove = false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Ошибка: неверный формат ввода!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGameFieldDisplay() {
        //outputArea.setText(""); //????
       
        StringBuilder fieldDisplay = new StringBuilder();
        for (int i = 0; i < playerBoard.getHeight(); i++) {
            for (int j = 0; j < playerBoard.getWidth(); j++) {
                Cell cell = playerBoard.getCell(new Point(j, i));
                if (cell == Cell.EMPTY) {
                    fieldDisplay.append(". ");
                } else if (cell == Cell.MY) {
                    fieldDisplay.append("M ");
                } else if (cell == Cell.ENEMY) {
                    fieldDisplay.append("O ");
                }
            }
            fieldDisplay.append("\n");
        }

        outputArea.setText(fieldDisplay.toString());
    }



}
