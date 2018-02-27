package util.parsers;


import config.templates.WebElementConfig;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class XMLParser {


    private XMLParser() {

    }



    private static Document read(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(file);
        return doc;
    }

    public static HashMap<String,WebElementConfig> getXMLMap(File file) throws DocumentException {
        HashMap<String,WebElementConfig> map = new HashMap();
        ArrayList<Element> tempAL =new ArrayList();
        treeWalk(map,tempAL,read(file).getRootElement());
        return map;
    }





    private static void treeWalk(HashMap<String,WebElementConfig> map,ArrayList<Element> tempAL,final Element element){

        WebElementConfig webElementConfig = loadWebElementConfig(element);

        if (map.keySet().size()==0){
            webElementConfig.setParentWebElementConfig(null);
        }else {
            webElementConfig.setParentWebElementConfig(map.get(element.getParent().getName()));
        }

        map.put(element.getName(),webElementConfig);
        tempAL.addAll(element.elements());
        if (tempAL.size()==0){
            return;
        }else {
            Element subElement = tempAL.remove(0);
            treeWalk(map,tempAL,subElement);
        }

    }

    private static    WebElementConfig loadWebElementConfig(Element element){
        int attributeNum = element.attributeCount();
        WebElementConfig webElementConfig = new WebElementConfig();
        webElementConfig.setName(element.getName());
        webElementConfig.setComponentRoot(element.isRootElement());

        for (int i = 0; i < attributeNum; i++) {
            Attribute attribute = element.attribute(i);
            if (attribute!=null) {
                String attributeValue = attribute.getValue();
                switch (attribute.getName()) {
                    case "method":
                        webElementConfig.setMethod(attributeValue);
                        break;
                    case "path":
                        webElementConfig.setPath(attributeValue);
                        break;
                    case "waitTime":
                        webElementConfig.setWaitTime(attributeValue);
                        break;
                }
            }
        }
        return  webElementConfig;
    }
}


