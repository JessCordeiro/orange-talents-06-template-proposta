package com.OrangeTalents.Proposta.proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OrangeTalents.Proposta.status.StatusProposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	boolean existsByDocumento(String documento);
	
	List<Proposta> findByStatus(PropostaStatus status);
}
