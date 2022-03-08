package ReviewOs.jdbc;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWithOracle {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @BeforeMethod
    public void ConnectToDB(){
        String dbUrl = "jdbc:oracle:thin:@18.233.164.111:1521:xe";
        String dbUserName = "hr";
        String dbPassWord = "hr";
        String query = "select first_name,last_name,salary from employees";

        try {
            connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @AfterMethod
    public void closeDB()  {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void connectionTest() throws SQLException {


        //resultSet.next() : get the cursor to the next row
        //resultSet.getObject(1): brings the info in that cell

        while (resultSet.next()){
            System.out.println(resultSet.getObject(1)+"|"+resultSet.getObject(2)+"|"+resultSet.getObject(3));
        }

    }
    @Test
    public void verifyExample() throws SQLException {
        //how to get steven King Salary and verify that it is 24000
        resultSet.next(); // at first query stands at the line zero

        //int actualSalary = resultSet.getInt(3);
        //int expectedSalary = 24000;

        String actualSalary = resultSet.getString(3);
        String expectedSalary = "24000";

        System.out.println("expectedSalary = " + expectedSalary);
        System.out.println("expectedSalary = " + expectedSalary);

        Assert.assertEquals(actualSalary,expectedSalary);
    }

    @Test
    public void listOfMapExample(){
        Map<String,Object> rowOneData = new HashMap<>(); //insertion order is not kept
        rowOneData.put("firstName","Steven");
        rowOneData.put("lastName","King");
        rowOneData.put("salary","24000");
        System.out.println("rowOneData = " + rowOneData);

        Map<String,Object> rowTwoData = new HashMap<>(); //insertion order is not kept
        rowTwoData.put("firstName","Neena");
        rowTwoData.put("lastName","Kochar");
        rowTwoData.put("salary","17000");
        System.out.println("rowOneData = " + rowTwoData);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(rowOneData);
        list.add(rowTwoData);

        //get NMeena's salary
        System.out.println("get Neena's salary= "+list.get(1).get("salary"));

    }

    @Test
    public void MetaDataExample () throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String columnName = rsmd.getColumnName(1);
        System.out.println("columnCount = " + columnCount);
        System.out.println("columnName = " + columnName);
    }
    @Test
    public void DynamicMapMethod() throws SQLException {

        List<Map<String,Object>> queryResultList = new ArrayList<>();
        // row: resultset.next
        // column count : rsmd.getColumnCount
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        // KEY : columnName : rsmd.getColumnName

        // VALUE:   how to read data from the cell : resultset.getObject

        while (resultSet.next()){
            Map<String,Object> rowMap = new HashMap<>();
            for (int i = 1; i <= columnCount ; i++) {
                rowMap.put(rsmd.getColumnName(i),resultSet.getObject(i));
            }
            queryResultList.add(rowMap);

        }
        for (Map<String,Object> eachRow: queryResultList ) {
            System.out.println("eachRow = " + eachRow);
        }

        }

    }

