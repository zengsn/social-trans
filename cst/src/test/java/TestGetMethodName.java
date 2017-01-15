import org.junit.Test;

/**
 * Created by machao on 2017/1/15.
 */
public class TestGetMethodName {

    /**
     * 获取类名
     */
    @Test
    public void GetClassName(){
        String name1 = this.getClass().getName();
        String name2 = this.getClass().getSimpleName();
        System.out.println("name1:"+name1);
        System.out.println("name2:"+name2);
    }

    /**
     * 获取方法名
     */
    @Test
    public void GetMethodName(){
//        for(int i=0;i<Thread.currentThread().getStackTrace().length;i++){
//            System.out.println("Thread"+i+":"+Thread.currentThread().getStackTrace()[i].getClassName());
//        }
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName();
        System.out.println(methodName);
    }
}
