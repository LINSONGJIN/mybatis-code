package v1.config.result;

import v1.config.Configuration;
import v1.config.MapperRegistory;

import java.sql.PreparedStatement;

public class ResultSetHandle {

    private final Configuration configuration;

    public ResultSetHandle(Configuration configuration) {
        this.configuration = configuration;
    }

    public Object handler(PreparedStatement stpm, MapperRegistory.MapperData mapperData) {
        return null;
    }
}
