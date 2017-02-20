package com.psoderi.leilao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.psoderi.leilao.model.Item;

@Repository
public class ItemDAO  {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Item> findAll(){
		return manager.createQuery("select i from com.psoderi.leilao.model.Item i", com.psoderi.leilao.model.Item.class).getResultList();
	}
	
	public Item recuperaItemPeloID(Integer idItem){
		return manager.find(Item.class, idItem);
	}
	
	public Item createItem(Item item) {
		manager.persist(item);
		return item;
	}
	

	public Item updateItem(Item item) {
		manager.merge(item);
		return item;
	}
	
	public void excluir(Item item) {
		try{
			manager.remove(item);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
}
