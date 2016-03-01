package com.TheIronYard;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by branden on 3/1/16 at 11:27.
 */
public class MainTest {

    //create the tables to work with
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test"); //define a different DB
        Main.createTables(conn);
        return conn;
    }

    //kill the tables so we have fresh data for new tests
    public  void endConnection(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE users");
        stmt.execute("DROP TABLE messages");
        conn.close();
    }



    //test to see that a user was created
    @Test
    public void testUser() throws SQLException {
        Connection conn = startConnection();

        Main.insertUser(conn,"Alice", "");  //call our insert method
        User user = Main.selectUser(conn, "Alice");  //select a user from the user we created
        endConnection(conn);  //kill DB
        assertTrue(user != null);  //check to see if the user was created
    }

    @Test
    public void testMessage() throws SQLException {
        Connection conn = startConnection();

        Main.insertUser(conn, "Alice", "");
        User user = Main.selectUser(conn, "Alice");  //grab the user

        Main.insertMessage(conn, user.id, -1, "Hello, world!");  //insert a message

        Message message = Main.selectMessage(conn, 1);  //grab the message
        endConnection(conn);

        assertTrue(message != null);  //see if it's not null
    }

    @Test
    public void testReplies() throws SQLException {
        Connection conn = startConnection();

        Main.insertUser(conn, "Alice", "");
        Main.insertUser(conn, "Bob", "");
        Main.insertUser(conn, "Charlie", "");
        User alice = Main.selectUser(conn, "Alice");
        User bob = Main.selectUser(conn, "Bob");

        Main.insertMessage(conn, alice.id, -1, "Hello World");
        Main.insertMessage(conn, bob.id, 1, "this is a reply");
        Main.insertMessage(conn, bob.id, 1, "This is another reply");

        ArrayList<Message> replies = Main.selectReplies(conn, 1);

        endConnection(conn);

        assertTrue(replies.size() == 2);

    }

}