package com.yixin;

import java.io.*;
/*

1、模拟输入流和输出流 从一个文件拷贝数据到另一个文件中
 */
public class testmain {

    /*
    练习输入流和输出流
     */
//    public static void main(String[] args) throws IOException {
//        File file = new File("D://text.txt");
//        File file2 = new File("D://text2.txt");
//        InputStream inputStream = new FileInputStream(file);
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"GBK");
//
//        OutputStream outputStream = new FileOutputStream(file2);
//
//        char[] bytes = new char[10];
//
//        int len = 0;
//        while ((len=inputStreamReader.read(bytes)) != -1){
//            String str = new String(bytes,0,len);
//            outputStream.write(str.getBytes());
//            outputStream.flush();
//            System.out.println(str);
//        }
//
//        if (inputStream != null){
//            inputStream.close();
//        }
//
//
//    }

    public static void main(String[] args) {
        //Math.random()方法乘以一个数字，是最大随机生成到多少位数，以下代表随机生成1~999的数字
        //Math.random()默认是double类型的，可以强制转换成int
        int a = (int) (Math.random()*(1000-1));
        System.out.println(a);
    }
}
