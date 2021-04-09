package br.com.treinamento.cadastrarcliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.cadastrarcliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}