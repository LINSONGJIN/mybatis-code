package v1;

import v1.config.Configuration;
import v1.config.MapperRegistory;
import v1.config.excutor.Executor;

import java.lang.reflect.Proxy;

public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public Configuration getConfiguration() {
        return configuration;
    }

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(this,clazz));
    }

    public <T> T selectOne(MapperRegistory.MapperData statem, Object parameter) throws Exception {
        return executor.query(statem,parameter);
    }
}
