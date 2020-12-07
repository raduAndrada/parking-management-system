package ro.upet.parking.system.management.business.api.core;

import java.util.List;


public interface BaseService<T> {
	
	/**
	 * @param id the id of the searched entity
	 * @return the requested object 
	 */
	public T getById(final Long id) ;
	
	/**
	 * @param Code the code of the  searched 
	 * @return the requested 
	 */
	public T getByCode(final String Code);
	
	/**
	 * @return the list of all the s
	 */
	public List<T> getList();
	
	/**
	 * @param  the entity to be added
	 * @return the added entity
	 */
	public T add(final T add );
	
	/**
	 * @param  the updated entity
	 * @return the updated entity
	 */
	public T update(final T update);
	
	/**
	 * @param Id the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws BusinessException if the  doesn't exist
	 */
	public T removeById(final Long Id) throws BusinessException;
	
	/**
	 * @param Code the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public T removeByCode(final String Code);


	

}
