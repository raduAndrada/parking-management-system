package ro.upet.parking.system.management.business.api.core;

import java.util.List;

/**
 * Basic functionality for all the services in the server
 * @author Andrada
 *
 * @param <T> class of the model 
 */
public interface BaseService<T> {
	
	/**
	 * @param id the id of the searched entity
	 * @return the requested object 
	 */
	 T getById(final Long id) ;
	
	/**
	 * @param Code the code of the  searched 
	 * @return the requested 
	 */
	 T getByCode(final String Code);
	
	/**
	 * @return the list of all the s
	 */
	 List<T> getList();
	
	/**
	 * @param add the entity to be added
	 * @return the added entity
	 */
	 T add(final T add );
	
	/**
	 * @param update the updated entity
	 * @return the updated entity
	 */
	 T update(final T update);
	
	/**
	 * @param Id the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws BusinessException if the  doesn't exist
	 */
	 T removeById(final Long Id) throws BusinessException;
	
	/**
	 * @param Code the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	 T removeByCode(final String Code);


	

}
