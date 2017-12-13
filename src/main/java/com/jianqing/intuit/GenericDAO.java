package com.jianqing.intuit;

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

    public Map<String, Entity> map = new HashMap<>();

    public Entity save(Entity entity) {
        map.put(entity.getPK(),entity);
        return entity;
    }

    public Entity find(String pk) {
       return map.get(pk);
    }

    public void delete(Entity entity){
        map.remove(entity.getPK());
    }

    public Entity update(Entity entity){
        map.put(entity.getPK(), entity);
        return entity;
    }

    public List<Entity> findByProperty(Class clazz, String propertyName, Object value) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        List<Entity> res = new ArrayList<>();
        for(Map.Entry<String, Entity> entry : map.entrySet()){
            Entity entity = entry.getValue();
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
