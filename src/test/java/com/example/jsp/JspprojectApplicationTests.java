package com.example.jsp;

import cn.dev33.satoken.stp.StpInterface;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.ProjectExceptionOld;
import com.example.jsp.manager.toservice.*;
import com.example.jsp.pojo.*;
import com.example.jsp.service.StoreService;
import com.example.jsp.utils.login.LoginId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class JspprojectApplicationTests {

	@Autowired
	private UserManager userManager;

	@Autowired
	private StoreManager storeManager;

	@Autowired
	private DeliverManager deliverManager;

	@Autowired
	private GuestManager guestManager;

	@Autowired
	private ProductManager productManager;

	@Autowired
	private OrderManager orderManager;

	@Autowired
	private StpInterface stpInterface;

	@Autowired
	private StoreService storeService;

	@Test
	@Transactional(rollbackFor = Exception.class)
	void contextLoads () {
		User user = new User();
		user.setUsername("cjj").setPassword("2022337");
		try {
			userManager.insert(user);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
		Deliver deliver = new Deliver();
		deliver.setName("d").setTelephone("123").setLoginUser(user);
		try {
			deliverManager.insert(deliver);
			deliverManager.select(deliver.getId());
			deliver.getLoginUser().setUsername("cjj1");
			userManager.restore(user);
			deliver.setTelephone("d1");
			System.out.println(deliver);
			deliverManager.restore(deliver);
			List<Deliver> select = deliverManager.select();
			for (Deliver deliver1 : select) {
				System.out.println(deliver1);
			}
			deliverManager.destroy(deliver);

		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	void addressGuestTest () {
		Guest guest = new Guest();
		List<Address> addresses = new LinkedList<>();
		guest.setAddresses(addresses);
		User user = new User();
		user.setUsername("user").setPassword("pwd");
		guest.setLoginUser(user).setTelephone("2022337").setName("cjj");
		Address address = new Address();
		address.setAddressString("65432A");

		guest.getAddresses().add(address);
		try {
			userManager.insert(user);
			guestManager.insert(guest);
			Guest select = guestManager.select(guest.getId());
			System.out.println(select);
			select.setTelephone("2022338");
			guestManager.restore(select);
			System.out.println("//////////////////////" + guestManager.select(select.getId()));
			guestManager.destroy(guest);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	public void productTest () {
		Product product = new Product();
		product.setName("蟹皇堡");
		product.setPrice(BigDecimal.valueOf(11.4514));
		Store store = new Store();
		store.setName("蟹堡王");
		store.setTelephone("2022337");
		store.setAddress("under sea");
		User user = new User();
		user.setUsername("cjj");
		user.setPassword("cjj");
		store.setUser(user);
		product.setStore(store);

		try {
			userManager.insert(user);
			storeManager.insert(store);
			productManager.insert(product);
			Product select = productManager.select(product.getId());
			System.out.println(select);
			select.setName("蟹堡皇");
			productManager.restore(select);
			select = productManager.select(select.getId());
			System.out.println(select);
			productManager.destroy(product);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	public void build () {
		User user = new User();
		user.setUsername("userForStore").setPassword("2022337");
		Store store = new Store().setUser(user).setTelephone("StoreTelephone").setAddress("StoreAddress").setName("StoreName");
		try {
			userManager.insert(user);
			storeManager.insert(store);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	public void GuestBuild () {
		User user = new User().setUsername("UserForGuest").setPassword("2022337");
		Guest guest = new Guest().setAddresses(new LinkedList<>()).setName("GuestName").setTelephone("2022337").setLoginUser(user);
		guest.getAddresses().add(new Address().setAddressString("海里"));
		guest.getAddresses().add(new Address().setAddressString("河里"));
		System.out.println(guest);
		try {
			userManager.insert(user);
			guestManager.insert(guest);
			System.out.println(guestManager.select(guest.getId()));
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	public void productBuild () {
		Product product = new Product();
		product.setName("productName").setStore(storeManager.select(18)).setPrice(BigDecimal.valueOf(114.514));
		try {
			productManager.insert(product);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	public void test () {
		Product product = new Product()
				.setId(1)
				.setPrice(BigDecimal.valueOf(2.23))
				.setName("cjj");
		/*推荐的setter调用方法*/
		System.out.println(product);
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	public void OrderTest () {
		Order order = new Order()
				.setGuest(guestManager.select(21))
				.setTime(LocalTime.now().toString())
				.setAddress(guestManager.select(21).getAddresses().get(0))
				.setDeliver(deliverManager.select(37))
				.setStore(storeManager.select(18))
				.setStatus(1)
				.setMessage("没问题")
				.setOrderInfos(new LinkedList<>())
				.setProductPackages(new LinkedList<>());
		try {
			orderManager.addProduct(order, 13, 1)
					.addProduct(order, 13, 2)
					.linkedInsert(order)
					.addProduct(order, 13, 1)
					.addOrderInfo(order, new OrderInfo().setMessage("Hello!!!!!!!!!!!!!!!!!!!!!"))
					.linkedRestore(order)
					.destroy(order);
			System.out.println("///////////////////" + orderManager.select(order.getId()));
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();

		}
	}


	@Test
	public void roleTest () {
		String temp = "aa,";
		stpInterface.getRoleList(new LoginId("aa"), temp);
	}

	@Test
	@Transactional(rollbackFor = Exception.class)
	void addressGuestTest2 () {
		Guest guest = new Guest();
		List<Address> addresses = new LinkedList<>();
		guest.setAddresses(addresses);
		User user = new User();
		user.setUsername("user").setPassword("pwd");
		guest.setLoginUser(user).setTelephone("2022337").setName("cjj");
		Address address = new Address();
		address.setAddressString("65432A");

		guest.getAddresses().add(address);
		try {
			userManager.insert(user);
			guestManager.insert(guest);
			Guest select = guestManager.select(guest.getId());
			System.out.println(select);
			select.setTelephone("2022338");
			guestManager.restore(select);
			System.out.println("//////////////////////" + guestManager.select(select.getId()));
			guestManager.destroy(guest);
		} catch (ProjectExceptionOld e) {
			e.printStackTrace();
		}
	}

	@Test
	public void build2 () {
		Store store = storeManager.select(18);
		try {
			storeService.addProduct(store, new Product().setPrice(new BigDecimal(1.234)).setName("HXF"));
		} catch (ProjectException e) {
			e.printStackTrace();
		}
	}
}
