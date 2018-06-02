package introsde.assignment3.soap.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import introsde.assignment3.soap.dao.UniversityDao;


@Entity  // indicates that this class is an entity to persist in DB
@Table(name="Person") // to whate table must be persisted
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlRootElement(name="person")
public class Person implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id // defines this attributed as the one that identifies the entity 
    @GeneratedValue
    @Column(name="personId") // maps the following attribute to a column
    private int personId;
	@Column(name="lastname")
    private String lastname;
    @Column(name="name")
    private String name;
    @Column(name="birthdate")
    private String birthdate;
    @OneToMany(
    		mappedBy="person",
    		cascade=CascadeType.ALL,
    		fetch=FetchType.EAGER)
	private List<Activity> activities;
    
    public Person() {}
    
    public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "Person [idPerson=" + personId + ", lastname=" + lastname + ", name=" + name + ", birthdate=" + birthdate
				+ "]";
	}
	
	// the XmlElementWrapper defines the name of node in which the list of Activitie elements
	// will be inserted
	//@XmlElementWrapper(name="preferences")
	public List<Activity> getActivities() {
		if(this.activities==null) 
			this.activities= new LinkedList<>();
	    return this.activities;
	}
	public void setActivities(List<Activity> activities2) {
		this.activities=activities2;
		
	}
	
    public static Person getPersonById(int personId) {
        EntityManager em = UniversityDao.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
        UniversityDao.instance.closeConnections(em);
        return p;
    }

    public static List<Person> getAll() {
        EntityManager em = UniversityDao.instance.createEntityManager();
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
        UniversityDao.instance.closeConnections(em);
        return list;
    }

    public static Person savePerson(Person p){
        EntityManager em = UniversityDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        Person person = em.find(Person.class, p.getPersonId());
        UniversityDao.instance.closeConnections(em);
        return person;
    } 

    public static Person updatePerson(Person p) {
        EntityManager em = UniversityDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(p);
        tx.commit();
        Person person = em.find(Person.class, p.getPersonId());
        UniversityDao.instance.closeConnections(em);
        return person;
    }

    public static void removePerson(Person p) {
        EntityManager em = UniversityDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        for(Activity act: p.getActivities()) {
        		Activity.removeActivity(act);
        }
        em.remove(p);
        tx.commit();
        UniversityDao.instance.closeConnections(em);
    }
    
    
}