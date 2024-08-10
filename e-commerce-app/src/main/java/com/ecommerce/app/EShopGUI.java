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
    private BackgroundPanel buttonPanel;
    private JPanel formPanel;
    private JPanel buttonGridPanel;
    private String previousSelection;

    private final ImageIcon pcTowerIcon = new ImageIcon(getClass().getResource("/photographs/computer.jpg"));
    private final ImageIcon pcScreenIcon = new ImageIcon(getClass().getResource("/photographs/screen.jpg"));
    private final ImageIcon personalComputerIcon = new ImageIcon(getClass().getResource("/photographs/desktop.jpg"));
    private final ImageIcon workstationIcon = new ImageIcon(getClass().getResource("/photographs/workstation.jpg"));
    private final ImageIcon background = new ImageIcon(getClass().getResource("/photographs/background.jpg"));


    public void createAndShowGUI() {

        frame = new JFrame("E-Shop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
    
        // BackgroundPanel with BorderLayout
        buttonPanel = new BackgroundPanel(background, new BorderLayout());
    
        // Header with label and exit button
        JLabel chooseProductLabel = new JLabel("Please choose the product that you desire");
        chooseProductLabel.setForeground(Color.WHITE); // Ensuring text is visible on the background
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false); // Ensure headerPanel is transparent
        headerPanel.add(chooseProductLabel, BorderLayout.CENTER);
        headerPanel.add(exitButton, BorderLayout.EAST);
    
        // Add the headerPanel to the top of buttonPanel
        buttonPanel.add(headerPanel, BorderLayout.NORTH);
    
        // Create buttons with icons and custom sizes
        Dimension buttonSize = new Dimension(300, 200);
        JButton pcTowerButton = createButton("PC Tower", pcTowerIcon, buttonSize);
        JButton pcScreenButton = createButton("PC Screen", pcScreenIcon, buttonSize);
        JButton personalComputerButton = createButton("Personal Computer", personalComputerIcon, buttonSize);
        JButton workstationButton = createButton("Workstation", workstationIcon, buttonSize);
    
        // Create a grid panel for buttons
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setOpaque(false); // Ensure gridPanel is transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        gbc.gridx = 0; gbc.gridy = 0;
        gridPanel.add(pcTowerButton, gbc);
    
        gbc.gridx = 1; gbc.gridy = 0;
        gridPanel.add(pcScreenButton, gbc);
    
        gbc.gridx = 0; gbc.gridy = 1;
        gridPanel.add(personalComputerButton, gbc);
    
        gbc.gridx = 1; gbc.gridy = 1;
        gridPanel.add(workstationButton, gbc);
    
        // Add the grid panel to the center of buttonPanel
        buttonPanel.add(gridPanel, BorderLayout.CENTER);
    
        //Create a text area for order summary and add to buttonPanel
        orderSummary = new JTextArea(5, 30);
        orderSummary.setEditable(false);
        orderSummary.setOpaque(false); // Make JTextArea transparent
        orderSummary.setForeground(Color.WHITE); // Ensure text is visible
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        scrollPane.setOpaque(false); // Make JScrollPane transparent
        scrollPane.getViewport().setOpaque(false); // Ensure the viewport is transparent
        buttonPanel.add(scrollPane, BorderLayout.SOUTH);
        
        // Add buttonPanel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);
    
        // Action listeners for buttons
        pcTowerButton.addActionListener(e -> showPCTowerForm());
        pcScreenButton.addActionListener(e -> showPCScreenForm());
        personalComputerButton.addActionListener(e -> showPersonalComputerForm());
        workstationButton.addActionListener(e -> showWorkstationForm());
    
        frame.pack();
        frame.setVisible(true);
    }
    
    private JButton createButton(String text, ImageIcon icon, Dimension size) {
        //a simple method to create a jbutton object, along with an image, and modify its size, dimensions and color etc
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setForeground(Color.WHITE);
        button.setIcon(resizeIcon(icon, size));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return button;
    }
    




    
    private void showForm(String prompt, String label1, String label2,
                      JTextField field1, JTextField field2, JTextField field3, JTextField field4,
                      JComboBox<String> comboBox) {
    clearPanel();

    // Create BackgroundPanel with the same background image as the main panel
    BackgroundPanel formBackgroundPanel = new BackgroundPanel(background, new BorderLayout());
    
    // Use GridBagLayout for the form content
    formPanel = new JPanel(new GridBagLayout());
    formPanel.setOpaque(false); // Ensure the formPanel is transparent
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Add header panel with exit button
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setOpaque(false); // Ensure headerPanel is transparent
    JLabel headerLabel = new JLabel(prompt);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    headerLabel.setForeground(Color.WHITE); // Ensure text is visible on background
    headerPanel.add(headerLabel, BorderLayout.CENTER);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(e -> System.exit(0));
    headerPanel.add(exitButton, BorderLayout.EAST);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    formPanel.add(headerPanel, gbc);
    gbc.gridwidth = 1;

    int gridY = 1;

    if (label1 != null && field1 != null) {
        JLabel label1Component = new JLabel(label1);
        label1Component.setFont(new Font("Arial", Font.BOLD, 14));
        label1Component.setForeground(Color.WHITE); // Ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(label1Component, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(field1, gbc);
        gridY++;
    }

    if (label2 != null && field2 != null) {
        JLabel label2Component = new JLabel(label2);
        label2Component.setFont(new Font("Arial", Font.BOLD, 14));
        label2Component.setForeground(Color.WHITE); // Ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(label2Component, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(field2, gbc);
        gridY++;
    }

    if (field3 != null) {
        JLabel screenSizeLabel = new JLabel("Screen Size (inches):");
        screenSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        screenSizeLabel.setForeground(Color.WHITE); // Ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(screenSizeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(field3, gbc);
        gridY++;
    }

    if (field4 != null) {
        JLabel hddLabel = new JLabel("HDD Size (GB):");
        hddLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hddLabel.setForeground(Color.WHITE); // Ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(hddLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(field4, gbc);
        gridY++;
    }

    if (comboBox != null) {
        JLabel osLabel = new JLabel("Operating System:");
        osLabel.setFont(new Font("Arial", Font.BOLD, 14));
        osLabel.setForeground(Color.WHITE); // Ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(osLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(comboBox, gbc);
        gridY++;
    }

    // Place "Go Back" button on the left
    goBackButton = new JButton("Go Back");
    gbc.gridx = 0;
    gbc.gridy = gridY;
    formPanel.add(goBackButton, gbc);

    // Place "Submit" button on the right
    JButton submitButton = new JButton("Submit");
    gbc.gridx = 1;
    gbc.gridy = gridY;
    formPanel.add(submitButton, gbc);

    // Add the formPanel to the BackgroundPanel
    formBackgroundPanel.add(formPanel, BorderLayout.CENTER);

    // Add BackgroundPanel to frame
    frame.add(formBackgroundPanel, BorderLayout.CENTER);













    submitButton.addActionListener(e -> handleSubmit(field1, field2, field3, field4, comboBox));
    goBackButton.addActionListener(e -> showProductSelection());

    frame.revalidate();
    frame.repaint();
}
















    private void showPCTowerForm() {
        previousSelection = "PC Tower";
        showForm("Please specify the attributes of the desired product",
                "Memory Size (GB):", "CPU Frequency (GHz):",
                new JTextField(10), new JTextField(10), null, null, null);
    }

    private void showPCScreenForm() {
        previousSelection = "PC Screen";

        showForm("Please specify the attributes of the desired product",
                "Screen Size (inches):", null, new JTextField(10), null, null, null, null);
    }

    private void showPersonalComputerForm() {
        previousSelection = "Personal Computer";
        showForm("Please specify the attributes of the desired product",
                "Memory Size (GB):", "CPU Frequency (GHz):",
                new JTextField(10), new JTextField(10),
                new JTextField(10), new JTextField(10), null);
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
    //this method creates our final, confirmation window displaying the objects (and their objects) and their attributes respectively. it has the background of the selected product, as opposed to the initial screen's background    

    JFrame confirmationFrame = new JFrame("Order Confirmation");
    confirmationFrame.setSize(400, 300);
    confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    confirmationFrame.setLayout(new BorderLayout());

    ImageIcon image = null;
    //depending on the object, choose an appropriate background image.
    if (objectName.equals("PC Tower")) {
        image = pcTowerIcon;
    } else if (objectName.equals("PC Screen")) {
        image = pcScreenIcon;
    } else if (objectName.equals("Personal Computer")) {
        image = personalComputerIcon;
    } else {
        image = workstationIcon;
    }


    //create a JPanel object containing the image as its background
    BackgroundPanel panel = new BackgroundPanel(image, new BorderLayout());

    JLabel messageLabel = new JLabel("Congratulations! You have made the order", JLabel.CENTER);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
    // Set the label to be non-opaque so it doesn't block the background
    messageLabel.setOpaque(false);
    messageLabel.setForeground(Color.WHITE);  // Set text color to white

    panel.add(messageLabel, BorderLayout.NORTH);

    JTextArea detailsArea = new JTextArea(10, 30);
    detailsArea.setEditable(false);
    // Make the text area transparent
    detailsArea.setOpaque(false);
    detailsArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
    detailsArea.setForeground(Color.WHITE); // Change text color for better visibility

    // Build the detailed confirmation message
    StringBuilder result = new StringBuilder();
    result.append("Order: ").append(objectName).append("\n\n");
    result.append(details);

    detailsArea.setText(result.toString());
    JScrollPane scrollPane = new JScrollPane(detailsArea);
    scrollPane.setOpaque(false); // Make the scroll pane transparent
    scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
    panel.add(scrollPane, BorderLayout.CENTER);

    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> confirmationFrame.dispose());
    okButton.setOpaque(false); // Make the button transparent
    okButton.setContentAreaFilled(false); // Ensure the button doesn't have a filled background
    okButton.setForeground(Color.WHITE); // Set text color to white

    panel.add(okButton, BorderLayout.SOUTH);

    //add the panel to the frame
    confirmationFrame.add(panel);

    //revalidate and repaint the frame to ensure the background is drawn
    confirmationFrame.revalidate();
    confirmationFrame.repaint();

    confirmationFrame.setVisible(true);
}


    
    


    private ImageIcon resizeIcon(ImageIcon icon, Dimension size) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }







}