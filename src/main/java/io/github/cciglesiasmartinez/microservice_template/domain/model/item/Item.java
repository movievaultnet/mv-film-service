package io.github.cciglesiasmartinez.microservice_template.domain.model.item;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.Condition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Domain entity representing a collection item owned by a user.
 * <p>
 * An {@code Item} is a specific instance of an {@link Edition} with associated condition,
 * purchase information, and ownership details. Each item has a unique {@link ItemId} and
 * tracks audit timestamps for creation and modification.
 * </p>
 * <p>
 * Items are immutable and can only be created through factory methods to ensure valid state.
 * </p>
 *
 * @see ItemId
 * @see Edition
 * @see Condition
 */
public class Item {

    private final ItemId id;
    private final Edition edition;
    private final String userId;
    private final String purchasePlace;
    private final LocalDate purchaseDate;
    private final Double purchasePrice;
    private final Condition mediaCondition;
    private final Condition caseCondition;
    private final String comments;
    private final LocalDateTime addedDate;
    private LocalDateTime lastModified;

    private Item(
            ItemId id,
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments,
            LocalDateTime addedDate,
            LocalDateTime lastModified) {
        this.id = id;
        this.edition = edition;
        this.userId = userId;
        this.purchasePlace = purchasePlace;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.mediaCondition = mediaCondition;
        this.caseCondition = caseCondition;
        this.comments = comments;
        this.addedDate = addedDate;
        this.lastModified = lastModified;
    }

    /**
     * Factory method to create a new Item instance with auto-generated ID and timestamps.
     * <p>
     * This method encapsulates the creation logic for an Item, ensuring that:
     * <ul>
     *   <li>A unique {@link ItemId} is automatically generated</li>
     *   <li>Creation and modification timestamps are set to current time</li>
     *   <li>All required associations and properties are properly initialized</li>
     * </ul>
     * </p>
     *
     * @param edition        the {@link Edition} associated with this item. Must not be null.
     * @param userId         a unique identifier of the user who owns this item. Must not be null or empty.
     * @param purchasePlace  the place where the item was purchased (e.g., store name, website). May be null.
     * @param purchaseDate   the {@link LocalDate} when the item was purchased. May be null if unknown.
     * @param purchasePrice  item purchase price in the currency of the transaction. May be null if not disclosed.
     * @param mediaCondition the {@link Condition} of the physical media (e.g., disc, cartridge). Must not be null.
     * @param caseCondition  the {@link Condition} of the case or packaging. Must not be null.
     * @param comments       additional user comments or notes about the item. May be null or empty.
     *
     * @return a new {@link Item} instance with generated ID and current timestamps for both
     *         {@code addedDate} and {@code lastModified} fields
     *
     * @throws NullPointerException if {@code edition}, {@code userId}, {@code mediaCondition},
     *                              or {@code caseCondition} are null
     *
     * @see ItemId#generate()
     * @see Edition
     * @see Condition
     */
    public static Item create(
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments) {
        LocalDateTime now = LocalDateTime.now();
        return new Item(
                ItemId.generate(),
                edition,
                userId,
                purchasePlace,
                purchaseDate,
                purchasePrice,
                mediaCondition,
                caseCondition,
                comments,
                now,
                now
        );
    }

    /**
     * Reconstructs an Item from persisted data.
     * <p>
     * Used by the persistence layer to reconstitute domain objects from the database.
     * Accepts all fields including existing ID and timestamps.
     * </p>
     *
     * @param id             existing {@link ItemId}
     * @param edition        associated {@link Edition}
     * @param userId         owner's identifier
     * @param purchasePlace  purchase location (optional)
     * @param purchaseDate   purchase date (optional)
     * @param purchasePrice  purchase price (optional)
     * @param mediaCondition media {@link Condition}
     * @param caseCondition  case {@link Condition}
     * @param comments       user notes (optional)
     * @param addedDate      creation timestamp
     * @param lastModified   last modification timestamp
     *
     * @return reconstituted {@link Item}
     *
     * @see #create(Edition, String, String, LocalDate, Double, Condition, Condition, String)
     */
    public static Item of(
            ItemId id,
            Edition edition,
            String userId,
            String purchasePlace,
            LocalDate purchaseDate,
            Double purchasePrice,
            Condition mediaCondition,
            Condition caseCondition,
            String comments,
            LocalDateTime addedDate,
            LocalDateTime lastModified) {
        return new Item(
                id,
                edition,
                userId,
                purchasePlace,
                purchaseDate,
                purchasePrice,
                mediaCondition,
                caseCondition,
                comments,
                addedDate,
                lastModified
        );
    }

    /**
     * Marks a {@link Item} as modified.
     */
    public void markAsModified() {
        this.lastModified = LocalDateTime.now();
    }

    public ItemId id() {
        return this.id;
    }
    public Edition edition() {
        return this.edition;
    }
    public String userId() {
        return this.userId;
    }
    public String purchasePlace() {
        return this.purchasePlace;
    }
    public LocalDate purchaseDate() {
        return this.purchaseDate;
    }
    public Double purchasePrice() {
        return this.purchasePrice;
    }
    public Condition mediaCondition() {
        return this.mediaCondition;
    }
    public Condition caseCondition() {
        return this.caseCondition;
    }
    public String comments() {
        return this.comments;
    }
    public LocalDateTime addedDate() {
        return this.addedDate;
    }
    public LocalDateTime lastModified() {
        return this.lastModified;
    }

}
