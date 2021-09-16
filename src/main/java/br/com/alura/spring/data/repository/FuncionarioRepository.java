package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioDto;
import br.com.alura.spring.data.orm.FuncionarioProjecao;


//JpaSpecificationExecutor eh para fazer consultas dinamicas
//ver br.com.alura.spring.data.specification.SpecificationFuncionario
@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
		JpaSpecificationExecutor<Funcionario> {

	List<Funcionario> findByNome(String nome);

	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	
	//esse é análogo ao metodo acima, mas com retorno de classe e nao interface
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioDto> findFuncionarioSalarioComProjecaoClasse();
	
	
	
	
	
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	//String nome = "%maria%";
	List<Funcionario> findByNomeLike(String nome);
	
	List<Funcionario> findByNomeEndingWith(String nome);
	
	List<Funcionario> findByNomeStartingWith(String nome);
	
	List<Funcionario> findByNomeIsNull();
	
	List<Funcionario> findByNomeIsNotNull();
	
	List<Funcionario> findByNomeOrderByNomeAsc(String nome);
	
	
	
	
	
	List<Funcionario> findByCargoDescricao(String descricao);
	
	//esse é análogo ao metodo acima
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> findByCargoPelaDescricao(String descricao);
	
	//precisa underline porque UnidadeTrabalhos sao 2 palavras
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	//esse é análogo ao metodo acima
	@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadeTrabalhosDescricao(String descricao);
}