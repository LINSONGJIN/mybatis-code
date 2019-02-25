package v1.config.excutor;

import v1.config.Configuration;

public class ExecutorFactory {

    public static final String CACHE = "CACHE";
    public static final String SIMPLE = "SIMPLE";

    private Configuration configuration;

    public static Executor DEFALT(Configuration configuration){
        return new SimpleExecutor(configuration);
    }

    public static Executor get(String key,Configuration configuration){
        if(CACHE.equalsIgnoreCase(key)){
            return new CachingExcutor(configuration);
        }
        if(SIMPLE.equalsIgnoreCase(key)){
            return new SimpleExecutor(configuration);
        }
        throw new RuntimeException("no Executor found");
    }
}
