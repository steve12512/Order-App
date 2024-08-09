package com.ecommerce.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

    private final ImageIcon pcTowerIcon = new ImageIcon(getClass().getResource("/photographs/computer.jpg"));
    private final ImageIcon pcScreenIcon = new ImageIcon(getClass().getResource("/photographs/screen.jpg"));
    private final ImageIcon personalComputerIcon = new ImageIcon(getClass().getResource("/photographs/desktop.jpg"));
    private final ImageIcon workstationIcon = new ImageIcon(getClass().getResource("/photographs/workstation.jpg"));



    public void createAndShowGUI() {

        frame = new JFrame("E-Shop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        buttonGridPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new BorderLayout());

        JLabel chooseProductLabel = new JLabel("Please choose the product that you desire");
        buttonPanel.add(chooseProductLabel, BorderLayout.NORTH);
        buttonPanel.add(buttonGridPanel, BorderLayout.CENTER);

        
        //set default button dimension
        Dimension buttonSize = new Dimension(300, 200);

        //create the buttons
        JButton pcTowerButton = new JButton("PC Tower");
        JButton pcScreenButton = new JButton("PC Screen");
        JButton personalComputerButton = new JButton("Personal Computer");
        JButton workstationButton = new JButton("Workstation");

        //specify their size dimensions
        pcTowerButton.setPreferredSize(buttonSize);
        pcScreenButton.setPreferredSize(buttonSize);
        personalComputerButton.setPreferredSize(buttonSize);
        workstationButton.setPreferredSize(buttonSize);

        //change button colors
        pcTowerButton.setForeground(Color.WHITE);
        pcScreenButton.setForeground(Color.WHITE);
        personalComputerButton.setForeground(Color.WHITE);
        workstationButton.setForeground(Color.WHITE);


        //resize images so that they fit nicely with their corresponding buttons and then add the images to their buttons
        pcTowerButton.setIcon(resizeIcon(pcTowerIcon, buttonSize));
        pcScreenButton.setIcon(resizeIcon(pcScreenIcon, buttonSize));
        personalComputerButton.setIcon(resizeIcon(personalComputerIcon, buttonSize));
        workstationButton.setIcon(resizeIcon(workstationIcon, buttonSize));


        //set positions
        pcTowerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pcScreenButton.setHorizontalTextPosition(SwingConstants.CENTER);
        personalComputerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        workstationButton.setHorizontalTextPosition(SwingConstants.CENTER);

        //add button frames
        pcTowerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border
        pcScreenButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border
        personalComputerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border
        workstationButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border


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



    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.add(chooseProductLabel, BorderLayout.CENTER);
    headerPanel.add(exitButton, BorderLayout.EAST);
    buttonPanel.add(headerPanel, BorderLayout.NORTH);

    frame.pack();
    frame.setVisible(true);
    }

    // Method for all parameters including JComboBox
private void showForm(String prompt, String label1, String label2,
                      JTextField field1, JTextField field2, JTextField field3, JTextField field4,
                      JComboBox<String> comboBox) {
    clearPanel();

    formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Add header panel with exit button
    JPanel headerPanel = new JPanel(new BorderLayout());
    JLabel headerLabel = new JLabel(prompt);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    headerPanel.add(headerLabel, BorderLayout.CENTER);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    headerPanel.add(exitButton, BorderLayout.EAST);

    gbc.gridx = 0; gbc.gridy = 0;
    gbc.gridwidth = 2;
    formPanel.add(headerPanel, gbc);
    gbc.gridwidth = 1;

    int gridY = 1;

    if (label1 != null && field1 != null) {
        JLabel label1Component = new JLabel(label1);
        label1Component.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(label1Component, gbc);
        gbc.gridx = 1; gbc.gridy = gridY;
        formPanel.add(field1, gbc);
        gridY++;
    }

    if (label2 != null && field2 != null) {
        JLabel label2Component = new JLabel(label2);
        label2Component.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(label2Component, gbc);
        gbc.gridx = 1; gbc.gridy = gridY;
        formPanel.add(field2, gbc);
        gridY++;
    }

    if (field3 != null) {
        JLabel screenSizeLabel = new JLabel("Screen Size (inches):");
        screenSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(screenSizeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = gridY;
        formPanel.add(field3, gbc);
        gridY++;
    }

    if (field4 != null) {
        JLabel hddLabel = new JLabel("HDD Size (GB):");
        hddLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(hddLabel, gbc);
        gbc.gridx = 1; gbc.gridy = gridY;
        formPanel.add(field4, gbc);
        gridY++;
    }

    if (comboBox != null) {
        JLabel osLabel = new JLabel("Operating System:");
        osLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = gridY;
        formPanel.add(osLabel, gbc);
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

// Overloaded method for cases where JComboBox and some fields might be null
private void showForm(String prompt, String label1, String label2,
                      JTextField field1, JTextField field2, JTextField field3, JTextField field4) {
    showForm(prompt, label1, label2, field1, field2, field3, field4, null);
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













    

    


    private void handleSubmit(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JComboBox<String> comboBox) {
        try {
            double memorySize = field1 != null ? Double.parseDouble(field1.getText()) : 0;
            double cpuFreq = field2 != null ? Double.parseDouble(field2.getText()) : 0;
            double screenSize = field3 != null ? Double.parseDouble(field3.getText()) : 0;
            int hddSize = field4 != null ? Integer.parseInt(field4.getText()) : 0;
            String os = comboBox != null ? (String) comboBox.getSelectedItem() : null;
    
            String result;
            String objectName;
    
            switch (previousSelection) {
                case "PC Tower":
                    Pc_Tower pcTower = new Pc_Tower(memorySize, cpuFreq);
                    result = pcTower.toString();
                    objectName = "PC Tower";
                    break;
                case "PC Screen":
                    Pc_Screen pcScreen = new Pc_Screen(screenSize);
                    result = pcScreen.toString();
                    objectName = "PC Screen";
                    break;
                case "Personal Computer":
                    Pc_Tower pcTowerPersonal = new Pc_Tower(memorySize, cpuFreq);
                    Pc_Screen pcScreenPersonal = new Pc_Screen(screenSize);
                    Personal_Computer pc = new Personal_Computer(pcTowerPersonal, pcScreenPersonal, hddSize);
                    result = pc.toString();
                    objectName = "Personal Computer";
                    break;
                case "Workstation":
                    Pc_Tower pcTowerWorkstation = new Pc_Tower(memorySize, cpuFreq);
                    Pc_Screen pcScreenWorkstation = new Pc_Screen(screenSize);
                    Personal_Computer pcWorkstation = new Personal_Computer(pcTowerWorkstation, pcScreenWorkstation, hddSize);
                    Workstation workstation = new Workstation(pcWorkstation, os);
                    result = workstation.toString();
                    objectName = "Workstation";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + previousSelection);
            }
    
            showConfirmationDialog(objectName, result);
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
    
    private void showConfirmationDialog(String objectName, String details) {
        JFrame confirmationFrame = new JFrame("Order Confirmation");
        confirmationFrame.setSize(400, 300);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirmationFrame.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Congratulations! You have made the order", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        confirmationFrame.add(messageLabel, BorderLayout.NORTH);

        JTextArea detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        detailsArea.setText("Order: " + objectName + "\n\nDetails:\n" + details);
        confirmationFrame.add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> confirmationFrame.dispose());
        confirmationFrame.add(okButton, BorderLayout.SOUTH);

        confirmationFrame.setVisible(true);
    }


    private ImageIcon resizeIcon(ImageIcon icon, Dimension size) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }







}