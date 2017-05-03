import com.qingtian.apps.system.taskTranslate.SplitFile;
import com.qingtian.apps.task.entity.ParentFile;
import com.qingtian.apps.task.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by qingtian on 2017/5/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:appConf/spring-context.xml"})
public class testService {

    @Autowired
    private TaskService taskService;

//    @Test
//    public void testSplitTask() throws Exception{
//        String filePath= "F:\\machao1\\test\\test.txt";
//        String fileId = "3";
////        taskService.splitTask(fileId,filePath);
//    }
//
//    @Test
//    public void testSplitFile1() throws Exception{
////        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/appConf/spring-context.xml"});
//        ParentFile parentFile = taskService.getParentFile();
//        SplitFile sf = new SplitFile();
//        sf.sqlitFile1(parentFile);
//    }
}
