package br.com.gm.deveficiente.casadocodigo.processocompra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gm.deveficiente.casadocodigo.cupons.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

	Optional<Cupom> findByCodigo(String codigo);
}
