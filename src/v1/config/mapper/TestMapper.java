package v1.config.mapper;

import v1.bean.Test;

public interface TestMapper {
    Test selectById(String id);
}
