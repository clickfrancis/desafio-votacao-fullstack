package com.db.votacao.service;

import com.db.votacao.mapper.AssociadoMapper;
import com.db.votacao.model.Associado;
import com.db.votacao.model.dto.request.AssociadoRequestDTO;
import com.db.votacao.model.dto.response.AssociadoResponseDTO;
import com.db.votacao.repository.AssociadoRepository;
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

class AssociadoServiceTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private AssociadoMapper associadoMapper;

    @InjectMocks
    private AssociadoService associadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarAssociadoComSucesso() {
        AssociadoRequestDTO requestDTO = new AssociadoRequestDTO("João Silva", "12345678901");
        Associado associado = new Associado();
        associado.setNome(requestDTO.nome());
        associado.setCpf(requestDTO.cpf());

        Associado associadoSalvo = new Associado();
        associadoSalvo.setId(1L);
        associadoSalvo.setNome("João Silva");
        associadoSalvo.setCpf("12345678901");

        AssociadoResponseDTO responseDTO = new AssociadoResponseDTO(1L, "João Silva", "12345678901", null);

        when(associadoRepository.existsByCpf(requestDTO.cpf())).thenReturn(false);
        when(associadoRepository.save(any(Associado.class))).thenReturn(associadoSalvo);
        when(associadoMapper.toResponseDTO(associadoSalvo)).thenReturn(responseDTO);

        AssociadoResponseDTO resultado = associadoService.cadastrarAssociado(requestDTO);
        
        assertNotNull(resultado);
        assertEquals(responseDTO.id(), resultado.id());
        assertEquals(responseDTO.nome(), resultado.nome());
        verify(associadoRepository, times(1)).save(any(Associado.class));
    }

    @Test
    void deveLancarExcecaoAoCadastrarAssociadoComCpfJaExistente() {

        AssociadoRequestDTO requestDTO = new AssociadoRequestDTO("João Silva", "12345678901");
        when(associadoRepository.existsByCpf(requestDTO.cpf())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> associadoService.cadastrarAssociado(requestDTO));
        assertEquals("Já existe um associado cadastrado com o CPF: 12345678901", exception.getMessage());
        verify(associadoRepository, never()).save(any(Associado.class));
    }

    @Test
    void deveListarTodosOsAssociados() {
        Associado associado1 = new Associado();
        Associado associado2 = new Associado();
        List<Associado> associados = Arrays.asList(associado1, associado2);

        AssociadoResponseDTO responseDTO1 = new AssociadoResponseDTO(1L, "João Silva", "12345678901", null);
        AssociadoResponseDTO responseDTO2 = new AssociadoResponseDTO(2L, "Maria Souza", "98765432100", null);

        when(associadoRepository.findAll()).thenReturn(associados);
        when(associadoMapper.toResponseDTO(associado1)).thenReturn(responseDTO1);
        when(associadoMapper.toResponseDTO(associado2)).thenReturn(responseDTO2);

        List<AssociadoResponseDTO> resultado = associadoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(associadoRepository, times(1)).findAll();
    }

    @Test
    void deveObterAssociadoPorIdComSucesso() {
        Associado associado = new Associado();
        AssociadoResponseDTO responseDTO = new AssociadoResponseDTO(1L, "João Silva", "12345678901", null);

        when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
        when(associadoMapper.toResponseDTO(associado)).thenReturn(responseDTO);

        AssociadoResponseDTO resultado = associadoService.obterAssociadoPorId(1L);

        assertNotNull(resultado);
        assertEquals(responseDTO.id(), resultado.id());
        verify(associadoRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoObterAssociadoPorIdInexistente() {

        when(associadoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> associadoService.obterAssociadoPorId(1L));
        assertEquals("Associado não encontrado com ID: 1", exception.getMessage());
        verify(associadoRepository, times(1)).findById(1L);
    }

    @Test
    void deveValidarCpfComSucesso() {

        String cpfValido = "12345678901";

        boolean resultado = associadoService.validarCpf(cpfValido);

        assertTrue(resultado);
    }

    @Test
    void deveValidarCpfInvalido() {

        String cpfInvalido = "12345678";

        boolean resultado = associadoService.validarCpf(cpfInvalido);

        assertFalse(resultado);
    }
}
