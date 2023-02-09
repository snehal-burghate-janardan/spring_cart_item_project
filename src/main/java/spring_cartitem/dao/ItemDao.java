package spring_cartitem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring_cartitem.dto.Item;

@Component
public class ItemDao {
	@Autowired
	EntityManager entityManager;

	public void saveItem(Item item) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(item);
		entityTransaction.commit();
	}

	public void updateItem(Item item) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(item);
		entityTransaction.commit();
	}

	public void deleteItem(int id) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Item item = entityManager.find(Item.class, id);
		if (item != null) {
			entityTransaction.begin();
			entityManager.remove(item);
			entityTransaction.commit();
		}
	}

	public Item getItem(int id) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Item item = entityManager.find(Item.class, id);
		return item;
	}

	public List<Item> getAllItem() {
		Query query = entityManager.createQuery("Select p from Cart p");
		List<Item> list = query.getResultList();
		return list;
	}

}
