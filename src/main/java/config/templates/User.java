package config.templates;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public class User implements DataTemplate {

    @CsvBindByName
    private String id = null;
    @CsvBindByName
    private String userName = null;
    @CsvBindByName
    private String password = null;
    @CsvBindByName
    private String firstName = null;
    @CsvBindByName
    private String lastName = null;
    @CsvBindByName
    private String alias = null;
    @CsvBindByName
    private String user_id = null;
    @CsvBindByName
    private String comment = null;



    public String getId(){ return id ;}

    public String getUsername(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstname(){
        return firstName;
    }
    public String getLastname(){
        return lastName;
    }

    public String getFullname(){
        return firstName+" "+lastName;
    }

    public String getAlias(){
        return alias;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUsername(String name ){
        this.userName = name;
    }

    public void setPassword(String pw ){
        this.password = pw;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Id : "+id+", Username : "+userName;
    }

    @Override
    public boolean equals(Object obj) {
        String compareString = obj.toString();
        if (compareString.equals(id)){
            return true;
        }
        else if (compareString.equals(userName)){
            return true;
        }
        else if (compareString.equals(password)){
            return true;
        }
        else if (compareString.equals(firstName)){
            return true;
        }
        else if (compareString.equals(lastName)){
            return true;
        }
        else if (compareString.equals(alias)){
            return true;
        }
        else if (compareString.equals(user_id)){
            return true;
        }
        else {
            return false;
        }
    }
}
