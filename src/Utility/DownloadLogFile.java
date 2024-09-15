package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class DownloadLogFile {
    private static JPanel logsPanel;
    private static JPanel optionsPanel;

    public static void downloadLogs(JPanel optionsPanel, Properties properties) {
        DownloadLogFile.optionsPanel = optionsPanel;
        optionsPanel.setVisible(false);


        logsPanel = new JPanel();
        logsPanel.setLayout(new BoxLayout(logsPanel, BoxLayout.Y_AXIS)); // Use BoxLayout to stack components vertically

        // Create a panel for the text field
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel("Enter Session ID");
        textFieldPanel.add(label);

        JTextField sessionTextField = new JTextField();
        textFieldPanel.add(sessionTextField);

        // Add spacing above the text field
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 0, 10));

        logsPanel.add(textFieldPanel); // Place the text field at the top

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Use FlowLayout for buttons

        JButton downloadButton = new JButton("Download");
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sessionId = sessionTextField.getText();
                if (isValidSessionId(sessionId)) {
                    downloadLogsForSession(sessionId);
                } else {
                    JOptionPane.showMessageDialog(logsPanel, "Invalid Session ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(downloadButton);

        JButton openLogButton = new JButton("Open Log");
        openLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sessionId = sessionTextField.getText();
                if (isValidSessionId(sessionId)) {
                    openLogForSession(sessionId);
                } else {
                    JOptionPane.showMessageDialog(logsPanel, "Invalid Session ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(openLogButton);

        logsPanel.add(buttonPanel); // Place buttons below the text field

        // Create a panel for the Back button at the bottom
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-align the Back button

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOptionsPanel();
            }
        });
        backButtonPanel.add(backButton);

        logsPanel.add(backButtonPanel); // Place the Back button at the bottom

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.add(logsPanel, BorderLayout.CENTER);
        frame.revalidate();
    }

    // Check if the session ID is valid (you can customize this validation logic)
    private static boolean isValidSessionId(String sessionId) {
        return sessionId.matches("\\d{10}"); // Assuming a valid session ID is a 10-digit number
    }


    private static void downloadLogsForSession(String sessionId) {
        System.out.println("Opening log for session ID: " + sessionId);

        try {
            // Construct the URL by appending the session ID
            String baseUrl = "http://127.0.0.1:3000/api/session/";
            String url = baseUrl + sessionId + "/log?format=download";

            // Create a URI object and open it in the default web browser
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void openLogForSession(String sessionId) {
        System.out.println("Opening log for session ID: " + sessionId);

        try {
            // Construct the log file path based on the session ID
            String downloadsFolder = System.getProperty("user.home") + File.separator + "Downloads";
            String logFilePath = downloadsFolder + File.separator + sessionId + ".log";

            File logFile = new File(logFilePath);

            if (logFile.exists()) {
                Desktop.getDesktop().open(logFile);

            } else {
                System.err.println("Log file not found: " + logFile.getAbsolutePath());
                // Handle the case where the log file does not exist.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showOptionsPanel() {
        logsPanel.setVisible(false);
        optionsPanel.setVisible(true);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.remove(logsPanel);
        frame.revalidate();
    }
}
