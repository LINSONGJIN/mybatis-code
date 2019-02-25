package v1.config.excutor;

import v1.config.MapperRegistory;

public interface Executor {
    <T> T query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception;
}
