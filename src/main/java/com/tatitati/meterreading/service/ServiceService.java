package com.tatitati.meterreading.service;

import com.tatitati.meterreading.model.User;
import com.tatitati.meterreading.model.mService;
import com.tatitati.meterreading.model.mUserService;
import com.tatitati.meterreading.repository.DatabaseUtil;
import com.tatitati.meterreading.repository.Repository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ServiceService implements Repository<mService> {

    private Session session = DatabaseUtil.getSession();
    @Override
    public mService save(mService service) {
        return null;
    }

    @Override
    public mService update(mService service) {
        return null;
    }

    @Override
    public mService create(mService service) {
        return null;
    }

    public mService createService(Integer userId, mService service) {
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
//        em.getTransaction().begin();
//        em.merge(service);
//        mUserService userService = new mUserService();
//        userService.setUser(session.get(User.class, userId));
//        userService.setService(service);
//        user.add
//        em.merge(userService);
        User user = session.get(User.class, userId);
        service.addUser_service(user);
        session.save(mService.class.getName(), service);
        user.addUserService(session.get(mService.class, service.getId()));
        em.merge(user);
        em.getTransaction().commit();
        return session.get(mService.class, service.getId());
    }

    @Override
    public mService findById(Integer id) {
        return null;
    }

    @Override
    public void delete(mService service) {

    }

    @Override
    public List<mService> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
