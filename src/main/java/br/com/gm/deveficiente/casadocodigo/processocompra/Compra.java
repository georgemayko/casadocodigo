package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.gm.deveficiente.casadocodigo.cupons.Cupom;
import br.com.gm.deveficiente.casadocodigo.estado.Estado;
import br.com.gm.deveficiente.casadocodigo.pais.Pais;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank @Email String email;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String cidade;
	@ManyToOne
	private @NotNull Pais pais;
	@ManyToOne
	private Estado estado;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private @NotNull Pedido pedido;
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	@Embedded
	private CupomAplicado cupomAplicado;

	public Compra(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull @Valid Pais pais, @NotBlank String telefone, @NotBlank String cep, 
			Function<Compra, Pedido> funcaoCriadoraPedido) {
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
				this.pedido = funcaoCriadoraPedido.apply(this);
				this.status = StatusCompra.INICIADA;
	}

	public void setEstado(Estado estado) {
		Assert.notNull(this.pais, "Não deve ser possível associar um estado a Compra que não possui país");
		Assert.isTrue(estado.pertenceAPais(this.pais), "O estado informado não pertence ao país informado");
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", pais=" + pais + ", estado=" + estado + ", telefone=" + telefone + ", cep=" + cep + ", pedido="
				+ pedido + "]";
	}

	public void aplicaCupom( @Valid Cupom cupom) {
		Assert.notNull(cupom, "Para aplica um cupom, este não pode ser nulo");
		Assert.isNull(this.cupomAplicado, "Só é possível aplicar um cupom por compra");
		Assert.isTrue(cupom.valido(), "O cupom já não é mais válido de acordo com a data de validade");
		this.cupomAplicado = new CupomAplicado(cupom);
	}
	
	
	
}
