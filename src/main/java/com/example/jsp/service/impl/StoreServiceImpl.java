package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.pojo.Store;
import com.example.jsp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 橙鼠鼠
 */
@Service
public class StoreServiceImpl implements StoreService {
	private StoreManager storeManager;
	@Autowired
	public void setStoreManager (StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Override
	public void create (Store store)throws ProjectException {
		try {
			storeManager.insert(store);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(),303);
		}
	}
}
