package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.Format;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.PackagingType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "editions")
public class EditionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @Column(name = "slug")
    private String slug;

    @Column(name = "cover_picture")
    private String coverPicture;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "country")
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private Format format;

    @Column(name = "release_year")
    private Year releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "packaging_type")
    private PackagingType packagingType;

    @OneToMany(
            mappedBy = "edition",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PictureEntity> pictures = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "notes")
    private String notes;

}
