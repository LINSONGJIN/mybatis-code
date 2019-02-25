package v1.config.statement;

import v1.config.Configuration;
import v1.config.MapperRegistory;
import v1.config.result.ResultSetHandle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库操作和结果分发
 */
public class StatementHandler {

    private final Configuration configuration;

    private final ResultSetHandle resultSetHandle;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandle = new ResultSetHandle(configuration);
    }

    public <T> T query(MapperRegistory.MapperData mapperData, Object parameter) {

        try {
            Connection conn = getConnection();
            PreparedStatement stpm = conn.prepareStatement(mapperData.getSql(),Integer.parseInt(String.valueOf(parameter)));
            stpm.execute();
            return (T) resultSetHandle.handler(stpm,mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
