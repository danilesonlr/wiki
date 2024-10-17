public class TesteComReflectionExemplo {
    @Mock
	private ProcessorServiceClient processorServiceClient;//Esse objeto e protegido dentro de uma classe abstrata extendida na classe CardsNoNameService

    @InjectMocks
	private CardsNoNameService cardsNoNameService;


    @Test
    public void testParaDeixarVisivelVariaveisEObjetosProvados(){
        ApsCartaoNoNameProduto apsCartaoNoNameProduto = new ApsCartaoNoNameProduto();
		String account = "fdsafasfsa";
		String token = "fkdaofuipe";

        //recuperando a variável protegida
		Field field = ProcessorIntegrationService.class.getDeclaredField("processorServiceClient");

		// Altera a acessibilidade do campo
		field.setAccessible(true);

		// Define o valor do mock no campo do objeto concreto (CardsNoNameService)
		field.set(cardsNoNameService, processorServiceClient);

		when(enderecoRepository.findByTipoEntidadeAndEntidadeId(any(), anyLong()))
				.thenReturn(List.of(enderecoEntity));

        //Acessando o metodo privado
		Method method = CardsNoNameService.class.getDeclaredMethod("integrationAssignCard",
				ApsCartaoNoNameProduto.class,
				String.class,
				String.class);
		method.setAccessible(true);

		method.invoke(cardsNoNameService, apsCartaoNoNameProduto, account, token);
		verify(processorServiceClient).postAssignNoName(anyString(), any(), any());

    }

    void testSobrescrevendoClasseAbstrataParaTeste () throws Exception {

        consumer = spy(new ProcessamentoLoteKafkaConsumer() {

            @Override
            protected String getNomeTopico() {
                return "nomeDoTopico";
            }

            @Override
            protected String getGrupoId() {
                return "grupoId";
            }

            @Override
            protected ItemLote preExecucao(ItemLoteRequest itemLoteRequest) {
                return null;
            }

            @Override
            protected Optional<ApsLoteEntity> execucaoItem(ItemLote itemLote) {
                ApsLoteEntity apsLote = new ApsLoteEntity();
                apsLote.setId(1L);
                return Optional.of(apsLote);
            }

            @Override
            protected void posExecucao(ItemLoteRequest itemLoteRequest) {
                getException();
            }
        });
    }
    
}