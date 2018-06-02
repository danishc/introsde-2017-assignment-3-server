package introsde.assignment3.soap.ws;

import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import static java.lang.Math.toIntExact;

import introsde.assignment3.soap.init.DatabaseINIT;
import introsde.assignment3.soap.model.Activity;
import introsde.assignment3.soap.model.ActivityType;
import introsde.assignment3.soap.model.Person;

@WebService(endpointInterface = "introsde.assignment3.soap.ws.PeopleActivity")

public class PeopleActivityImpl implements PeopleActivity{
	public PeopleActivityImpl() {
		DatabaseINIT.initializeDB();
	}

	@Override
	public Person readPerson(Long id) {
		Person person = this.getPersonById(toIntExact(id));
        if (person == null)
            throw new RuntimeException("Get: Person with " + id + " not found");
        return person;
	}

	@Override
	public List<Person> getPeople() {
		System.out.println("Getting list of people...");
        List<Person> people = Person.getAll();
        return people;
	}

	@Override
	public Person updatePerson(Person person) {
		Person.updatePerson(person);
		return person;
	}

	@Override
	public Person addPerson(Person person) {
		System.out.println("Creating new person...");            
        return Person.savePerson(person);
	}

	@Override
	public int deletePerson(Long id) {
		Person p = Person.getPersonById(toIntExact(id));
        if (p!=null) {
            Person.removePerson(p);
            return toIntExact(id);
        } else {
            return -1;
        }
	}

	@Override
	public List<Activity> readPersonActivitiesByType(Long id, ActivityType activity_type) {
		List<Activity> tmp = new LinkedList<>();
		for(Activity activity :getPersonById(toIntExact(id)).getActivities()) {
			if(activity.getType().equals(activity_type) && activity.getType()!=null) {
				tmp.add(activity);
			}
		}
		if (tmp.isEmpty()) {
			System.out.println("no activity or person found with same data");
		}
		return tmp;
	}

	@Override
	public List<Activity> readAllActivities() {
		return Activity.getAll();
	}

	@Override
	public Activity readPersonActivitiesByID(Long id, Long activityId) {
		//List<Activity> act = getPersonById(toIntExact(id)).getActivities();
		for(Activity act: getPersonById(toIntExact(id)).getActivities()) {
			if(act.getIdActivity() == activityId)
				return Activity.getActivityById(toIntExact(activityId));
		}
		return null;
	}

	@Override
	public void savePersonActivity(Long id, Activity activity) {
		Person p =getPersonById(toIntExact(id));
		List<Activity> act=p.getActivities();
		act.add(activity);
		p.setActivities(act);
		Person.updatePerson(p);
	}

	@Override
	public Activity updatePersonActivity(Long id, Activity activity) {
		//Activity exixtingActivity= null;
        for(Activity act: getPersonById(toIntExact(id)).getActivities()) {
			if(act.getIdActivity() == activity.getIdActivity()) {
				
	            	Activity.updateActivity(activity);
	            //Person.updatePerson(Person.getPersonById(toIntExact(id)));
	            	return Activity.getActivityById(activity.getIdActivity());
			}
		}

       return null;
        
	}
	
	public Person getPersonById(int personId) {
        System.out.println("Reading person from DB with id: "+personId);
        Person person = Person.getPersonById(personId);
        System.out.println("Person: "+person.toString());
        return person;
    }

	@Override
	public Activity evaluatePersonActivity(Long id, Activity activity, int value) {
		for(Activity act: getPersonById(toIntExact(id)).getActivities()) {
			if(act.getIdActivity() == activity.getIdActivity()) {
				Activity actv= Activity.getActivityById(act.getIdActivity());
				actv.setEvaluatione(value);
				Activity.updateActivity(actv);
				return Activity.getActivityById(actv.getIdActivity());
			}
		}
		return null;
	}

	@Override
	public List<Activity> getBestPersonActivity(Long id) {
		List<Activity> ret= new LinkedList<>();
		int max=0;
		for(Activity act: getPersonById(toIntExact(id)).getActivities()) {
			if(act.getEvaluatione()>max) {
				max=act.getEvaluatione();
			}
		}
		for(Activity act: getPersonById(toIntExact(id)).getActivities()) {
			if(act.getEvaluatione()==max) {
				ret.add(act);
			}
		}
		return ret;
	}


}
