package io.github.cciglesiasmartinez.microservice_template.domain.model;

import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Description;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.FilmId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Poster;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ProducingCountry;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Rating;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ReleaseYear;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Title;

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
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,          
            Rating rating,
            Poster poster
    ) {
        this.id = id;
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
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,
            Rating rating,
            Poster poster
    ) {
        return new Film(FilmId.generate(), title, description, releaseYear, producingCountry, rating, poster);
    }

    /**
     * Método factoría para reconstruir una película desde persistencia.
     */
    public static Film of(
            FilmId filmId,
            Title title,
            Description description,
            ReleaseYear releaseYear,
            ProducingCountry producingCountry,
            Rating rating,
            Poster poster
    ) {
        return new Film(filmId, title, description, releaseYear, producingCountry, rating, poster);
    }

    public FilmId itemId() { return this.id; }
    public Title title() { return this.title; }
    public Description description() { return this.description; }
    public ReleaseYear releaseYear() { return this.releaseYear; }
    public ProducingCountry producingCountry() { return this.producingCountry; }
    public Rating rating() { return this.rating; }
    public Poster poster() { return this.poster; }
}
