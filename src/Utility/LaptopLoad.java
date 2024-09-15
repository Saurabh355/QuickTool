package Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class LaptopLoad {

    private static JPanel laptopLoadPanel1;
    private static JPanel optionsPanel;
    private static Properties properties;

    public static void laptopLoad(JPanel optionsPanel, Properties properties) {

        LaptopLoad.optionsPanel = optionsPanel;
        optionsPanel.setVisible(false);

        LaptopLoad.properties = properties;

        laptopLoadPanel1 = new JPanel();
        laptopLoadPanel1.setLayout(new BorderLayout(20, 20));
        laptopLoadPanel1.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Use an array to store the isAdminChecked value
        final boolean[] isAdminChecked = {false};

        // Top left panel with the checkbox
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JCheckBox checkBox = new JCheckBox("Administrator");
        checkBoxPanel.add(checkBox);

        laptopLoadPanel1.add(checkBoxPanel, BorderLayout.NORTH);

        // Center panel with the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));

        JButton button0 = new JButton("Clear NewPos");
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = getPath("LaptopLoad")+ "1 Clear_NewPOS.bat"; // Replace with the actual path of the bat file
                runBatFile(path, isAdminChecked[0]);
            }
        });
        buttonPanel.add(button0);

        JButton button1 = new JButton("Start Npsharp");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = getPath("LaptopLoad")+"2 Start by NPSharp.bat";
                System.out.println(getPath("LaptopLoad")+"2 Start by NPSharp.bat");  // Replace with the actual path of the bat file
                runBatFile(path, isAdminChecked[0]);
            }
        });
        buttonPanel.add(button1);

        JButton button2 = new JButton("Close All Apps");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = getPath("LaptopLoad")+"3 Close_All_Apps.bat"; // Replace with the actual path of the bat file
                runBatFile(path, isAdminChecked[0]);
            }
        });
        buttonPanel.add(button2);

        JButton button3 = new JButton("Clear Npsharp Logs");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = getPath("LaptopLoad")+"Clear_NewPOS_Logs.bat"; // Replace with the actual path of the bat file
                runBatFile(path, isAdminChecked[0]);
            }
        });
        buttonPanel.add(button3);

        laptopLoadPanel1.add(buttonPanel, BorderLayout.CENTER);

        // Bottom panel with the "Back" button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOptionsPanel();
            }
        });
        bottomPanel.add(backButton);

        laptopLoadPanel1.add(bottomPanel, BorderLayout.SOUTH);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.add(laptopLoadPanel1, BorderLayout.CENTER);
        frame.revalidate();
    }

    private static String getPath(String key) {
        return properties.getProperty(key, "");
    }

    private static void runBatFile(String path, boolean isAdminChecked) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Error: Bat file not found at " + path);
            return;
        }

        try {            // Use Desktop class to open the bat file            if (Desktop.isDesktopSupported()) {                Desktop desktop = Desktop.getDesktop();                if (isAdminChecked) {                    // Use runas command to execute the bat file as administrator                    Runtime.getRuntime().exec("runas /user:Administrator " + file.getAbsolutePath());                } else {                    // Just open the bat file without admin privileges                    desktop.open(file);                }            }        } catch (IOException ex) {            ex.printStackTrace();        }
    }

    private static void showOptionsPanel() {
        laptopLoadPanel1.setVisible(false);
        optionsPanel.setVisible(true);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.remove(laptopLoadPanel1);
        frame.revalidate();
    }
}
