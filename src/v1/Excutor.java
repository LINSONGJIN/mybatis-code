package v1;

import v1.config.MapperRegistory;

import java.sql.SQLException;

public interface Excutor {
    <T> T query(MapperRegistory.MapperData statem, Object parameter) throws SQLException;
}
