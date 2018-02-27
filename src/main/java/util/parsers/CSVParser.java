package util.parsers;


import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import config.templates.DataTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class CSVParser {

    private CSVParser() {

    }

    public static List getData(DataTemplate template , File file){
        CSVReader reader ;
        try {
            //Note the default separator is ","
            reader = new CSVReader(new FileReader(file));
            return convertTemplate(reader,template);
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Cannot find the csv file hence cannot initialize the reader");
        }

    }

    private static List convertTemplate(CSVReader reader , DataTemplate template ){
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(template.getClass());
        CsvToBean csv = new CsvToBean();
        return csv.parse(strategy,reader);

    }

}
