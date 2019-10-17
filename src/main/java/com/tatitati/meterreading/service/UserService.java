package com.tatitati.meterreading.service;

import com.tatitati.meterreading.model.Meter;
import com.tatitati.meterreading.model.User;
import com.tatitati.meterreading.repository.DatabaseUtil;
import com.tatitati.meterreading.repository.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserService implements Repository<User> {

    private Session session = DatabaseUtil.getSession();

    @Override
    public User findById(Integer id){
        return session.get(User.class, id);
    }

    public User findUserByUsername(String username){
//        from ContactEntity where firstName = :paramName
        Query<User> query = session.createQuery("from User where username = :paramName");
        query.setParameter("paramName", username);
        return query.list().get(0);
    }

    @Override
    public User save(User user) {
        session.save(User.class.getName(), user);
        return user;
    }

    @Override
    public User update(User user) {
        session.update(User.class.getName(), user);
        return user;
    }

    @Override
    public User create(User user) {
        session.save(user);
        return (User) session.get(User.class.getName(), user.getId());
    }

    @Override
    public void delete(User user) {
        session.delete(user);
    }

    @Override
    public List<User> findAll() {
        Query<User> query = session.createQuery("select * from user");
        return query.list();
    }

    @Override
    public int countAll() {
        Query<User> query = session.createQuery("select count(distinct user.id) from User user");
        return query.getFetchSize();
    }

    public void deleteById(Integer id){
        User user = findById(id);
        session.delete(user);
    }

    public User addMeter(Integer id, Meter meter){
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        User user = session.get(User.class, id);
        meter.setUserid(user.getId());
        user.addMeter(meter);
        session.save(meter);
        em.merge(session.get(Meter.class, meter.getId()));

        session.update(user);

        return (User) session.get(User.class.getName(), id);
    }

//    public List<Meter>

//    private UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public List<User> findByUsername(String username) {
//        return userRepository.findByUsernameContaining(username);
//    }
//
//    public Optional<User> findById(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    public User save(User stock) {
//        return userRepository.save(stock);
//    }
//
//    public void deleteById(Integer id) {
//        userRepository.deleteById(id);
//    }
}
