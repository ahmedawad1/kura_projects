package org.subscribe.service;

import java.util.Map;

import org.eclipse.kura.KuraException;
import org.eclipse.kura.cloud.CloudClient;
import org.eclipse.kura.cloud.CloudClientListener;
import org.eclipse.kura.cloud.CloudService;
import org.eclipse.kura.configuration.ConfigurableComponent;

import org.eclipse.kura.message.KuraPayload;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subscribe_service implements ConfigurableComponent, CloudClientListener {
			
	private static final Logger s_logger = LoggerFactory.getLogger(Subscribe_service.class);
//	the name of the APP_ID in the subscriber should be the same as in the publisher 
	private static final String APPLICATION_ID = "App_id.ID";
	
	private static  String   PUBLISH_QOS_PROP_NAME    = "subscribe.qos";
	private   static String   PUBLISH_TOPIC_PROP_NAME  = "subscribe.semanticTopic";
	
	private CloudService                m_cloudService;
	private CloudClient      			m_cloudClient;
	private Map<String, Object>         m_properties;
	private String  APP_ID;
	
	public void setCloudService(CloudService cloudService) {
		m_cloudService = cloudService;
	}

	public void unsetCloudService(CloudService cloudService) {
		m_cloudService = null;
	}
	
	protected void activate(ComponentContext componentContext, Map<String,Object> properties) 
	{
		s_logger.info("Activating Heater...");
		System.out.println("activate");
		m_properties = properties;
		for (String s : properties.keySet()) {
			s_logger.info("Activate - "+s+": "+properties.get(s));
		}

		s_logger.info("Activating the service subscription... Done.");
	}
	
	protected void deactivate(ComponentContext componentContext) throws KuraException 
	{
		s_logger.debug("Deactivating Heater...");

		
		
		// Releasing the CloudApplicationClient
		s_logger.info("Releasing CloudApplicationClient for {}...", APPLICATION_ID);
		
//		m_cloudClient.unsubscribe(APP_ID);
		

		s_logger.debug("Deactivating service... Done.");
	}	
	
	public void updated(Map<String,Object> properties) throws KuraException
	{
		s_logger.info("Updated Heater...");
		System.out.println("updated");
		// store the properties received
		m_properties = properties;
		for (String s : properties.keySet()) {
			s_logger.info("Update - "+s+": "+properties.get(s));
		}
		// only one cloud client with one application ID
		if(m_cloudClient!=null){
			
		    m_cloudClient.release();
		
		}
		APP_ID  = (String) m_properties.get(APPLICATION_ID);
		m_cloudClient = m_cloudService.newCloudClient(APP_ID);
		m_cloudClient.addCloudClientListener(this);
		
		doSubscribe() ;
		s_logger.info("Updated Heater... Done.");
	}
	private void doSubscribe() throws KuraException 
	{	
		
		System.out.println((String) m_properties.get(PUBLISH_TOPIC_PROP_NAME));
		m_cloudClient.subscribe((String) m_properties.get(PUBLISH_TOPIC_PROP_NAME), (Integer) m_properties.get(PUBLISH_QOS_PROP_NAME));
	}
	@Override
	public void onControlMessageArrived(String deviceId, String appTopic,
			KuraPayload msg, int qos, boolean retain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageArrived(String deviceId, String appTopic,
			KuraPayload msg, int qos, boolean retain) {
		// TODO Auto-generated method stub
		System.out.println("message arrived man .. get the payload and topic "+appTopic+"  "+msg.getMetric("temperatureInternal") );
	}

	@Override
	public void onConnectionLost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionEstablished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageConfirmed(int messageId, String appTopic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessagePublished(int messageId, String appTopic) {
		// TODO Auto-generated method stub
		
	}

}
