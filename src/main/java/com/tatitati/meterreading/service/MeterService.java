package com.tatitati.meterreading.service;

import com.tatitati.meterreading.model.Meter;
import com.tatitati.meterreading.model.MeterReading;
import com.tatitati.meterreading.repository.DatabaseUtil;
import com.tatitati.meterreading.repository.Repository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class MeterService implements Repository<Meter> {

    private Session session = DatabaseUtil.getSession();

    @Override
    public Meter save(Meter meter) {
        return null;
    }

    @Override
    public Meter update(Meter meter) {
        return null;
    }

    @Override
    public Meter create(Meter meter) {
        session.save(Meter.class.getName(), meter);
        return (Meter) session.get(Meter.class.getName(), meter.getId());
    }

    @Override
    public Meter findById(Integer id) {
        return session.get(Meter.class, id);
    }

    @Override
    public void delete(Meter meter) {

    }

    @Override
    public List<Meter> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }

    public Meter createMeterReading(Integer id, MeterReading meterReading){
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        Meter meter = findById(id);
        meterReading.setMeterId(meter.getId());
        meter.addMeterReading(meterReading);
        session.save(meterReading);
        em.merge(session.get(MeterReading.class, meterReading.getId()));
        session.update(meter);

        return (Meter) session.get(Meter.class.getName(), id);
    }

}
