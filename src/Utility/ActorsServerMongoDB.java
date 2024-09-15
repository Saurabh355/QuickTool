package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ActorsServerMongoDB {
    private static JPanel reportsPanel;
    private static JPanel optionsPanel;
    private static Properties properties;

    public static void actorsServerMongoDB(JPanel optionsPanel, Properties properties) {
        ActorsServerMongoDB.optionsPanel = optionsPanel;
        optionsPanel.setVisible(false);

        ActorsServerMongoDB.properties = properties;

        reportsPanel = new JPanel();
        reportsPanel.setLayout(new BorderLayout());

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(4, 3));

        JCheckBox checkBoxA = new JCheckBox("FC");
        JCheckBox checkBoxB = new JCheckBox("SD1");
        JCheckBox checkBoxC = new JCheckBox("FCX");
        JCheckBox checkBoxD = new JCheckBox("DRINKS");
        JCheckBox checkBoxE = new JCheckBox("XML");
        JCheckBox checkBoxF = new JCheckBox("OT");
        JCheckBox checkBoxG = new JCheckBox("CS");
        JCheckBox checkBoxH = new JCheckBox("DTR");
        JCheckBox checkBoxI = new JCheckBox("DTX");
        JCheckBox selectAllCheckBox = new JCheckBox("All");

        selectAllCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean selected = selectAllCheckBox.isSelected();
                checkBoxA.setSelected(selected);
                checkBoxB.setSelected(selected);
                checkBoxC.setSelected(selected);
                checkBoxD.setSelected(selected);
                checkBoxE.setSelected(selected);
                checkBoxF.setSelected(selected);
                checkBoxG.setSelected(selected);
                checkBoxH.setSelected(selected);
                checkBoxI.setSelected(selected);
            }
        });

        checkboxPanel.add(checkBoxA);
        checkboxPanel.add(checkBoxB);
        checkboxPanel.add(checkBoxC);
        checkboxPanel.add(checkBoxD);
        checkboxPanel.add(checkBoxE);
        checkboxPanel.add(checkBoxF);
        checkboxPanel.add(checkBoxG);
        checkboxPanel.add(checkBoxH);
        checkboxPanel.add(checkBoxI);
        checkboxPanel.add(selectAllCheckBox);

        // Create buttons for starting and closing Actors
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton startActorsButton = new JButton("Start Actors");
        JButton closeActorsButton = new JButton("Close Actors");
        JButton startServerButton = new JButton("Start Server");
        JButton startMongoDBButton = new JButton("Start MongoDB");

        startActorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the "Start Actors" button click
               
                startActors(checkBoxA.isSelected(), checkBoxB.isSelected(),
                        checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
                        checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
                        checkBoxI.isSelected());
            }
        });


        closeActorsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                closeActors(checkBoxA.isSelected(), checkBoxB.isSelected(),
                    checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
                    checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
                    checkBoxI.isSelected());
                
                closeActors(checkBoxA.isSelected(), checkBoxB.isSelected(),
                        checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
                        checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
                        checkBoxI.isSelected());
                
            }
        });

        startServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openFile(getPath("Server") + "run.bat");
            }
        });

        startMongoDBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	startMongoDB();
                       
            }
        });

        buttonPanel.add(startActorsButton);
        buttonPanel.add(closeActorsButton);
        buttonPanel.add(startServerButton);
        buttonPanel.add(startMongoDBButton);

        // Create a "Back" button
        JPanel backButtonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOptionsPanel();
            }
        });
        backButtonPanel.add(backButton);

        // Add components to reportsPanel
        reportsPanel.add(checkboxPanel, BorderLayout.NORTH);
        reportsPanel.add(buttonPanel, BorderLayout.CENTER);
        reportsPanel.add(backButtonPanel, BorderLayout.SOUTH);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.add(reportsPanel, BorderLayout.CENTER);
        frame.revalidate();
    }
    
    
	private static String getPath(String key) {
		return properties.getProperty(key, "");
	}

    private static void startActors(boolean checkA, boolean checkB, boolean checkC,
            boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {
 

        // Check the state of each checkbox and open run.bat if checked
        if (checkA) {
            openFile(getPath("FC") + "run.bat");
        }
        if (checkB) {
            openFile(getPath("SD1") + "run.bat");
        }
        if (checkC) {
            openFile(getPath("FCX") + "run.bat");
        }
        if (checkD) {
            openFile(getPath("DRINKS") + "run.bat");
        }
        if (checkE) {
            openFile(getPath("XML") + "run.bat");
        }
        if (checkF) {
            openFile(getPath("OT") + "run.bat");
        }
        if (checkG) {
            openFile(getPath("CS") + "run.bat");
        }
        if (checkH) {
            openFile(getPath("DTR") + "run.bat");
        }
        if (checkI) {
            openFile(getPath("DTX") + "run.bat");
        }
    }

    private static void openFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", filePath);
                pb.directory(file.getParentFile()); // Set the working directory
                pb.inheritIO(); // Redirects the input and output streams to the current process
                pb.start();
            } else {
                System.err.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void closeActors(boolean checkA, boolean checkB, boolean checkC,
    		boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {
    	// Close Actors for the selected checkboxes

    	try {
    		if (checkA) {
    			closeActorsForCheckbox("FC");
    			Thread.sleep(1000);
    		}
    		if (checkB) {
    			closeActorsForCheckbox("SD1");
    			Thread.sleep(1000);
    		}
    		if (checkC) {
    			closeActorsForCheckbox("FCX");
    			Thread.sleep(1000); 
    		}
    		if (checkD) {
    			closeActorsForCheckbox("DRINKS");
    			Thread.sleep(1000);    
    		}
    		if (checkE) {
    			closeActorsForCheckbox("XML");
    			Thread.sleep(1000);
    		}
    		if (checkF) {
    			closeActorsForCheckbox("OT");
    			Thread.sleep(1000);
    		}
    		if (checkG) {
    			closeActorsForCheckbox("CS");
    			Thread.sleep(1000);
    		}
    		if (checkH) {
    			closeActorsForCheckbox("DTR");
    			Thread.sleep(1000);
    		}
    		if (checkI) {
    			closeActorsForCheckbox("DTX");
    			Thread.sleep(1000);
    		}

    	} catch (IOException | InterruptedException e) {
    		e.printStackTrace();
    	}
    	// Repeat this for other checkboxes
    }
       
    private static void closeActorsForCheckbox(String windowTitle) throws IOException, InterruptedException {
        // Use the taskkill command to kill processes by their window title
        String cmd = "taskkill /F /FI \"WINDOWTITLE eq " + windowTitle + "\"";
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", cmd);
        pb.inheritIO(); // Redirects the input and output streams to the current process
        Process process = pb.start();
        process.waitFor(); // Wait for the process to finish (you can add error handling here)
    }

    private static void startMongoDB() {
        try {
        	ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/k", "mongod --bind_ip_all");
            pb.inheritIO(); // Redirects the input and output streams to the current process
            pb.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void showOptionsPanel() {
        reportsPanel.setVisible(false);
        optionsPanel.setVisible(true);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
        frame.remove(reportsPanel);
        frame.revalidate();
    }
}
