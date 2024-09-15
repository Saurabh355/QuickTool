package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.List;

public class OpenReports {
	private static JPanel reportsPanel;
	private static JPanel optionsPanel;
	private static Properties properties;

	public static void openReports(JPanel optionsPanel, Properties properties) {
		OpenReports.optionsPanel = optionsPanel;
		optionsPanel.setVisible(false);
		
		OpenReports.properties = properties;

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

		reportsPanel.add(checkboxPanel, BorderLayout.NORTH);

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new GridLayout(4, 1));

		JLabel label = new JLabel("Enter Session ID");
		textFieldPanel.add(label);

		JTextField sessionTextField = new JTextField();
		textFieldPanel.add(sessionTextField);

		reportsPanel.add(textFieldPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sessionId = sessionTextField.getText();
				reportWithSessionId(sessionId, checkBoxA.isSelected(), checkBoxB.isSelected(),
						checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
						checkBoxF.isSelected(), checkBoxG.isSelected(),checkBoxH.isSelected(),
						checkBoxI.isSelected());
			}
		});
		buttonPanel.add(okButton);
		
		JButton makeZipButton = new JButton("Make Zip");
		makeZipButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        makeZipFile(sessionTextField.getText(), checkBoxA.isSelected(), checkBoxB.isSelected(),
		                checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
		                checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
		                checkBoxI.isSelected());
		    }
		});
		buttonPanel.add(makeZipButton);

		

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOptionsPanel();
			}
		});
		buttonPanel.add(backButton);

		reportsPanel.add(buttonPanel, BorderLayout.SOUTH);

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(optionsPanel);
		frame.add(reportsPanel, BorderLayout.CENTER);
		frame.revalidate();
	}
	private static String getPath(String key) {
		return properties.getProperty(key, "");
	}

	private static void reportWithSessionId(String sessionId, boolean checkA, boolean checkB, boolean checkC,
			boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {
		System.out.println("Opening reports for session ID: " + sessionId);

		try {
			
			if (checkA) {
				
				openFile(getPath("FC") + "/" + "shared-location" + "/" + "ExecutionResults" + "/"+ sessionId+"/" + sessionId+ "_FC" + ".html");
			}

			if (checkB) {
				openFile(getPath("SD1") + "/" + "ExecutionResults" + "/"+ sessionId+"/" + sessionId+ "_SD1" + ".html");
			}

			if (checkC) {
				openFile(getPath("FCX") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_FCX" + ".html");
			}

			if (checkD) {
				openFile(getPath("DRINKS") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DRINKS" + ".html");
			}

			if (checkE) {
				openFile(getPath("XML") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_XML" + ".html");
			}

			if (checkF) {
				openFile(getPath("OT") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_OT" + ".html");
			}

			if (checkG) {
				openFile(getPath("CS") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_CS" + ".html");
			}
			if (checkH) {
				openFile(getPath("DTR") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DTR" + ".html");
			}
			if (checkI) {
				openFile(getPath("DTX") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DTX" + ".html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void makeZipFile(String sessionId, boolean checkA, boolean checkB, boolean checkC,
	        boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {
	    // Define the output directory path
	    String outputPath = "C:\\openTest\\QuickTool\\sessionReports"; 
	    
	    // Create a zip file with a unique name
	    String zipFileName = generateUniqueZipFileName(outputPath, sessionId + "_reports.zip");

	    try (FileOutputStream fos = new FileOutputStream(zipFileName);
	         ZipOutputStream zos = new ZipOutputStream(fos)) {

	        if (checkA) {
	            addToZip(zos, sessionId + "_FC.html", getPath("FC") + "/" + "shared-location" + "/" + "ExecutionResults" + "/"+ sessionId+"/" + sessionId+ "_FC" + ".html");
	        }

	        if (checkB) {
	            addToZip(zos, sessionId + "_SD1.html", getPath("SD1") + "/" + "ExecutionResults" + "/"+ sessionId+"/" + sessionId+ "_SD1" + ".html");
	        }
	        if (checkC) {
	            addToZip(zos, sessionId + "_FCX.html", getPath("FCX") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_FCX" + ".html");
	        }
	        if (checkD) {
	            addToZip(zos, sessionId + "_DRINKS.html", getPath("DRINKS") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DRINKS" + ".html");
	        }
	        if (checkE) {
	            addToZip(zos, sessionId + "_XML.html", getPath("XML") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_XML" + ".html");
	        }
	        if (checkF) {
	            addToZip(zos, sessionId + "_OT.html", getPath("OT") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_OT" + ".html");
	        }
	        if (checkG) {
	            addToZip(zos, sessionId + "_CS.html", getPath("CS") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_CS" + ".html");
	        }
	        if (checkH) {
	            addToZip(zos, sessionId + "_DTR.html", getPath("DTR") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DTR" + ".html");
	        }
	        if (checkI) {
	            addToZip(zos, sessionId + "_DTX.html", getPath("DTX") + "/" + "ExecutionResults" +"/"+ sessionId+"/" + sessionId+ "_DTX" + ".html");
	        }

	        // Close the zip file
	        zos.close();
	        
	     // Show a message without requiring user interaction
            JOptionPane pane = new JOptionPane("Session reports Zip created", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = pane.createDialog("Success");
            dialog.setModal(false); // Make it non-modal
            dialog.setVisible(true);

            // Schedule the dialog to close automatically after 1000 milliseconds (1 second)
            Timer timer = new Timer(2000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    dialog.dispose();
                }
            });
            timer.setRepeats(false); // Run only once
            timer.start();
            
	        System.out.println("Zip file created: " + zipFileName);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	// Helper method to generate a unique zip file name
	private static String generateUniqueZipFileName(String outputPath, String zipFileName) {
	    int count = 1;
	    String originalFileName = zipFileName;
	    while (new File(outputPath + File.separator + zipFileName).exists()) {
	        zipFileName = originalFileName.replace(".zip", "_" + count + ".zip");
	        count++;
	    }
	    return outputPath + File.separator + zipFileName;
	}

	private static void addToZip(ZipOutputStream zos, String entryName, String filePath) throws IOException {
	    File file = new File(filePath);
	    if (file.exists()) {
	        ZipEntry zipEntry = new ZipEntry(entryName);
	        zos.putNextEntry(zipEntry);

	        FileInputStream fis = new FileInputStream(file);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = fis.read(buffer)) > 0) {
	            zos.write(buffer, 0, length);
	        }
	        zos.closeEntry();
	        fis.close();
	    } else {
	        System.err.println("File not found: " + filePath);
	    }
	}
    
	private static void openFile(String filePath) throws IOException {
	    File file = new File(filePath);
	    if (file.exists()) {
	        try {
	        	
	        	// Create a list to hold the URIs of all the reports to open
	            List<URI> uris = new ArrayList<>();

	            // Add the URI for the report file to the list
	            uris.add(file.toURI());

	            // Open each URI in a new tab of the default web browser
	            for (URI uri : uris) {
	                Desktop.getDesktop().browse(uri);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.err.println("File not found: " + filePath);
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
