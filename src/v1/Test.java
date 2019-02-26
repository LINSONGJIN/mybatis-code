package v1;

import v1.bean.User;
import v1.config.Configuration;
import v1.config.excutor.Executor;
import v1.config.excutor.ExecutorFactory;
import v1.config.mapper.TestMapper;
import v1.config.sqlsession.SqlSession;

public class Test {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Configuration configuration = new Configuration("v1.config.mapper.TestMapper");
        Executor executor = ExecutorFactory.get(ExecutorFactory.SIMPLE, configuration);
        SqlSession sqlSession = new SqlSession(configuration, executor);
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        User user = testMapper.selectById("1");
        System.out.println(user.getId());
    }
}
