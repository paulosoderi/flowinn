package com.psoderi.leilao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "lance")
public class Lance {
	
	@Id
	@Column(name = "id_lance")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	@Column(name = "val_lance")
	private Double valorLance;
	
	@Column(name="data_lance")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLance;
		
	@ManyToOne
	@JoinColumn(name="id_item")
	private Item item;
	
	public Lance(){
		super();
	}
	
	public Lance(Double valorLance){
		this.dataLance = new Date();
		this.valorLance = valorLance;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorLance() {
		return valorLance;
	}

	public void setValorLance(Double valorLance) {
		this.valorLance = valorLance;
	}

	public Date getDataLance() {
		return dataLance;
	}

	public void setDataLance(Date dataLance) {
		this.dataLance = dataLance;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}