package org.eclipse.kura.example.configurable;



import java.util.Map;

import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.kura.configuration.ConfigurableComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ConfigurableExample implements ConfigurableComponent {
	
    private static final Logger s_logger = LoggerFactory.getLogger(ConfigurableExample.class);
    private static final String APP_ID = "org.eclipse.kura.configurable.ConfigurableExample";
    private GpioController gpio;
    GpioPinDigitalOutput pin;


    protected void activate() {
    	   System.out.println("started");
    	
    }


    protected void deactivate(ComponentContext componentContext)  {
        s_logger.info("Bundle " + APP_ID + " has stopped!");
    }

    public void updated(Map<String, Object> properties) throws InterruptedException {
    	System.out.println("Updated");
//        this.properties = properties;
        int param3 = (Integer) properties.get("param3.integer");
        float param2 = (float) properties.get("param2.float");
        String param1 = (String) properties.get("param1.string");
        System.out.println(param3 +" "+ param2 + " "+param1 );
        gpio = GpioFactory.getInstance();
        if(pin==null)
   	     pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");
//   	    System.out.println("--> GPIO state should be: ON");

//        Thread.sleep(param3);
        pin.blink(param3);
        // turn off gpio pin #01
//        pin.high();
//        System.out.println("--> GPIO state should be: OFF");
//
//        Thread.sleep(param3);
//
//        // toggle the current state of gpio pin #01 (should turn on)
//        pin.low();
//       
//        
//        // stop all GPIO activity/threads by shutting down the GPIO controller
//        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
//        gpio.shutdown();
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