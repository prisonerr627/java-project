package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Entity.*;
import File.*;

public class Librarymanagementsystem extends JFrame implements ActionListener {

    Font font15 = new Font("Consolas", Font.BOLD, 15);

    JLabel titleLabel, idLabel, nameLabel, descriptionLabel, searchLabel, passLabel;
    JTextField idTf, nameTf, descriptionTf, searchTf;
    JPasswordField passTf;
    JTextArea screen;

    // JTextArea display;
    JButton addBtn, updateBtn, deleteBtn, saveBtn, clearBtn, searchBtn, loadBtn;

    Book[] books = new Book[100];

    public Librarymanagementsystem() {
        super("Student Manager");
        this.setSize(800, 600);
        this.setLocation(200, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250));
        this.add(panel);

        FileIo.loadFromFile(books);

        titleLabel = createLabel(panel, 10, 0, 300, 30, "Book Management System");

        idLabel = createLabel(panel, 10, 50, 100, 30, "Book ID");
        idTf = createTextField(panel, 120, 50, 150, 30, "");

        passLabel = createLabel(panel, 300, 50, 100, 30, "Password:");
        
        passTf = new JPasswordField();
        passTf.setBounds(400, 50, 150, 30);
        passTf.setFont(font15);
        panel.add(passTf);

        nameLabel = createLabel(panel, 10, 90, 100, 30, "Name");
        nameTf = createTextField(panel, 120, 90, 150, 30, "");

        descriptionLabel = createLabel(panel, 10, 130, 100, 30, "Description");
        descriptionTf = createTextField(panel, 120, 130, 150, 30, "");

        addBtn = createButton(panel, 10, 180, 120, 30, "Add", new Color(70, 130, 180));
        updateBtn = createButton(panel, 140, 180, 120, 30, "Update", new Color(34, 139, 34));
        deleteBtn = createButton(panel, 270, 180, 120, 30, "Delete", new Color(178, 34, 34));

        loadBtn = createButton(panel, 10, 220, 120, 30, "Load", new Color(72, 61, 139));
        saveBtn = createButton(panel, 140, 220, 120, 30, "Save", new Color(123, 104, 238));
        clearBtn = createButton(panel, 270, 220, 120, 30, "Clear", new Color(105, 105, 105));

        searchTf = createTextField(panel, 10, 270, 150, 30, "");
        searchBtn = createButton(panel, 170, 270, 120, 30, "Search", new Color(255, 140, 0));

        screen = new JTextArea();
        screen.setFont(font15);
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(10, 320, 760, 220);
        panel.add(scrollPane);

        updateScreen();

        this.setVisible(true);
    }

    JLabel createLabel(JPanel panel, int x, int y, int w, int h, String text) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font15);
        panel.add(label);
        return label;
    }

    JTextField createTextField(JPanel panel, int x, int y, int w, int h, String text) {
        JTextField tf = new JTextField(text);
        tf.setBounds(x, y, w, h);
        tf.setFont(font15);
        panel.add(tf);
        return tf;
    }

    JButton createButton(JPanel panel, int x, int y, int w, int h, String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(font15);
        btn.setBackground(bgColor);
        btn.setForeground(Color.white);
        btn.addActionListener(this);
        panel.add(btn);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        String id = idTf.getText();
        String name = nameTf.getText();
        String description = descriptionTf.getText(); 
        if (e.getSource() == addBtn) {
            if (!id.isEmpty() && !idExists(id)) {
                int index = getEmptyIndex();
                if (index != -1) {
                    books[index] = new Book(id, name, description);
                    updateScreen();
                } else {
                    JOptionPane.showMessageDialog(this, "No more space to add new books!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "ID is empty or already exists!");
            }
        } else if (e.getSource() == updateBtn) {
            
            String password = new String(passTf.getPassword());

            if (password.equals("admin")) {
                int index = getIndexById(id);
                if (index != -1) {
                    books[index].setName(name);
                    books[index].setDescription(description);
                    updateScreen();
                    passTf.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Book with ID " + id + " not found!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Access Denied: Wrong Password!");
            }

        } else if (e.getSource() == deleteBtn) {
            
            String password = new String(passTf.getPassword());

            if (password.equals("admin")) {
                int index = getIndexById(id);
                if (index != -1) {
                    books[index] = null;
                    updateScreen();
                    passTf.setText(""); 
                    JOptionPane.showMessageDialog(this, "Deleted Successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Book with ID " + id + " not found!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Access Denied: Wrong Password!");
            }

        } else if (e.getSource() == saveBtn) {
            FileIo.saveToFile(books);
            JOptionPane.showMessageDialog(this, "Books saved to file!");
        } else if (e.getSource() == loadBtn) { 
            FileIo.loadFromFile(books);
            updateScreen();
            JOptionPane.showMessageDialog(this, "Data loaded!");
        } else if (e.getSource() == clearBtn) {
            idTf.setText("");
            nameTf.setText("");
            descriptionTf.setText("");
            searchTf.setText(""); 
            passTf.setText(""); 
        } else if (e.getSource() == searchBtn) {
            String searchId = searchTf.getText(); 
            int index = getIndexById(searchId);
            if (index != -1) {
                idTf.setText(books[index].getId());
                nameTf.setText(books[index].getName());
                descriptionTf.setText(books[index].getDescription());
            } else {
                JOptionPane.showMessageDialog(this, "Book with ID " + searchId + " not found!");
            }
        }
    }

    // Helper Functions
    int getIndexById(String id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
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
        String all = "";
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) all += books[i].getInfo() + "\n";
        }
        screen.setText(all);
    }

    boolean idExists(String id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}