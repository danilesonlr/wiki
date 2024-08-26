package br.com.innovatis.amplus.portal.db.amplus.repositories;

import br.com.innovatis.amplus.portal.controllers.view.BatchStatusView;
import br.com.innovatis.amplus.portal.controllers.view.GetLoteDetailsView;
import br.com.innovatis.amplus.portal.controllers.view.GetLotesView;
import br.com.innovatis.amplus.portal.db.amplus.entities.ApsLoteEntity;
import br.com.innovatis.amplus.portal.utils.security.TokenInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ApsLoteRepository extends JpaRepository<ApsLoteEntity, Long> {
    @Query("SELECT new br.com.innovatis.amplus.portal.controllers.view.GetLotesView( " +
            "lote.id, lote.nomeArquivo, lote.totalRegistros, lote.dataCadastro, lote.dataAgendamento, " +
            "lote.ultimaAtualizacao, " +
            "pag.valorTotal, lote.status) " +
            "FROM EmpresaProdutoEntity empresaProduto, ApsLoteEntity lote " +
            "LEFT JOIN ApsPagamentoLoteEntity pag ON pag.loteId = lote.id " +
            "WHERE lote.empresaProdutoId = empresaProduto.id " +
            "AND empresaProduto.empresa.id = :#{#tokenInfo.empresaId} " +
            "AND empresaProduto.produto.id = :#{#tokenInfo.produtoId} " +
            "AND empresaProduto.status = 'A'" +
            "AND (:id IS NULL OR lote.id = :id) " +
            "AND (:nomeArquivo IS NULL OR UPPER(lote.nomeArquivo) LIKE CONCAT('%',UPPER(:nomeArquivo),'%')) " +
            "AND (:valorTotal IS NULL OR pag.valorTotal = :valorTotal) " +
            "AND (COALESCE(:status) IS NULL OR lote.status IN :status) " +
            "AND lote.tipo = 'CARGA_CREDITO_FUNCIONARIO' " +
            "ORDER BY lote.id desc")
    Page<List<GetLotesView>> findLoteWithOptionalParams(@Param("id") Long id,
                                                        @Param("nomeArquivo") String nomeArquivo,
                                                        @Param("valorTotal") BigDecimal valorTotal,
                                                        @Param("status") List<String> status,
                                                        @Param("tokenInfo") TokenInfo tokenInfo,
                                                        Pageable pageable);

}