package transportsystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import transportsystem.model.Employee;

import java.util.List;

/**
 * Created by DZCKJS0 on 27.11.2019.
 */
@Repository
public class EmployeeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    public void add(Employee employee)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    public void delete(Employee employee)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
    }

    public void edit(Employee employee)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    public Employee getEmployeeById(Integer id)
    {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class,id);
    }

    public List<Employee> getAllEmployees()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee").list();
    }
}
