package ReviewOs.jdbc;

import org.testng.annotations.Test;

import java.sql.*;

public class TestWithOracle {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Test
    public void connectionTest() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@18.233.164.111:1521:xe";
        String dbUserName = "hr";
        String dbPassWord = "hr";
        String query = "select first_name,last_name,salary from employees";

        connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        //resultSet.next() : get the cursor to the next row
        //resultSet.getObject(1): brings the info in that cell

        while (resultSet.next()){
            System.out.println(resultSet.getObject(1)+"|"+resultSet.getObject(2)+"|"+resultSet.getObject(3));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
