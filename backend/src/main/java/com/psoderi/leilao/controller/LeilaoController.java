	package com.psoderi.leilao.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psoderi.leilao.exception.GenericException;
import com.psoderi.leilao.exception.ItemException;
import com.psoderi.leilao.exception.LanceException;
import com.psoderi.leilao.model.Item;
import com.psoderi.leilao.model.request.ItemRequest;
import com.psoderi.leilao.model.request.LanceRequest;
import com.psoderi.leilao.model.response.MessageResponse;
import com.psoderi.leilao.response.ErroResponse;
import com.psoderi.leilao.service.LeilaoService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LeilaoController {

	@Autowired
	LeilaoService leilaoService;

	@ApiOperation(value = "Verifica se o servidor está no ar")
	@RequestMapping(value = "/api/v1/", method = RequestMethod.GET)
	public ResponseEntity<Object> index() throws ParseException{
		return new ResponseEntity<Object>(new MessageResponse<String>("200", "SUCCESS", "Server is alive..."), HttpStatus.OK);		
	}
		
	@ApiOperation(value = "Recupera todos os itens")
	@RequestMapping(value = "/api/v1/itens", method = RequestMethod.GET)
	public ResponseEntity<Object> getItens(){
		return new ResponseEntity<Object>(new MessageResponse<List<Item>>("200", "Lista de todos os itens", (List<Item>) leilaoService.getItens() ), HttpStatus.OK);
	}
	

	@ApiOperation(value = "Adiciona um novo item na aplicacao")
	@RequestMapping(value = "/api/v1/item", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> criarItem(@RequestBody ItemRequest itemRequest){
		String path = "/api/v1/item";
		try{
			Item item = new Item(itemRequest.getDescricao(), itemRequest.getValorInicial(), itemRequest.getValorDefaultIncremento(), itemRequest.getDataFechamento());
			
			leilaoService.createItem(item);
			return new ResponseEntity<Object>(new MessageResponse<String>("200", "Item criado com sucesso", null ), HttpStatus.OK);
		}catch(ParseException ex){
			return new ResponseEntity<Object>(new ErroResponse(new GenericException("Data deve estar no formado 'dd/mm/yyyy hh:mm:ss'"), HttpStatus.BAD_REQUEST, path), HttpStatus.BAD_REQUEST);
		}catch(Exception ex){
			return new ResponseEntity<Object>(new ErroResponse(new GenericException(ex), HttpStatus.INTERNAL_SERVER_ERROR, path), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Da um lance em um item, se o parametro lance for nulo, o valor do lance sera o valor atual + valor incrementador default")
	@RequestMapping(value = "/api/v1/item/licitar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> darLance(@RequestBody LanceRequest lanceRequest){
		String path = "/api/v1/item/licitar";
		try{
			leilaoService.darLance(lanceRequest.getIdItem(),lanceRequest.getValorLance());
			return new ResponseEntity<Object>(new MessageResponse<String>("200", "Lance efetuado com sucesso", null ), HttpStatus.OK);
		}catch(LanceException ex){
			return new ResponseEntity<Object>(new ErroResponse(ex, HttpStatus.BAD_REQUEST, path), HttpStatus.BAD_REQUEST);
		}catch(Exception ex){
			return new ResponseEntity<Object>(new ErroResponse(new GenericException(ex), HttpStatus.INTERNAL_SERVER_ERROR, path), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@ApiOperation(value = "Recupera um item pelo id")
	@RequestMapping(value = "/api/v1/item", method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<Object> getActivationCode(@RequestParam(value="idItem", required=true) Integer idItem){
		String path = "/api/v1/item";
		
		try{
			Item item = leilaoService.getItem(idItem);
			return new ResponseEntity<Object>(new MessageResponse<Item>("200", "Item recuperado com sucesso", item), HttpStatus.OK);
			
		}catch(ItemException e){
			return new ResponseEntity<Object>(new ErroResponse(e, HttpStatus.BAD_REQUEST, path), HttpStatus.BAD_REQUEST);
		}catch(GenericException ex){
			return new ResponseEntity<Object>(new ErroResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, path), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Listar itens abertos")
	@RequestMapping(value = "/api/v1/itens/abertos", method = RequestMethod.GET)
	public ResponseEntity<Object> getItensAbertos(){
		return new ResponseEntity<Object>(new MessageResponse<List<Item>>("200", "Lista de itens abertos", (List<Item>) leilaoService.getItensAbertos() ), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar itens fechados")
	@RequestMapping(value = "/api/v1/itens/fechados", method = RequestMethod.GET)
	public ResponseEntity<Object> getItensFechados(){
		return new ResponseEntity<Object>(new MessageResponse<List<Item>>("200", "Lista de itens fechados", (List<Item>) leilaoService.getItensFechados() ), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Exclui um item do cadastro")
	@RequestMapping(value = "/api/v1/item/delete", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> excluir(@RequestBody (required=true) Item item){
		String path = "/api/v1/item";
		try{
			leilaoService.excluir(item.getId());
			return new ResponseEntity<Object>(new MessageResponse<String>("200", "Item removido com sucesso", null ), HttpStatus.OK);
		}catch(ItemException e){
			return new ResponseEntity<Object>(new ErroResponse(e, HttpStatus.BAD_REQUEST, path), HttpStatus.BAD_REQUEST);
		}catch(Exception ex){
			return new ResponseEntity<Object>(new ErroResponse(new GenericException(ex), HttpStatus.INTERNAL_SERVER_ERROR, path), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
