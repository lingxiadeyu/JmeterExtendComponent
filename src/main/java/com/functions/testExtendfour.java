package com.functions;
/*
扩展开发函数助手
 */

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class testExtendfour extends AbstractFunction {

    //定义参数,因为不确定是什么类型和几个参数，所以用Object[]来定义
    private Object[] values;

    //定义CompoundVariable类型的参数，用来存储用户输入的参数值
    private CompoundVariable firstvalue,secondvalue;

    /**
     * 从用户输入的参数中获取参数值
     * @param collection
     * @throws InvalidVariableException
     */
    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {

        //检查函数中的参数是否合法
        checkParameterCount(collection,2);

        //集合数组赋值给values，返回类型就是object[]，所以定义object[]类型的values
        values = collection.toArray();

        System.out.println("setParameters run");
    }

    /**
     * 设置函数助手中函数名称
     * @return
     */
    @Override
    public String getReferenceKey() {

        System.out.println("getReferenceKey run");
        return "__MyDemoPlus";
    }


    /**
     * 设置函数中的参数名称
     * @return
     */
    @Override
    public List<String> getArgumentDesc() {
        System.out.println("getArgumentDesc run");
        List desc = new ArrayList();
        desc.add("第一个参数");
        desc.add("第二个参数");
        return desc;
    }

    /**
     * 在Beanshell中执行函数
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("execute run");

        //把values数组中的第一个值付给firstvalue参数，要强转下类型
        firstvalue = (CompoundVariable) values[0];
        secondvalue = (CompoundVariable) values[1];

        System.out.println("两个参数的值分别为"+firstvalue+"  "+secondvalue);
        //两个数相加
        int count = new Integer(firstvalue.execute().trim())+new Integer(secondvalue.execute().trim());
        System.out.println("两数相加结果"+count);
        return String.valueOf(count);
    }



}
