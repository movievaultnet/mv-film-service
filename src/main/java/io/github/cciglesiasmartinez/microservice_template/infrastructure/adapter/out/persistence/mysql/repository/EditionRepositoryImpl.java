package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.Edition;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.BarCode;
import io.github.cciglesiasmartinez.microservice_template.domain.model.edition.valueobjects.EditionId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.EditionRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.EditionEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper.EditionEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional(readOnly = true)
public class EditionRepositoryImpl implements EditionRepository {

    private final SpringDataEditionRepository springDataEditionRepository;
    private final EditionEntityMapper editionEntityMapper;

    @Override
    public Edition create(Edition edition) {
        EditionEntity saved = springDataEditionRepository.save(editionEntityMapper.toEntity(edition));
        return editionEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Edition> findById(EditionId id) {
        return springDataEditionRepository.findById(id.value()).map(editionEntityMapper::toDomain);
    }

    @Override
    public List<Edition> findAll() {
        return springDataEditionRepository.findAll().stream().map(editionEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Edition> findByBarCode(BarCode barcode) {
        return springDataEditionRepository.findByBarCode(barcode.value()).map(editionEntityMapper::toDomain);
    }

    @Override
    public boolean existsById(EditionId id) {
        return springDataEditionRepository.existsById(id.value());
    }

    @Override
    public boolean existsByBarCode(BarCode barcode) {
        return springDataEditionRepository.existsByBarCode(barcode.value());
    }

    @Override
    @Transactional
    public Edition update(Edition edition) {
        EditionEntity entity = springDataEditionRepository
                .findById(edition.editionId().value()) // Updates through dirty checking
                .orElseThrow();
        return editionEntityMapper.updateEntity(entity, edition);
    }


    @Override
    public Edition save(Edition edition) {
        EditionEntity saved = springDataEditionRepository.save(editionEntityMapper.toEntity(edition));
        return editionEntityMapper.toDomain(saved);
    }

    @Override
    public void persist(Edition edition) {
        EditionEntity saved = springDataEditionRepository.save(editionEntityMapper.toEntity(edition));
    }

    @Override
    public void delete(Edition edition) {
        springDataEditionRepository.delete(editionEntityMapper.toEntity(edition));
    }

    @Override
    @Transactional
    public boolean deleteById(EditionId id) {
        if (!springDataEditionRepository.existsById(id.value())) return false;
        springDataEditionRepository.deleteById(id.value());
        return true;
    }

    @Override
    public PageResult<Edition> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EditionEntity> p = springDataEditionRepository.findAll(pageable);

        var content = p.getContent().stream().map(editionEntityMapper::toDomain).toList();

        return new PageResult<>(
                content,
                p.getNumber(),
                p.getSize(),
                p.getTotalElements(),
                p.getTotalPages(),
                p.hasNext(),
                p.hasPrevious()
        );
    }
}
