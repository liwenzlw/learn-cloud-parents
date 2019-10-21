package spring.cloud.learn.springbootadmin.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringBootAdminClientApplicationTests {

    @Test
    public void contextLoads() {
        int loop = 2;
        while (loop -- > 0) {
            System.out.println(loop);
        }
    }

}
