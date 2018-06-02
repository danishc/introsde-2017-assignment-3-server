package introsde.assignment3.soap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum UniversityDao {
    instance;
    private EntityManagerFactory emf;

    private UniversityDao() {
        if (emf!=null) {
            emf.close();
        }
        emf = Persistence.createEntityManagerFactory("introsde_asgn31");
        
//        String dbUri = System.getenv("DATABASE_URL");
//        StringTokenizer st = new StringTokenizer(dbUri, ":@/");
//        @SuppressWarnings("unused")
//		String dbVendor = st.nextToken(); //if DATABASE_URL is set
//		String username = st.nextToken();
//      	String password = st.nextToken();
//      	String host = st.nextToken();
//      	String port = st.nextToken();
//      	String databaseName = st.nextToken();
//		String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
//
//		Map<String, String> properties = new HashMap<String, String>();
//		properties.put("javax.persistence.jdbc.url", jdbcUrl );
//		properties.put("javax.persistence.jdbc.user", username );
//		properties.put("javax.persistence.jdbc.password", password );
//		
//		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		emf = Persistence.createEntityManagerFactory("introsde_asgn31", properties);

    }

    public EntityManager createEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    
    }

    public void closeConnections(EntityManager em) {
        em.close();
    }

    public EntityTransaction getTransaction(EntityManager em) {
        return em.getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }  
}
