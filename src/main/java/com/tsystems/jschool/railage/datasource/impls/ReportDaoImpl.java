package com.tsystems.jschool.railage.datasource.impls;

import com.tsystems.jschool.railage.datasource.interfaces.ReportDao;
import com.tsystems.jschool.railage.domain.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by rudolph on 11.08.15.
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class ReportDaoImpl extends JpaDao<Report> implements ReportDao {

    @Override
    public Report findById(Integer id) {

        String queryStr = "SELECT r FROM Report r WHERE r.id = ?1";
        TypedQuery<Report> query = entityManager.createQuery(
                queryStr, Report.class);
        query.setParameter(1,id);
        Report requiredReport = null;
        try {
            requiredReport = query.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        return requiredReport;
    }

    @Override
    public List<Report> findAll() {

        String queryStr = "SELECT r FROM Report r ORDER BY r.id";
        TypedQuery<Report> query = entityManager.createQuery(
                queryStr, Report.class);
        return query.getResultList();
    }
}
