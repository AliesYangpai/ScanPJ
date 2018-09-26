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

public class IChickenInfoRawDaoimpl implements IBaseDao<ChickenInfoRaw> {



    @Override
    public boolean save(ChickenInfoRaw chickenInfoRaw) {
        return chickenInfoRaw.save();
    }

    @Override
    public void saveAll(List<ChickenInfoRaw> t) {
        DataSupport.saveAll(t);
    }

    @Override
    public int deleteAll(Class<ChickenInfoRaw> chickenInfoRawClass) {
        return DataSupport.deleteAll(chickenInfoRawClass);
    }

    @Override
    public int deleteAllByCondition(Class<ChickenInfoRaw> chickenInfoRawClass, String... args) {
        return DataSupport.deleteAll(chickenInfoRawClass, args);
    }

    @Override
    public List<ChickenInfoRaw> findAll(Class<ChickenInfoRaw> chickenInfoRawClass) {
        return DataSupport.findAll(chickenInfoRawClass);
    }

    @Override
    public ChickenInfoRaw findFirst(Class<ChickenInfoRaw> chickenInfoRawClass) {
        return DataSupport.findFirst(chickenInfoRawClass);
    }

    @Override
    public List<ChickenInfoRaw> findByCondition(Class<ChickenInfoRaw> chickenInfoRawClass, String... args) {
        return DataSupport.where(args).find(chickenInfoRawClass);
    }

    @Override
    public List<ChickenInfoRaw> findAllWithLimiteOffset(Class<ChickenInfoRaw> chickenInfoRawClass, int limit, int offset) {
        return DataSupport.limit(limit).offset(offset).find(chickenInfoRawClass);
    }

    @Override
    public List<ChickenInfoRaw> findAllWithLimiteOffsetByCondition(Class<ChickenInfoRaw> chickenInfoRawClass, int limit, int offset, String... args) {
        return DataSupport.where(args).limit(limit).offset(offset).find(chickenInfoRawClass);
    }

    @Override
    public int findCountByCondition(Class<ChickenInfoRaw> chickenInfoRawClass, String... args) {
        return DataSupport.where(args).count(chickenInfoRawClass);
    }

    @Override
    public int findAllCount(Class<ChickenInfoRaw> chickenInfoRawClass) {
        return DataSupport.count(chickenInfoRawClass);
    }



}
