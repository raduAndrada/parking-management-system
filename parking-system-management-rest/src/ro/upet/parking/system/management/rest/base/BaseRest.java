package ro.upet.parking.system.management.rest.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.upet.parking.system.management.business.api.core.BaseService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
public class BaseRest<T> {

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
        log.info("REST request to GET entity by code: {}", code);
        final T entity = service.getByCode(code);
        if (entity == null) {
            log.info("Entity with code: {} does not exist", code);
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
        log.info("REST request to GET entity by id: {}", id);
        final T entity = service.getById(id);
        if (entity == null) {
            log.info("entity with id: {} does not exist", id);
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
        log.info("REST request to GET all entities");
        final List<T> entityList = service.getList();
        if (entityList == null) {
            log.info("No entities found");
            return ResponseEntity.notFound().build();
        } else {
            log.info("Entities found : {}", entityList.toString());
            return ResponseEntity.ok(entityList);
        }
    }

    /**
     * @param entity the entity to be added
     * @return the created entity
     */
    @PostMapping
    @Transactional
    public ResponseEntity<T> post(@RequestBody final T entity) {
        log.info("REST request to POST entity : {}", entity);
        final T created;
        try {
            created = service.add(entity);
        } catch (final Exception e) {
            log.info("Something went wrong creating the entity : {}", entity);
            return null;
        }
        return ResponseEntity.ok(created);
    }


    /**
     * @param entity the entity to be added
     * @return the created entity
     */
    @PutMapping
    @Transactional
    public ResponseEntity<T> put(@RequestBody final T entity) {
        log.info("REST request to PUT entity : {}", entity);
        final T updated;
        try {
            updated = service.update(entity);
        } catch (final Exception e) {
            log.info("Something went wrong updating the entity: {}", entity);
            return null;
        }
        return ResponseEntity.ok(updated);
    }


    /**
     * @param id the identifier for the entity to be deleted
     * @return the deleted entity
     */
    @DeleteMapping(path = ID_PATH)
    @Transactional
    public ResponseEntity<T> delete(@PathVariable final Long id) {
        log.info("REST request to DELETE entity with id : {}", id);
        final T deleted;
        try {
            deleted = service.removeById(id);
        } catch (final Exception e) {
            log.info("Something went wrong deleting the entity with the id: {}", id);
            return null;
        }
        return ResponseEntity.ok(deleted);
    }

    /**
     * @param code the unique code for the entity to be deleted
     * @return the deleted entity
     */
    @DeleteMapping(path = CODE_PATH)
    @Transactional
    public ResponseEntity<T> delete(@PathVariable final String code) {
        log.info("REST request to DELETE entity with code : {}", code);
        final T deleted;
        try {
            deleted = service.removeByCode(code);
        } catch (final Exception e) {
            log.info("Something went wrong deleting the entity with the code: {}", code);
            return null;
        }
        return ResponseEntity.ok(deleted);
    }
}
