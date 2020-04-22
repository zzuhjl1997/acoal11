package Demo;

import com.plat.acoal.entity.CD;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE)
public class DemoApplicationTests {
    //自动装配
    @Autowired
    private CD cd;
    @Test
    public  void fun(){
        System.out.println(cd.getName());
    }
}
