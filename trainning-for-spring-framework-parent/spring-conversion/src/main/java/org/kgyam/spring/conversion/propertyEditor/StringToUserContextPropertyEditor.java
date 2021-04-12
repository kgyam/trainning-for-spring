package org.kgyam.spring.conversion.propertyEditor;

import com.kgyam.domain.User;
import com.kgyam.domain.UserContext;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * @author kg yam
 * @date 2021-04-12 11:01
 * @since
 */
public class StringToUserContextPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] arr = text.split (",");
        UserContext userContext = new UserContext ();
        userContext.setContext (arr);
        setValue (userContext);
    }


    @Override
    public String getAsText() {
        UserContext userContext = (UserContext) getValue ();
        return Arrays.toString (userContext.getContext ());
    }


}
