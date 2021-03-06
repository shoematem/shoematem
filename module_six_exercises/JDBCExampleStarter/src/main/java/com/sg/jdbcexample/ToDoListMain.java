/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.jdbcexample;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author kylerudy
 */

public class ToDoListMain
{
    private static Scanner sc;

    public static void main(String[] args)
    {
        sc = new Scanner(System.in);
        private static DataSource ds;

        try
        {
            ds = getDataSource();
        } catch(SQLException ex)
        {
            System.out.println("Error connecting to database");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        
        do
        {
            System.out.println("To-Do List");
            System.out.println("1. Display List");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");

            System.out.println("Enter an option:");
            String option = sc.nextLine();
            
            try
            {
                switch (option)
                {
                    case "1":
                        displayList();
                        break;
                    case "2":
                        addItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        removeItem();
                        break;
                    case "5":
                        System.out.println("Exiting");
                        System.exit(0);
                }
            } catch (SQLException ex)
            {
                System.out.println("Error communicating with database");
                System.out.println(ex.getMessage());
                System.exit(0);
            }
        } while (true);
    }

    private static void displayList() throws SQLException
    {
        try(Connection conn = ds.getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM toDo");
            
            while(rs.next())
            {
                System.out.printf("%s: %s -- %s -- %s\n");
                rs.getString("ID");
                rs.getString("toDo");
                rs.getString("note");
                rs.getBoolean("finished");
            }
            
            System.out.println("");
        }
    }

    private static void addItem() throws SQLException
    {
        System.out.println("Add Item");

        System.out.println("What is the task?");
        String task = sc.nextLine();

        System.out.println("Any additional notes?");
        String note = sc.nextLine();

        try(Connection conn = ds.getConnection())
        {
            String sql = "INSERT INTO toDo(toDo, note) VALUES(?,?)";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, task);
            pStmt.setString(2, task);
            pStmt.executeUpdate();
            
            System.out.println("Add Complete");
        }
    }

    private static void updateItem() throws SQLException
    {
        System.out.println("Update Item");
        
        System.out.println("Which item do you want to update?");
        String itemID = sc.nextLine();
        
        try(Connection conn = ds.getConnection())
        {
            String sql = "SELECT * FROM toDo WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, itemID);
            
            ResultSet rs = pStmt.executeQuery();
            rs.next();
            
            ToDo td = new ToDo();
            td.setID(rs.getInt("ID"));
            td.setToDo(rs.getString("toDo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));

            System.out.println("1. ToDo - " + td.getToDo);
            System.out.println("2. Note - " + td.getNote());
            System.out.println("3. Finished - " + td.isFinished());
            
            System.out.println("What would you like to change?");
            String choice = sc.nextLine();

            switch(choice)
            {
                case "1":
                    System.out.println("Enter new ToDo:");
                    String toDo = sc.nextLine();
                    td.setToDo(toDo);
                    break;
                case "2":
                    System.out.println("Enter new Note:");
                    String note = sc.nextLine();
                    td.setNote(note);
                    break;
                case "3":
                    System.out.println("Toggling Finished to " + !td.isFinished());
                    td.setFinished(!td.isFinished());
                    break;
                default:
                    System.out.println("No change made");
            }

            String updateSql = "UPDATE toDo SET toDo = ?, note = ?, finished = ? WHERE ID = ?";
            
            PreparedStatement updatePStmt = conn.prepareCall(updateSql);
            updatePStmt.setString(1, td.getToDo());
            updatePStmt.setString(2, td.getNote());
            updatePStmt.setBoolean(3, td.isFinished());
            updatePStmt.setInt(4, td.getID());
            updatePStmt.executeUpdate();

            System.out.println("Update Complete");
        }
    }

    private static void removeItem() throws SQLException
    {
        System.out.println("Remove Item");

        System.out.println("Which item would you like to remove?");
        String itemID = sc.nextLine();
        
        try(Connection conn = ds.getConnection())
        {
            String sql = "DELETE FROM toDo WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, itemID);
            pStmt.executeUpdate();
            
            System.out.println("Remove Complete");
        }
    }
    
    private static DataSource getDataSource() throws SQLException
    {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("toDoDB");
        ds.setUser("root");
        ds.setPassword("51913142");
        ds.setServerTimezone("America/New York");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        return ds;
    }
}