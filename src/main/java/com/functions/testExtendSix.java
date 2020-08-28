package com.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
实现输入两个经纬度，根据两个经纬度计算出两个经纬度之间的直线距离
 */
public class testExtendSix extends AbstractFunction {

    //定义函数名称,定义成静态的只加载一次
    private static final String key = "__CalculateLongLat";

    //获取用户输入的参数值
    public static Object[] longlatparameters;

    /**
     * 返回函数名称
     * @return
     */
    @Override
    public String getReferenceKey() {
        return key;
    }

    /**
     * 定义函数中的参数
     * @return
     */
    @Override
    public List<String> getArgumentDesc() {
        List<String> desc=new ArrayList<>();
        desc.add("输入两个经纬度格式为39.968386,116.417975,39.903024,116.688358");
        return desc;
    }

    /**
     * 从用户输入的参数中获取参数值
     * @param collection
     * @throws InvalidVariableException
     */
    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {

        //先检查用户输入的参数是否合法
        checkParameterCount(collection,4);

        //获取用户输入的参数存到数组中
        longlatparameters=collection.toArray();
    }


    /**
     * 执行操作，计算两个经纬度的距离
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        //从数组中获取参数，参数类型是CompoundVariable
        CompoundVariable value1=(CompoundVariable)longlatparameters[0];
        CompoundVariable value2=(CompoundVariable)longlatparameters[1];
        CompoundVariable value3=(CompoundVariable)longlatparameters[2];
        CompoundVariable value4=(CompoundVariable)longlatparameters[3];

//        //需要先对参数类型转换成string类型，然后再转换成double类型参与经纬度运算
        double lat1=Double.parseDouble(value1.execute());
        double lon1=Double.parseDouble(value2.execute());
        double lat2=Double.parseDouble(value3.execute());
        double lon2=Double.parseDouble(value4.execute());

        System.out.println(String.valueOf(lat1)+","+String.valueOf(lon1)+","+String.valueOf(lat2)+","+String.valueOf(lon2));

        //返回两个经纬度的直线距离
        return String.valueOf(Distance(lat1,lon1,lat2,lon2));
    }

    public static double HaverSin(double theta)
    {
        double v = Math.sin(theta / 2 );
        return v * v;
    }

    static double EARTH_RADIUS = 6371.0;//km 地球半径 平均值，千米

    /// <summary>
    /// 给定的经度1，纬度1；经度2，纬度2. 计算2个经纬度之间的距离。
    /// </summary>
    /// <param name="lat1">经度1</param>
    /// <param name="lon1">纬度1</param>
    /// <param name="lat2">经度2</param>
    /// <param name="lon2">纬度2</param>
    /// <returns>距离（公里、千米）</returns>
    public static double Distance(double lat1,double lon1, double lat2,double lon2)
    {
        //用haversine公式计算球面两点间的距离。
        //经纬度转换成弧度
        lat1 = ConvertDegreesToRadians(lat1);
        lon1 = ConvertDegreesToRadians(lon1);
        lat2 = ConvertDegreesToRadians(lat2);
        lon2 = ConvertDegreesToRadians(lon2);

        //差值
        double vLon = Math.abs(lon1 - lon2);
        double vLat = Math.abs(lat1 - lat2);

        //h is the great circle distance in radians, great circle就是一个球体上的切面，它的圆心即是球心的一个周长最大的圆。
        double h = HaverSin(vLat) + Math.cos(lat1) * Math.cos(lat2) * HaverSin(vLon);

        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));

        return distance;
    }

    /// <summary>
    /// 将角度换算为弧度。
    /// </summary>
    /// <param name="degrees">角度</param>
    /// <returns>弧度</returns>
    public static double ConvertDegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }

    public static double ConvertRadiansToDegrees(double radian)
    {
        return radian * 180.0 / Math.PI;
    }




}
