package util.helpers;


import config.templates.DataTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ShepardPin on 13/2/2018.
 */
public abstract class DataHelper {

    private final static String rootPath = "src/main/resources/testData";

    protected static String getRootPath(){
        return rootPath;
    }


    protected static List getDataByConditions(List<DataTemplate> dataCollection, Object... conditions){
        List<DataTemplate> extractData = new ArrayList();

        for ( DataTemplate data: dataCollection) {
            boolean isAdd =true;
            for ( Object condition : conditions) {
                if (!data.equals(condition)){
                    isAdd = false;
                    break;
                }
            }
            if (isAdd==true){
                extractData.add(data);
            }
        }



        if (extractData.size()>1){
            throw new IllegalArgumentException(extractData.size()+" data fulfilled the required conditions : "+ Arrays.toString(extractData.toArray())  );
        }else if (extractData.size()==0){
            throw new IllegalArgumentException("No data fulfilled the required conditions") ;
        }



        return extractData;
    }


}
