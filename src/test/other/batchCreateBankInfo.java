package other;

import org.testng.annotations.Test;
import util.parsers.MysqlHelper;

import java.sql.Statement;

/**
 * Created by ShepardPin on 3/3/2018.
 */
public class batchCreateBankInfo {


    String account_name;
    String bank_nickname;
    String beneficiary_bank_name;
    String beneficiary_name;
    String branch_name;
    String commemt;
    String recipient_address;
    String swift_code;
    String user_id;


    @Test
    public void batchBankInfo() throws Exception {


        Statement statement = MysqlHelper.getConnection().createStatement();

        try {

            for (int i = 0; i < 51 ; i++) {
                String sql = "insert into t_bank_account (created_at,updated_at,version,account_name,bank_nickname,beneficiary_bank_name,beneficiary_name,branch_name,comment,recipient_address,swift_code,user_id) values"+
                        "(now(),now(),0,"+i+","+i+","+i+","+i+","+i+","+i+","+i+","+i+","+"'1b94ceb9-55d3-4a52-ac74-3dad4474a048'"+")";


                System.out.println(sql);

                statement.executeUpdate(sql);
            }
        }finally {
            MysqlHelper.disconnect();

        }



    }
}
