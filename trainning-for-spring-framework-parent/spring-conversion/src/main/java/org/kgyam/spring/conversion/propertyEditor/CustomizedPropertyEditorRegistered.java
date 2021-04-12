package org.kgyam.spring.conversion.propertyEditor;

import com.kgyam.domain.User;
import com.kgyam.domain.UserContext;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义PropertyEditor注册器，需要将其加载到容器中并在回调方法中针对对应的类做类型转换
 */
public class CustomizedPropertyEditorRegistered implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor (UserContext.class, new StringToUserContextPropertyEditor ());
    }
}
