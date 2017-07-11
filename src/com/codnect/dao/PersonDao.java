package com.codnect.dao;

import com.codnect.model.Person;

import java.util.List;

/**
 * Created by Burak Köken on 11.7.2017.
 */
public interface PersonDao {

    void add(Person person);

    void delete(long id);

    List<Person> getPersonList();

}
