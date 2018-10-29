

import com.jnshu.entity.User;
import com.jnshu.mapper.UserMapper;
import com.jnshu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUser {
    Logger logger = Logger.getLogger(TestUser.class.getName());
@Autowired
    UserMapper userMapper;
@Autowired
UserService userService;

    @Test
    public void testRegister(){
    User user = new User();
    user.setUsername("root2");
    user.setPassword("123456");
    logger.info("注册结果：" + userMapper.addOne(user));
    }
    @Test
    public void testCheck(){
        User user = new User();
        user.setUsername("root2");
        user.setPassword("123456");
        logger.info("登陆结果：" + userMapper.findOne(user));
    }

    @Test
    public void testLogin(){
        User user = new User();
        user.setUsername("root1");
        user.setPassword("12345");
        logger.info("登陆结果：" + userService.login(user));
    }

    @Test
    public void testFindByName(){
        logger.info("查询结果：" + userMapper.findByName("root4"));
    }
}
