# ac3-trabalho2-grupo
Esse repositório foi criado no intuito de demonstrar o algoritmo de Tomasulo utilizando uma simulação de instruções 
de uma arquitetura ARM.

Instruções
--------------------------------------------------------------------------------------------------------
Para introduzir as instruções no simulador é necessário editar o arquivo input.txt,
inserindo uma quebra de linha em cada instrução, de modo que cada instrução fique em uma linha.

ADD XN.XN.XN

SUB XN.XN.XN

SDIV XN.XN.XN

MUL XN.XN.XN

LDR XN(XN)

STR XN(XN)

BEQ XN.XN.XN

--------------------------------------------------------------------------------------------------------
Arquivos
--------------------------------------------------------------------------------------------------------
No App.java, há um método para verificar a dependência e o método main do projeto.

No File.java, está o método reader para leitura do arquivo e split para fazer manipulação de strings.

No FPRegisterStatus.java, mostra o status de registro.

No InstructionStatus.java, serão definidas as instruções e seus status.

No ReorderBuffer.java, há a definição do Buffer de Reordenação.

No ReservationStations.java, há a definição das estações de reserva.

No Simulation.java, há toda a lógica da execução do algoritmo de Tomasulo.

No Swing.java, está código da interface gráfica.

No Tables.java, é onde está o esqueleto das tabelas presentes na interface gráfica.

--------------------------------------------------------------------------------------------------------
Execução
--------------------------------------------------------------------------------------------------------
Em seguida é necessário apenas executar o programa e clicar em "next" para cada ciclo até a finalização.

Quando finalizar, será aberta uma tela com um botão que fechará o programa.

--------------------------------------------------------------------------------------------------------


