package refdefcwk; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * important :
 * // i am not sure how this file got deleted in the main submision file
 * // we changed nothing in this file i have added this again in the demo test downloaded from canvas zip
 * // please give me marks for this file as well as i had practice challenge task and hopeful that done it correctly
 * Provide a GUI interface for the simulation
 * 
 * @author A.A.Marczyk
 * @version 20/05/25
 */


public class ManagerGUI {
    private HISS mg = new Manager("Mary", 1000);
    private JFrame myFrame = new JFrame("Project GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel();
    private JButton listBtn = new JButton("List all Staff");
    private JButton quitBtn = new JButton("Quit");
    private JButton hireBtn = new JButton("Hire Staff");
    private JButton teamBtn = new JButton("List Team");
    private JButton clearBtn = new JButton("Clear");
    private JButton showBtn = new JButton("Show State");
    private JButton getStaffBtn = new JButton("Get Staff"); // Task 6.1 - New button
    
    private JPanel eastPanel = new JPanel();

    public static void main(String[] args) {
        new ManagerGUI();
    }
    
    public ManagerGUI() {
        makeFrame();
        makeMenuBar(myFrame);
        listing.setVisible(true);
    }
    
    private void makeFrame() {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing, BorderLayout.CENTER);
        myFrame.add(eastPanel, BorderLayout.EAST);
        
        // Configure east panel with buttons
        eastPanel.setLayout(new GridLayout(7, 1)); // Changed to 7 for new button
        eastPanel.add(listBtn);
        eastPanel.add(quitBtn);
        eastPanel.add(hireBtn);
        eastPanel.add(teamBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(showBtn);
        eastPanel.add(getStaffBtn); // Task 6.1 - Add new button
        
        // Add action listeners
        listBtn.addActionListener(new ListHandler());
        quitBtn.addActionListener(new QuitHandler());
        hireBtn.addActionListener(new HireHandler());
        teamBtn.addActionListener(new TeamHandler());
        clearBtn.addActionListener(new ClearHandler());
        showBtn.addActionListener(new StateHandler());
        getStaffBtn.addActionListener(new GetStaffHandler()); // Task 6.2
        
        // Make all buttons visible
        listBtn.setVisible(true);
        teamBtn.setVisible(true);
        hireBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        showBtn.setVisible(true);
        getStaffBtn.setVisible(true);
        
        // Finalize frame
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    private void makeMenuBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // Create the File menu
        JMenu fileMenu = new JMenu("Jobs");
        menubar.add(fileMenu);

        JMenuItem view = new JMenuItem("View State of project");
        view.addActionListener(new StateHandler());
        fileMenu.add(view);

        JMenuItem listJobsItem = new JMenuItem("List all Jobs");
        listJobsItem.addActionListener(new ListJobsHandler());
        fileMenu.add(listJobsItem);

        JMenuItem doJobItem = new JMenuItem("Do a job");
        doJobItem.addActionListener(new DoJobHandler());
        fileMenu.add(doJobItem);

        JMenuItem all = new JMenuItem("See all staff");
        all.addActionListener(new ListHandler());
        fileMenu.add(all);

        JMenuItem stf = new JMenuItem("Get Staff");
        stf.addActionListener(new StfHandler());
        fileMenu.add(stf);
    }

    // ========== HANDLER CLASSES ==========
    
    // Menu item handlers
    private class StateHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            listing.setText(mg.toString());
        }
    }
    
    private class ListJobsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            listing.setText(mg.getAllJobs());
        }
    }
    
    private class DoJobHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String inputValue = JOptionPane.showInputDialog("Job no?: ");
            if (inputValue != null) {
                try {
                    int jbNo = Integer.parseInt(inputValue);
                    JOptionPane.showMessageDialog(myFrame, mg.doJob(jbNo));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(myFrame, "Invalid job number");
                }
            }
        }
    }
    
    // Button handlers
    private class ListHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            listing.setText(mg.getAllAvailableStaff());
        }
    }

    private class QuitHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int answer = JOptionPane.showConfirmDialog(myFrame,
                    "Are you sure you want to quit?", "Finish",
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
    
    private class HireHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            String inputValue = JOptionPane.showInputDialog("Staff name?: ");
            if (inputValue != null && !inputValue.trim().isEmpty()) {
                JOptionPane.showMessageDialog(myFrame, 
                    mg.hireStaff(inputValue.trim()));
            }
        }
    }
        
    private class TeamHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
            listing.setText(mg.getTeam());
        }
    }
    
    private class ClearHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {            
            listing.setText("");
        }
    }
    
    // Task 6.2 - New handler for Get Staff button
    private class GetStaffHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String staffName = JOptionPane.showInputDialog(myFrame, "Enter staff name:");
            if (staffName != null && !staffName.trim().isEmpty()) {
                listing.setText(mg.getStaff(staffName.trim()));
            }
        }
    }
    
    // Existing menu handler for Get Staff
    private class StfHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputValue = JOptionPane.showInputDialog("Staff name?: ");
            if (inputValue != null && !inputValue.trim().isEmpty()) {
                JOptionPane.showMessageDialog(myFrame, 
                    mg.getStaff(inputValue.trim()));
            }
        }
    }
}

    

   
