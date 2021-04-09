package br.com.treinamento.cadastrarcliente.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.treinamento.cadastrarcliente.model.Cliente;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "'cpf' é obrigatório")
	private Long cpf;
	
	@Column(name="primeiro_nome")
	@NotBlank(message = "'primeiroNome' é obrigatório")
	private String primeiroNome;

	@Column(name="ultimo_nome")
	private String ultimoNome;

	private Double salario;
	
	private String profissao;
	
	private String sexo;
	
	@Past(message = "'dataNascimento' deve estar no passado")
	@Column(name="data_nascimento")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNascimento;

	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	
	public String getUltimoNome() {
		return ultimoNome;
	}
	
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getProfissao() {
		return profissao;
	}
	
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List <Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List <Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
