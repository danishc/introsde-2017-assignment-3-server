package introsde.assignment3.soap.endpoint;

import javax.xml.ws.Endpoint;

import introsde.assignment3.soap.ws.PeopleActivityImpl;

public class PeoplePublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "6902";
	public static String BASE_URL = "/ws/people?wsdl";
	
	public static String getEndpointURL() {
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) {
		String endpointUrl = getEndpointURL();
		System.out.println("Starting People Service...");
		System.out.println("--> Published at = "+endpointUrl);
		Endpoint.publish(endpointUrl, new PeopleActivityImpl()); 
    }
}
