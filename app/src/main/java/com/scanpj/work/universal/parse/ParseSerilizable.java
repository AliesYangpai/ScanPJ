package com.scanpj.work.universal.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/2.
 * 类描述   实体类对象解析
 * 版本
 */
public class ParseSerilizable {


    public ParseSerilizable() {

    }


    /**
     * 解析成对象
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getParseToObj(String back, Class<T> cls) {
        T t = null;


        try {
            t = new Gson().fromJson(back, cls);
        } catch (Exception e) {
            return t;
        }
        return t;
    }


    /**
     * 解析成集合
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> getParseToList(String back, Class<T> cls) {


        JsonObject asJsonObject = null;
        JsonArray jsonArray = null;
        List<T> list = null;
        Gson gson = null;
        try {
            asJsonObject = new JsonParser().parse(back).getAsJsonObject();
            String items = "items";
            jsonArray = asJsonObject.getAsJsonArray(items);

            gson = new Gson();
            list = new ArrayList<T>();
            for (JsonElement elem : jsonArray) {
                list.add(gson.fromJson(elem, cls));
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }





    public HttpResult getParseDataListZTE(String json, Class clazz) {
        return new GsonBuilder()
                .registerTypeAdapter(HttpResult.class, new JsonFormatParser(clazz))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create()
                .fromJson(json, HttpResult.class);
    }





}


//public class ParseSerilizable {
//
//
//    public ParseSerilizable() {
//
//    }
//
//
//    /**
//     * 解析成对象
//     *
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T> T getParseToObj(String back, Class<T> cls) {
//        T t = null;
//
//
//        try {
//            t = new Gson().fromJson(back, cls);
//        } catch (Exception e) {
//            return t;
//        }
//        return t;
//    }
//
//
//    /**
//     * 解析成集合
//     *
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> getParseToList(String back, Class<T> cls) {
//
//
//        JsonObject asJsonObject = null;
//        JsonArray jsonArray = null;
//        List<T> list = null;
//        Gson gson = null;
//        try {
//            asJsonObject = new JsonParser().parse(back).getAsJsonObject();
//            String items = "items";
//            jsonArray = asJsonObject.getAsJsonArray(items);
//
//            gson = new Gson();
//            list = new ArrayList<T>();
//            for (JsonElement elem : jsonArray) {
//                list.add(gson.fromJson(elem, cls));
//            }
//
//        } catch (Exception e) {
//            return list;
//        }
//        return list;
//    }
//
//
//    /**
//     * 解析count
//     * @param back
//     * @return
//     */
//    public int getParseCount(String back) {
//
//
//        int count =0;
//        try {
//            JSONObject jsonObject = new JSONObject(back);
//            count = jsonObject.getInt("count");
//
//
//        } catch (JSONException e) {
//            return count;
//
//        }
//
//        return count;
//
//    }
//
//
//    /**
//     * 解析成集合
//     *
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> getParseToNoItemsList(String back, Class<T> cls) {
//
//        JsonArray asJsonArray = null;
//
//        Gson gson = null;
//        List<T> list = null;
//
//        try {
//
//            asJsonArray = new JsonParser().parse(back).getAsJsonArray();
//            gson = new Gson();
//            list = new ArrayList<T>();
//
//            for (JsonElement elem : asJsonArray) {
//                list.add(gson.fromJson(elem, cls));
//            }
//
//
//        }catch (Exception e) {
//
//            return list;
//
//        }
//        return list;
//
//    }
//
//
//    /**
//     * 这个是 org的jsonobject
//     *
//     * @param back
//     * @param key
//     * @return
//     */
//    public String getParseString(String back, String key) {
//
//        String json = "";
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(back);
//            json = jsonObject.getString(key);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }
//
//
//    /**
//     * 将对象转化为 json对象
//     * @param o
//     * @return
//     */
//    public JsonElement getParseObjToJson(Object o) {
//
//        JsonElement jsonElement = null;
//
//
//        try{
//            jsonElement = new Gson().toJsonTree(o);
//        }catch (Exception e){
//
//            return jsonElement;
//        }
//
//        return jsonElement;
//    }
//
//
//    /**
//     * 解析成对象
//     *
//     * @param back
//     * @param <T>
//     * @return
//     */
//
//
//    public  <T> HttpResult<List<T>> getParseDataListZTE(String back, Class<T> clazz) {
//
//        HttpResult<List<T>> listHttpResult = null;
//        // 生成List<T> 中的 List<T>
//        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
//        // 根据List<T>生成完整的Result<List<T>>
//        Type type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{listType});
//
//        try {
//            listHttpResult  = new Gson().fromJson(back, type);
//        } catch (Exception e) {
//            return listHttpResult;
//        }
//        return listHttpResult;
//
//    }
//
//
//    public  <T> HttpResult<T> getParseDataObjZTE(String back, Class<T> clazz) {
//
//        HttpResult<T> httpResult = null;
//        // 生成List<T> 中的 List<T>
//        Type objType = new ParameterizedTypeImpl(Object.class, new Class[]{clazz});
//        // 根据List<T>生成完整的Result<List<T>>
//        Type type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{objType});
//
//        try {
//            httpResult  = new Gson().fromJson(back, type);
//        } catch (Exception e) {
//            return httpResult;
//        }
//        return httpResult;
//
//    }
//
//
//
//}
