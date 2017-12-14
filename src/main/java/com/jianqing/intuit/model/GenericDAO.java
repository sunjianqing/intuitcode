package com.jianqing.intuit.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianqing_sun on 12/10/17.
 */
public class GenericDAO {

    // In the real project, we will inject an EntityManager to connect database source
    //@Inject
    //private EntityManager entityManager;

    public Map<String, BaseEntity> map = new HashMap<>();

    public BaseEntity save(BaseEntity entity) {
        map.put(entity.getPK(),entity);
        return entity;
    }

    public BaseEntity find(String pk) {
       return map.get(pk);
    }

    public void delete(BaseEntity entity){
        map.remove(entity.getPK());
    }

    public BaseEntity update(BaseEntity entity){
        map.put(entity.getPK(), entity);
        return entity;
    }

    public List<BaseEntity> findByProperty(Class clazz, String propertyName, Object value) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        List<BaseEntity> res = new ArrayList<>();
        for(Map.Entry<String, BaseEntity> entry : map.entrySet()){
            BaseEntity entity = entry.getValue();
            Method[] methods = Class.forName(clazz.getCanonicalName()).getMethods();
            for(Method m: methods){
                if(m.getName().equals("get"+propertyName)){
                    if(m.invoke(entity).equals(value))
                        res.add(entity);
                }
            }
        }

        return res;
    }



}
