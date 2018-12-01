package demo.la.thread;
/**
 * @Author LA
 * @Date 2018/12/1 13:52
 * @Description Runnable 类测试
 */

public class MyTask implements Runnable {
    private static  volatile  int conId=0;
    private int order;
    public MyTask(){};
    public MyTask(int order){
        this.order = order;
    }

    public void run() {
        System.out.println("当前顺序为："+getOrder(order)+"，线程信息："+Thread.currentThread().getName());
    }


    /**
     * 返回输入
     * @param order
     * @return
     */
    protected  int getOrder(int order){
        return order;
    }


}
class Main{
    public static void main(String[]args){
        MyTask myTask;
        Thread thread;
        for (int i=0;i<20;i++){
            myTask = new MyTask(i);
            thread  = new Thread(myTask);
            thread.start();
        }
        System.out.println("Main["+Thread.currentThread().getName()+" ]thread end!");
    }
}
