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
    /*class that creates window frames with buttons with icons, for the user to select which product he/she wants to purchase. he/she then proceeds to fill the attributes of the desired product in another window,
    and then has them portrayed along with their individual components and attributes, in the final window. there is the option to go back and select another product. null or non numeric values will throw an exception in the second window*/
    
    private JFrame frame;
    private JTextArea orderSummary;
    private JButton goBackButton;
    private BackgroundPanel buttonPanel;
    private JPanel formPanel;
    private String previousSelection;

    private final ImageIcon pcTowerIcon = new ImageIcon(getClass().getResource("/photographs/computer.jpg"));
    private final ImageIcon pcScreenIcon = new ImageIcon(getClass().getResource("/photographs/screen.jpg"));
    private final ImageIcon personalComputerIcon = new ImageIcon(getClass().getResource("/photographs/desktop.jpg"));
    private final ImageIcon workstationIcon = new ImageIcon(getClass().getResource("/photographs/workstation.jpg"));
    private final ImageIcon background = new ImageIcon(getClass().getResource("/photographs/background.jpg"));


    public void createAndShowGUI() {
        //creates and displays the initial window frame

        frame = new JFrame("E-Shop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
    
        //custom backgroundPanel with BorderLayout and custom background image
        buttonPanel = new BackgroundPanel(background, new BorderLayout());
    
        //header with label and exit button
        JLabel chooseProductLabel = new JLabel("Please choose the product that you desire");
        chooseProductLabel.setForeground(Color.WHITE); // Ensuring text is visible on the background
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false); // Ensure headerPanel is transparent
        headerPanel.add(chooseProductLabel, BorderLayout.CENTER);
        headerPanel.add(exitButton, BorderLayout.EAST);
    
        //add the headerPanel to the top of buttonPanel
        buttonPanel.add(headerPanel, BorderLayout.NORTH);
    
        //create buttons with icons and custom sizes
        Dimension buttonSize = new Dimension(300, 200);
        JButton pcTowerButton = createButton("PC Tower", pcTowerIcon, buttonSize);
        JButton pcScreenButton = createButton("PC Screen", pcScreenIcon, buttonSize);
        JButton personalComputerButton = createButton("Personal Computer", personalComputerIcon, buttonSize);
        JButton workstationButton = createButton("Workstation", workstationIcon, buttonSize);
    
        //create a grid panel for buttons
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
    
        //add the grid panel to the center of buttonPanel. make sure to have transparent components, so that they dont cancel the background image
        buttonPanel.add(gridPanel, BorderLayout.CENTER);
    
        //create a text area for order summary and add to buttonPanel
        orderSummary = new JTextArea(5, 30);
        orderSummary.setEditable(false);
        orderSummary.setOpaque(false); //make JTextArea transparent
        orderSummary.setForeground(Color.WHITE); //ensure text is visible
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        scrollPane.setOpaque(false); //make JScrollPane transparent
        scrollPane.getViewport().setOpaque(false); //ensure the viewport is transparent
        buttonPanel.add(scrollPane, BorderLayout.SOUTH);
        
        //add buttonPanel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);
    
        //action listeners for buttons
        //depending on the button clicked, a product specific method is called, which then calls the general show form method
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
    

    //these methods call the general show form method
    private void showPCTowerForm() {
        previousSelection = "PC Tower";
        showForm("Please specify the attributes of the desired product",
                "Memory Size (GB):", "CPU Frequency (GHz):",
                new JTextField(10), new JTextField(10), null, null, null);
    }

    private void showPCScreenForm() {
        previousSelection = "PC Screen";
        showForm("Please specify the attributes of the desired product",
                "Screen Size (inches):", null, null, null, new JTextField(10), null, null);
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
                new JComboBox<>(new String[]{"Windows", "MacOs", "Linux"}));
    }




