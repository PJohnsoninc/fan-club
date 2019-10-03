package controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Person;

public class PersonHelper {

	static	EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("FanClub");
	
	public	void insertPerson(Person p) {
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Person> showAllPeople() {
		EntityManager em =	emfactory.createEntityManager();
		
		List<Person> allPeople	= 
				em.createQuery("SELECT i FROM Person i").getResultList();
		
		return	allPeople;
	}
	
	public	void	deletePerson(Person	toDelete)	{
		EntityManager	em	=	emfactory.createEntityManager();
		
		em.getTransaction().begin();
		
		TypedQuery<Person> typedQuery	= em.createQuery("select i from Person i "
				+ "where i.firstName =	:selectedFirstName	and	i.lastName	= :selectedLastName"
				+ " and i.email = :selectedEmail", 
				Person.class);
		
		//Substitute	parameter	with	actual	data	from	the	toDelete	item
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedLastName",	toDelete.getLastName());
		typedQuery.setParameter("selectedEmail",	toDelete.getEmail());
		
		//we	only	want	one	result
		typedQuery.setMaxResults(1);
		
		//get	the	result	and	save it	into	a new list item
		Person	result	=	typedQuery.getSingleResult();
		
		//remove	it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public	Person searchForPersonById(int	id)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Person	found	=	em.find(Person.class,	id);
		em.close();
		
		return	found;
	}
	
	public	void	updatePerson(Person	toEdit)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public	List<Person>	searchPersonByName(String firstName, String lastName)	{
		EntityManager	em	=	emfactory.createEntityManager();
		
		em.getTransaction().begin();
		
		TypedQuery<Person>	typedQuery	=	
				em.createQuery("select	i	from Person i	"
						+ "where i.firstName =	:selectedFirstName and "
						+ "i.lastName = :selectedLastName",	Person.class);
				
		typedQuery.setParameter("selectedFirstName", firstName);
		typedQuery.setParameter("selectedLastName", lastName);
		List<Person>	foundPeople	=	typedQuery.getResultList();
		
		em.close();
		
		return	foundPeople;
	}
	
	public	List<Person>	searchForPersonByEmail(String	email) {
		EntityManager em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Person> typedQuery	= 
				em.createQuery("select	i	from Person i	"
						+ "where i.email =	:selectedEmail",	Person.class);
		
		
		typedQuery.setParameter("selectedEmail",	email);
		List<Person>	foundPeople	=	typedQuery.getResultList();
		
		em.close();
		
		return	foundPeople;
	}
	
	public	void	cleanUp(){
		emfactory.close();
	}
}
