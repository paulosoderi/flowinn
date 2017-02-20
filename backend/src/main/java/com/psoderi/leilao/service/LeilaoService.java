package com.psoderi.leilao.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psoderi.leilao.dao.ItemDAO;
import com.psoderi.leilao.enums.ErrorCode;
import com.psoderi.leilao.exception.ItemException;
import com.psoderi.leilao.exception.LanceException;
import com.psoderi.leilao.model.Item;
import com.psoderi.leilao.model.Lance;

@Service
public class LeilaoService {
	
	@Autowired
	private ItemDAO	itemDAO;
	
	public List<Item> getItens(){
		return (List<Item>) itemDAO.findAll();
	}
	
	
	public List<Item> getItensAbertos(){
		List<Item> itens = itemDAO.findAll();
		List<Item> itemRetorno = new ArrayList<Item>();
		for(Item item : itens){
			if(item.getStatus()=="Aberto"){
				itemRetorno.add(item);
			}
		}
		return itemRetorno;
	}
	
	
	
	public List<Item> getItensFechados(){
		List<Item> itens = itemDAO.findAll();
		List<Item> itemRetorno = new ArrayList<Item>();
		for(Item item : itens){
			if(item.getStatus()=="Fechado"){
				itemRetorno.add(item);
			}
		}
		return itemRetorno;
	}
	
	
	@Transactional
	public void createItem(Item item) throws Exception{
		itemDAO.createItem(item);
	}
	
	@Transactional
	public void darLance(Integer idItem, Double valor) throws Exception{

		Item item = itemDAO.recuperaItemPeloID(idItem);
		
		if(valor == null){
			item.incrementaValorAtualComDefault();
		}else{
			if(!this.validaValorLance(valor, item.getValorAtual())){
				throw new LanceException(ErrorCode.LANCE_MENOR_VALOR_ATUAL);
			}
			item.incrementaValorAtualComLance(valor);
		}
		
		
		Calendar cal = Calendar.getInstance();		
		Date agora = new Date();
		
	    cal.add(Calendar.MINUTE, -1);
	    if(agora.before(item.getDataFechamento()) && agora.after(cal.getTime())){
	    	cal.add(Calendar.MINUTE, 1);
	    	cal.add(Calendar.SECOND, 30);
	    	item.setDataFechamento(cal.getTime());
		}
		
		Lance lance = new Lance(valor);
		item.addLance(lance);
		
		itemDAO.updateItem(item);
	}
	
	public boolean validaValorLance(Double valorLance, Double valorAtual){
		return  valorLance <= valorAtual ? false : true;
	}

	public Item getItem(Integer id) {
		Item item  = itemDAO.recuperaItemPeloID(id);
		
		//verifica se o item existe
		if(item == null){
			throw new ItemException(ErrorCode.ITEM_NAO_EXISTE);
		}
		
		return item;
	}
	
	@Transactional
	public void excluir(Integer idItem) throws Exception{
		Item item  = itemDAO.recuperaItemPeloID(idItem);
		
		//verifica se o item existe
		if(item == null){
			throw new ItemException(ErrorCode.ITEM_NAO_EXISTE);
		}
		
		itemDAO.excluir(item);
	}
	
}
