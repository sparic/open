import cn.muye.Application;
import cn.muye.cooperation.domain.AgentApply;
import cn.muye.cooperation.service.AgentApplyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestProperties {

    @Value("${devCenter.pushDirs}")
    private String blogAuthor;

    @Value("${devCenter.pushHttp}")
    private String blogTitle;

    @Autowired
    private AgentApplyService agentApplyService;

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

    @Test
    public void test2() {
        AgentApply agentApply = agentApplyService.getById(134L);
        System.out.println(agentApply);
    }
}