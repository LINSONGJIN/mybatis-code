package v1;

import v1.config.Configuration;
import v1.config.MapperRegistory;

import java.lang.reflect.Proxy;

public class SqlSession {

    private Configuration configuration;
    private Excutor excutor;

    public Configuration getConfiguration() {
        return configuration;
    }

    public SqlSession(Configuration configuration, Excutor excutor) {
        this.configuration = configuration;
        this.excutor = excutor;
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(this,clazz));
    }

    public <T> T selectOne(MapperRegistory.MapperData statem, Object parameter){
        return excutor.query(statem,parameter);
    }
}
