import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class UserForms extends JFrame {
private JPanel contentPane;

public static Student s;
public static Technician t;
public static void main(String[] args) throws FileNotFoundException {
 new UserForms();
}
public UserForms() {
setTitle("Inventory Management");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JLabel lblNewLabel = new JLabel("Welcome to Inventory Management!");
lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 21));
lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel.setBounds(10, 11, 414, 56);
contentPane.add(lblNewLabel);
setVisible(true); 
JButton btnNewButton = new JButton("Student");
btnNewButton.setBackground(Color.YELLOW);
sbtn shandler = new sbtn();
btnNewButton.addActionListener(shandler);
btnNewButton.setBounds(10, 96, 203, 56);
contentPane.add(btnNewButton);
JButton btnNewButton_1 = new JButton("Technician");
btnNewButton_1.setBackground(Color.YELLOW);
tbtn thandler = new tbtn();
btnNewButton_1.addActionListener(thandler);
btnNewButton_1.setBounds(223, 96, 201, 56);
contentPane.add(btnNewButton_1);
}
private class sbtn implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
setVisible(false); 
new stdPage();
}
}
private class tbtn implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
setVisible(false); 
 new techPage();
}
} 
}

class techPage extends JFrame {
private JTextField textField_1;
private JPasswordField passwordField;
private JPanel contentPane;
public techPage() {
setTitle("Tech Log in Page");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JLabel lblNewLabel = new JLabel("Welcome!");
lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
lblNewLabel.setBounds(149, 0, 139, 57);
getContentPane().add(lblNewLabel);
JLabel lblNewLabel_2 = new JLabel("Name:");
lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_2.setBounds(10, 77, 76, 27);
getContentPane().add(lblNewLabel_2);
textField_1 = new JTextField();
textField_1.setBackground(Color.ORANGE);
textField_1.setBounds(235, 79, 127, 27);
getContentPane().add(textField_1);
textField_1.setColumns(10);
JLabel lblNewLabel_3 = new JLabel("Password:");
lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_3.setBounds(10, 147, 76, 27);
getContentPane().add(lblNewLabel_3);
passwordField = new JPasswordField();
passwordField.setBackground(Color.ORANGE);
passwordField.setBounds(235, 149, 127, 27);
getContentPane().add(passwordField);

JButton btnNewButton = new JButton("Log in");
btnNewButton.setBackground(Color.YELLOW);
sbtn shandler = new sbtn();
btnNewButton.addActionListener(shandler);
btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
btnNewButton.setBounds(10, 208, 194, 42);
getContentPane().add(btnNewButton);
JButton btnNewButton_1 = new JButton("Back");
btnNewButton_1.setBackground(Color.yellow);
bbtn bhandler = new bbtn();
btnNewButton_1.addActionListener(bhandler); 
btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
btnNewButton_1.setBounds(231, 208, 173, 42);
getContentPane().add(btnNewButton_1);
setVisible(true);
}
private class sbtn implements ActionListener {
public void actionPerformed(ActionEvent e) {
    if (textField_1.getText().equals("tech") && Arrays.equals(passwordField.getPassword(), "t2023".toCharArray())) {
        UserForms.t = new Technician(textField_1.getText(), new String(passwordField.getPassword()));
        new TechnicianGUI(UserForms.t);
        dispose();
        new UserForms();

} else {
JOptionPane.showMessageDialog(null,"name or password entered is incorrect");
}
}
}
private class bbtn implements ActionListener {
public void actionPerformed(ActionEvent e) {
//setVisible(false);
new UserForms();

}
}
}
class stdPage extends JFrame{
protected String name,id,password;
private JPanel contentPane;
private JTextField textField;
private JTextField textField_1;
public stdPage() {
setTitle("Student");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JLabel lblNewLabel = new JLabel("Welcome Student!");
lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel.setBounds(10, 11, 414, 57);
contentPane.add(lblNewLabel);
textField = new JTextField();
textField.setBackground(Color.ORANGE);
textField.setBounds(234, 79, 190, 40);
contentPane.add(textField);
textField.setColumns(10);
JLabel lblNewLabel_1 = new JLabel("Id:");
lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
lblNewLabel_1.setBounds(20, 79, 161, 40);
contentPane.add(lblNewLabel_1);
JLabel lblNewLabel_2 = new JLabel("Password: ");
lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
lblNewLabel_2.setBounds(21, 141, 160, 40);
contentPane.add(lblNewLabel_2);
textField_1 = new JTextField();
textField_1.setBackground(Color.ORANGE);
textField_1.setBounds(234, 144, 190, 40);
contentPane.add(textField_1);
textField_1.setColumns(10);
JButton btnNewButton = new JButton("Log in");
btnNewButton.setBackground(Color.yellow);
lbtn lhandler = new lbtn();
btnNewButton.addActionListener(lhandler);
btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
btnNewButton.setBounds(10, 198, 190, 52);
contentPane.add(btnNewButton);
JButton btnNewButton_2 = new JButton("Back");
bbtn bhandler = new bbtn();
btnNewButton_2.addActionListener(bhandler);
btnNewButton_2.setBounds(10, 11, 89, 23);
contentPane.add(btnNewButton_2);
JButton btnNewButton_1 = new JButton("Sign up");
btnNewButton_1.setBackground(Color.yellow);
sbtn shandler = new sbtn();
btnNewButton_1.addActionListener(shandler);
btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
btnNewButton_1.setBounds(210, 198, 214, 52);
contentPane.add(btnNewButton_1);
setVisible(true);
}
private class lbtn implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
String x = textField.getText();
String y = textField_1.getText();
Scanner Fin = null;
try {
Fin = new Scanner(new FileReader("Student" + x));
id = Fin.next();
name = Fin.next();
password = Fin.next();

if (x.equals(id)&& y.equals(password)) {
    if(UserForms.t==null){
        JOptionPane.showMessageDialog(null, "There is no technician");
        System.exit(0);
    }
setVisible(false);
UserForms.s = new Student(id,name,password,Fin.next());
new StudentGUI(UserForms.t, UserForms.s);
}else {
JOptionPane.showMessageDialog(null,"ID or password entered is incorrect");
}
} catch (FileNotFoundException e1) {
JOptionPane.showMessageDialog(null, "Error: File not found.");
} 

}
}
private class bbtn implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
setVisible(false);
new UserForms();
}
}
private class sbtn implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
setVisible(false);
 new signUp();
}
}
}
class signUp extends JFrame {
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JTextField textField_3;
private JPanel contentPane;
public signUp() {
setTitle("Sign Up Page");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);
JLabel lblNewLabel = new JLabel("Please Sign Up");
lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
lblNewLabel.setBounds(149, 0, 139, 57);
getContentPane().add(lblNewLabel);
JLabel lblNewLabel_1 = new JLabel("Id:");
lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_1.setBounds(10, 56, 76, 27);
getContentPane().add(lblNewLabel_1);
textField = new JTextField();
textField.setBackground(Color.ORANGE);
textField.setBounds(235, 58, 127, 27);
getContentPane().add(textField);
textField.setColumns(10);
JLabel lblNewLabel_2 = new JLabel("Name:");
lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_2.setBounds(10, 94, 76, 27);
getContentPane().add(lblNewLabel_2);
textField_1 = new JTextField();
textField_1.setBackground(Color.ORANGE);
textField_1.setBounds(235, 96, 127, 27);
getContentPane().add(textField_1);
textField_1.setColumns(10);
JLabel lblNewLabel_3 = new JLabel("Password:");
lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_3.setBounds(10, 132, 76, 27);
getContentPane().add(lblNewLabel_3);
textField_2 = new JTextField();
textField_2.setBackground(Color.ORANGE);
textField_2.setBounds(235, 134, 127, 27);
getContentPane().add(textField_2);
textField_2.setColumns(10);
JLabel lblNewLabel_4 = new JLabel("Phone #:");
lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
lblNewLabel_4.setBounds(10, 170, 86, 27);
getContentPane().add(lblNewLabel_4);
textField_3 = new JTextField();
textField_3.setBackground(Color.ORANGE);
textField_3.setBounds(235, 167, 127, 27);
getContentPane().add(textField_3);
textField_3.setColumns(10);
JButton btnNewButton = new JButton("Sign Up");
sbtn shandler = new sbtn();
btnNewButton.addActionListener(shandler);
btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
btnNewButton.setBounds(10, 208, 194, 42);
getContentPane().add(btnNewButton);
JButton btnNewButton_1 = new JButton("Back");
bbtn bhandler = new bbtn();
btnNewButton_1.addActionListener(bhandler); 
btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
btnNewButton_1.setBounds(231, 208, 173, 42);
getContentPane().add(btnNewButton_1);



setVisible(true);
}
private class sbtn implements ActionListener {
public void actionPerformed(ActionEvent e) {
PrintWriter output;
try {
if (!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")) {
output = new PrintWriter("Student"+textField.getText());
output.println(textField.getText());
output.println(textField_1.getText());
output.println(textField_2.getText());
output.println(textField_3.getText());

output.close();
} else {
JOptionPane.showMessageDialog(null,"Please fill all details");
}
} catch (FileNotFoundException e1) {
e1.printStackTrace();
}
setVisible(false);
 new stdPage();
}
}
private class bbtn implements ActionListener {
public void actionPerformed(ActionEvent e) {
setVisible(false);
new stdPage();
}
}
}


