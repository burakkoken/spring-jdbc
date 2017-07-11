package com.codnect;

import com.codnect.dao.PersonDao;
import com.codnect.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Burak Köken on 11.7.2017.
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");

        PersonDao personDao = context.getBean("personDaoBean", PersonDao.class);

        Person person = new Person();
        person.setName("Burak Köken");
        person.setAge(22);

        personDao.add(person);
    }
}
