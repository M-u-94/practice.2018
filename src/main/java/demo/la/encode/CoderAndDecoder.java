package demo.la.encode;


import java.io.*;
import java.util.Arrays;

/**
 * @Author LA
 * @Date 2019/1/5 00:08
 * @Description
 * <pre>
 *     1.UTF-8中文使用三字节表示；GBK对中英文都是用双字节表示；ISO8859-1使用单字节表示，因此很多中文无法表示
 *     2.jvm 内存中的编码都是unicode,因此只要确认外存（文件或者数据库）中数据编码，并使用相同的编码去读取就能在内存中正常显示
 *     3.如果使用inputsteam 字节流读取文件时，制定错误的编码转为字符，以后是无法转回来的。参考例子2
 * </pre>
 *
 */
public class CoderAndDecoder {
    private static String inputFilePath ="C:\\Users\\LA\\Desktop\\demo\\GBK.txt";
    private static String outputFilePath ="C:\\Users\\LA\\Desktop\\demo\\result.txt";

    /**
     * 读文件
     * @param charsetName
     * @return
     * @throws Exception
     */
    public  static String  read(String charsetName) throws  Exception{
        BufferedReader reader  = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath),charsetName));
        String line="";
        while((line = reader.readLine()) != null ){
            System.out.println(line);
            break;
        }
        reader.close( );
        return line ;
    }

    /**
     * 写入文件
     * @param path
     * @param line
     * @throws Exception
     */
    public static  void write(String path,String line) throws  Exception{
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
        writer.write(line);
        writer.close();
    }


    public static void main(String[] args) {
        try {
            String str=read("gbk");
            byte[] utf8Bytes = str.getBytes("utf-8");
            System.out.println(Arrays.toString(utf8Bytes));
            System.out.println("utf8 String:"+new String(utf8Bytes,"utf-8") );
            byte[] gbkBytes = str.getBytes("gbk");
            System.out.println(Arrays.toString(gbkBytes) );
            System.out.println("gbk String:"+new String(gbkBytes,"gbk") );
            /*例子二，使用错误的编码utf-8读取gbk文件*/
            String line2 = read("utf-8");
            System.out.println("line2="+line2 );
            System.out.println("line2 utf-8 bytes:"+Arrays.toString(line2.getBytes("utf-8")) );
            System.out.println("line2 gbk bytes:"+Arrays.toString(line2.getBytes("gbk")) );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
