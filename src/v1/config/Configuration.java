package v1.config;

public class Configuration {

    private String scanPath;
    private MapperRegistory mapperRegistory;

    public Configuration(String scanPath){
        this.scanPath = scanPath;
}

    private void build(String path,String method){
        if(null == scanPath || scanPath.length() <= 0){
            throw new RuntimeException("Scan Path is required!");
        }
        mapperRegistory = new MapperRegistory(path,method);
    }


    public MapperRegistory getMapperRegistory(String path,String method) {
        if(mapperRegistory == null){
            build(path,method);
        }
        return mapperRegistory;
    }
}
