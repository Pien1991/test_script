package util.helpers;

import config.templates.DataTemplate;
import config.templates.User;
import util.parsers.CSVParser;

import java.io.File;
import java.util.List;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class UsersHelper extends DataHelper {

    //TODO : hard-coded
    private static String path = getRootPath()+"/users.csv";
    private static List<DataTemplate> users ;

    static {
        users = CSVParser.getData(new User() , new File(path));
    }

    public static List<DataTemplate> getUserList(){
        return users;
    }


    public static  User getUserByUsername(String username ){
       return (User) getDataByConditions(users,username).get(0);
    }

    public static  User getUserById(int i){
        return (User) getDataByConditions(users,i).get(0);
    }


    public static  User getUserByAlias(String alias){
        return (User) getDataByConditions(users,alias).get(0);
    }


}
