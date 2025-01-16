package com.db.votacao.service;

import com.db.votacao.mapper.PautaMapper;
import com.db.votacao.model.Assembleia;
import com.db.votacao.model.Enum.VotoOpcao;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Voto;
import com.db.votacao.model.dto.request.PautaRequestDTO;
import com.db.votacao.model.dto.response.PautaResponseDTO;
import com.db.votacao.model.dto.response.ResultadoVotacaoResponseDTO;
import com.db.votacao.repository.AssembleiaRepository;
import com.db.votacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private AssembleiaRepository assembleiaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarPautaComSucesso() {
        PautaRequestDTO requestDTO = new PautaRequestDTO("Titulo da Pauta", null, null);
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1L);

        Pauta pauta = new Pauta();
        pauta.setTitulo("Titulo da Pauta");
        pauta.setAssembleia(assembleia);

        Pauta pautaSalva = new Pauta();
        pautaSalva.setId(1L);
        pautaSalva.setTitulo("Titulo da Pauta");
        pautaSalva.setAssembleia(assembleia);

        PautaResponseDTO responseDTO = new PautaResponseDTO(1L, "Titulo da Pauta", null, null);

        when(assembleiaRepository.findById(1L)).thenReturn(Optional.of(assembleia));
        when(pautaMapper.toEntity(requestDTO)).thenReturn(pauta);
        when(pautaRepository.save(pauta)).thenReturn(pautaSalva);
        when(pautaMapper.toResponseDTO(pautaSalva)).thenReturn(responseDTO);

        PautaResponseDTO resultado = pautaService.cadastrarPauta(requestDTO);

        assertNotNull(resultado);
        assertEquals(responseDTO.id(), resultado.id());
        assertEquals(responseDTO.titulo(), resultado.titulo());
        verify(pautaRepository, times(1)).save(pauta);
    }

    @Test
    void deveLancarExcecaoAoCadastrarPautaComAssembleiaInexistente() {
        PautaRequestDTO requestDTO = new PautaRequestDTO("Titulo da Pauta", null,null);

        when(assembleiaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> pautaService.cadastrarPauta(requestDTO));
        assertEquals("Assembleia não encontrada com ID: 99", exception.getMessage());
        verify(pautaRepository, never()).save(any());
    }

    @Test
    void deveListarPautasComSucesso() {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        pauta1.setTitulo("Pauta 1");

        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setTitulo("Pauta 2");

        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);

        PautaResponseDTO responseDTO1 = new PautaResponseDTO(1L, "Pauta 1", null, null);
        PautaResponseDTO responseDTO2 = new PautaResponseDTO(2L, "Pauta 2", null, null);

        when(pautaRepository.findAll()).thenReturn(pautas);
        when(pautaMapper.toResponseDTO(pauta1)).thenReturn(responseDTO1);
        when(pautaMapper.toResponseDTO(pauta2)).thenReturn(responseDTO2);

        List<PautaResponseDTO> resultado = pautaService.listarPautas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(pautaRepository, times(1)).findAll();
    }

    @Test
    void deveContabilizarVotosComSucesso() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Pauta Teste");

        Voto voto1 = new Voto();
        voto1.setVoto(VotoOpcao.SIM);

        Voto voto2 = new Voto();
        voto2.setVoto(VotoOpcao.NAO);

        Voto voto3 = new Voto();
        voto3.setVoto(VotoOpcao.SIM);

        pauta.setVotos(Arrays.asList(voto1, voto2, voto3));

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        ResultadoVotacaoResponseDTO resultado = pautaService.contabilizarVotos(1L);

        assertNotNull(resultado);
        assertEquals(3, resultado.totalVotos());
        assertEquals(2, resultado.votosSim());
        assertEquals(1, resultado.votosNao());
        assertEquals("Aprovado", resultado.resultado());
        verify(pautaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoContabilizarVotosDePautaInexistente() {
        when(pautaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> pautaService.contabilizarVotos(99L));
        assertEquals("Pauta não encontrada.", exception.getMessage());
        verify(pautaRepository, times(1)).findById(99L);
    }
}
