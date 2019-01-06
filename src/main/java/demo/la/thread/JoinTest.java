package demo.la.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author LA
 * @Date 2018/12/2 17:39
 * @Description JOIN 练习，可用来做优先级控制
 * 在线程A上执行线程B.join()，线程B执行结束后，A才继续执行
 */
public class JoinTest {
    public static void main(String[] args) {
        BeInvoked th1 = new  BeInvoked("I am Beinvoked!");
        Invoker th2 = new  Invoker(th1);
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        th1.interrupt();

    }


}
class BeInvoked extends Thread{
    private  String msg;
    public  BeInvoked(String msg){
        this.msg = msg;
        super.start();
    }
    @Override
    public  void run(){
        try {
            System.out.println("BeInvoked start...");
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("BeInvoked被调用的线程睡眠中断1");
        }

        System.out.println("BeInvoked线程结束,输入参数为:"+msg);
    }
}

class Invoker extends Thread{
    private  BeInvoked beInvoked;
    public  Invoker(BeInvoked beInvoked){
        this.beInvoked = beInvoked;
        super.start();
    }
    @Override
    public  void run(){
        System.out.println("Invoker 开始在 BeInvoked上调用join");
        try {
            beInvoked.join();
        } catch (InterruptedException e) {
            System.out.println("调用BeInvoked时发生中断异常");
            e.printStackTrace();
        }
        System.out.println("Invoker执行结束");
    }
}
