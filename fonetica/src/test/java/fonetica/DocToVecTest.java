package fonetica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class DocToVecTest {

	// @Test
	public void getBigramVectors() throws FileNotFoundException {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileReader("dataset-lei.txt"));
		while (sc.hasNextLine()) {
			d2bp.addSentence(sc.nextLine());
		}
		sc.close();
		DocToVec d2v = new DocToVec(d2bp);

		for (Double[] value : d2v.getBigramVectorsFrom("abescorpus")) {
			System.out.println(Arrays.toString(value));
		}
	}

	// @Test
	public void embbedVectors() throws FileNotFoundException {
		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileReader("dataset-lei.txt"));
		while (sc.hasNextLine()) {
			d2bp.addSentence(sc.nextLine());
		}
		sc.close();
		DocToVec d2v = new DocToVec(d2bp);

		List<Double[]> values = d2v.getBigramVectorsFrom("o menino não gosta de leite");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("gato\n");
		Double[] gato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(gato) + "\n----------");

		values = d2v.getBigramVectorsFrom("a menina gosta de chupar chiclete");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("pato");
		Double[] pato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(pato) + "\n----------");

		values = d2v.getBigramVectorsFrom("tato sente algo. o menino tem tato. a menina tem tato");
		for (Double[] value : values) {
			System.out.println(Arrays.toString(value));
		}

		System.out.println("tato");
		Double[] tato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(tato) + "\n----------");

		System.out.println("distancias");

		System.out.println("gato-pato " + EmbbededVectorsFactory.cos(gato, pato));
		System.out.println("gato-tato " + EmbbededVectorsFactory.cos(gato, tato));
		System.out.println("tato-pato " + EmbbededVectorsFactory.cos(tato, pato));
	}

	@Test
	public void embbedFoneticVectors() throws FileNotFoundException {

		DocToBigramProb d2bp = new DocToBigramProb();

		Scanner sc = new Scanner(new FileInputStream(new File("Constituicao de 1988.txt")), "UTF-8");
		while (sc.hasNextLine()) {
			d2bp.addSentence((sc.nextLine()));
		}
		sc.close();

		d2bp.getBigrams().forEach(b -> {
			System.out.println(b);
		});

		DocToVec d2v = new DocToVec(d2bp);

		String sentOne = "Casal parental merece maior atenção do Direito de FamíliaNada obstante se colocarem como ex-parceiros de um relacionamento findo, eles continuam substancialmente permanentes, como pais comuns que são dos mesmos filhos. Assim, sujeitos às mesmas obrigações parentais e mais que isso, submetidos a uma nova realidade familiar, pelo axioma de que “a separação do casal exige melhores pais” (EDUARDO SÁ, 2011).É o denominado “casal parental”, constituindo uma nova família jurídica, merecedora de maiores atenções do moderno direito de família.Este “casal parental” representa, em cena, os novos protagonistas da família mais duradoura possível, aquela que tem sua extensão na exata medida que prossegue pelos filhos que existem; desafiando os sistemas jurídicos, a doutrina e a jurisprudência a uma vigília anti-alienante de uma parentalidade mórbida e desconforme.É a família “post pactum finitum”, a que tem começo quando o casal termina, e que faz nítida a distinção entre as frustrações de êxito do casal conjugal extinto e as necessidades continuadas de realização pessoal do filho, no desenvolvimento saudável de sua formação como pessoa.Em bom rigor, a reforma legal civil portuguesa, trazida com a Lei 61/2008, introduziu um novo sistema de regulação do exercício das responsabilidades parentais em face do divórcio, acrescentando outros dispositivos ao Código Civil.Vê-se, de saída, que a referida lei superou uma concepção reducionista da função jurídica do poder paternal, concebida na teoria geral do direito civil, como destaca Maria Clara Sottomayor. Nessa linha de superação, assumiu uma concepção personalista das responsabilidades parentais, onde a criança é sujeito de direito, titular de relações jurídicas ordenadas pelos seus superiores interesses e centro irradiante do sistema criado.Mais ainda: A expressão “poder paternal” é abolida, sendo substituída pela nomenclatura “responsabilidades parentais”, a tanto buscar expressar um liame interrelacional fundado na funcionalidade de um conjunto de direitos e deveres nas relações paterno-filiais. Decai o vocábulo “poder” com o seu significante de autoridade parental, domínio ou posse, colocando-se como regra a repartição das obrigações, no exercício em comum daquelas responsabilidades.Mais precisamente, o casal formado pelos progenitores dos filhos permanece, juridicamente unido por responsabilidades inerentes de suas condições e postos em igualdade como pai e mãe.Boaventura Santos, nesse ponto, assinala que “as questões de particular importância para a vida do filho, são exercidas por ambos os pais, nos termos que vigoravam na constância do matrimônio (artigo 1906, 01, Código Civil português) enquanto que, todavia, questões relativas aos atos da vida corrente do filho, caberão ao pai ou mãe com que ele resida habitualmente (artigo 1.906, 3, CCpt.).O direito brasileiro tem dispositivo algo semelhante, em seu artigo 1.632, quando preceitua que “a separação judicial, o divórcio e a dissolução da união estável não alteram as relações entre pais e filhos senão quanto ao direito, que aos primeiros cabe, de terem em sua companhia os segundos”.Este casal parental, “para além do divórcio”, instituindo uma nova família jurídica, fundada e verticalizada nos seus descendentes, tem sido tratado em diversos ordenamentos jurídicos com maiores atenções. Suficiente especificar:(i) o Código Civil francês, em seu artigo 373-2, estabelece que “a separação dos pais em nada influencia as regras de exercício da autoridade parental. Cada progenitor deve manter relação de convívio com os filhos e respeitar o vínculo com o outro pai.”(ii) na Itália, a lei divorcista 54, de 02.02.2006, modificou o Código Civil italiano para efeito de o seu artigo 155 dispor que, em caso de divórcio, “os menores mantém o direito à convivência com ambos os genitores”;(iii) o Código Civil alemão, o vetusto BGB de 1896 (18.08), teve alteração pela Lei 04, de dezembro de 2008, acrescentando item 3 ao artigo 1.626, para prever que o superior interesse da criança, como norma geral, inclui o contato do menor com ambos os pais, o mesmo se aplicando a outras pessoas com quem a criança tenha laços, se forem benéficos para o seu desenvolvimento.(iv) o direito de família inglês, no “Family Law Act”, de 1996, também ao tratar do superior interesse da criança, determina que o tribunal deve prover o regular contato da criança com ambos os pais e membros da família.Pois bem. Relevante e inconteste o fato de a responsabilidade parental comum envolver os genitores separados, no trato dos cuidados e proteção dos filhos, em convivência familiar com eles, independente de seus conflitos interpessoais de ex-parceiros, caso é que a matéria de regulação das responsabilidades parentais, está a exigir novos diplomas normativos, tratando de forma exauriente as designadas situações, nomeadamente pelo direito brasileiro.Em verdade, enquanto o direito português, nitidamente, vem estabelecer pela Lei 62/2008, disciplina de exercício das responsabilidades parentais, tendo por objeto (i) determinação de residência habitual (ii) modelo de exercício, (iii) regime de convívio e (iv) definição de alimentos pelo genitor não residente; o direito brasileiro, a seu turno, não descreve as diretivas desse exercício, para enfrentamento, inclusive, das hipóteses de eventuais incumprimentos.Aliás, diversos tem sido os atos normativos nacionais que apresentam modelo jurídico ao regime da responsabilidade parental, pelo princípio da co-responsabilização dos pais, instituindo a “co-parentalidade positiva”, em benefício construtivo dos laços familiares.Com precisão, no âmbito do incumprimento das obrigações parentais, legislação estrangeira da última década tem sido diligente em promover medidas sancionatórias por atos de transgressão a acordos ou a decisões judiciais que venham ocorrer. Assim é que anota-se no Código Civil francês, a pena de prisão até dois anos e multa de 15 mil euros (artigo 227-3); o direito português, com a reportada Lei 61/208, alinhou no artigo 249 do CP, a tipificação penal dos crimes de rapto parental e subtração de menor; tipos penais também previstos no código penal alemão.Diante do significativo aumento de divórcios litigiosos, com a disputa acirrada de custódia dos filhos, sem soluções pronunciadas a contento, urge que o direito de família intervenha, decisivamente, a fazer cumprir princípios e valores que devem reger a co-parentalidade e o seu regular e eficiente exercício.O “casal parental” é a família que não deixa de existir, quando os filhos estão a exigir que esta subsista neles.Texto confeccionado por: Jones Figueirêdo Alves. Desembargadores do TJPE.";
		List<Double[]> values = d2v.getBigramVectorsFrom((sentOne));

		System.out.println(sentOne);
		Double[] gato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(gato) + " -> " + d2bp.getBigramFromIndex(gato[1]) + "\n----------");

		String sentTwo = "Reflexões sobre a indicação de desembargadores para o TREOs Tribunais Regionais Eleitorais são compostos por sete juízes. Dois desembargadores estaduais e dois juízes de direito, eleitos pelo Tribunal de Justiça, dois advogados nomeados pelo poder Executivo e um juiz federal, ou desembargador federal se o estado for sede de Tribunal Regional Federal.A Constituição Federal reservou duas vagas especialmente para os advogados. É de se perguntar: atenderia a intenção do legislador constitucional o fato de os TJs indicarem (mais) um terceiro e/ou quarto juiz oriundos da advocacia, do quinto constitucional, para as vagas reservadas para a carreira de juiz de segunda instância (desembargador), nos TREs?Merece uma reflexão profunda essa situação.Até que ponto seria justo os Tribunais irem além destas duas vagas obrigatoriamente já destinadas aos advogados, nomeados pelo poder Executivo, vindo o Judiciário optar por indicar mais desembargadores de origem da advocacia, via quinto constitucional, para as vagas reservadas à magistratura estadual e federal?Evidente que essa discussão não está a debater a grande capacidade jurídica, intelectual ou cultural, e muito menos a excelência do trabalho desempenhado pelos magistrados do quinto constitucional da advocacia nos tribunais.O debate se coloca em outra esfera, ou seja, no respeito à origem do juiz (classe), pois a intenção do legislador foi levar para os tribunais eleitorais culturas jurídicas e sociais diferentes, até pela matéria que compete a essa corte decidir.Deve-se relembrar que na composição do Tribunal Superior do Trabalho, após a extinção do juiz classista, o legislador preservou a existência do quinto constitucional dedicado à advocacia e ao Ministério Público. No entanto, nessa inovadora mesma linha de raciocínio que apresentamos, então para as vagas destinadas aos magistrados no TST há menção expressa e modernizante de que os desembargadores dos TRTs, para terem acesso à corte trabalhista nacional, terão que observar a origem na carreira, consoante o artigo 111A, II, da Constituição da República.Acreditamos, sinceramente, que esse comando constitucional moderno e avançado deva servir de reflexão e alerta à justiça estadual quanto ao acesso aos TREs às duas vagas destinadas aos desembargadores estaduais no que se refere aos egressos do ‘quinto’ da advocacia nos TJs e TRFs.Quando o artigo 25, parágrafo 2º, do Código Eleitoral, vigia com a redação que lhe conferiu o artigo 8º, da Lei 4.961/66, recepcionada pela Constituição de 1988 — segundo entendimento do STF (RMS 23.123 de 15.12.1999) — o inciso III proibia a OAB e os tribunais de indicarem para as listas tríplices para ocupar as duas vagas destinadas aos advogados nos TREs nomes de profissionais que foram membros do MP e magistrados aposentados. Ou seja, proibiam indicar aqueles advogados de então, mas que antes exerciam a magistratura ou a atividade do MP.Por certo que a Lei 7.191, de 1984, alterou o aludido dispositivo, o que não afasta a necessidade da discussão do tema, forte no sentido de que as vagas destinadas à magistratura estadual ou federal nos TREs recaiam somente entre os desembargadores não oriundos do quinto constitucional da advocacia, por questão de justiça e lógica.Curiosamente, essa é uma decisão que cabe aos próprios desembargadores adotar e praticar no dia a dia. A advocacia já fez a parte dela, preservando e reservando para si a classe de origem para suas duas vagas nos TREs.Em tempos de esperança, mudanças, renovação, modernização e transparência, a opção pela valorização das carreiras jurídicas (juiz e membro do MP) deve se impor dentro do próprio Judiciário, cabendo portanto ser refletida legitimamente pelos próprios tribunais.Por exemplo, hoje o Judiciário mineiro conta com 99,8% de magistrados com origem na carreira jurídica de juiz e MP, o que confere legitimidade e alta representatividade ao desembargador oriundo da magistratura e MP para ocupar a vaga de desembargador no TRE, considerando que os oriundos da OAB (quinto constitucional) somam apenas 0,015% da magistratura mineira (13 membros no TJMG). Logo, não seria correto ver o Tribunal de Justiça, neste caso, sacrificar um desembargador oriundo de longa carreira jurídica para desestimular ainda mais a atividade do magistrado.Na verdade, o que se busca e pretende é que, aquele que enfrentou os pleitos eleitorais nos mais longínquos rincões deste Estado, de peito aberto e obstinação republicana, possa exercer a jurisdição eleitoral perante o TRE, munido desta bagagem jurídica e da vivência democrática. Uma questão de equidade e justiça plena.Texto confeccionado por: Marco Aurélio Ferenzini. Desembargador do Tribunal de Justiça de Minas Gerais.";
		values = d2v.getBigramVectorsFrom((sentTwo));

		System.out.println(sentTwo);
		Double[] pato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(pato) + " -> " + d2bp.getBigramFromIndex(pato[1]) + "\n----------");

		String sentThree = "Escritura de divórcio que prevê pensão tem força jurídicaO site do Tribunal de Justiça de São Paulo veiculou, no dia 18 de dezembro de 2013, a notícia de que a 3ª Câmara de Direito Privado negou o processamento de uma execução de alimentos em que era pedida a prisão civil do devedor (artigo 733 do Código de Processo Civil). Entendeu-se que o pedido não era possível porque o título executivo era extrajudicial — uma escritura pública de divórcio —, e não uma decisão judicial.Vejamos a fundamentação do voto:É que o art. 733 do Código de Processo Civil estabelece que a prisão civil pode decorrer da inércia do devedor em pagar ou se escusar os alimentos fixados em sentença ou decisão (“Na execução de sentença ou decisão, que fixa os alimentos provisionais, o juiz mandará citar o devedor para, em 3 (três) dias, efetuar o pagamento, provar que o fez ou justificar a impossibilidade de fazê-lo”).Contudo, a escritura pública de divórcio é título executivo extrajudicial (art. 1.124-A, parágrafo 1º, CPC), cujo grau de certeza é menor do que o do título produzido em juízo após contraditório e cognição exaurientes.Daí porque não se pode admitir a prisão civil do devedor, medida excepcional e extremamente gravosa, em decorrência de ajuste que constou de escritura pública.Para a execução desse débito alimentar, a agravada poderia se valer do rito da execução por quantia certa contra devedor solvente (art. 732, CPC), mas não do rito que prevê a prisão civil.O assunto é de grande alcance prático, ultrapassando os limites do simples interesse das partes, visto que milhares são os casos de separação e divórcio instrumentalizados por escritura pública com a estipulação de pensão alimentícia em favor de um dos cônjuges ou dos filhos maiores.Por primeiro, deve-se refutar a tese de que a obrigação de prestar alimentos firmada em cartório de notas é desprovida da observância do princípio do contraditório. Entende-se que há, sim, contraditório na formação do acordo de divórcio feito perante o tabelião, pois no ato as duas partes devem estar presentes e assessoradas pelo advogado escolhido por elas, que tanto pode ser um só para as duas ou um para cada. Como se vê, nada é feito sem a presença e a anuência do devedor, que está amparado por profissionais do direito de sua confiança. O tabelião fará as vezes de um juiz, confirmando a vontade das partes e, com o advogado, alertando-as das consequências do ato que está sendo feito. Tudo isso com a participação ativa dos interessados.Os cartórios são parceiros da justiça e assim devem ser vistos. É o poder Judiciário que seleciona e fiscaliza os tabeliães. Por isso a Resolução 35 do CNJ, no seu art. 52 diz: os cônjuges separados judicialmente podem, mediante escritura pública, converter a separação judicial ou extrajudicial em divórcio, mantendo as mesmas condições ou alterando-as.Em outras palavras, as partes podem, por escritura, alterar até mesmo o que antes tinham combinado sobre alimentos na presença do juiz! Portanto, não há espaço para entender-se que a escritura tem menos valor que a homologação judicial e por isso é incabível obstar a execução da pensão alimentícia na forma do art. 733 do CPC.Não podemos nos prender à literalidade do art. 733 do CPC, que fala em execução de sentença ou decisão. Este dispositivo só se refere a esses dois tipos de pronunciamentos judiciais porque foi redigido na época em que só por meio de um magistrado era definido o valor de uma pensão, ainda que por mera homologação.Porém, os tempos mudaram, o direito não é o mesmo e, com o advento da Lei 11.441/07, em muito boa hora, o divórcio consensual sem filhos menores passou a poder ser feito por escritura pública, na qual os alimentos são convencionados para o casal ou para os filhos maiores. Portanto, desde 2007, a definição do valor dos alimentos não é mais privativa de uma decisão judicial. Há mais liberdade para as próprias pessoas resolverem suas vidas. Portanto, o artigo 733 deve ser interpretado de forma sistemática e atual, não podendo ser apenas lido de forma literal.O entendimento do julgado que se analisa parece ter considerado que o devedor é a parte mais fraca na relação jurídica da dívida alimentar. Todavia, o que ocorre é exatamente o contrário. Nessa relação alimentar a parte mais forte é quem paga e não quem recebe os alimentos. Quem paga tem para se manter e ainda pode ajudar alguém. Quem recebe não tem nem para o próprio sustento.A pessoa que recebe a pensão está em situação de vulnerabilidade, pois precisa que outra pessoa contribua para o que é necessário para o seu bem estar: alimentação, vestuário, educação, transporte, saúde e lazer.A interpretação meramente literal do artigo 733 do CPC, feita pelo acórdão noticiado, criou uma exceção, não prevista na lei e nem na Constituição, em que uma dívida alimentar ficou sem a força da possibilidade de prisão do devedor, enfraquecendo o direito do credor dos alimentos, que deles necessita para ter uma vida humana com dignidade, o que é um dos fundamentos da República Federativa do Brasil (art. 1º, III, da Constituição).O entendimento do julgado em análise retira das escrituras, indevidamente, eficácia jurídica que lhes é conferida pela Lei 11.441/07 e tem a consequência perniciosa de fazer com que a Justiça tenda a ser cada vez mais sobrecarregada, pois, com menos eficácia nos acordos de divórcio feitos nos cartórios de notas, as pessoas tendem a procurar o Judiciário para fazer o mesmo acordo que poderiam perfeitamente fazer fora dele.O credor dos alimentos tem direito à proteção que decorre da possibilidade da prisão do devedor inadimplente. Se não for reconhecida essa eficácia no título extrajudicial, produzido no cartório de notas, a tendência é o credor fazer questão de que o acordo seja feito perante o Judiciário, com isso gerando processos e mais processos totalmente desnecessários, para mera homologação, exatamente o que a Lei 11.441/07 quis evitar.Por sua vez, esse afluxo maior de processos tornará a justiça ainda menos célere e menos eficiente, o que contraria pelo menos dois princípios constitucionais: eficiência (art. 37) e duração razoável do processo (art. 5º, LXXVIII).Lembre-se que os tabeliães não são os únicos que podem celebrar acordos de alimentos. A defensoria pública e o ministério público também podem lavrar termos de acordos, gerando igualmente títulos extrajudiciais. E o Superior Tribunal de Justiça já reconheceu a possibilidade de prisão civil na execução de tais títulos. Vejamos os precedentes adiante.RECURSO ESPECIAL – OBRIGAÇÃO ALIMENTAR EM SENTIDO ESTRITO – DEVER DE SUSTENTO DOS PAIS A BEM DOS FILHOS – EXECUÇÃO DE ACORDO EXTRAJUDICIAL FIRMADO PERANTE O MINISTÉRIO PÚBLICO – DESCUMPRIMENTO – COMINAÇÃO DA PENA DE PRISÃO CIVIL – POSSIBILIDADE.1. Execução de alimentos lastrada em título executivo extrajudicial, consubstanciado em acordo firmado perante órgão do Ministério Público (art. 585, II, do CPC), derivado de obrigação alimentar em sentido estrito – dever de sustento dos pais a bem dos filhos.2. Documento hábil a permitir a cominação de prisão civil ao devedor inadimplente, mediante interpretação sistêmica dos arts. 19 da Lei n. 5.478/68 e Art. 733 do Estatuto Processual Civil. A expressão “acordo” contida no art. 19 da Lei n. 5.478/68 compreende não só os acordos firmados perante a autoridade judicial, alcançando também aqueles estabelecidos nos moldes do art. 585, II, do Estatuto Processual Civil, conforme dispõe o art. 733 do Código de Processo Civil. Nesse sentido: REsp 1117639/MG, Rel. Ministro Massami Uyeda, Terceira Turma, julgado em 20/05/2010, DJe 21/02/2011.3. Recurso especial provido, a fim de afastar a impossibilidade apresentada pelo Tribunal de origem e garantir que a execução alimentar seja processada com cominação de prisão civil, devendo ser observada a previsão constante da Súmula 309 desta Corte de Justiça.RESP 1285254/DF – Relator Ministro Marco Buzzi – T4 – j. 04.12.12RECURSO ESPECIAL – PROCESSUAL CIVIL – EXECUÇÃO DE ALIMENTOS – ACORDO REFERENDADO PELA DEFENSORIA PÚBLICA ESTADUAL – AUSÊNCIA DE HOMOLOGAÇÃO JUDICIAL – OBSERVÂNCIA DO RITO DO ARTIGO 733 E SEGUINTES DO CÓDIGO DE PROCESSO CIVIL – POSSIBILIDADE, NA ESPÉCIE – RECURSO ESPECIAL PROVIDO.1. Diante da essencialidade do crédito alimentar, a lei processual civil acresce ao procedimento comum algumas peculiaridades tendentes a facilitar o pagamento do débito, dentre as quais destaca-se a possibilidade de a autoridade judicial determinar a prisão do devedor. 2. O acordo referendado pela Defensoria Pública estadual, além de se configurar como título executivo, pode ser executado sob pena de prisão civil. 3. A tensão que se estabelece entre a tutela do credor alimentar versus o direito de liberdade do devedor dos alimentos resolve-se, em um juízo de ponderação de valores, em favor do suprimento de alimentos a quem deles necessita.4. Recurso especial provido.REsp 1117639/MG – Relator Ministro Massami Uyeda – T3 – j. 20.05.2010Com tantas possibilidades de soluções dos problemas por outras vias que não o processo judicial, não me parece correto o entendimento que induz as pessoas a procurar a justiça nos casos em que não há litígio, pois em tais casos elas estão de acordo e podem resolver o seu problema muito mais rapidamente, num cartório extrajudicial ou perante outros órgãos como a defensoria pública ou o ministério público.Devemos ter em mente que não há possibilidade de prisão civil sem o crivo judicial. Quem decreta a prisão não é o advogado, não é o tabelião e nem são as partes. A prisão só é decretada por um juiz e sempre depois de possibilidade de defesa.De fato, o devedor é citado para pagar, comprovar que pagou ou se justificar no prazo legal de três dias. A prisão só vem rapidamente quando ocorrem essas três omissões do devedor. Não existe entre nós uma prisão automática, decorrente da pura e simples falta de pagamento. Desde a inadimplência até a ordem de prisão, há uma importante tramitação processual, que assegura uma série de garantias.Deve ficar bem claro que a escritura não acarreta a prisão de ninguém. Não há o que temer. Todos estão seguros, inclusive os devedores. A prisão é excepcional, pois é a última opção do juiz, reservada apenas para quem não tem motivo justo para deixar de pagar.Portanto, é um equívoco ser rigoroso demais na exigência formal do título que gera o crédito aos alimentos. Isso fez o julgado em questão. Se a preocupação é não prender alguém desnecessariamente, basta que o juiz só decrete a prisão nos casos em que isso realmente é necessário, mas independentemente de o título ser judicial ou extrajudicial.As pessoas costumam pagar as pensões não porque são presas, mas pelo temor de ter a sua prisão decretada. Por isso que, para que as coisas funcionem bem, basta que exista a mera possibilidade de a prisão ser decretada. Mas, quando se considera, de antemão, que a prisão é incabível porque o título é extrajudicial, o temor desaparece e com ele um importante estímulo ao pagamento pontual.Com a impontualidade estimulada, aumenta ainda mais o número dos processos de execução, sobrecarregando-se ainda a justiça, de maneira totalmente desnecessária. Como vemos, um dos efeitos é uma litigiosidade maior.Finalmente, do ponto de vista de política judiciária e de planejamento estratégico do Poder Judiciário, é um equívoco grave negar a possibilidade de prisão por alimentos convencionados em escritura de divórcio, pois o fundamento de proteger o devedor inadimplente acaba sendo um golpe gravíssimo contra o instituto do divórcio extrajudicial, que muito tem contribuído para melhorar a atuação da justiça e a vida de tantas pessoas.Negar eficácia parcial aos divórcios extrajudiciais é estimular que eles sejam feitos em juízo, o que contraria o momento em que vivemos. Não devemos fomentar o litígio e nem a desnecessária judicialização, que já é grande. Devemos buscar formas alternativas de resolução dos conflitos, como tem dito o Conselho Nacional de Justiça.O Presidente do Tribunal de Justiça de São Paulo, Desembargador José Renato Nalini, em entrevista publicada no jornal Valor Econômico, no dia 02 de janeiro de 2014, quando tomou posse, disse que uma das metas de sua gestão é reduzir o número de demandas. Vejamos um trecho do que foi dito pelo chefe do Poder Judiciário Paulista.“Gostaria que a sociedade paulista prestasse mais atenção ao Judiciário e ajudasse a definir se esse é o modelo realmente hábil para a solução de conflitos. Há um excesso de demandismo. O Brasil tem 93 milhões de processos para quase 200 milhões de habitantes. Isso é irreal. O Judiciário deve investir cada vez mais nos meios alternativos de solução de conflitos. A população se acostumou a discutir todas as suas questões, desde as mais graves até as menores, em juízo. Nós alargamos a porta de acesso à Justiça. Todos entram, mas agora não encontram a saída, que é um funil. O Judiciário deve mostrar que a solução pacífica, a autocomposição, é muito mais eficaz do que a solução dada pelo Estado-juiz. Quando se faz um acordo, além de economizar tempo e dinheiro, você foi protagonista da sua história. Opinou, discutiu e entendeu. Você não foi excluído. No processo, a parte é excluída. Ela fica ali. É só o advogado que fala”.Em conclusão, a escritura de divórcio que estipula alimentos entre os cônjuges não é juridicamente frágil e nem potencialmente perigosa para a proteção dos direitos dos envolvidos. Ao contrário, ela é um importante instrumento de realização rápida do direito, bem como da “desjudicialização”, de modo que, a regra procedimental prevista no artigo 733, do CPC deve ser harmonizada com a inovação prevista na Lei 11.441/07, viabilizando, portanto, o método coercitivo do devedor, em consonância ao que dispõe a Constituição Federal, consistente na admissão da excepcional prisão do devedor de alimentos, ainda que estes tenham sido estipulados consensualmente perante um cartório de notas. Com esta ótica não se prega a indiscriminada prisão civil dos devedores de pensões alimentícias. O que se defende é a mera possibilidade do cabimento da prisão civil, sem se fazer a discriminação da natureza do título executivo, seja ele judicial ou uma escritura pública.Texto confeccionado por: José Luiz Germano. Desembargador da 2ª câmara de direito público do Tribunal de Justiça de São Paulo.";
		values = d2v.getBigramVectorsFrom((sentThree));

		System.out.println(sentThree);
		Double[] tato = EmbbededVectorsFactory.embbed(values);
		System.out.println(Arrays.toString(tato) + " -> " + d2bp.getBigramFromIndex(tato[1]) + "\n----------");

		System.out.println("distancias");

		System.out.println("one-two " + (1 - Math.abs(EmbbededVectorsFactory.cos(gato, pato))));
		System.out.println("one-three " + (1 - Math.abs(EmbbededVectorsFactory.cos(gato, tato))));
		System.out.println("three-two " + (1 - Math.abs(EmbbededVectorsFactory.cos(tato, pato))));

		System.out.println("edit distance");
		System.out.println(EmbbededVectorsFactory.editDistance(sentOne, sentTwo));
		System.out.println(EmbbededVectorsFactory.editDistance(sentOne, sentThree));
		System.out.println(EmbbededVectorsFactory.editDistance(sentThree, sentTwo));
	}
}
