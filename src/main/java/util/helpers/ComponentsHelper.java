package util.helpers;


import config.ComponentImp;
import core.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShepardPin on 13/02/18.
 */
public class ComponentsHelper {


    private static final Map<ComponentImp, Component> componentsCache = new HashMap<>();

    public static Component getComponent(ComponentImp target) {
        Component component =  componentsCache.get(target);
        if (component==null){

            File file = new File(target.getPath());

            try {
                component = new Component(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            componentsCache.put(target,component);
        }
        return component;
    }

}
