package com.psoderi.leilao.model.request;

public class LanceRequest {
	private Double valorLance;
	private Integer idItem;
	public Double getValorLance() {
		return valorLance;
	}
	public void setValorLance(Double valorLance) {
		this.valorLance = valorLance;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
}
