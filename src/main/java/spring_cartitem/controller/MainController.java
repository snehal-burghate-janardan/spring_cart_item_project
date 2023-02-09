package spring_cartitem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring_cartitem.dao.CartDao;

import spring_cartitem.dto.Cart;
import spring_cartitem.dto.Config;
import spring_cartitem.dto.Item;

public class MainController {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Cart cart = context.getBean("cart", Cart.class);
		Item items = context.getBean("item", Item.class);
		CartDao cartDao = context.getBean("cartDao", CartDao.class);
		boolean flag = true;
		do {
			System.out.println("1.Save Cart");
			System.out.println("2.Update Cart");
			System.out.println("3.delete Cart");
			System.out.println("4.get Cart by Id");
			System.out.println("5.Get all Cart");
			System.out.println("6.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("enter the email");
				String email = scanner.next();
				System.out.println("enter the password");
				String password = scanner.next();

				cart.setEmail(email);
				cart.setPassword(password);

				System.out.println("enter your item name");
				String name = scanner.next();
				System.out.println("enter the item cost");
				long cost = scanner.nextLong();
				System.out.println("enter your item manufacturer");
				String manuf = scanner.next();
				System.out.println("enter your item review ");
				String review = scanner.next();

				items.setName(name);
				items.setCost(cost);
				items.setManuf(manuf);
				items.setReview(review);

				List<Item> items2 = new ArrayList<Item>();
				items2.add(items);

				cart.setItems(items2);

				cartDao.saveCart(cart);
				System.out.println("sucessfully saved");

			}
				break;
			case 2: {
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				System.out.println("enter the email");
				String email = scanner.next();

				cart.setEmail(email);
				cartDao.updateCart(id, email);
				System.out.println("sucessfully updated");
			}
				break;
			case 3: {
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				cart.setId(id);
				cartDao.deleteCart(id);
				System.out.println("sucessfully deleted");
			}
				break;

			case 4: {
				System.out.println("enter the id ");
				int id = scanner.nextInt();
				System.out.println(cartDao.getCartById(id));

			}
				break;
			case 5: {
				System.out.println(cartDao.getAllCart());
			}
				break;
			case 6: {
				flag = false;
			}
			default: {
				System.out.println("Invalid password");
			}
				break;
			}

		} while (flag);
	}
}

//		cart.setEmail("a@gmail.com");
//		cart.setPassword("123");
//		
//		items1.setName("coca");
//		items1.setCost(1234);
//		items1.setManuf("coca");
//		items1.setReview("good");
//		
//		List<Item>itemset=new ArrayList<Item>();
//		itemset.add(items1);
//		
//		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
//		EntityManager entityManager=entityManagerFactory.createEntityManager();
//		EntityTransaction entityTransaction=entityManager.getTransaction();
//		entityTransaction.begin();
//		entityManager.persist(items1);
//		entityManager.persist(cart);
//		entityTransaction.commit();

//		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
//		EntityManager entityManager=entityManagerFactory.createEntityManager();
//		cart.setEmail("Asdf@gmail.com");
//		cart.setPassword("456");
//		cartDao.updateCart(cart);

//		
