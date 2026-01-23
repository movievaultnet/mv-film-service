package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Currently disabled. Might be useful for the future.
 */
@Entity
@Table(name = "media")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", nullable = false)
    private EditionEntity edition;

    @Column(name = "disc_number", nullable = false)
    private int discNumber;

    @Column(name = "media_type", nullable = false)
    private String mediaType;

    @Column(name = "content")
    private String content;

    @Column(name = "notes")
    private String notes;

}
