package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.DeliverManager;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class DeliverServiceImpl implements DeliverService {
	DeliverManager deliverManager;

	@Autowired
	public void setDeliverManager (DeliverManager deliverManager) {
		this.deliverManager = deliverManager;
	}

	@Override
	public void create (Deliver deliver) throws ProjectException {
		try {
			deliverManager.insert(deliver);
		} catch (SonElementNotExistExceptionOld e) {
			throw new ProjectException(e.toString(), 302);
		}
	}

	@Override
	public void delete (Integer deliverId) throws ProjectException {
		deliverManager.destroy(deliverId);
	}

	@Override
	public void delete (Deliver deliver) throws ProjectException {
		deliverManager.destroy(deliver);
	}

	@Override
	public void restore (Deliver deliver) throws ProjectException {
		try {
			deliverManager.restore(deliver);
		} catch (SonElementNotExistExceptionOld e) {
			throw new ProjectException(e.toString(), 302);
		}
	}

	@Override
	public Deliver select (Integer id) throws ProjectException {
		return deliverManager.select(id);
	}

	@Override
	public List<Deliver> select () throws ProjectException {
		return deliverManager.select();
	}
}
