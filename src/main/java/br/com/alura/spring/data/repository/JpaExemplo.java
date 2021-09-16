package br.com.alura.spring.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.spring.data.orm.Cargo;

public class JpaExemplo {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public JpaExemplo() {
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();
	}
	
	public void save(Cargo cargo) {
		em.getTransaction().begin();
		em.persist(cargo);
		em.getTransaction().commit();
		em.close();
	}
	
	public Cargo findById(Integer id) {
		em.getTransaction().begin();
		Cargo cargo = em.find(Cargo.class, id);
		em.getTransaction().commit();
		em.close();
		return cargo;
	}
	
	public void deleteById(Integer id) {
		Cargo cargo = em.find(Cargo.class, id);
		
		em.getTransaction().begin();
		em.remove(cargo);
		em.getTransaction().commit();
	}
}
