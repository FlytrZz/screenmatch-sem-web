package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.DadosSerie;
import services.ConsumoAPI;
import services.converteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var ConsumoAPI = new ConsumoAPI();
		var json = ConsumoAPI.obterDados("https://www.omdbapi.com/?t=The+Flash&apikey=e9da31f0");
		System.out.println(json);
		//json =  ConsumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);
		converteDados c = new converteDados();
		DadosSerie dados = c.obterDados(json,DadosSerie.class);
		System.out.println(dados);
	}

}
