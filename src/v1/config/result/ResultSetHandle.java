package v1.config.result;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import v1.config.Configuration;
import v1.config.MapperRegistory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ResultSetHandle {

    private final Configuration configuration;

    public ResultSetHandle(Configuration configuration) {
        this.configuration = configuration;
    }

    public Object handler(PreparedStatement stpm, MapperRegistory.MapperData mapperData) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object resultObject = new DefaultObjectFactory().create(mapperData.getType());
        ResultSet rs = stpm.getResultSet();
        if(rs.next()){
            int i = 0;
            for (Field field : resultObject.getClass().getDeclaredFields()) {
                setValue(resultObject,field,rs,i);
            }
        }
        return null;
    }

    private void setValue(Object object, Field field, ResultSet rs, int n) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        String methodName = "set" + upperCapital(field.getName());
        Method method = object.getClass().getMethod(methodName,field.getType());
        method.invoke(object,getResult(rs,field));

    }

    private Object getResult(ResultSet rs, Field field) throws SQLException {
        Class<?> type = field.getType();
        if(String.class == type){
            return rs.getString(field.getName());
        }
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(Date.class == type){
            return rs.getDate(field.getName());
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
