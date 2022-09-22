package at.miriam.movierandomizer.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import at.miriam.movierandomizer.model.StreamingService;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class StreamingServiceRepository implements Repository<StreamingService> {

	@Override
	public void create(StreamingService service) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(service);
		
		transaction.commit();
		
	}

	@Override
	public Optional<StreamingService> read(long id) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		StreamingService service = em.find(StreamingService.class, id);
		
		transaction.commit();
		
		return Optional.ofNullable(service);
	}

	@Override
	public List<StreamingService> readAll() throws SQLException {
		
		TypedQuery<StreamingService> query = em.createQuery("SELECT s FROM StreamingService s", StreamingService.class);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		List<StreamingService> services = query.getResultList();
		
		transaction.commit();
		
		return services;
	}

	@Override
	public StreamingService update(StreamingService service) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		StreamingService updatedService = em.merge(service);
		
		transaction.commit();
		
		return updatedService;
	}

	@Override
	public void delete(StreamingService service) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.remove(service);
		
		transaction.commit();
		
		
	}

	@Override
	public void deleteAll() throws SQLException {
		
		TypedQuery<StreamingService> query = em.createNamedQuery("SELECT s FROM StreamingService s", StreamingService.class);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		List<StreamingService> serviceToDelete = query.getResultList();
		
		for (StreamingService service : serviceToDelete) {
			
			em.remove(service);
			
		}
		
		transaction.commit();
	}

	@Override
	public void deleteWithID(long id) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		StreamingService serviceToDelete = em.find(StreamingService.class, id);
		
		em.remove(serviceToDelete);
		
		transaction.commit();
	}
	
	

}
