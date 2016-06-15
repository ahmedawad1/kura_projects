package org.publish.sub;


import java.util.Map;



import org.eclipse.kura.KuraException;


import org.eclipse.kura.data.DataService;
import org.eclipse.kura.data.DataServiceListener;


import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentException;


public class test implements DataServiceListener {
	public DataService dataService;

	
	
	
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
        onConnectionEstablished();
    }
    
    public void unsetDataService(DataService dataService) {
        this.dataService = null;
    }
    
    
    protected void activate(ComponentContext componentContext, Map<String,Object> properties) 
	{
		
		System.out.println("activate");
		
		
		// get the mqtt client for this application
		try  {
			
			// Acquire a Cloud Application Client for this Application 
			
		}
		catch (Exception e) {
			
			throw new ComponentException(e);
		}
		
	}
    protected void deactivate(ComponentContext componentContext) 
	{
		
	}	
    public void updated(Map<String,Object> properties)
	{
		
	}
	@Override
	public void onConnectionEstablished() {
		// TODO Auto-generated method stub
		String topic = "data";
        System.out.println("connection has been established");
        try {
            dataService.subscribe(topic, 0);//egmkey/device2/cmd/PING
            System.out.println("subscription done to topic" + topic);
        } catch (KuraException e) {
            System.out.println("failed to subscribe: " + e);
        }
	}

	@Override
	public void onDisconnecting() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageArrived(String topic, byte[] payload, int qos,
			boolean retained) {
		 System.out.println("message received!!!!!!!!!!!!!!!!!!!!!!:" + topic + "  "+ payload);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessagePublished(int messageId, String topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageConfirmed(int messageId, String topic) {
		// TODO Auto-generated method stub
		
	}

	
	
}

