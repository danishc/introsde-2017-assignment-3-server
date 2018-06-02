package introsde.assignment3.soap.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.assignment3.soap.dao.UniversityDao;

@Entity  // indicates that this class is an entity to persist in DB
@Table(name="Activity") // to what table must be persisted
@NamedQuery(name="Activity.findAll", query="SELECT p FROM Activity p")
@XmlRootElement(name="activity")
public class Activity implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id // defines this attributed as the one that identifies the entity
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="idActivity") // maps the following attribute to a column
    //@XmlAttribute
    private int idActivity;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="place")
    private String place;
    @Column(name="type")
    private ActivityType type;
	@Column(name="startdate")
    private String startdate;
    @Column(name="evaluationes")
    private int evaluatione;
    @ManyToOne
	@JoinColumn(name="personId",referencedColumnName="personId")
	private Person person;
    
    @XmlTransient
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Activity() {}
    
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public int getEvaluatione() {
		return evaluatione;
	}
	public void setEvaluatione(int value) {
		this.evaluatione = value;
	}
	@Override
	public String toString() {
		return "Activity [idActivity=" + idActivity + ", name=" + name + ", description=" + description + ", place="
				+ place + ", type=" + type + ", startdate=" + startdate + "]";
	}
	
	
	public static Activity saveActivity(Activity ac) {
        EntityManager em = UniversityDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(ac);
        tx.commit();
        UniversityDao.instance.closeConnections(em);
        return ac;
    }
	
	public static Activity getActivityById(int activityId) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		Activity p = em.find(Activity.class, activityId);
		UniversityDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Activity> getAll() {
		EntityManager em = UniversityDao.instance.createEntityManager();
	    List<Activity> list = em.createNamedQuery("Activity.findAll", Activity.class)
	    		.getResultList();
	    UniversityDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Activity updateActivity(Activity p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    UniversityDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeActivity(Activity p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    UniversityDao.instance.closeConnections(em);
	}
    
    
}
