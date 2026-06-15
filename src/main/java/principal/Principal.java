package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.DadosEpisodio;
import model.DadosSerie;
import model.DadosTemporada;
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
	}

}
