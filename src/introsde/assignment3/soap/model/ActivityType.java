package introsde.assignment3.soap.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="activity_Type")
public enum ActivityType implements Serializable {
	Sport("Sport"),
	Social("Social"),
	Culture("Culture"),
	WorkMeeting("WorkMeeting");
	
	private String name;
	
	ActivityType(String name) {
		name=this.name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Override
	 public String toString() {
	    return name;
	 }
}
