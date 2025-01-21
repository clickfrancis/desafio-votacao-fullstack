package com.db.votacao.service;

import com.db.votacao.fixture.SessaoResponseDTOFixture;
import com.db.votacao.mapper.SessaoMapper;
import com.db.votacao.model.Pauta;
import com.db.votacao.model.Sessao;
import com.db.votacao.model.dto.response.SessaoResponseDTO;
import com.db.votacao.repository.PautaRepository;
import com.db.votacao.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class SessaoVotacaoServcieTest {

        @Mock
        private PautaRepository pautaRepository;

        @Mock
        private SessaoRepository sessaoRepository;

        @Mock
        private SessaoMapper sessaoMapper;

        @InjectMocks
        private SessaoVotacaoService sessaoVotacaoService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void deveAbrirSessaoVotacaoComSucesso() {
            Long pautaId = 1L;
            Integer tempoEmMinutos = 30;

            Pauta pauta = new Pauta();
            pauta.setId(pautaId);
            pauta.setTitulo("Pauta Teste");

            Sessao sessao = new Sessao();
            sessao.setId(1L);
            sessao.setPauta(pauta);

            SessaoResponseDTO sessaoResponseDTO = SessaoResponseDTOFixture.criarSessaoResponseDTOPadrao();

            when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));
            when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);
            when(sessaoMapper.toResponseDTO(sessao)).thenReturn(sessaoResponseDTO);

            SessaoResponseDTO resultado = sessaoVotacaoService.abrirSessaoVotacao(pautaId, tempoEmMinutos);

            assertNotNull(resultado);
            assertEquals(sessaoResponseDTO.id(), resultado.id());
            assertEquals(sessaoResponseDTO.pauta().id(), resultado.pauta().id());
            verify(pautaRepository, times(1)).findById(pautaId);
            verify(sessaoRepository, times(1)).save(any(Sessao.class));
        }

        @Test
        void deveListarTodasAsSessoes() {
            Sessao sessao = new Sessao();
            sessao.setId(1L);

            List<Sessao> sessoes = Collections.singletonList(sessao);
            SessaoResponseDTO sessaoResponseDTO = SessaoResponseDTOFixture.criarSessaoResponseDTOPadrao();

            when(sessaoRepository.findAll()).thenReturn(sessoes);
            when(sessaoMapper.toListSessaoResponseDTO(sessoes)).thenReturn(Collections.singletonList(sessaoResponseDTO));

            List<SessaoResponseDTO> resultado = sessaoVotacaoService.listarTodasSessoes();

            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            assertEquals(sessaoResponseDTO.id(), resultado.get(0).id());
            verify(sessaoRepository, times(1)).findAll();
        }

        @Test
        void deveBuscarSessaoPorIdComSucesso() {
            Long sessaoId = 1L;

            Sessao sessao = new Sessao();
            sessao.setId(sessaoId);

            SessaoResponseDTO sessaoResponseDTO = SessaoResponseDTOFixture.criarSessaoResponseDTOPadrao();

            when(sessaoRepository.findById(sessaoId)).thenReturn(Optional.of(sessao));
            when(sessaoMapper.toResponseDTO(sessao)).thenReturn(sessaoResponseDTO);

            SessaoResponseDTO resultado = sessaoVotacaoService.buscarSessaoPorId(sessaoId);

            assertNotNull(resultado);
            assertEquals(sessaoResponseDTO.id(), resultado.id());
            verify(sessaoRepository, times(1)).findById(sessaoId);
        }

        @Test
        void deveLancarExcecaoQuandoSessaoNaoForEncontrada() {
            Long sessaoId = 1L;

            when(sessaoRepository.findById(sessaoId)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> sessaoVotacaoService.buscarSessaoPorId(sessaoId));
            assertEquals("Sessão não encontrada com o ID: " + sessaoId, exception.getMessage());
            verify(sessaoRepository, times(1)).findById(sessaoId);
        }
    }
