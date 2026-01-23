package io.github.cciglesiasmartinez.microservice_template.domain.model.film;

import io.github.cciglesiasmartinez.microservice_template.domain.model.film.valueobjects.*;

/**
 * Representa una película dentro del sistema.
 * <p>
 * Esta clase representa una entidad de dominio, con comportamientos y restricciones propias
 * del negocio. Siempre que sea posible, los atributos serán "value objects" especializados
 * (no tipos primitivos).
 * <p>
 * Los getters no empiezan por "get", por convención usan directamente el nombre del atributo.
 */
public class Film {

    private final FilmId id;
    private final TmdbId tmdbId;
    private final Title title;
    private final Description description;
    private final ReleaseYear releaseYear;
    private final ProducingCountry producingCountry;
    private final Rating rating;
    private final Poster poster;

    /**
     * Constructor privado: instanciación a través de métodos factoría.
     */
    private Film(
            FilmId id,
            TmdbId tmdbId,
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,          
            Rating rating,
            Poster poster
    ) {
        this.id = id;
        this.tmdbId = tmdbId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.producingCountry = producingCountry;       
        this.rating = rating;
        this.poster = poster;
    }

    /**
     * Método factoría para dar de alta una película por primera vez.
     */
    public static Film create(
            TmdbId tmdbId,
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,
            Rating rating,
            Poster poster
    ) {
        return new Film(FilmId.generate(), tmdbId, title, description, releaseYear, producingCountry, rating, poster);
    }

    /**
     * Método factoría para reconstruir una película desde persistencia.
     */
    public static Film of(
            FilmId filmId,
            TmdbId tmdbId,
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,
            Rating rating,
            Poster poster
    ) {
        return new Film(filmId, tmdbId, title, description, releaseYear, producingCountry, rating, poster);
    }

    public FilmId id() { return this.id; }
    public TmdbId tmdbId() { return this.tmdbId; }
    public Title title() { return this.title; }
    public Description description() { return this.description; }
    public ReleaseYear releaseYear() { return this.releaseYear; }
    public ProducingCountry producingCountry() { return this.producingCountry; }
    public Rating rating() { return this.rating; }
    public Poster poster() { return this.poster; }
}
