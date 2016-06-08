package org.eclipse.kura.example.configurable;


import java.util.Map;

import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.kura.configuration.ConfigurableComponent;

public class ConfigurableExample implements ConfigurableComponent {

    private static final Logger s_logger = LoggerFactory.getLogger(ConfigurableExample.class);
    private static final String APP_ID = "org.eclipse.kura.configurable.ConfigurableExample";
//    private Map<String, Object> properties;

    protected void activate(ComponentContext componentContext) {
    	System.out.println("activate");
      s_logger.info("Bundle " + APP_ID + " has started!");
    }

    protected void activate(ComponentContext componentContext, Map<String, Object> properties) {
        s_logger.info("Bundle " + APP_ID + " has started with config!");
        System.out.println("activate_2nd constructor");
        updated(properties);
    }

    protected void deactivate(ComponentContext componentContext) {
        s_logger.info("Bundle " + APP_ID + " has stopped!");
    }

    public void updated(Map<String, Object> properties) {
    	System.out.println("Updated");
//        this.properties = properties;
        int param3 = (Integer) properties.get("param3.integer");
        float param2 = (float) properties.get("param2.float");
        String param1 = (String) properties.get("param1.string");
        System.out.println(param3 +" "+ param2 + " "+param1 );
//        if(properties != null && !properties.isEmpty()) {
//            Iterator<Entry<String, Object>> it = properties.entrySet().iterator();
//            while(it.hasNext()) {
//                Entry<String, Object> entry = it.next();
//                s_logger.info("New property - " + entry.getKey() + " = " +
//                entry.getValue() + " of type " + entry.getValue().getClass().toString());
//                System.out.println(entry.getKey()+ "  " +entry.getValue()+ "  ");
//                System.out.println("________________________________________________ ");
//            }
//        }
    }
}