package org.pi4j.blinkled;


import java.util.Map;


import org.eclipse.kura.configuration.ConfigurableComponent;
import org.osgi.service.component.ComponentContext;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Blinkled implements ConfigurableComponent{
	    private GpioPinDigitalOutput pin;
	    private GpioController gpio = GpioFactory.getInstance();
	    
	    protected void activate(ComponentContext componentContext) {
	        
	       
	    }

	    protected void activate(ComponentContext componentContext, Map<String, Object> properties) throws InterruptedException {
	       
	        updated(properties);
	    }

	    protected void deactivate(ComponentContext componentContext) {
	      
	    }

	    public void updated(Map<String, Object> properties) throws InterruptedException {
	        
	    	System.out.println("updated start");
	    	System.out.println("<--Pi4J--> GPIO Control Example ... started.");
	    	int param3 = (Integer) properties.get("param3.integer");

	
	        
	        // provision gpio pin #01 as an output pin and turn on
	        if(pin== null)
	        	pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.HIGH);

	        // set shutdown state for this pin
	        pin.setShutdownOptions(true, PinState.LOW);

	        System.out.println("--> GPIO state should be: ON");

	        Thread.sleep(param3);
	        
	        // turn off gpio pin #01
	        pin.low();
	        System.out.println("--> GPIO state should be: OFF");

	        Thread.sleep(param3);

	        // toggle the current state of gpio pin #01 (should turn on)
	        pin.toggle();
	        System.out.println("--> GPIO state should be: ON");

	        Thread.sleep(param3);

	        // toggle the current state of gpio pin #01  (should turn off)
	        pin.toggle();
	        System.out.println("--> GPIO state should be: OFF");
	        
	        Thread.sleep(param3);

	        // turn on gpio pin #01 for 1 second and then off
	        System.out.println("--> GPIO state should be: ON for only 1 second");
	        pin.pulse(1000, true); // set second argument to 'true' use a blocking call
	        
	        // stop all GPIO activity/threads by shutting down the GPIO controller
	        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
	        gpio.shutdown();
	    }
	}
