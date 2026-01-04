package io.github.cciglesiasmartinez.microservice_template.application;

import io.github.cciglesiasmartinez.microservice_template.application.usecases.*;
import org.springframework.stereotype.Service;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.FilmUseCase;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.FilmRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.UpdateFilmRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.DeleteFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetFilmResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.UpdateFilmResponse;
// ✂️ Imports de paginación/DTO listado eliminados de aquí (se mueven al nuevo caso de uso)
// import io.github...PageResult;
// import io.github...listfilmsresponse.FilmSummaryDto;
// import io.github...listfilmsresponse.ListFilmsResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.listfilmsresponse.ListFilmsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FilmUseCaseImpl implements FilmUseCase {

    private final FilmRepository filmRepository;
    private final ListFilmsUseCase listFilmsUseCase;

    // TODO: Refactor these usecases, give each one a class then inject them here via constructor

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
	public Envelope<ListFilmsResponse> listFilms(int page, int size) {
		return listFilmsUseCase.execute(page, size);
	}

}

