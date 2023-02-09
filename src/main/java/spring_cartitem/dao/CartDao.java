package spring_cartitem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring_cartitem.dto.Cart;
import spring_cartitem.dto.Item;

@Component
public class CartDao {
	@Autowired
	EntityManager entityManager;

	public void saveCart(Cart cart) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Item> items = cart.getItems();
		for (Item items1 : items) {
			entityManager.persist(items1);
		}
		entityTransaction.begin();
		entityManager.persist(cart);
		entityTransaction.commit();
	}

	public void updateCart(int id, String email) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Cart cart=entityManager.find(Cart.class, id);
		cart.setId(id);
		cart.setEmail(email);
		entityTransaction.begin();
		entityManager.merge(cart);
		entityTransaction.commit();
	}

	public void deleteCart(int id) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Cart cart = entityManager.find(Cart.class, id);
		if (cart != null) {
			entityTransaction.begin();
			entityManager.remove(cart);
			entityTransaction.commit();
		}
	}

	public Cart getCartById(int id) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Cart cart = entityManager.find(Cart.class, id);
		return cart;
	}

	public List<Cart> getAllCart() {
		Query query = entityManager.createQuery("Select c from Cart c");
		List<Cart> list = query.getResultList();
		return list;
	}

}
