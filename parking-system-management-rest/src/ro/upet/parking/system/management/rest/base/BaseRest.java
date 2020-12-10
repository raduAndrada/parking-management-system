package ro.upet.parking.system.management.rest.base;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ro.upet.parking.system.management.business.api.core.BaseService;

public class BaseRest<T> {
	
	protected static final Logger LOGGER  = Logger.getLogger(BaseRest.class.getName());

	private static final String CODE_PATH = "/code/{code}";
	private static final String ID_PATH = "/id/{id}";
	private static final String LIST_PATH = "/list";
	
	protected BaseService<T> service;
	protected static final String USER_USERNAME_PATH = "/user/{username}";
	
	
	public BaseService<T> getService() {
		return service;
	}

	public void setService(BaseService<T> service) {
		this.service = service;
	}

	/**
	 * @param code unique for each entity
	 * @return the entity with the corresponding code
	 */
	@GetMapping(path = CODE_PATH)
	public ResponseEntity<T> get(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET entity by code: %s", code));
		final T entity = service.getByCode(code);
		if (entity == null) {
			LOGGER.info(String.format("entity with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(entity);
		}
	}
	
	/**
	 * @param id of the entity
	 * @return the entity with the corresponding id
	 */
	@GetMapping(path = ID_PATH)
	public ResponseEntity<T> get(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET entity by id: %s", id));
		final T entity = service.getById(id);
		if (entity == null) {
			LOGGER.info(String.format("entity with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(entity);
		}
	}


	/**
	 * @return the list with all the entities
	 */
	@GetMapping(LIST_PATH)
	public ResponseEntity<List<T>> gets() {
		LOGGER.info(String.format("REST request to GET all entities"));
		final List<T> entityList= service.getList();
		if (entityList == null) {
			LOGGER.info(String.format("No entities found"));
			return ResponseEntity.notFound().build();
		} else {
			LOGGER.info(String.format("Entities found : %s", entityList.toString()));
			return ResponseEntity.ok(entityList);
		}
	}

	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<T> post(@RequestBody final T entity) {
		LOGGER.info(String.format("REST request to POST entity : %s", entity));
		final T created;
		try {
			created = service.add(entity);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the entity : %s", entity));
			return null;
		}
		return ResponseEntity.ok(created);
	}
	
	

	
	/**
	 * 
	 * @param entity the entity to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<T> put(@RequestBody final T entity) {
		LOGGER.info(String.format("REST request to PUT entity : %s", entity));
		final T updated;
		try {
			updated = service.update(entity);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the entity: %s", entity));
			return null;
		}
		return ResponseEntity.ok(updated);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = ID_PATH)
	@Transactional
	public ResponseEntity<T> delete(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE entity with id : %s", id));
		final T deleted;
		try {
			deleted = service.removeById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the entity with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deleted);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = CODE_PATH)
	@Transactional
	public ResponseEntity<T> delete(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE entity with code : %s", code));
		final T deleted;
		try {
			deleted = service.removeByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the entity with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deleted);
	}
}
