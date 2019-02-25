package v1.config;

import v1.bean.User;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistory {

    private static final Map<String,MapperData> methodSqlMapping = new HashMap<String,MapperData>();

    public MapperRegistory(String path,String method) {
        path = path.replaceAll("//",".")+"."+method;
        String sql = "SELECT * FROM test where id = %d";
        methodSqlMapping.put(path,new MapperData(sql,User.class));
    }

    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }

    public MapperData get(String namespace){
        return methodSqlMapping.get(namespace);
    }
}
