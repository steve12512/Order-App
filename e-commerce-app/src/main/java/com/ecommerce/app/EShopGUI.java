package com.ecommerce.app;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EShopGUI {

    private JFrame frame;
    private JTextField memorySizeField;
    private JTextField cpuFreqField;
    private JTextField screenSizeField;
    private JTextField hddField;
    private JComboBox<String> osComboBox;
    private JTextArea orderSummary;
    private JButton goBackButton;
    private JPanel buttonPanel;
    private JPanel formPanel;
    private JPanel buttonGridPanel;
    private String previousSelection;

    public void createAndShowGUI() {
        frame = new JFrame("E-Shop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        buttonGridPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new BorderLayout());

        JLabel chooseProductLabel = new JLabel("Please choose the product that you desire");
        buttonPanel.add(chooseProductLabel, BorderLayout.NORTH);
        buttonPanel.add(buttonGridPanel, BorderLayout.CENTER);

        JButton pcTowerButton = new JButton("PC Tower");
        JButton pcScreenButton = new JButton("PC Screen");
        JButton personalComputerButton = new JButton("Personal Computer");
        JButton workstationButton = new JButton("Workstation");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        buttonGridPanel.add(pcTowerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        buttonGridPanel.add(pcScreenButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        buttonGridPanel.add(personalComputerButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        buttonGridPanel.add(workstationButton, gbc);

        orderSummary = new JTextArea(5, 30);
        orderSummary.setEditable(false);
        buttonPanel.add(new JScrollPane(orderSummary), BorderLayout.SOUTH);

        frame.add(buttonPanel, BorderLayout.CENTER);

        pcTowerButton.addActionListener(e -> showPCTowerForm());
        pcScreenButton.addActionListener(e -> showPCScreenForm());
        personalComputerButton.addActionListener(e -> showPersonalComputerForm());
        workstationButton.addActionListener(e -> showWorkstationForm());

        frame.setVisible(true);
    }

    private void showPCTowerForm() {
        previousSelection = "PC Tower";
        showForm("Please specify the attributes of the desired product",
                 "Memory Size (GB):", "CPU Frequency (GHz):",
                 new JTextField(10), new JTextField(10), null, null);
    }

    private void showPCScreenForm() {
        previousSelection = "PC Screen";
        showForm("Please specify the attributes of the desired product",
                 "Screen Size (inches):", null,
                 new JTextField(10), null, null, null);
    }

    private void showPersonalComputerForm() {
        previousSelection = "Personal Computer";
        showForm("Please specify the attributes of the desired product",
                 "Memory Size (GB):", "CPU Frequency (GHz):",
                 new JTextField(10), new JTextField(10),
                 new JTextField(10), new JTextField(10));
    }

    private void showWorkstationForm() {
        previousSelection = "Workstation";
        showForm("Please specify the attributes of the desired product",
                 "Memory Size (GB):", "CPU Frequency (GHz):",
                 new JTextField(10), new JTextField(10),
                 new JTextField(10), new JTextField(10),
                 new JComboBox<>(new String[]{"Windows", "Linux"}));
    }

    private void showForm(String prompt, String label1, String label2,
                          JTextField field1, JTextField field2, JTextField field3, JTextField field4) {
        showForm(prompt, label1, label2, field1, field2, field3, field4, null);
    }

    private void showForm(String prompt, String label1, String label2,
                          JTextField field1, JTextField field2, JTextField field3, JTextField field4,
                          JComboBox<String> comboBox) {
        clearPanel();

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel promptLabel = new JLabel(prompt);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(promptLabel, gbc);
        gbc.gridwidth = 1;

        int gridY = 1;

        if (label1 != null) {
            gbc.gridx = 0; gbc.gridy = gridY;
            formPanel.add(new JLabel(label1), gbc);
            gbc.gridx = 1; gbc.gridy = gridY;
            formPanel.add(field1, gbc);
            gridY++;
        }

        if (label2 != null) {
            gbc.gridx = 0; gbc.gridy = gridY;
            formPanel.add(new JLabel(label2), gbc);
            gbc.gridx = 1; gbc.gridy = gridY;
            formPanel.add(field2, gbc);
            gridY++;
        }

        if (field3 != null) {
            gbc.gridx = 0; gbc.gridy = gridY;
            formPanel.add(new JLabel("Screen Size (inches):"), gbc);
            gbc.gridx = 1; gbc.gridy = gridY;
            formPanel.add(field3, gbc);
            gridY++;
        }

        if (field4 != null) {
            gbc.gridx = 0; gbc.gridy = gridY;
            formPanel.add(new JLabel("HDD Size (GB):"), gbc);
            gbc.gridx = 1; gbc.gridy = gridY;
            formPanel.add(field4, gbc);
            gridY++;
        }

        if (comboBox != null) {
            gbc.gridx = 0; gbc.gridy = gridY;
            formPanel.add(new JLabel("Operating System:"), gbc);
            gbc.gridx = 1; gbc.gridy = gridY;
            formPanel.add(comboBox, gbc);
            gridY++;
        }

        JButton submitButton = new JButton("Submit");
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(submitButton, gbc);

        goBackButton = new JButton("Go Back");
        gbc.gridx = 1; gbc.gridy = gridY;
        formPanel.add(goBackButton, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> handleSubmit(field1, field2, field3, field4, comboBox));
        goBackButton.addActionListener(e -> showProductSelection());

        frame.revalidate();
        frame.repaint();
    }

    private void handleSubmit(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JComboBox<String> comboBox) {
        try {
            double memorySize = field1 != null ? Double.parseDouble(field1.getText()) : 0;
            double cpuFreq = field2 != null ? Double.parseDouble(field2.getText()) : 0;
            double screenSize = field3 != null ? Double.parseDouble(field3.getText()) : 0;
            int hddSize = field4 != null ? Integer.parseInt(field4.getText()) : 0;
            String os = comboBox != null ? (String) comboBox.getSelectedItem() : null;

            switch (previousSelection) {
                case "PC Tower":
                    Pc_Tower pcTower = new Pc_Tower(memorySize, cpuFreq);
                    orderSummary.setText(pcTower.toString());
                    break;
                case "PC Screen":
                    Pc_Screen pcScreen = new Pc_Screen(screenSize);
                    orderSummary.setText(pcScreen.toString());
                    break;
                case "Personal Computer":
                    Pc_Tower pcTowerPersonal = new Pc_Tower(memorySize, cpuFreq);
                    Pc_Screen pcScreenPersonal = new Pc_Screen(screenSize);
                    Personal_Computer pc = new Personal_Computer(pcTowerPersonal, pcScreenPersonal, hddSize);
                    orderSummary.setText(pc.toString());
                    break;
                case "Workstation":
                    Pc_Tower pcTowerWorkstation = new Pc_Tower(memorySize, cpuFreq);
                    Pc_Screen pcScreenWorkstation = new Pc_Screen(screenSize);
                    Personal_Computer pcWorkstation = new Personal_Computer(pcTowerWorkstation, pcScreenWorkstation, hddSize);
                    Workstation workstation = new Workstation(pcWorkstation, os);
                    orderSummary.setText(workstation.toString());
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showProductSelection() {
        clearPanel();
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void clearPanel() {
        frame.getContentPane().removeAll();
        orderSummary.setText(""); // Clear the text area
        frame.repaint();
    }
    
}