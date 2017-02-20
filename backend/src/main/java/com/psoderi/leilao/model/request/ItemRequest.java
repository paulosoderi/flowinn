package com.psoderi.leilao.model.request;

public class ItemRequest {

	private String descricao;
	private Double valorInicial;
	private Double valorDefaultIncremento;
	private String dataFechamento;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}
	public Double getValorDefaultIncremento() {
		return valorDefaultIncremento;
	}
	public void setValorDefaultIncremento(Double valorDefaultIncremento) {
		this.valorDefaultIncremento = valorDefaultIncremento;
	}
	public String getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
}
