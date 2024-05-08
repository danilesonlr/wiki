package projuris.customchanges;


import com.fasterxml.jackson.core.JsonProcessingException;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import projuris.setup.liquibase.customchanges.UpdateFiedDataEvento;
import java.sql.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateFiedDataEventoUnitTest {

  public static final long ID = 1L;
  Database mockedDatabase;
  JdbcConnection mockedConnection;
  UpdateFiedDataEvento updateFiedDataEvento;
  Statement mockStatement;
  ResultSet resultSetMock;

  @BeforeEach
  void setup() {
    mockedDatabase = Mockito.mock(Database.class);
    mockedConnection = Mockito.mock(JdbcConnection.class);
    mockStatement = Mockito.mock(Statement.class);
    resultSetMock = Mockito.mock(ResultSet.class);
    updateFiedDataEvento = Mockito.spy(new UpdateFiedDataEvento());
  }
  @Test
  void shouldUpdateFilterTable() throws SQLException {
    UpdateFiedDataEvento.TableInfo portletUsuarioTable = getTable();
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    updateFiedDataEvento.updateFilterTable(portletUsuarioTable, mockStatement, ID, "TESTE_FILTRO", "TESTE_QUEY");
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals(getUpdateTableFilter(), updateBackup);
  }

  @Test
  void shouldchangeJsonDataFieldToInicialDataTablesFilter() throws JsonProcessingException {
    UpdateFiedDataEvento.TableInfo portletUsuarioTable = getTable();
    String json = "[{\"id\":1,\"field\":{\"id\":\"eventoContrato.lastModifiedDate\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"lastModifiedDate\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"INSTANT\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.lastModifiedDate\",\"size\":null,\"enumId\":null,\"mask\":\"DATE_FORMAT3\",\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"DATETIME\",\"restrictedBy\":\"lastModifiedDate\"},\"name\":\"lastModifiedDate\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.lastModifiedDate\"},\"label\":\"Data de atualização\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.lastModifiedDate\"]}, {\"id\":2,\"field\":{\"id\":\"eventoContrato.data\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"data\",\"type\":\"NATIVE\",\"required\":true,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"LOCALDATE\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.data\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"DATE\",\"restrictedBy\":\"data\"},\"name\":\"data\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.data\"},\"label\":\"Data início\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.dataInicial\"]}, {\"id\":3,\"field\":{\"id\":\"user.name\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"name\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"user\",\"simpleFieldType\":\"STRING\",\"classNameDomain\":\"projuris.domain.UserAudit\",\"translationLabel\":\"user.name\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"user\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"TEXT\",\"restrictedBy\":\"name\"},\"name\":\"name\",\"parentReference\":\"createdBy\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.domain.UserAudit\",\"translationLabel\":\"user.name\"},\"label\":\"Nome\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.createdBy\",\"user.name\"]}, {\"id\":4,\"field\":{\"id\":\"eventoContrato.hora\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"hora\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"LOCALTIME\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.hora\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"TIME\",\"restrictedBy\":\"horaInicial\"},\"name\":\"hora\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.hora\"},\"label\":\"Hora\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.hora\"]}]\n";
    String jsonExpected = "[{\"id\":1,\"field\":{\"id\":\"eventoContrato.lastModifiedDate\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"lastModifiedDate\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"INSTANT\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.lastModifiedDate\",\"size\":null,\"enumId\":null,\"mask\":\"DATE_FORMAT3\",\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"DATETIME\",\"restrictedBy\":\"lastModifiedDate\"},\"name\":\"lastModifiedDate\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.lastModifiedDate\"},\"label\":\"Data de atualização\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.lastModifiedDate\"]}, {\"id\":2,\"field\":{\"id\":\"eventoContrato.dataInicial\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"dataInicial\",\"type\":\"NATIVE\",\"required\":true,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"LOCALDATE\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.dataInicial\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"DATE\",\"restrictedBy\":\"dataInicial\"},\"name\":\"dataInicial\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.dataInicial\"},\"label\":\"Data início\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.dataInicial\"]}, {\"id\":3,\"field\":{\"id\":\"user.name\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"name\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"user\",\"simpleFieldType\":\"STRING\",\"classNameDomain\":\"projuris.domain.UserAudit\",\"translationLabel\":\"user.name\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"user\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"TEXT\",\"restrictedBy\":\"name\"},\"name\":\"name\",\"parentReference\":\"createdBy\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.domain.UserAudit\",\"translationLabel\":\"user.name\"},\"label\":\"Nome\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.createdBy\",\"user.name\"]}, {\"id\":4,\"field\":{\"id\":\"eventoContrato.horaInicial\",\"fieldPrefix\":\"\",\"dto\":{\"attribute\":{\"name\":\"horaInicial\",\"type\":\"NATIVE\",\"required\":false,\"entityName\":\"eventoContrato\",\"simpleFieldType\":\"LOCALTIME\",\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.horaInicial\",\"size\":null,\"enumId\":null,\"mask\":null,\"context\":\"eventoContrato\",\"group\":\"NATIVE_FIELD\",\"dataType\":\"TIME\",\"restrictedBy\":\"horaInicial\"},\"name\":\"horaInicial\",\"target\":null,\"type\":\"SIMPLE\",\"belongsToChild\":false,\"classNameDomain\":\"projuris.eventocontrato.EventoContrato\",\"translationLabel\":\"eventoContrato.horaInicial\"},\"label\":\"Hora início\",\"joins\":\"\"},\"operator\":{\"type\":\"NOT_NULL\",\"label\":\"filtro.operador.NOT_NULL\",\"symbol\":\"is not null\",\"requireUserValue\":false},\"fieldLabelPaths\":[\"eventoContrato.horaInicial\"]}]";
    String jsonReturn = updateFiedDataEvento.changeDataFieldToInicialDataTablesFilter(ID, json,portletUsuarioTable);
    Assertions.assertEquals(jsonExpected, jsonReturn);
  }

  @Test
  void shouldUpdatePreferenceTable() throws SQLException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    updateFiedDataEvento.updatePreference(mockStatement, ID, "TESTE");
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals("UPDATE preference SET dados = 'TESTE' WHERE id = 1", updateBackup);
  }

  @Test
  void shouldchangeJsonDataFieldToInicialDataTablePreference() throws JsonProcessingException {
    String json = "{\"columnPreferences\":{\"createdBy.name\":{\"sort\":null,\"show\":true},\"horaInicial\":{\"sort\":null,\"show\":true},\"dataFinal\":{\"sort\":null,\"show\":false},\"horaFinal\":{\"sort\":null,\"show\":false}},\"columnOrder\":[\"proximo\",\"data\",\"hora\",\"tipoEvento.nome\",\"atual\",\"realizado\",\"createdBy.name\",\"horaFinal\"]}";
    String jsonExpected = "{\"columnPreferences\":{\"createdBy.name\":{\"sort\":null,\"show\":true},\"horaInicial\":{\"sort\":null,\"show\":true},\"dataFinal\":{\"sort\":null,\"show\":false},\"horaFinal\":{\"sort\":null,\"show\":false}},\"columnOrder\":[\"proximo\",\"dataInicial\",\"horaInicial\",\"tipoEvento.nome\",\"atual\",\"realizado\",\"createdBy.name\",\"horaFinal\"]}";
    String jsonReturn = updateFiedDataEvento.changeDataFieldToInicialDataTablePreference(json);
    Assertions.assertEquals(jsonExpected, jsonReturn);
  }

  @Test
  void shouldRecoverQueryPreferenceTable() throws SQLException {
    String query = updateFiedDataEvento.getSelectPreferenceTable();
    Assertions.assertEquals(getQueryPreferenceTable(), query);
  }

  @Test
  void shouldRetriveQueryColumn() throws SQLException {
    UpdateFiedDataEvento.TableInfo portletUsuarioTable = getTable();
    UpdateFiedDataEvento.TableInfo relatorioTable = new UpdateFiedDataEvento.TableInfo("id_relatorio", "m_relatorio", "filtro_campo_exibicao", "filtro_campo_relatorio");
    String columnPortletUsuarioTable = updateFiedDataEvento.getFiltroColumnForSelect(portletUsuarioTable);
    String columnRelatorioTable = updateFiedDataEvento.getFiltroColumnForSelect(relatorioTable);
    Assertions.assertEquals("query_filtro", columnPortletUsuarioTable);
    Assertions.assertEquals("convert_from(lo_get(filtro_campo_relatorio::oid), 'UTF8') as query_filtro", columnRelatorioTable);
  }

  @Test
  void shouldRecoverQueryPortletUsuarioTable() throws SQLException {
    UpdateFiedDataEvento.TableInfo table = getTable();

    String query = updateFiedDataEvento.getSelectFilter(table, "");
    Assertions.assertEquals(getQueryPortletUsuarioTable(), query);
  }

  @Test
  void shouldCreateColumnRollbackPreferenceTable() throws SQLException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    updateFiedDataEvento.createColumnPreferenceBackup(mockStatement);
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals(getAlterTablePreferenceTableBackup(), updateBackup);
  }

  @Test
  void shouldCreateColumnRollbackPortletUsuarioTable() throws SQLException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    UpdateFiedDataEvento.TableInfo table = getTable();
    updateFiedDataEvento.createColumnFilterBackup(table, mockStatement);
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals(getAlterTablePortletUsuarioTableBackup(), updateBackup);
  }

  @Test
  void shouldSaveRecordsBeforeChangingPortletUsuarioTable() throws SQLException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    UpdateFiedDataEvento.TableInfo table = getTable();
    updateFiedDataEvento.saveFilterTableBackup(table, mockStatement, ID);
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals(getUpdatePortletUsuarioTableBackup(), updateBackup);
  }

  @Test
  void shouldSaveRecordsBeforeChangingPreferenceTable() throws SQLException {
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    updateFiedDataEvento.savePreferenceTableBackup(ID, mockStatement);
    verify(mockStatement).executeUpdate(argumentCaptor.capture());
    String updateBackup = argumentCaptor.getValue();
    Assertions.assertEquals(getUpdatePreferenceTableBackup(), updateBackup);
  }

  @NotNull
  private static UpdateFiedDataEvento.TableInfo getTable() {
    return new UpdateFiedDataEvento.TableInfo("id_portlet_usuario", "m_portlet_usuario", "valor_filtro", "query_filtro");
  }

  @NotNull
  private String getQueryPreferenceTable(){
    return """
                                                    SELECT id,
                                                           dados
                                                      FROM preference r
                                                    WHERE domain_id IN ('evento-contrato',
                                                                        'evento-procuracao',
                                                                        'evento-reclamacao',
                                                                        'evento-marca-patente',
                                                                        'evento-certidao',
                                                                        'evento-societario',
                                                                        'evento-processo',
                                                                        'evento-distribuicao',
                                                                        'evento-imovel',
                                                                        'evento-escritorio',
                                                                        'evento-requisicao',
                                                                        'evento-entidade')
                                                      AND dados IS NOT NULL
                                                      AND dados <> ''
                                                      AND EXISTS (
                                                        SELECT 1
                                                        FROM jsonb_array_elements(r.dados::jsonb->'columnOrder') AS item
                                                        WHERE item in ('"data"', '"hora"'))
      """;
  }

  @NotNull
  private String getQueryPortletUsuarioTable(){
    return   """
        select convert_from(lo_get(valor_filtro::oid), 'UTF8') as converted_filtro,
                id_portlet_usuario as id_filtro,
                query_filtro
           from m_portlet_usuario
           where valor_filtro is not null
              and valor_filtro <> ''
              and convert_from(lo_get(valor_filtro::oid), 'UTF8')::jsonb->0->'field'->'dto'->'attribute'->>'context' in ('eventoCertidao',
                                                                                                               'eventoContrato',
                                                                                                               'eventoEntidade',
                                                                                                               'eventoImovel',
                                                                                                               'eventoProcuracao',
                                                                                                               'eventoRequisicao',
                                                                                                               'eventoSocietario',
                                                                                                               'eventoProcesso',
                                                                                                               'eventoMarcaPatente',
                                                                                                               'eventoEscritorio',
                                                                                                               'eventoDistribuicao',
                                                                                                               'eventoReclamacao'
                                                                                                               )
             and EXISTS (SELECT 1
                 FROM jsonb_array_elements(convert_from(lo_get(valor_filtro::oid), 'UTF8')::jsonb) AS item
                 WHERE item->'field'->'dto'->'attribute'->>'name' in ('data', 'hora'))
      """;
  }

  @NotNull
  private String getUpdatePreferenceTableBackup(){
    return   """
           UPDATE preference  AS r
            SET
              dados_rollback = f.dados
            FROM preference AS f
            WHERE r.id = 1 AND r.id = f.id;
      """;
  }

  @NotNull
  private String getUpdatePortletUsuarioTableBackup(){
    return """
           UPDATE m_portlet_usuario AS r
           SET
             valor_filtro_rollback = f.valor_filtro,
             query_filtro_rollback = f.query_filtro
           FROM m_portlet_usuario AS f
           WHERE r.id_portlet_usuario = 1 AND r.id_portlet_usuario = f.id_portlet_usuario;
      """;
  }

  @NotNull
  private String getAlterTablePortletUsuarioTableBackup(){
    return """
       ALTER TABLE m_portlet_usuario
       ADD COLUMN valor_filtro_rollback text,
       ADD COLUMN query_filtro_rollback varchar(255);
      """;
  }
  @NotNull
  private String getAlterTablePreferenceTableBackup(){
    return """
         ALTER TABLE preference
         ADD COLUMN dados_rollback text NULL
      """;
  }

  @NotNull
  private static String getQueryFiltro() {
    return " data is not null and data != '' ";
  }

  @NotNull
  private static String getConvertedFiltro() {
    return """
      [{"id":1,"field":{"id":"eventoContrato.data","fieldPrefix":"","dto":{"attribute":{"name":"data","type":"NATIVE","required":true,"entityName":"eventoContrato","simpleFieldType":"LOCALDATE","classNameDomain":"projuris.eventocontrato.EventoContrato","translationLabel":"eventoContrato.data","size":null,"enumId":null,"mask":null,"context":"eventoContrato","group":"NATIVE_FIELD","dataType":"DATE","restrictedBy":"data"},"name":"data","target":null,"type":"SIMPLE","belongsToChild":false,"classNameDomain":"projuris.eventocontrato.EventoContrato","translationLabel":"eventoContrato.data"},"label":"Data início","joins":""},"operator":{"type":"EQUAL","label":"filtro.operador.EQUAL","symbol":"=","requireUserValue":true},"fieldLabelPaths":["eventoContrato.data"],"value":"2024-01-05T03:00:00.000Z"}]
      """;
  }

  @NotNull
  private static String getUpdateTableFilter() {
    return """
      UPDATE m_portlet_usuario SET valor_filtro = lo_from_bytea(0, convert_to('TESTE_FILTRO', 'UTF8')),
                    query_filtro = 'TESTE_QUEY'
      WHERE id_portlet_usuario = 1
      """;
  }

  //TESTE UTILIZANDO REFLECTION PARA METODO PRIVADO.

  @Test
    public void shouldConfirmFinancialRegisteredTransaction() throws Exception {
        Method method = PixOperationService.class.getDeclaredMethod("confirmFinancialRegisteredTransaction",
                SendMoneyOrderForm.class, ApsEnvioPagamentoPixEntity.class,
                FinancialTransactionSummaryInfo.class, AccountHolderInfo.class);
        SendMoneyOrderForm pixPaymentRequest = PixOperationServiceHelper.getSendMoneyOrderForm();
        ApsEnvioPagamentoPixEntity registeredTransaction = PixOperationServiceHelper.getApsEnvioPagamentoPixEntity();
        FinancialTransactionSummaryInfo transactionSummary = PixOperationServiceHelper.getFinancialTransactionSummaryInfo();
        AccountHolderInfo accountHolderInfo = PixOperationServiceHelper.getAccountHolderInfo();
        method.setAccessible(true);
        method.invoke(pixOperationService, pixPaymentRequest, registeredTransaction, transactionSummary, accountHolderInfo);
        verify(apsMovimentacaoPixRepository,times(1)).save(any());
        verify(apsEnvioPagamentoPixRepository,times(1)).save(any());
    }

}
