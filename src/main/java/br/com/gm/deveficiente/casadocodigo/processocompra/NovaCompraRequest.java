package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.gm.deveficiente.casadocodigo.estado.Estado;
import br.com.gm.deveficiente.casadocodigo.pais.Pais;
import br.com.gm.deveficiente.casadocodigo.validator.CPForCNPJ;
import br.com.gm.deveficiente.casadocodigo.validator.ExistId;
//5 ou 7 se contar as annotations
public class NovaCompraRequest {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	@CPForCNPJ //1
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistId(domainClass = Pais.class) //1
	private Long paisId;
	@ExistId(domainClass = Estado.class, isRequired = false)
	private Long estadoId;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	
	public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long estadoId, @NotBlank String telefone,
			@NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.paisId = paisId;
		this.estadoId = estadoId;
		this.telefone = telefone;
		this.cep = cep;
	}
	
	public Long getEstadoId() {
		return estadoId;
	}
	
	public Long getPaisId() {
		return paisId;
	}

	//5
	public Compra toModel(EntityManager entityManager) {
		Compra compra;
		if(this.estadoId != null) {
			Estado estado = entityManager.find(Estado.class, this.estadoId);
			Assert.state(estado != null ,"Estado não encontrado para o id: " + this.estadoId);
			compra = new Compra(this.nome, this.sobrenome, this.email, this.documento, this.endereco,
							this.complemento, this.cidade, estado, telefone, cep);
		} else {
			Pais pais = entityManager.find(Pais.class, this.paisId);
			Assert.state(pais != null, "Pais não encontrado para o id: " + this.paisId);
			Assert.isTrue(!pais.possuiEstados(), "O pais tem estados, mas não foi indicado o estado na requisicao");
			compra  = new Compra(this.nome, this.sobrenome, this.email, this.documento, this.endereco,
							this.complemento, this.cidade, pais, telefone, cep);
		}
		return compra;
	}
	
}
