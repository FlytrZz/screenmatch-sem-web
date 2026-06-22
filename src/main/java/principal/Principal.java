package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.DadosEpisodio;
import model.DadosSerie;
import model.DadosTemporada;
import model.Episódio;
import services.ConsumoAPI;
import services.converteDados;

public class Principal {
	private Scanner input = new Scanner(System.in);
	private ConsumoAPI consumo = new ConsumoAPI();
	private converteDados conversor = new converteDados();
	private final String ENDEREÇO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=e9da31f0";
	
	public void exibeMenu() {
		System.out.println("Digite o nome da série para busca:");
		var serie = input.nextLine();
		var json = consumo.obterDados(ENDEREÇO+serie.replace(" ", "+")+API_KEY);
		DadosSerie dados = conversor.obterDados(json,DadosSerie.class);
		System.out.println(dados);
		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i=1;i<=dados.totalTemporadas();i++) {
			json = consumo.obterDados(ENDEREÇO+serie.replace(" ", "+")+"&season="+i+API_KEY);
			DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(temporada);
		}
		temporadas.forEach(System.out::println);
		
		/*for(int i=0; i<dados.totalTemporadas();i++) {
			List <DadosEpisodio> episodios = temporadas.get(i).episodios();
			for(int j=0;j<episodios.size();j++) {
				System.out.println(episodios.get(j).titulo());
			}
		}*/
		
		temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
		temporadas.forEach(System.out::println);
		
		/*List<String> nomes = Arrays.asList("Jacque","Yasmin","Rodrigo","Paulo","Nico");
		nomes.stream().sorted().limit(3).filter(f -> f.startsWith("N")).map(n -> n.toUpperCase()).forEach(System.out::println);*/
		
		List<DadosEpisodio> dadosepisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());
		
		/*System.out.println("\nTop 5 Episódios:");
		dadosepisodios.stream().filter(f -> !f.avaliação().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DadosEpisodio::avaliação).reversed()).limit(5).forEach(System.out::println);*/
		
		List<Episódio> episodios = temporadas.stream().flatMap(t -> t.episodios().stream()).map(d -> new Episódio(d.numero(),d)).collect(Collectors.toList());
		episodios.forEach(System.out::println);
		
		System.out.println("A partir de que ano você deseja ver os episódios?");
		var ano = input.nextInt();
		input.nextLine();
		
		LocalDate dataBusca = LocalDate.of(ano, 1, 1);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		episodios.stream().filter(f -> f.getDataDeLançamento()!=null && f.getDataDeLançamento().isAfter(dataBusca)).forEach(e -> System.out.println("Temporada: "+e.getTemporada()+
				" Episódio: "+e.getTitulo()+
				" Data: "+e.getDataDeLançamento().format(formatador)));
}
	}
