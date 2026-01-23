package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "items")
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", nullable = false)
    private EditionEntity edition;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "purchase_place")
    private String purchasePlace;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_condition")
    private Condition mediaCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_condition")
    private Condition caseCondition;

    @Column(name = "comments")
    private String comments;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

}
