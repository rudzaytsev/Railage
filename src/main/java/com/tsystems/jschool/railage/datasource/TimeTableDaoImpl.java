package com.tsystems.jschool.railage.datasource;

import com.tsystems.jschool.railage.domain.TimeTableLine;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 01.07.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class TimeTableDaoImpl extends JpaDao<TimeTableLine> implements TimeTableDao {

    @Override
    public TimeTableLine findById(Integer id) {

        String queryStr = "SELECT t FROM TimeTableLine t WHERE t.id = ?1";
        TypedQuery<TimeTableLine> query = entityManager.createQuery(
                queryStr, TimeTableLine.class);
        query.setParameter(1,id);
        TimeTableLine requiredTimeTableLine = null;
        try {
            requiredTimeTableLine = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredTimeTableLine;
    }

    @Override
    public List<TimeTableLine> findAll() {
        String queryStr = "SELECT t FROM TimeTableLine t";
        TypedQuery<TimeTableLine> query = entityManager.createQuery(
                queryStr, TimeTableLine.class);
        return query.getResultList();
    }

    @Override
    public  List<TimeTableLine> findByStationId(Integer stationId){
        String queryStr = "SELECT t FROM TimeTableLine t WHERE t.station.id = ?1" +
                          " ORDER BY t.timeInfo.departureTime";
        TypedQuery<TimeTableLine> query = entityManager.createQuery(
                queryStr, TimeTableLine.class);
        query.setParameter(1,stationId);
        return query.getResultList();
    }
}
