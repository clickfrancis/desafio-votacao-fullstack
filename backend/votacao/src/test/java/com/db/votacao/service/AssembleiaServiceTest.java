package com.db.votacao.service;

import com.db.votacao.mapper.AssembleiaMapper;
import com.db.votacao.model.Assembleia;
import com.db.votacao.model.dto.request.AssembleiaRequestDTO;
import com.db.votacao.model.dto.response.AssembleiaResponseDTO;
import com.db.votacao.repository.AssembleiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssembleiaServiceTest {

    @Mock
    private AssembleiaRepository assembleiaRepository;

    @Mock
    private AssembleiaMapper assembleiaMapper;

    @InjectMocks
    private AssembleiaService assembleiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarAssembleiaComSucesso() {
        AssembleiaRequestDTO requestDTO = new AssembleiaRequestDTO("Assembleia Geral");
        Assembleia assembleia = new Assembleia();
        assembleia.setNome(requestDTO.nome());

        Assembleia assembleiaSalva = new Assembleia();
        assembleiaSalva.setId(1L);
        assembleiaSalva.setNome("Assembleia Geral");

        AssembleiaResponseDTO responseDTO = new AssembleiaResponseDTO(1L, "Assembleia Geral", LocalDateTime.now(), Collections.emptyList());

        when(assembleiaMapper.toEntity(requestDTO)).thenReturn(assembleia);
        when(assembleiaRepository.save(assembleia)).thenReturn(assembleiaSalva);
        when(assembleiaMapper.toResponseDTO(assembleiaSalva)).thenReturn(responseDTO);

        AssembleiaResponseDTO resultado = assembleiaService.criarAssembleia(requestDTO);

        assertNotNull(resultado);
        assertEquals(responseDTO.id(), resultado.id());
        assertEquals(responseDTO.nome(), resultado.nome());
        verify(assembleiaRepository, times(1)).save(assembleia);
    }

    @Test
    void deveObterAssembleiaPorIdComSucesso() {
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1L);
        assembleia.setNome("Assembleia Geral");

        AssembleiaResponseDTO responseDTO = new AssembleiaResponseDTO(1L, "Assembleia Geral", LocalDateTime.now(), Collections.emptyList());

        when(assembleiaRepository.findById(1L)).thenReturn(Optional.of(assembleia));
        when(assembleiaMapper.toResponseDTO(assembleia)).thenReturn(responseDTO);

        AssembleiaResponseDTO resultado = assembleiaService.obterAssembleia(1L);

        assertNotNull(resultado);
        assertEquals(responseDTO.id(), resultado.id());
        assertEquals(responseDTO.nome(), resultado.nome());
        verify(assembleiaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoObterAssembleiaPorIdInexistente() {
        when(assembleiaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> assembleiaService.obterAssembleia(1L));
        assertEquals("Assembleia não encontrada com ID: 1", exception.getMessage());
        verify(assembleiaRepository, times(1)).findById(1L);
    }

    @Test
    void deveListarTodasAsAssembleias() {
        Assembleia assembleia1 = new Assembleia();
        assembleia1.setId(1L);
        assembleia1.setNome("Assembleia Geral 1");

        Assembleia assembleia2 = new Assembleia();
        assembleia2.setId(2L);
        assembleia2.setNome("Assembleia Geral 2");

        List<Assembleia> assembleias = Arrays.asList(assembleia1, assembleia2);

        AssembleiaResponseDTO responseDTO1 = new AssembleiaResponseDTO(1L, "Assembleia Geral 1", LocalDateTime.now(), Collections.emptyList());
        AssembleiaResponseDTO responseDTO2 = new AssembleiaResponseDTO(2L, "Assembleia Geral 2",  LocalDateTime.now(), Collections.emptyList());

        when(assembleiaRepository.findAll()).thenReturn(assembleias);
        when(assembleiaMapper.toResponseDTO(assembleia1)).thenReturn(responseDTO1);
        when(assembleiaMapper.toResponseDTO(assembleia2)).thenReturn(responseDTO2);

        List<AssembleiaResponseDTO> resultado = assembleiaService.listarAssembleias();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(assembleiaRepository, times(1)).findAll();
    }
}
