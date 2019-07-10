package com.functions;

import com.yixin.SercertGenerate;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterVariables;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
/*
扩展开发一个随机生成手机号的函数
 */

public class testExtendFive extends AbstractFunction {
    //定义函数名称,定义成静态的只加载一次
    private static final String key = "__MobileGenerator";

    //定义函数中参数描述,只定义一个参数，是list类型的,定义成静态的只加载一次
    private static final List<String> desc = new LinkedList<String>();
    static {
        desc.add("Name of variable in which to store the result (optional)");
    }

    //定义参数用来存储用户输入的参数值
    CompoundVariable varName;

    //定义手机号的前三位
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * 返回函数名称
     * @return
     */
    @Override
    public String getReferenceKey() {
        return key;
    }

    /**
     * 设置函数中的参数描述
     * @return
     */
    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }



    /**
     * 设置参数值，并获取用户输入的参数值
     * @param collection
     * @throws InvalidVariableException
     */
    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {

        //校验参数输入的合法性
        checkParameterCount(collection,0,1);
        Object[] values = collection.toArray();

        if (values.length > 0){
            varName = (CompoundVariable) values[0];
        }else {
            varName = null;
        }
    }

    /**
     * 执行
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        //随机生成一个下标值
        int index = getNum(0,telFirst.length-1);
        String first = telFirst[index];
        //随机生成小于888的三位数，然后再加上10000，保证是5位数，再从索引1开始截取，截取后4位
        String second = String.valueOf(getNum(1,888)+10000).substring(1);
        //随机生成小于9100的三位数，然后再加上10000，保证是5位数，再从索引1开始截取，截取后4位
        String third = String.valueOf(getNum(1,9100)+10000).substring(1);
        //组成生成手机号码,加一个L代表是long长类型数据，要不然查看结果树中会报错
        String mobile = first+second+third;

        //不是特别明白是什么意思？？？注释掉也不影响功能
        if (varName != null){
            JMeterVariables variables = getVariables();
            final String varTrim = varName.execute().trim();

            if (variables != null && varTrim.length() > 0){
                variables.put(varTrim,mobile);
            }
        }

        System.out.println();
        return mobile;
    }

    //创建一个随机生成数字的方法
    private static int getNum(int start,int end){
        return (int) (Math.random()*(end - 1));

    }



}
