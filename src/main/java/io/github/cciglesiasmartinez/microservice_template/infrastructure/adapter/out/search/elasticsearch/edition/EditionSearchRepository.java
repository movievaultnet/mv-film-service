package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.search.elasticsearch.edition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionSearchRepository extends ElasticsearchRepository<EditionDocument, String> {

    Page<EditionDocument> findBySearchableText(String text, Pageable pageable);

}
