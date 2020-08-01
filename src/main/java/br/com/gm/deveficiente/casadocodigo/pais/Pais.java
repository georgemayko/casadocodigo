package br.com.gm.deveficiente.casadocodigo.pais;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.gm.deveficiente.casadocodigo.estado.Estado;

@Entity
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "pais")
	/**
	 * Anotation utilizada para responder ao metodo possuiEstados sem precisar carregar a lista inteira
	 * Caso seja necessario carregar a lista inteira por outra funcionalidade remover essa anotacao
	 * e verificar outra solução pois causará o problema de consulta N+1 uma vez que cada informacao é
	 * carregada sob demanda.
	 */
	@LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Estado> estados;
	
	@Deprecated
	public Pais() {	}

	public Pais(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + "]";
	}

	public boolean possuiEstados() {
		return this.estados.size() != 0;
	}
	
}
