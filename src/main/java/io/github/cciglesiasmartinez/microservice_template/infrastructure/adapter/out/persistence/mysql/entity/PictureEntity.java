package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pictures")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PictureEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", nullable = false)
    private EditionEntity edition;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    void assignTo(EditionEntity edition) {
        this.edition = edition;
    }
}
