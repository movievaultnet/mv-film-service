package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.application.usecases.film.*;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.requests.TmdbSearchRequest;
import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.FilmUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.requests.TmdbDiscoverRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.film.responses.UpdateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.common.responses.ListGenericResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.tmdb.responses.TmdbFilmListResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FilmUseCaseImpl implements FilmUseCase {

	
    private final ListFilmsUseCase listFilmsUseCase;
    private final TmdbDiscoverUseCase tmdbDiscoverUseCase;
    private final TmdbSearchUseCase tmdbSearchUseCase;
    private final CreateFilmUseCase createFilmUseCase;
    private final GetFilmUseCase getFilmUseCase;
    private final UpdateFilmUseCase updateFilmUseCase;
    private final DeleteFilmUseCase deleteFilmUseCase;

    @Override
    public Envelope<CreateFilmResponse> createFilm(CreateFilmRequest request) {
        return createFilmUseCase.execute(request);
    }

    @Override
    public Envelope<GetFilmResponse> getFilm(String filmId) {
        return getFilmUseCase.execute(filmId);
    }

    @Override
    public Envelope<UpdateFilmResponse> updateFilm(UpdateFilmRequest request) {
        return updateFilmUseCase.execute(request);
    }

    @Override
    public Envelope<DeleteFilmResponse> deleteFilm(String id) {
        return deleteFilmUseCase.execute(id);
    }

    @Override
    public Envelope<ListGenericResponse> listFilms(int page, int size) {
        return listFilmsUseCase.execute(page, size);
    }

	@Override
	public Envelope<TmdbFilmListResponse> tmdbDiscover(TmdbDiscoverRequest request) {
		return tmdbDiscoverUseCase.execute(request);
	}

    @Override
    public Envelope<TmdbFilmListResponse> tmdbSearch(TmdbSearchRequest request) {
        return tmdbSearchUseCase.execute(request);
    }

}

