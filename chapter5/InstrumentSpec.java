package chapter5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstrumentSpec {

    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if(properties == null) {
            this.properties = new HashMap<String, Object>();
        } else {
            this.properties = new HashMap<String, Object>(properties);
        }
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean matches(InstrumentSpec otherSpec) {
       
       for(Iterator<String> i = otherSpec.getProperties().keySet().iterator(); i.hasNext(); ) {
           String propertyName = (String) i.next();
           if(!properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
               return false;
           }
       }
       return true;
    }
}
