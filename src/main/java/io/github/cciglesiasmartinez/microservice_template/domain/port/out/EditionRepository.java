package io.github.cciglesiasmartinez.microservice_template.domain.port.out;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.BarCode;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;

import java.util.List;
import java.util.Optional;

public interface EditionRepository {

    // Create
    Edition create(Edition edition);

    // Read
    Optional<Edition> findById(EditionId id);
    List<Edition> findAll();
    Optional<Edition> findByBarCode(BarCode barcode);
    boolean existsById(EditionId id);
    boolean existsByBarCode(BarCode barcode);
    PageResult<Edition> findPage(int page, int size);

    // Update
    Edition update(Edition edition);
    Edition save(Edition edition);
    void persist(Edition edition);

    // Delete
    void delete(Edition edition);
    boolean deleteById(EditionId id);

    // TODO: Clean redundant methods. Do the same for the other repo interface (Film).
}
