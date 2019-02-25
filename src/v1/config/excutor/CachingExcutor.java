package v1.config.excutor;

import v1.Excutor;
import v1.config.Configuration;
import v1.config.MapperRegistory;

import java.util.HashMap;
import java.util.Map;

public class CachingExcutor implements Excutor {

    private Configuration configuration;

    private SimpleExecutor delegate;

    private Map<String,Object> localCache = new HashMap<String,Object>();

    public CachingExcutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExcutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperRegistory.MapperData mapperData, Object parameter) {
        Object result = localCache.get(mapperData.getSql());
        if (null != result){
            System.out.println("命中缓存");
            return (T) result;
        }
        result = delegate.query(mapperData,parameter);
        return null;
    }
}
