package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.UpdateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;

public interface FilmUseCase {
	
	// CREATE
    Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request);
    // (Opcional) soporte de idempotencia en la request (idempotencyKey)

    // READ
    Envelope<GetFilmResponse> getFilm(String id); // Pay attention here, an adapter DOES NOT receive domain entities/value objects
  
    // UPDATE (PUT completo o PATCH parcial)
    Envelope<UpdateFilmResponse> updateFilm(UpdateFilmRequest request);
    // Sugerencia: control de concurrencia optimista (version/E-Tag) en request

    // DELETE
    Envelope<DeleteFilmResponse> deleteFilm(String id);
    // o Envelope<Void> si no necesitas payload
    
    Envelope<ListFilmsResponse> listFilms(int page, int size);
    
    // Aqui o con Mono si se tiene que ahcer, o con Envelope directamente, el metodo de TmdbSearch
    // Mono<Envelope<TmdbSearchResponse>> tmdbSearch(TmdbSearchRequest request)
    
}


