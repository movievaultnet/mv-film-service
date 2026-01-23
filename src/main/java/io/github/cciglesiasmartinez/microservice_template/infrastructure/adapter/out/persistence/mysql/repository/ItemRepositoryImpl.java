package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.domain.model.item.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.item.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.item.wrappers.CollectionItemWrapper;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper.ItemEntityMapper;
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
public class ItemRepositoryImpl implements ItemRepository {

    private final SpringDataItemRepository springDataItemRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public Item create(Item item) {
        ItemEntity created = springDataItemRepository.save(itemEntityMapper.toEntity(item));
        return itemEntityMapper.toDomain(created);
    }

    @Override
    public Optional<Item> findById(ItemId itemId) {
        return springDataItemRepository.findById(itemId.value()).map(itemEntityMapper::toDomain);
    }

    @Override
    public List<Item> findAll() {
        return List.of();
    }

    @Override
    public List<Item> findAllByUserId(String userId) {
        return List.of();
    }

    @Override
    public PageResult<CollectionItemWrapper> findPageByUserId(int page, int size, String userId) {
        Pageable pageable = PageRequest.of(page,size);
        Page<CollectionItemWrapper> p = springDataItemRepository.findAllByUserId(userId, pageable);
//        List<Item> elements = p.getContent().stream().map(itemEntityMapper::toDomain).toList();
        return new PageResult<>(
                p.getContent(),
                p.getNumber(),
                p.getSize(),
                p.getTotalElements(),
                p.getTotalPages(),
                p.hasNext(),
                p.hasPrevious()
        );
    }

    @Override
    public PageResult<Item> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemEntity> p = springDataItemRepository.findAll(pageable);
        List<Item> elements = p.getContent().stream().map(itemEntityMapper::toDomain).toList();
        return new PageResult<>(
                elements,
                p.getNumber(),
                p.getSize(),
                p.getTotalElements(),
                p.getTotalPages(),
                p.hasNext(),
                p.hasPrevious()
        );
    }

    @Override
    @Transactional
    public void update(Item item) {
        ItemEntity entity = springDataItemRepository
                .findById(item.id().value())
                .orElseThrow(() -> new IllegalArgumentException("Item ID not found."));
        itemEntityMapper.updateEntity(entity, item);
    }

    @Override
    public void deleteById(ItemId id) {
        springDataItemRepository.deleteById(id.value());
    }
}
