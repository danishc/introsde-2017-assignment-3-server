package introsde.assignment3.soap.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import introsde.assignment3.soap.model.*;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface PeopleActivity {
	@WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") Long id);
	
	@WebMethod(operationName="readPeopleList")
    @WebResult(name="people") 
    public List<Person> getPeople();
	
	@WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") Person person);
	
	@WebMethod(operationName="addPerson")
    @WebResult(name="person") 
    public Person addPerson(@WebParam(name="person") Person person);
	
	@WebMethod(operationName="deletePerson")
    @WebResult(name="result") 
    public int deletePerson(@WebParam(name="personId") Long id);
	
	@WebMethod(operationName="readPersonActivityByType")
    @WebResult(name="Activities") 
    public List<Activity> readPersonActivitiesByType(@WebParam(name="personId") Long id,@WebParam(name="activity_type") ActivityType activity_type);
	
	@WebMethod(operationName="readAllActivity")
    @WebResult(name="Activities")
	public List<Activity> readAllActivities();
	
	@WebMethod(operationName="readPersonActivityByID") 
	@WebResult(name="Activities")
	public Activity readPersonActivitiesByID(@WebParam(name="personId") Long id,@WebParam(name="activityId") Long activityId);

	@WebMethod(operationName="savePersonActivity") 
	@WebResult(name="void")
	public void savePersonActivity(@WebParam(name="personId") Long id,@WebParam(name="activity") Activity activity);

	@WebMethod(operationName="updatePersonActivity") 
	@WebResult(name="activity")
	public Activity updatePersonActivity(@WebParam(name="personId") Long id,@WebParam(name="activity") Activity activity);
	
	@WebMethod(operationName="evaluatePersonActivity") 
	@WebResult(name="activity")
	public Activity evaluatePersonActivity(@WebParam(name="personId") Long id,@WebParam(name="activity") Activity activity,@WebParam(name="evaluation") int value);
	
	@WebMethod(operationName="bestPersonActivity") 
	@WebResult(name="activity")
	public List<Activity> getBestPersonActivity(@WebParam(name="personId") Long id);
}
