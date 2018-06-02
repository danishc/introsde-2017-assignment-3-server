package introsde.assignment3.soap.init;

import introsde.assignment3.soap.model.*;

public class DatabaseINIT {
	public static void initializeDB(){
		
		Activity ar= setActivity("Meeting","Project meeting","Torino","2018-01-15T09:00:00.0",ActivityType.WorkMeeting,8);
		@SuppressWarnings("unused")
		Person p= setPerson("1980-06-20", "george","michael",ar);
		
		ar= setActivity("Fundraising","Fundraising event","Trento","2018-01-16T09:00:00.0",ActivityType.Culture,6);
		p= setPerson( "1992-03-19", "Fillipo","Max",ar);
		
		ar= setActivity("Hiking","Hiking in monte bianco","Torino","2018-01-17T09:00:00.0",ActivityType.Sport,3);
		p= setPerson("1995-04-21", "Franco","Charli",ar);
		
		ar= setActivity("Get Together","Alumnus Get together","Venezia","2018-01-23T09:00:00.0",ActivityType.Social,7);
		p= setPerson("1985-09-22", "Ali","Muhammad",ar);
		
		ar= setActivity("Summer School","Summer school","Milano","2018-01-30T09:00:00.0",ActivityType.Sport,9);
		p= setPerson("1987-08-23", "Hamid","Raza",ar);
	}

	private static Person setPerson(String date, String string, String string2, Activity ar){
		Person p=new Person();
		p.setBirthdate(date);
		p.setName(string);
		p.setLastname(string2);
		p.getActivities().add(ar);
		p=Person.savePerson(p);
		ar.setPerson(p);
		Activity.updateActivity(ar);
		return p;
	}

	private static Activity setActivity(String string, String string2, String string3, String date, ActivityType type, int i) {
		Activity ar= new Activity();
		ar.setName(string);
		ar.setDescription(string2);
		ar.setPlace(string3);
		ar.setStartdate(date);
		ar.setType(type);
		ar.setEvaluatione(i);
		return ar;
	}

}
