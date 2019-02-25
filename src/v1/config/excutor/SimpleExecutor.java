package v1.config.excutor;

import v1.config.Configuration;
import v1.config.MapperRegistory;
import v1.config.statement.StatementHandler;

import java.sql.SQLException;


public class SimpleExecutor implements Executor {

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public <T> T query(MapperRegistory.MapperData statem, Object parameter) throws SQLException {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        return (T) handler.query(statem,parameter);
    }
}
