import cn.muye.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestProperties {

    @Value("${devCenter.push.dirs}")
    private String blogAuthor;

    @Value("${devCenter.push.http}")
    private String blogTitle;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test1(){
        try{
            Assert.assertEquals("bluecoffee", blogAuthor);
            Assert.assertEquals("Spring Boot基础教程", blogTitle);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}