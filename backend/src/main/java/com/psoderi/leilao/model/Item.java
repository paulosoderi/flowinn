package com.psoderi.leilao.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity(name = "item")
public class Item {
	
	@Id
	@Column(name = "id_item")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	@Column(name = "des_item")
	private String descricao;
	
	@Column(name = "val_inicial")
	private Double valorInicial;
	
	@Column(name = "val_atual")
	private Double valorAtual;
	
	@Column(name = "val_incremento")
	private Double valorDefaultIncremento;

	@Column(name="data_fechamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@Column(name="data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Transient
	private String status;
	
	@OneToMany(mappedBy = "item", targetEntity = Lance.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lance> lances;
	
	public Item(){
		super();
	}
	
	public Item(String descricao, Double valorInicial, Double valorDefaultIncremento, String dataFechamento) throws ParseException{
		super();
		this.descricao = descricao;	
		this.dataCriacao = new Date();
		this.valorInicial = valorInicial;
		this.valorAtual = this.valorInicial;
		this.valorDefaultIncremento = valorDefaultIncremento;
		
		SimpleDateFormat formdata= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		this.dataFechamento = formdata.parse(dataFechamento);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getStatus() {
		return this.dataFechamento.after(new Date()) ? "Aberto" : "Fechado";
	}


	public List<Lance> getLances() {
		if(lances == null)
			lances = new LinkedList<Lance>();
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}
	
	public void incrementaValorAtualComDefault(){
		this.valorAtual = this.valorAtual + this.valorDefaultIncremento;
	}
	
	public void incrementaValorAtualComLance(Double valorLance){
		this.valorAtual = valorLance;
	}
	
	public void addLance(Lance lance){
		this.getLances().add(lance);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}