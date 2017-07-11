package com.codnect.dao.impl;

import com.codnect.dao.PersonDao;
import com.codnect.model.Person;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burak Köken on 11.7.2017.
 */
public class PersonDaoImpl implements PersonDao{

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Person person) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            String sql = "INSERT INTO springdb.person (name, age) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            String sql = "DELETE FROM springdb.person WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            String sql = "SELECT * FROM springdb.person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Person person = null;
            while (resultSet.next()){
                person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                personList.add(person);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }

}
