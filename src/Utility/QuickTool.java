package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QuickTool {
	private static Properties properties;

	public static void main(String[] args) {
		properties = new Properties();
		loadConfiguration();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void loadConfiguration() {
		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\openTest\\QuickTool\\config.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Return the properties object so that it can be passed to other classes
	public static Properties getProperties() {
		return properties;
	}

	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Quick Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(7, 1, 0, 10));
        
        
        JButton LaptopLoadButton = new JButton("Laptop_Load");
        LaptopLoadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LaptopLoad.laptopLoad(optionsPanel, QuickTool.getProperties()); // Call the method from the StartLaptopLoad class
            }
        });
        optionsPanel.add(LaptopLoadButton);
        
        JButton actorsServerButton = new JButton("Actors and Server");
        actorsServerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ActorsServerMongoDB.actorsServerMongoDB(optionsPanel, QuickTool.getProperties());
        	}
        });
        optionsPanel.add(actorsServerButton);
        
        JButton openReportsButton = new JButton("Open Reports");
        openReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	OpenReports.openReports(optionsPanel,QuickTool.getProperties());
            }
        });
        optionsPanel.add(openReportsButton);
        
            
        JButton downloadLogFileButton = new JButton("Download Log File");
        downloadLogFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DownloadLogFile.downloadLogs(optionsPanel,QuickTool.getProperties());
            }
        });
        optionsPanel.add(downloadLogFileButton);
   
        JButton deleteLogsButton = new JButton("Delete Logs and Reports");
        deleteLogsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DltLogsAndReports.dltLogsAndReports(optionsPanel,QuickTool.getProperties());
        	}

        });
        optionsPanel.add(deleteLogsButton);


        JButton deleteReportsButton = new JButton("Option 6");
        deleteReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteReports();
            }
        });
        optionsPanel.add(deleteReportsButton);

        JButton option7Button = new JButton("Option 7");
        option7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	option7();
            }
        });
        optionsPanel.add(option7Button);

        frame.add(optionsPanel, BorderLayout.CENTER);
        

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 

    private static void deleteReports() {
        // TODO: Implement the action for "Delete Reports" option
        System.out.println("Option 6");
    }

    private static void option7() {
        // TODO: Implement the action for Option 7
        System.out.println("Option 7 selected.");
    }
}
