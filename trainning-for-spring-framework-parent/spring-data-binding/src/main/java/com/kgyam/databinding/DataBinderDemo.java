package com.kgyam.databinding;

import com.kgyam.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kg yam
 * @date 2021-04-01 16:18
 * @since
 */
public class DataBinderDemo {

    public static void main(String[] args) {
        User user = new User ();
        DataBinder dataBinder = new DataBinder (user, "binding-user");
        //构建PropertyValues,即配置元数据
        PropertyValues mpv = bulidPropertyValues ();

        /*设置是否忽略未知属性，默认是ture。
        如果将这个设置为false，设置未知属性会抛出org.springframework.beans.NotWritablePropertyException
         */
        //dataBinder.setIgnoreUnknownFields (true);

        /*
        设置是否忽略非法属性，默认是false。

        如果设置了true，而setAutoGrowNestedPaths设置了false。本来如果会抛出的异常会被吞掉
        即此选项的功能可忽略目标对象图不存在的部分中嵌套对象的绑定参数
         */
        dataBinder.setIgnoreInvalidFields (true);

        /*
        设置是否自动生成嵌套路径，默认是true
        如果true,spring framework会自动生成内嵌对象并将值设置
        如果false,spring framework不会生成内嵌对象，如果自身代码没有set对象会抛出org.springframework.beans.NullValueInNestedPathException
        (setIgnoreInvalidFields设置了true的话不会抛出异常)
         */
        //dataBinder.setAutoGrowNestedPaths (false);


        /*
        设置必填属性，如果这里的PropertyValues缺失必填属性,程序不会抛出异常。
        具体的错误信息会在BindingResult中体现
         */
        dataBinder.setRequiredFields ("name", "age", "city");

        //绑定并赋值到对应的bean对象中
        dataBinder.bind (mpv);
        BindingResult bindingResult = dataBinder.getBindingResult ();
        System.out.println (bindingResult);
        System.out.println (user);
    }


    private static PropertyValues bulidPropertyValues() {
        Map<String, String> propertyValue = new HashMap<> ();
        propertyValue.put ("name", "dalipapa");
        propertyValue.put ("age", "30");
        propertyValue.put ("cost", "10000");
        propertyValue.put ("company.name", "tencent");
        MutablePropertyValues mpv = new MutablePropertyValues (propertyValue);
        return mpv;
    }
}
