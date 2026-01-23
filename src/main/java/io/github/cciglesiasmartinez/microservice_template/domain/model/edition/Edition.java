package io.github.cciglesiasmartinez.microservice_template.domain.model.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.*;
import io.github.cciglesiasmartinez.microservice_template.domain.model.film.Film;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

/**
 * Represents an edition within the system.
 * <p>
 * This works as an aggregate root with {@link Picture} domain entity.
 */
public class Edition {

    private EditionId id;
    private Film film;
    private Slug slug;
    private String coverPicture;
    private BarCode barCode;
    private Country country;
    private Format format;
    private Year releaseYear;
    private PackagingType packagingType;
    private Notes notes;
    private List<Picture> pictures;

    private Edition(
            EditionId id,
            Film film,
            Slug slug,
            String coverPicture,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes,
            List<Picture> pictures
    ) {
        this.id = id;
        this.film = film;
        this.slug = slug;
        this.coverPicture = coverPicture;
        this.barCode = barCode;
        this.country = country;
        this.format = format;
        this.releaseYear = releaseYear;
        this.packagingType = packagingType;
        this.notes = notes;
        this.pictures = pictures;
    }

    /**
     * Factory method intended to create an edition for the first time.
     *
     * @param film
     * @param barCode
     * @param country
     * @param format
     * @param releaseYear
     * @param packagingType
     * @param notes
     * @param pictures
     * @return
     */
    public static Edition create(
            Film film,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes,
            List<Picture> pictures
    ) {
        return new Edition(
                EditionId.generate(),
                film,
                Slug.generate(film.title().value(), film.releaseYear()),
                null,
                barCode,
                country,
                format,
                releaseYear,
                packagingType,
                notes,
                pictures
        );
    }

    /**
     * Factory method to hidrate a domain edition from a persistence object.
     *
     * @param id
     * @param film
     * @param barCode
     * @param country
     * @param format
     * @param releaseYear
     * @param packagingType
     * @param notes
     * @param pictures
     * @return
     */
    public static Edition of(
            EditionId id,
            Film film,
            Slug slug,
            String coverPicture,
            BarCode barCode,
            Country country,
            Format format,
            Year releaseYear,
            PackagingType packagingType,
            Notes notes,
            List<Picture> pictures
    ) {
        return new Edition(id, film, slug, coverPicture, barCode, country, format, releaseYear, packagingType, notes, pictures);
    }

    /**
     *
     * @param fileNameExtension
     * @return
     */
    public Picture addPicture(String fileNameExtension) {
        Picture picture = Picture.create(fileNameExtension, this,LocalDateTime.now());
        pictures.add(picture);
        return picture;
    }

    /**
     *
     * @param pictureId
     */
    public void removePicture(PictureId pictureId) {
        pictures.removeIf(p -> p.id().equals(pictureId));
    }

    public void setCoverPicture(PictureId pictureId) {
        this.coverPicture = pictureId.value();
    }

    public EditionId editionId() { return this.id; }
    public Film film() { return this.film; }
    public Slug slug() { return this.slug; }
    public String coverPicture() { return this.coverPicture; }
    public BarCode barCode() { return this.barCode; }
    public Country country() { return this.country; }
    public Format format() { return this.format; }
    public Year releaseYear() { return this.releaseYear; }
    public PackagingType packagingType() { return this.packagingType; }
    public Notes notes() { return this.notes; }
    public List<Picture> pictures() { return this.pictures; }
}
