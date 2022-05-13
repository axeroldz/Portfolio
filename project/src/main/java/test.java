/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author David.K
 */
public class test {
    
  static String myUrl= "jdbc:mysql://localhost:3307/product";
  static String user = "root";
  static String pass = "Nagasaki12";
  static Connection conn;
  static PreparedStatement preStatement;
    public static void main(String[] args) throws SQLException, ParseException {
     Object[] options1 = { "Yes", "No" };

        JPanel panel = new JPanel();
        panel.add(new JLabel("Do you want to edit more?"));
        
        int result = JOptionPane.showOptionDialog(null, panel, "Continue?",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        
    }
    static void add() throws SQLException, ParseException{
    conn = DriverManager.getConnection(myUrl, user,pass);
        preStatement = conn.prepareStatement("insert into info values(?, ?, ?, ?, ?);");
        
        String a = "20001214";
        Date date = new Date(0);
        String fDate =""; 
        for(int i = 0;i<a.length();i++){
            if(i == 4 || i == 6 )
                   fDate+="-";
            fDate+=a.charAt(i);
        }
        date = date.valueOf(fDate);
         
        
       try{
        preStatement.setDate(1, date);
            preStatement.setInt(2, 3);
            preStatement.setString(3, "test2");
            preStatement.setInt(4, 5);
            preStatement.setInt(5, 5);
            preStatement.executeUpdate();
       }
       catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
            System.out.println("handle duplicate index error here!");
        } 
    }
   }
}
    


