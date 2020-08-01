package br.com.gm.deveficiente.casadocodigo.estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.gm.deveficiente.casadocodigo.pais.Pais;

@Entity
public class Estado {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@ManyToOne
	private Pais pais;

	@Deprecated
	public Estado() {	}
	
	public Estado(@NotBlank String nome, @Valid Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}
	
	public Pais getPais() {
		return pais;
	}
	
}
