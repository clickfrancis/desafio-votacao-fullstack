import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { vi, describe, it, expect,  } from 'vitest';
import ListarAssembleias from '../../pages/Assembleia'; 
import api from '../../service/api/api';

vi.mock('../../service/api/api', () => ({
  default: {
    get: vi.fn(),
  },
}));

describe('ListarAssembleias Component', () => {
  it('deve exibir assembleias quando carregar todas as assembleias', async () => {
    const assembleiasMock = [
      {
        id: 1,
        nome: 'Assembleia 1',
        dataCriacao: '2025-01-01',
        pautas: [{ id: 1, titulo: 'Pauta 1', descricao: 'Descrição da Pauta 1' }],
      },
      {
        id: 2,
        nome: 'Assembleia 2',
        dataCriacao: '2025-01-02',
        pautas: [],
      },
    ];

    (api.get as vi.Mock).mockResolvedValueOnce({ data: assembleiasMock });

    render(<ListarAssembleias />);

    fireEvent.click(screen.getByRole('button', { name: /Carregar todas/i }));

    await waitFor(() => {
      expect(screen.getByText('Assembleia 1')).toBeInTheDocument();
      expect(screen.getByText('Assembleia 2')).toBeInTheDocument();
    });

    expect(screen.getByText('Assembleias carregadas com sucesso!')).toBeInTheDocument();
  });

  it('deve exibir uma mensagem de erro ao falhar ao carregar as assembleias', async () => {
    (api.get as vi.Mock).mockRejectedValueOnce(new Error('Erro ao carregar as assembleias.'));

    render(<ListarAssembleias />);

    fireEvent.click(screen.getByRole('button', { name: /Carregar todas/i }));

      await waitFor(() => {
      expect(screen.getByText('Erro ao carregar as assembleias. Tente novamente.')).toBeInTheDocument();
    });
  });

  it('deve exibir assembleia ao buscar por ID', async () => {
    const assembleiaMock = {
      id: 1,
      nome: 'Assembleia 1',
      dataCriacao: '2025-01-01',
      pautas: [{ id: 1, titulo: 'Pauta 1', descricao: 'Descrição da Pauta 1' }],
    };

    (api.get as vi.Mock).mockResolvedValueOnce({ data: assembleiaMock });

    render(<ListarAssembleias />);

    fireEvent.change(screen.getByPlaceholderText('Buscar por ID'), { target: { value: '1' } });
    fireEvent.click(screen.getByRole('button', { name: /Buscar por ID/i }));

    await waitFor(() => {
      expect(screen.getByText('Assembleia 1')).toBeInTheDocument();
    });

    expect(screen.getByText('Assembleia encontrada!')).toBeInTheDocument();
  });

  it('deve exibir mensagem de erro ao buscar assembleia com ID inválido', async () => {
    (api.get as vi.Mock).mockRejectedValueOnce(new Error('Erro ao buscar assembleia. Verifique o ID informado.'));

    render(<ListarAssembleias />);

    fireEvent.change(screen.getByPlaceholderText('Buscar por ID'), { target: { value: '999' } });
    fireEvent.click(screen.getByRole('button', { name: /Buscar por ID/i }));

    await waitFor(() => {
      expect(screen.getByText('Erro ao buscar a assembleia. Verifique o ID informado.')).toBeInTheDocument();
    });
  });
});
