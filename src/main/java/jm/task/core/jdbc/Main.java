package jm.task.core.jdbc;

import com.sun.jdi.connect.Connector;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDao userService = new UserDaoJDBCImpl();
        userService.createUsersTable();
        userService.saveUser("Dima","Pohlebaev", (byte) 24);
        userService.saveUser("Stas","Eremin", (byte) 23);
        userService.saveUser("Egor","Strizhenov", (byte) 24);
        userService.saveUser("Roma","Sotnikov", (byte) 25);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();




        }
    }
