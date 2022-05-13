/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author David.K
 */

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Product_Data extends javax.swing.JFrame{
    static String myUrl= "jdbc:mysql://localhost:3307/product";
    static String user = "root";
    static String pass = "Nagasaki1";
    static Connection conn;
    static PreparedStatement preStatement;
    static Statement st;
    static String strDate,name;  
    static int quantity,price,code;
    
    //static String statement = "";
    
    public static void main(String[] args) throws SQLException {
        System.out.println(ask());
        
    }
    
    static int ask(){
        int a;
        String in = JOptionPane.showInputDialog(null, "Product code?");
        if(in == null || in.equals("") )
            return -1;
        
        try{
            a = Integer.parseInt(in);
        }catch(Exception e){
            return -2;
        }
     return a;          
    }
    
    void error(){
        JOptionPane.showMessageDialog(null, "Error","Error!", 2);
    }
    boolean option(String message){
     Object[] options1 = { "Yes", "No" };

        JPanel panel = new JPanel();
        panel.add(new JLabel(message));
        
        int result = JOptionPane.showOptionDialog(null, panel, "Continue?",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        return result == JOptionPane.YES_OPTION;
    }
   static  void add(Date date, int code, String name, int quantity,int price) throws SQLException{
       
        conn = DriverManager.getConnection(myUrl, user,pass);
        preStatement = conn.prepareStatement("insert into info values(?, ?, ?, ?, ?);");
                
        preStatement.setDate(1, date);
        preStatement.setInt(2, code);
        preStatement.setString(3, name);
        preStatement.setInt(4, quantity);
        preStatement.setInt(5, price);
        
        preStatement.executeUpdate();
       
}
            
   static void showOne(int code) throws SQLException{
        conn = DriverManager.getConnection(myUrl, user,pass);
        String c = String.valueOf(code);
        c+=";";
        String query = "SELECT date,name,quantity,price FROM info WHERE code = "; 
        query += c;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Date date = rs.getDate("date");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
        strDate = dateFormat.format(date);  
        name = rs.getString("name");
        quantity = rs.getInt("quantity");
        price = rs.getInt("price");
        st.close();
   }
   
   static String showAll() throws SQLException{
       conn = DriverManager.getConnection(myUrl, user,pass);
        String query = "SELECT * FROM info"; 

        st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        String message = "Date\t|\tCode\t|\tName\t|\tQuantity\t|\tPrice\n\t|\t\t|\t\t|\t\t|\t\n"; //header
        while (rs.next()) {
            Date date = rs.getDate("date");
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
            strDate = dateFormat.format(date);  
            code = rs.getInt("code");
            
            name = rs.getString("name");
            quantity = rs.getInt("quantity");
            price = rs.getInt("price");

            // print the results
            message += strDate+"\t|\t"+String.valueOf(code)+"\t|\t"+name+"\t|\t   "
                    +String.valueOf(quantity)+"\t|\t"+String.valueOf(price)+"\n";
        }
        st.close();
        return message;
   }
   
   static void delete(int code) throws SQLException{
        conn = DriverManager.getConnection(myUrl, user,pass);
        
            preStatement = conn.prepareStatement("delete from info where code = (?);");
            preStatement.setInt(1, code);
            preStatement.executeUpdate();  
   }
   

   
   static void clear_all() throws SQLException{
       conn = DriverManager.getConnection(myUrl, user,pass);
        String query = "TRUNCATE TABLE info";
        st = conn.createStatement();
        st.executeUpdate(query);
        st.close();
   }
   static boolean check(int check_code)throws SQLException{
       conn = DriverManager.getConnection(myUrl, user,pass);
       String query = "SELECT code FROM info"; 

        // create the java statement
        st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int code = rs.getInt("code");
            if(check_code == code)
                return true; 
        }
        st.close();
        return false;
        
   }
}
