package Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class DltLogsAndReports {
	private static JPanel reportsPanel;
	private static JPanel optionsPanel;
	private static Properties properties;

	public static void dltLogsAndReports(JPanel optionsPanel, Properties properties) {
		DltLogsAndReports.optionsPanel = optionsPanel;
		optionsPanel.setVisible(false);

		DltLogsAndReports.properties = properties;

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

		JButton dltActorLogs = new JButton("Dlt Actor Logs");
		JButton dltActorReports = new JButton("Dlt Actor Reports");
		JButton dltServerLogs = new JButton("Dlt Server Logs");
		JButton dltZipReports = new JButton("Dlt Zip Reports");

		dltActorLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Handle the "Dlt Actor Logs" button click

				dltActorLogs(checkBoxA.isSelected(), checkBoxB.isSelected(),
						checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
						checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
						checkBoxI.isSelected());
			}
		});


		dltActorReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dltActorReports(checkBoxA.isSelected(), checkBoxB.isSelected(),
						checkBoxC.isSelected(), checkBoxD.isSelected(), checkBoxE.isSelected(),
						checkBoxF.isSelected(), checkBoxG.isSelected(), checkBoxH.isSelected(),
						checkBoxI.isSelected());

			}
		});

		dltServerLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dltServerReports();
			}
		});

		dltZipReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// dltZipReports();

			}
		});

		buttonPanel.add(dltActorLogs);
		buttonPanel.add(dltActorReports);
		buttonPanel.add(dltServerLogs);
		buttonPanel.add(dltZipReports);

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
	//getPath("Actors")

	private static void dltActorLogs(boolean checkA, boolean checkB, boolean checkC,
	        boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {

	    // Define the base path where actor folders are located
	    String basePath = getPath("Actors"); // Replace with the actual path

	    try {
	        // Create a JDialog without decorations
	        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(reportsPanel);
	        JDialog progressDialog = new JDialog(parentFrame, Dialog.ModalityType.MODELESS);
	        progressDialog.setUndecorated(true); // Remove decorations
	        progressDialog.setLayout(new BorderLayout());

	        // Create a JLabel to show the message
	        JLabel messageLabel = new JLabel("Deleting Actor Logs...");
	        messageLabel.setHorizontalAlignment(JLabel.CENTER);
	        progressDialog.add(messageLabel, BorderLayout.NORTH);

	        JProgressBar progressBar = new JProgressBar(0, 100);
	        progressBar.setStringPainted(true);
	        progressDialog.add(progressBar, BorderLayout.CENTER);

	        // Create a Timer to simulate progress
	        Timer timer = new Timer(1000, null);
	        int totalActorsToDelete = (checkA ? 1 : 0) + (checkB ? 1 : 0) + (checkC ? 1 : 0) +
	                (checkD ? 1 : 0) + (checkE ? 1 : 0) + (checkF ? 1 : 0) +
	                (checkG ? 1 : 0) + (checkH ? 1 : 0) + (checkI ? 1 : 0);

	        AtomicInteger actorsDeleted = new AtomicInteger(0);

	        // Start the timer to simulate progress
	        timer.addActionListener(new ActionListener() {
	            int progress = 0;

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (progress < 100) {
	                    progress += (100 / totalActorsToDelete);
	                    progressBar.setValue(progress);
	                } else {
	                    actorsDeleted.getAndIncrement();
	                    if (actorsDeleted.get() >= totalActorsToDelete) {
	                        // All actors have been deleted, stop the timer
	                        ((Timer) e.getSource()).stop();
	                        progressDialog.dispose();
	                    }
	                }
	            }
	        });

	        // Start the timer
	        timer.start();

	        // Delete logs for selected actors based on checkbox state
	        if (checkA) {
	            deleteLogsForActor(basePath, "FC");
	        }
	        if (checkB) {
	            deleteLogsForActor(basePath, "SD1");
	        }
	        if (checkC) {
	            deleteLogsForActor(basePath, "FCX");
	        }
	        if (checkD) {
	            deleteLogsForActor(basePath, "DRINKS");
	        }
	        if (checkE) {
	            deleteLogsForActor(basePath, "XML");
	        }
	        if (checkF) {
	            deleteLogsForActor(basePath, "OT");
	        }
	        if (checkG) {
	            deleteLogsForActor(basePath, "CS");
	        }
	        if (checkH) {
	            deleteLogsForActor(basePath, "DTR");
	        }
	        if (checkI) {
	            deleteLogsForActor(basePath, "DTX");
	        }

	        progressDialog.pack();
	        progressDialog.setLocationRelativeTo(parentFrame); // Center the dialog within the parent frame
	        progressDialog.setVisible(true);

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exceptions appropriately.
	    }
	}

	
	// Helper method to delete logs for a specific actor
	private static void deleteLogsForActor(String basePath, String actorName) {
		File actorFolder = new File(basePath, actorName);

		// Check if the actor folder exists
		if (actorFolder.exists() && actorFolder.isDirectory()) {
			// Assuming "out" is the folder where logs need to be deleted
			File outFolder = new File(actorFolder, "out");

			// Check if the "out" folder exists
			if (outFolder.exists() && outFolder.isDirectory()) {
				// Delete all files and subdirectories in the "out" folder
				File[] files = outFolder.listFiles();
				if (files != null) {
					for (File file : files) {
						deleteFileOrDirectory(file);
					}
				}
			}
		}
	}

	// Helper method to delete a file or directory recursively
	private static void deleteFileOrDirectory(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File subfile : files) {
					deleteFileOrDirectory(subfile);
				}
			}
		}
		file.delete();
	}



	private static void dltActorReports(boolean checkA, boolean checkB, boolean checkC,
			boolean checkD, boolean checkE, boolean checkF, boolean checkG, boolean checkH, boolean checkI) {
		// Close Actors for the selected checkboxes

		try {
			if (checkA) {

			}
			if (checkB) {

			}
			if (checkC) {

			}
			if (checkD) {

			}
			if (checkE) {

			}
			if (checkF) {

			}
			if (checkG) {

			}
			if (checkH) {

			}
			if (checkI) {

			}

		} catch (Exception e) {
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
