package com.tsystems.jschool.railage.datasource.interfaces;

import com.tsystems.jschool.railage.domain.Report;

import java.util.List;

/**
 * Created by rudolph on 11.08.15.
 */
public interface ReportDao extends Dao<Report,Integer> {
    @Override
    void persist(Report entity);

    @Override
    Report merge(Report entity);

    @Override
    Report findById(Integer id);

    @Override
    List<Report> findAll();
}
