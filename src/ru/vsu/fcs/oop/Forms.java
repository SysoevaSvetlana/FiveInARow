package ru.vsu.fcs.oop;

import javax.swing.*;

public class Forms extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Forms() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        Forms dialog = new Forms();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
