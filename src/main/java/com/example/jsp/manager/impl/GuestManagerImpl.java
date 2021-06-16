package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.dao.GuestDao;
import com.example.jsp.manager.todao.GuestManagerToDao;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class GuestManagerImpl implements GuestManagerToDao, GuestManager {
	private GuestDao guestDao;
	private UserManager userManager;
	private AddressManager addressManager;

	@Autowired
	public void setGuestDao (GuestDao guestDao) {
		this.guestDao = guestDao;
	}

	@Autowired
	public void setAddressManager (AddressManager addressManager) {
		this.addressManager = addressManager;
	}

	@Autowired
	public void setUserManager (UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @apiNote :如果寻找到有相同内容的数据,将停止插入对应的子元素转而使用数据表中原先记录的数据,
	 * 即如果发现插入的id与原先的不一样,需要使用update而不是insert
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert (Guest target) throws SonElementNotExistExceptionOld {
		Boolean notExist = userManager.isNotExist(target.getLoginUser().getId());
		if (Boolean.TRUE.equals(notExist)) {
			throw new SonElementNotExistExceptionOld();
		}

		Integer id = guestDao.getId(target);
		if (id == null) {
			save(target);
			addressManager.dropByGuestId(target.getId());
			for (Address address : target.getAddresses()) {
				address.setGuestId(target.getId());
				int i = addressManager.insert(address);
				address.setId(i);
			}
			return target.getId();
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Integer save (Guest guest) {
		guestDao.save(guest);
		return guest.getId();
	}

	@Override
	public void delete (Integer id) {
		guestDao.delete(id);
	}

	@Override
	public void update (Guest guest) {
		guestDao.update(guest);
	}

	@Override
	public Guest select (Integer id) {
		return guestDao.selectById(id);
	}

	@Override
	public List<Guest> select () {
		return guestDao.selectAll();
	}

	@Override
	public Boolean isGuest (int userId) {
		return guestDao.findIdByLoginUser(userId) != null;
	}

	@Override
	public User findUserByUserName (String username) {
		return guestDao.findUserByUserName(username);
	}

	@Override
	public Integer selectByUserId (Integer userId) {
		return guestDao.findIdByLoginUser(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Integer id) {
		destroy(select(id));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Guest guest) {
		addressManager.dropByGuestId(guest.getId());
		delete(guest.getId());
		userManager.destroy(guest.getLoginUser().getId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer restore (Guest target) throws SonElementNotExistExceptionOld,ProjectException {
		Integer id = getId(target);
		Boolean notExist = userManager.isNotExist(target.getLoginUser().getId());
		if (Boolean.TRUE.equals(notExist)) {
			throw new SonElementNotExistExceptionOld();
		}
		final var newMap = new HashMap<>();
		final var oldMap = new HashMap<>();
		final var addressesOld = addressManager.selectByGuestId(target.getId());
		for (Address address : target.getAddresses()) {
			newMap.put(address.getAddressString(),address.getId());
		}
		for (Address address : addressesOld) {
			oldMap.put(address.getAddressString(),address.getId());
		}
		for (Address address : target.getAddresses()) {
			if(!oldMap.containsKey(address.getAddressString())){
				address.setGuestId(target.getId());
				addressManager.insert(address);
				address.setId(address.getId());
			}
		}
		for (Address address : addressesOld) {
			if(!newMap.containsKey(address.getAddressString())){
				if(addressManager.inOrder(address.getId())!=null){
					throw new ProjectException("地址正被订单引用",704);
				}else {
					addressManager.destroy(address.getId());
				}
			}
		}
		if (id == null) {
			guestDao.update(target);
			return 0;
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Integer getId (Guest target) {
		return guestDao.getId(target);
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return select(id) == null;
	}
}
