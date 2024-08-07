package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("A", "O", (byte) 1);
        user.saveUser("AA", "OO", (byte) 2);
        user.saveUser("AAA", "OOO", (byte) 3);
        user.saveUser("AAAA", "OOOO", (byte) 4);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
