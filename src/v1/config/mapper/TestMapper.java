package v1.config.mapper;

import v1.bean.User;

public interface TestMapper {
    User selectById(String id);
}
