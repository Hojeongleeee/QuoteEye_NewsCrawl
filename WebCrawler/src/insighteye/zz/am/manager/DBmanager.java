package insighteye.zz.am.manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.security.auth.login.CredentialException;

import org.apache.http.auth.Credentials;

/* Class 생성일 : 2016-11-16
 * Class 작성자 : 곽민석
 * Class 용도 : MySQL DB 연결용
 * saveDB - 기사 정보 저장
 * readDB - 최근 저장 기사 index 획득
 */
public class DBmanager {
    Connection con;
    String sql;
    PreparedStatement pstmt;
    
    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/insighteye?autoReconnect=true&useSSL=false";
            con = DriverManager.getConnection(url, "root", "6303");
            return con;
        } catch (ClassNotFoundException ce) {
            System.out.println(ce.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("SQL Exception");
            return null;
        }
    }
}
