package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition;

import io.github.cciglesiasmartinez.microservice_template.domain.shared.PageResult;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditionSearchRepository extends ElasticsearchRepository<EditionDocument, String> {

    Optional<PageResult<EditionDocument>> searchBySearchableText(String text, int page, int size);

}
