package com.yixin;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
/*
在jmeter的控制台中输出“hello jmeter”

1、打jar包 mvn clean package
2、到target目录下获取jar包放到\apache-jmeter-4.0\lib\ext目录下
3、重启jmeter
4、建立java请求，在classname中找到该类名，执行就可以了
 */
public class testExtend extends AbstractJavaSamplerClient {

    public SampleResult runTest(JavaSamplerContext javaSamplerContext){
        System.out.println("hello jmeter");
        return null;

    }


}
