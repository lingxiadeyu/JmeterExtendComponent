package com.yixin;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
1、实现添加参数和默认参数值，也可以人工手动输入
2、实现自动执行url并获取返回结果输出到结果树中
 */
public class testExtendSecond implements JavaSamplerClient {

    private static final String URLNAME = "URL";
    private static  final  String DEFAULTURL = "http://www.baidu.com";


    /**
     * 设置参数，可以在jmeter上展示该参数
     * @return
     */
    public Arguments getDefaultParameters() {
        System.out.println("get default parameters");
        Arguments arguments = new Arguments();
        arguments.addArgument(URLNAME,DEFAULTURL);
        return arguments;
    }

    private String inputurl;

    /**
     * 初始化方法
     * @param javaSamplerContext
     */
    public void setupTest(JavaSamplerContext javaSamplerContext) {

        inputurl = javaSamplerContext.getParameter(URLNAME,DEFAULTURL);
        System.out.println("用户输入的地址是："+inputurl);
    }

    private String result;
    /**
     * 实现具体功能逻辑的方法
     * @param javaSamplerContext
     * @return
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        StringBuffer stringBuffer = new StringBuffer();
        //模拟请求
        try {
            //创建url，从用户输入的url中获取url值
            URL url = new URL(inputurl);
            //打开链接
            URLConnection urlConnection = url.openConnection();
            //定义一个字节数组，用来存放结果信息,设置1024个字节一个数组，循环读取
            byte[] bytes = new byte[1024];

            //也可以定义char类型数组，用来存放结果信息，字节可能存放数据不全
//            char[] bytes = new char[1024];

            //事务请求开始
            sampleResult.sampleStart();
            //定义一个输入流
            InputStream inputStream = urlConnection.getInputStream();

            int len = 0;
            while ((len=inputStream.read(bytes)) != -1){
                result = new String(bytes,"UTF-8");
                stringBuffer.append(result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        result = stringBuffer.toString();

        //获取result的方式不一定要调http链接，也可以直接把开发的接口拿过来直接调用获取，然后打包后把jar包放到jmeter中去做性能，上面代码的方式与http请求没什么区别
        //自定义java请求名称，在查看结果树中能看到名称是“自定义java请求名称”
        sampleResult.setSampleLabel("自定义java请求名称");
        //设置事务结果为true，也可以设置为false
        sampleResult.setSuccessful(true);
        //设置responsedata
        sampleResult.setResponseData(result,"UTF-8");
        //设置datatype类型为TEXT
        sampleResult.setDataType(SampleResult.TEXT);
        System.out.println("runtest run");

        //事务请求结束
        sampleResult.sampleEnd();
        return sampleResult;
    }

    /**
     * 收尾方法
     * @param javaSamplerContext
     */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

        System.out.println("teardowntest");
    }


}
