package com.yixin;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
/*
1、调SercertGenerate类然后打包在jmeter中执行测试计划
2、通过csv数据文件设置多个参数，设置多个线程，从而达到测试压测接口的目的
 */
public class testExtendThree implements JavaSamplerClient {


    private String key;
    private String sercert;
    private String method;
    private String path;
    private String gmtdate;

    private String resultdata;


    /**
     * 定义GUI图形界面的入参
     * @return
     */
    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("key","");
        params.addArgument("sercert","");
        params.addArgument("method","");
        params.addArgument("path","");
        return params;
    }


    /**
     * 初始化方法
     * @param javaSamplerContext
     */
    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {

    }

    /**
     * 最重要的执行步骤
     * @param javaSamplerContext
     * @return
     */
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        //从GUI图形界面上输入的值中可以获取到参数值
        key = javaSamplerContext.getParameter("key");
        sercert = javaSamplerContext.getParameter("sercert");
        method = javaSamplerContext.getParameter("method");
        path = javaSamplerContext.getParameter("path");

        //定义采样器结果名称
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("java request");

        try{
            //开始事务的执行
            sr.sampleStart();

            //调用测试方法
            SercertGenerate sg = new SercertGenerate();
            gmtdate = sg.getGMTDate();

            String[] strarry = {key,sercert,method,path};
            //调测试方法，获取返回结果
            resultdata = sg.generate(strarry);

            if (resultdata != null && gmtdate != null){
                sr.setResponseData("日期结果是：" + gmtdate +"\n"+ "加密结果是：" + resultdata, "UTF-8");
                sr.setDataType(SampleResult.TEXT);
            }

//            System.out.println(resultdata);
            sr.setSuccessful(true);



        }catch (Exception e){
            sr.setSuccessful(false);
            e.printStackTrace();

        }finally {
            //结束事务的执行
            sr.sampleEnd();
        }
        return sr;
    }

    /**
     * 收尾工作
     * @param javaSamplerContext
     */
    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }


}
