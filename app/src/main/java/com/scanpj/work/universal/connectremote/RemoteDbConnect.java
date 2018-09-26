package com.scanpj.work.universal.connectremote;


import android.util.Log;

import com.scanpj.work.constant.ConstDbRemote;
import com.scanpj.work.entity.ChickenInfoRaw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/6/5.
 * 类描述   远程数据库连接
 * 版本
 */
public class RemoteDbConnect {


    public RemoteDbConnect() {
    }


    public Connection openConnection(String url, String user,
                                     String password) {
        Connection conn = null;
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            conn = null;
        } catch (SQLException e) {
            conn = null;
        }
        return conn;
    }

    public List<ChickenInfoRaw>  query(Connection conn, String sql) {

        List<ChickenInfoRaw> list = null;

        if (conn == null) {
            return list;
        }

        Statement statement = null;
        ResultSet result = null;

        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            if (result != null && result.first()) {
                int columnIndexRingId = result.findColumn(ConstDbRemote.RING_ID); //脚环id
                int columnIndexHouseId = result.findColumn(ConstDbRemote.HOUSE_ID);//鸡场id
                int columnIndexBatchId = result.findColumn(ConstDbRemote.BATCH_ID);//批次id
                int columnIndexTotalStep = result.findColumn(ConstDbRemote.TOTAL_STEP);//总步数
                int columnIndexShortUrl = result.findColumn(ConstDbRemote.SHORT_URL);//短网址
                int columnIndexBatchName = result.findColumn(ConstDbRemote.BATCH_NAME);//批次名称

                list = new ArrayList<>();
                while (!result.isAfterLast()) {


                    String ringId = result.getString(columnIndexRingId);
                    String houseId = result.getString(columnIndexHouseId);
                    String batchId = result.getString(columnIndexBatchId);
                    int totalStep = result.getInt(columnIndexTotalStep);
                    String shortUrl = result.getString(columnIndexShortUrl);
                    String btachName = result.getString(columnIndexBatchName);


                    ChickenInfoRaw chickenInfoRaw = new ChickenInfoRaw();
                    chickenInfoRaw.setRingid(ringId);
                    chickenInfoRaw.setHouseid(houseId);
                    chickenInfoRaw.setBatchid(batchId);
                    chickenInfoRaw.setTotal(totalStep);
                    chickenInfoRaw.setShortUrl(shortUrl);
                    chickenInfoRaw.setName(btachName);

                    list.add(chickenInfoRaw);
                    Log.i("quray", "脚环id:" + ringId + " 鸡场id:" + houseId + " 批次id:" + batchId + " 总步数:" + totalStep+" short_url:"+shortUrl);
                    result.next();
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (null != conn) {
                    conn.close();
                }

            } catch (SQLException sqle) {

            }
        }

        return list;
    }


}