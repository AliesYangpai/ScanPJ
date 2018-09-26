package com.scanpj.work.universal.cache.db.dao.impl;

import com.scanpj.work.entity.ChickenInfoRaw;
import com.scanpj.work.entity.ChickenInfoScanAbout;
import com.scanpj.work.universal.cache.db.dao.IBaseDao;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2018/6/13.
 * 类描述
 * 版本
 */

public class IChickenInfoScanAboutimpl implements IBaseDao<ChickenInfoScanAbout> {
    @Override
    public boolean save(ChickenInfoScanAbout chickenInfoScanAbout) {
        return chickenInfoScanAbout.save();
    }

    @Override
    public void saveAll(List<ChickenInfoScanAbout> t) {
        DataSupport.saveAll(t);
    }

    @Override
    public int deleteAll(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass) {
        return DataSupport.deleteAll(chickenInfoScanAboutClass);
    }

    @Override
    public int deleteAllByCondition(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass, String... args) {
        return DataSupport.deleteAll(chickenInfoScanAboutClass, args);
    }

    @Override
    public List<ChickenInfoScanAbout> findAll(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass) {
        return DataSupport.findAll(chickenInfoScanAboutClass);
    }

    @Override
    public ChickenInfoScanAbout findFirst(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass) {
        return DataSupport.findFirst(chickenInfoScanAboutClass);
    }

    @Override
    public List<ChickenInfoScanAbout> findByCondition(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass, String... args) {
        return DataSupport.where(args).find(chickenInfoScanAboutClass);
    }




    @Override
    public List<ChickenInfoScanAbout> findAllWithLimiteOffset(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass, int limit, int offset) {
        return DataSupport.limit(limit).offset(offset).find(chickenInfoScanAboutClass);
    }

    @Override
    public List<ChickenInfoScanAbout> findAllWithLimiteOffsetByCondition(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass, int limit, int offset, String... args) {
        return DataSupport.where(args).limit(limit).offset(offset).find(chickenInfoScanAboutClass);
    }

    @Override
    public int findCountByCondition(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass, String... args) {
        return DataSupport.where(args).count(chickenInfoScanAboutClass);
    }

    @Override
    public int findAllCount(Class<ChickenInfoScanAbout> chickenInfoScanAboutClass) {
        return DataSupport.count(chickenInfoScanAboutClass);
    }



}
