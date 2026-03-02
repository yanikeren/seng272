package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
 
    private JTextField txtProjectName, txtTeamLeader, txtStartDate;
    private JComboBox<String> comboTeamSize, comboProjectType;
    private JButton btnSave, btnClear;

    public ProjectFormPanel() {
        
        setLayout(new GridLayout(7, 2, 10, 10));

        
        add(new JLabel("Project Name:"));
        txtProjectName = new JTextField();
        add(txtProjectName);

        add(new JLabel("Team Leader:"));
        txtTeamLeader = new JTextField();
        add(txtTeamLeader);

        add(new JLabel("Team Size:"));
        String[] sizes = {"1-3", "4-6", "7-10", "10+"};
        comboTeamSize = new JComboBox<>(sizes);
        add(comboTeamSize);

        add(new JLabel("Project Type:"));
        String[] types = {"Web", "Mobile", "Desktop", "API"};
        comboProjectType = new JComboBox<>(types);
        add(comboProjectType);

        add(new JLabel("Start Date (DD/MM/YYYY):"));
        txtStartDate = new JTextField();
        add(txtStartDate);

      
        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");
        add(btnSave);
        add(btnClear);

      
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void saveData() {
      
        if (txtProjectName.getText().isEmpty() || txtTeamLeader.getText().isEmpty() || txtStartDate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String recordTime = now.format(formatter);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {
            writer.write("=== Project Entry ===\n"); // [cite: 23]
            writer.write("Project Name : " + txtProjectName.getText() + "\n"); // [cite: 24]
            writer.write("Team Leader : " + txtTeamLeader.getText() + "\n"); // [cite: 25, 26]
            writer.write("Team Size : " + comboTeamSize.getSelectedItem() + "\n"); // [cite: 27, 28]
            writer.write("Project Type : " + comboProjectType.getSelectedItem() + "\n"); // [cite: 29, 30]
            writer.write("Start Date : " + txtStartDate.getText() + "\n"); // [cite: 31, 32]
            writer.write("Record Time : " + recordTime + "\n"); // [cite: 33, 34]
            writer.write("======\n"); // [cite: 35]
            
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); // [cite: 68]
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing to file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
      
        txtProjectName.setText("");
        txtTeamLeader.setText("");
        txtStartDate.setText("");
        comboTeamSize.setSelectedIndex(0);
        comboProjectType.setSelectedIndex(0);
    }
}