private void showForm(String prompt, String label1, String label2,
                      JTextField memory, JTextField cpu, JTextField screen_size, JTextField pc,
                      JComboBox<String> comboBox) {
    /*general showform method, called from a specific product showform method. some values are null, some arent. based on this, it decides which jtext fields to display and which not.
    after the user provides data for the fields, it passes them to the handle submit method. if any field is null, or of non numeric value, it throws an exception. it also provides a button to go back to the previous window*/

    clearPanel();

    BackgroundPanel formBackgroundPanel = new BackgroundPanel(background, new BorderLayout());    //create BackgroundPanel with the same background image as the main panel


    formPanel = new JPanel(new GridBagLayout());
    formPanel.setOpaque(false); //ensure the formPanel is transparent
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setOpaque(false); //ensure headerPanel is transparent
    JLabel headerLabel = new JLabel(prompt);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    headerLabel.setForeground(Color.WHITE); //ensure text is visible on background
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

    if (label1 != null && memory != null) {
        JLabel label1Component = new JLabel(label1);
        label1Component.setFont(new Font("Arial", Font.BOLD, 14));
        label1Component.setForeground(Color.WHITE); //ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(label1Component, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(memory, gbc);
        gridY++;
    }

    if (label2 != null && cpu != null) {
        JLabel label2Component = new JLabel(label2);
        label2Component.setFont(new Font("Arial", Font.BOLD, 14));
        label2Component.setForeground(Color.WHITE); //ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(label2Component, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(cpu, gbc);
        gridY++;
    }

    if (screen_size != null) {
        JLabel screenSizeLabel = new JLabel("Screen Size (inches):");
        screenSizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        screenSizeLabel.setForeground(Color.WHITE); //ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(screenSizeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(screen_size, gbc);
        gridY++;
    }

    if (pc != null) {
        JLabel hddLabel = new JLabel("HDD Size (GB):");
        hddLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hddLabel.setForeground(Color.WHITE); //ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(hddLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(pc, gbc);
        gridY++;

        //add label indicating a personal computer contains a PC Tower and a PC Screen
        //different messages whether this is just a personal computer and not a workstation
        JLabel pcInfoLabel;
        if (comboBox == null) {
             pcInfoLabel = new JLabel("Great! You have selected a Personal Computer, which contains a PC Tower and a PC Screen along with it.");
        } else {
             pcInfoLabel = new JLabel("A Personal Computer also contains a PC Tower and a PC Screen along with it.");
        }
        pcInfoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        pcInfoLabel.setForeground(Color.BLACK); //different color to highlight
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(pcInfoLabel, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gridY++;
    }

    if (comboBox != null) {
        JLabel osLabel = new JLabel("Operating System:");
        osLabel.setFont(new Font("Arial", Font.BOLD, 14));
        osLabel.setForeground(Color.WHITE); //ensure text is visible on background
        gbc.gridx = 0;
        gbc.gridy = gridY;
        formPanel.add(osLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = gridY;
        formPanel.add(comboBox, gbc);
        gridY++;

        //add label indicating a workstation contains a personal computer
        JLabel workstationInfoLabel = new JLabel("Great! You have selected a Workstation, which also contains a Personal Computer along with it.");
        workstationInfoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        workstationInfoLabel.setForeground(Color.BLACK); //different color to highlight
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(workstationInfoLabel, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gridY++;
    }

    //add a label if only PC is selected
    if (pc == null && comboBox == null && screen_size == null) {
        JLabel pcTowerLabel = new JLabel("Great! You have selected a PC Tower!");
        pcTowerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        pcTowerLabel.setForeground(Color.BLACK); //highlight color
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(pcTowerLabel, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gridY++;
    }

    //add a label if only PC Screen is selected
    if (pc == null && comboBox == null && memory == null && cpu == null && screen_size != null) {
        JLabel pcScreenLabel = new JLabel("Great! You have selected a PC Screen!");
        pcScreenLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        pcScreenLabel.setForeground(Color.BLACK); //highlight color
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(pcScreenLabel, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gridY++;
    }

    goBackButton = new JButton("Go Back"); //add a back button
    gbc.gridx = 0;
    gbc.gridy = gridY;
    formPanel.add(goBackButton, gbc);

    JButton submitButton = new JButton("Submit"); //add a submite button
    gbc.gridx = 1;
    gbc.gridy = gridY;
    formPanel.add(submitButton, gbc);

    formBackgroundPanel.add(formPanel, BorderLayout.CENTER);
    frame.add(formBackgroundPanel, BorderLayout.CENTER);

    submitButton.addActionListener(e -> handleSubmit(memory, cpu, screen_size, pc, comboBox));
    goBackButton.addActionListener(e -> showProductSelection());

    frame.revalidate();
    frame.repaint();
}







    private void handleSubmit(JTextField field1, JTextField field2, JTextField field3, JTextField field4, JComboBox<String> comboBox) {
        //this method handles the submit from the showform method, concatenated a result string based on the objects and the attributes selected, and pases it on to the next method, called confirmation window, which displays them

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
        //clears screen and reverts back to the product selection screen
        clearPanel();
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void clearPanel() {
        //clears the screen
        frame.getContentPane().removeAll();
        orderSummary.setText(""); //clear the text area
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

    JLabel messageLabel = new JLabel("Congratulations! You have made an order", JLabel.CENTER);
    messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
    //set the label to be non-opaque so it doesn't block the background
    messageLabel.setOpaque(false);
    messageLabel.setForeground(Color.WHITE);  // Set text color to white

    panel.add(messageLabel, BorderLayout.NORTH);

    JTextArea detailsArea = new JTextArea(10, 30);
    detailsArea.setEditable(false);
    //make the text area transparent
    detailsArea.setOpaque(false);
    detailsArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
    detailsArea.setForeground(Color.WHITE); // Change text color for better visibility

    //build the detailed confirmation message
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
    okButton.setOpaque(false); //make the button transparent
    okButton.setContentAreaFilled(false); //ensure the button doesn't have a filled background
    okButton.setForeground(Color.WHITE); //set text color to white

    panel.add(okButton, BorderLayout.SOUTH);

    //add the panel to the frame
    confirmationFrame.add(panel);

    //revalidate and repaint the frame to ensure the background is drawn
    confirmationFrame.revalidate();
    confirmationFrame.repaint();

    confirmationFrame.setVisible(true);
}


    

    private ImageIcon resizeIcon(ImageIcon icon, Dimension size) {
        //resizes an ImageIcon object so that it fits properly in our grid
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}