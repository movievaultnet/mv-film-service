package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.RefreshPolicy;

import java.util.Optional;

public class EditionSearchRepositoryImpl implements EditionSearchRepository{

    @Override
    public Optional<PageResult<EditionDocument>> searchBySearchableText(String text, int page, int size) {
        return null;
    }

    @Override
    public Page<EditionDocument> searchSimilar(EditionDocument entity, String[] fields, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends EditionDocument> S save(S entity, RefreshPolicy refreshPolicy) {
        return null;
    }

    @Override
    public <S extends EditionDocument> Iterable<S> saveAll(Iterable<S> entities, RefreshPolicy refreshPolicy) {
        return null;
    }

    @Override
    public void deleteById(String s, RefreshPolicy refreshPolicy) {

    }

    @Override
    public void delete(EditionDocument entity, RefreshPolicy refreshPolicy) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings, RefreshPolicy refreshPolicy) {

    }

    @Override
    public void deleteAll(Iterable<? extends EditionDocument> entities, RefreshPolicy refreshPolicy) {

    }

    @Override
    public void deleteAll(RefreshPolicy refreshPolicy) {

    }

    @Override
    public <S extends EditionDocument> S save(S entity) {
        return null;
    }

    @Override
    public <S extends EditionDocument> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<EditionDocument> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<EditionDocument> findAll() {
        return null;
    }

    @Override
    public Iterable<EditionDocument> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(EditionDocument entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends EditionDocument> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Iterable<EditionDocument> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<EditionDocument> findAll(Pageable pageable) {
        return null;
    }
}
