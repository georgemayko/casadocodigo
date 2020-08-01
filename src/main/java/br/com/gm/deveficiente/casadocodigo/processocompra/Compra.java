package br.com.gm.deveficiente.casadocodigo.processocompra;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.gm.deveficiente.casadocodigo.estado.Estado;
import br.com.gm.deveficiente.casadocodigo.pais.Pais;

public class Compra {

	private Long id;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank @Email String email;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String cidade;
	private Pais pais;
	private Estado estado;
	private @NotBlank String telefone;
	private @NotBlank String cep;


	public Compra(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull @Valid Pais pais, @NotBlank String telefone, @NotBlank String cep) {
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.email = email;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.cidade = cidade;
				this.pais = pais;
				this.telefone = telefone;
				this.cep = cep;
	}

	public Compra(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull @ Valid Estado estado, @NotBlank String telefone,
			@NotBlank String cep) {
		this(nome, sobrenome, email, documento, endereco, complemento,
				cidade, estado.getPais(), telefone, cep);
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Compra [nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade + ", pais=" + pais
				+ ", estado=" + estado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}
	
}
