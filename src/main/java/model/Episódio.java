package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;

public class Episódio {
	
	private int temporada;
	private String titulo;
	private LocalDate dataDeLançamento;
	private Integer numeroEpisódio;
	private double avaliação;
	
	public Episódio(int numeroEpisódio, DadosEpisodio dadosEpisodio) {
		this.temporada = numeroEpisódio;
		this.titulo = dadosEpisodio.titulo();
		this.numeroEpisódio = dadosEpisodio.numero();
		try{
			this.avaliação = Double.valueOf(dadosEpisodio.avaliação());
		} catch(NumberFormatException e) {
			this.avaliação = 0;
		}
		try{
			this.dataDeLançamento =  LocalDate.parse(dadosEpisodio.dataDeLançamento());
		} catch(DateTimeParseException e) {
			this.dataDeLançamento = null;
		}
		}
	
	@Override
	public String toString() {
		return "Temporada=" + temporada + ", titulo=" + titulo + ", dataDeLançamento=" + dataDeLançamento
				+ ", numeroEpisódio=" + numeroEpisódio + ", avaliação=" + avaliação;
	}
	
		public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDate getDataDeLançamento() {
		return dataDeLançamento;
	}
	public void setDataDeLançamento(LocalDate dataDeLançamento) {
		this.dataDeLançamento = dataDeLançamento;
	}
	public Integer getNumeroEpisódio() {
		return numeroEpisódio;
	}
	public void setNumeroEpisódio(Integer numeroEpisódio) {
		this.numeroEpisódio = numeroEpisódio;
	}
	public double getAvaliação() {
		return avaliação;
	}
	public void setAvaliação(double avaliação) {
		this.avaliação = avaliação;
	}
}
