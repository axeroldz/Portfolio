/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Font;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 *
 * @author David.K
 */
public class test2 {
        static String myUrl= "jdbc:mysql://localhost:3307/product";
  static String user = "root";
  static String pass = "Nagasaki12";
  static Connection conn;
  static PreparedStatement preStatement;
    public static void main(String[] args) throws SQLException, ParseException {
        add();
        
    }
    static String add() throws SQLException, ParseException{
        conn = DriverManager.getConnection(myUrl, user,pass);
    String message = "";
        String query = "SELECT * FROM info"; 

        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        System.out.println("Date\t|\tCode\t|\tName\t|\tQuantitiy\t|\tPrice");
        while (rs.next()) {
            Date date = rs.getDate("date");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            String strDate = dateFormat.format(date);  
            int code = rs.getInt("code");
            
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            int price = rs.getInt("price");

            // print the results
            message += strDate+"\t"+String.valueOf(code)+"\t\t"+name+"\t\t   "+String.valueOf(quantity)+"\t\t\t"+String.valueOf(price)+"\n";
        }
        st.close();
        return message;
   }
     static void create() throws SQLException, ParseException{
        JFrame frame = new JFrame("JTextArea Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JPanel panel = new JPanel();
        JTextArea textArea;
        textArea = new JTextArea(
                "hi",
                6, 
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);

        panel.add(textArea);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

  