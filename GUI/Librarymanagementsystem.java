package Librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Entity.*;
import File.*;

public class Librarymanagementsystem extends JFrame {

    Font font15 = new Font("Consolas", Font.BOLD, 15);
    JTextField txtId, txtName, txtPassword;
    JTextArea display;
    JButton addBtn, updateBtn, deleteBtn, saveBtn, clearBtn, searchBtn;

    // Student[] students = new Student[100];

    public Librarymanagementsystem() {
        setTitle("Library Management System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pink Theme Colors
        Color pinkLight = new Color(255, 182, 193);
        Color pinkDark = new Color(255, 105, 180);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(pinkLight);
        panel.setLayout(null);

        // Labels
        JLabel title = new JLabel("Library Management System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(80, 10, 350, 30);
        panel.add(title);

        JLabel lblId = new JLabel(" ID:");
        lblId.setBounds(30, 70, 100, 25);
        panel.add(lblId);

        JLabel lblName = new JLabel(" Name:");
        lblName.setBounds(30, 110, 100, 25);
        panel.add(lblName);

        JLabel lblAuthor = new JLabel("Password:");
        lblAuthor.setBounds(30, 150, 100, 25);
        panel.add(lblAuthor);

        // Text Fields
        txtId = new JTextField();
        txtId.setBounds(140, 70, 200, 25);
        panel.add(txtId);

        txtName = new JTextField();
        txtName.setBounds(140, 110, 200, 25);
        panel.add(txtName);

        txtPassword = new JTextField();
        txtPassword.setBounds(140, 150, 200, 25);
        panel.add(txtPassword);

        // Buttons
        JButton btnAdd = new JButton("Add Book");
        btnAdd.setBackground(pinkDark);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBounds(50, 200, 120, 30);
        panel.add(btnAdd);

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(pinkDark);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBounds(200, 200, 120, 30);
        panel.add(btnClear);

        // Display Area
        display = new JTextArea();
        display.setBounds(30, 250, 420, 180);
        display.setEditable(false);
        panel.add(display);

        // Button Actions
        btnAdd.addActionListener(e -> {
            display.append(
                "ID: " + txtId.getText() +
                ", Name: " + txtName.getText() +
                ", Password: " + txtPassword.getText() + "\n"
            );
        });

        btnClear.addActionListener(e -> {
            txtId.setText("");
            txtName.setText("");
            txtPassword.setText("");
        });

        add(panel);
        setVisible(true);
    }



    // Helper Functions
    int getIndexById(String id) {
        for (int i = 0; i < books.length; i++) {
                if (books[i] != null && books[i].getId().equals(id)) {
                    return i;
                }
            }
            return -1; // Not found
        }

    int getEmptyIndex() {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                return i;
            }
        }
        return -1;
    }

    void updateScreen() {
        String content = "";
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                // changed line here: formatting book info for the display area
                content += "ID: " + books[i].getId() + " | Name: " + books[i].getName() + "\n";
            }
        }
        // screen.setText(content);
    }

    boolean idExists(String id) {
        return getIndexById(id) != -1;
    }

    public static void main(String[] args) {
        new Librarymanagementsystem();
    }
}
