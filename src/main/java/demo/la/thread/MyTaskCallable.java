package demo.la.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author LA
 * @Date 2018/12/2 13:13
 * @Description 具有返回值的任务
 */
public class MyTaskCallable implements Callable<String>{
    private String res;
    public MyTaskCallable(){}
    public MyTaskCallable(String res){
        this.res =  res;
    }
    public String call() throws Exception {
        return String.format("当前线程【%s】的返回值为【%s】",Thread.currentThread().getName(),res);
    }

}
class Main3{
    public static void main(String[] args) {
        //使用executor创建可控制的线程
        ExecutorService exc = Executors.newFixedThreadPool(5);
        List<Future<String>> futures =  new ArrayList<Future<String>>();
        for(int i=1;i<=10;i++){
            futures.add(exc.submit(new MyTaskCallable("task" + i)));
        }
        exc.shutdown();
        for(Future f:futures){
            try {
                //method get will block untill current task is done
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



    }
}
