package v1;

import v1.config.MapperRegistory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {


    private SqlSession sqlSession;
    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistory.MapperData data= sqlSession.getConfiguration()
                .getMapperRegistory(method.getDeclaringClass().getName(),method.getName())
                .get(method.getDeclaringClass().getName()+"."+method.getName());
        if(null != data){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", data.getSql(), args[0]));
            return sqlSession.selectOne(data,args[0]);
        }
        return method.invoke(proxy,args);
    }
}
