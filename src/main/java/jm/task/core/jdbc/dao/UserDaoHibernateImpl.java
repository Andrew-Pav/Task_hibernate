package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS (ID INT PRIMARY KEY " +
                "AUTO_INCREMENT, NAME VARCHAR(40), LAST_NAME VARCHAR(40), AGE TINYINT(3))").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().getCurrentSession();
        User user = new User(name, lastName, age);

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        User userToDelete = session.get(User.class, id);
        session.delete(userToDelete);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
