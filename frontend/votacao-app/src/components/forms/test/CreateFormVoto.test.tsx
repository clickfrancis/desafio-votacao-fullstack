import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { describe, it, vi, expect } from 'vitest';
import CreateFormVoto from './../CeateFormVoto';
import api from '../../../service/api/api';

// Mock do módulo API
vi.mock('../../service/api/api', () => ({
  default: {
    post: vi.fn(),
  },
}));

describe('CreateFormVoto Component', () => {
  it('deve renderizar todos os campos do formulário', () => {
    render(<CreateFormVoto />);

    expect(screen.getByText('Registrar seu voto')).toBeInTheDocument();
    expect(screen.getByLabelText('Opção de Voto')).toBeInTheDocument();
    expect(screen.getByLabelText('ID da Sessão')).toBeInTheDocument();
    expect(screen.getByLabelText('ID da Pauta')).toBeInTheDocument();
    expect(screen.getByLabelText('ID do Associado')).toBeInTheDocument();
    expect(screen.getByText('Registrar')).toBeInTheDocument();
  });

  it('deve exibir mensagens de erro ao tentar enviar o formulário vazio', async () => {
    render(<CreateFormVoto />);

    const submitButton = screen.getByText('Registrar');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.getByText('Por favor, escolha uma opção de voto!')).toBeInTheDocument();
      expect(screen.getByText('Por favor, insira o ID da sessão!')).toBeInTheDocument();
      expect(screen.getByText('Por favor, insira o ID da pauta!')).toBeInTheDocument();
      expect(screen.getByText('Por favor, insira o ID do associado!')).toBeInTheDocument();
    });
  });

  it('deve chamar a API ao submeter o formulário com dados válidos', async () => {
    const mockApiResponse = { data: { message: 'Voto registrado com sucesso!' } };
    (api.post as  ReturnType<typeof vi.fn>).mockResolvedValueOnce(mockApiResponse);

    render(<CreateFormVoto />);

    fireEvent.change(screen.getByLabelText('ID da Sessão'), { target: { value: 1 } });
    fireEvent.change(screen.getByLabelText('ID da Pauta'), { target: { value: 1 } });
    fireEvent.change(screen.getByLabelText('ID do Associado'), { target: { value: 1 } });
    fireEvent.click(screen.getByLabelText('Sim'));

    const submitButton = screen.getByText('Registrar');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(api.post).toHaveBeenCalledWith('/voto', {
        votoOpcao: 'SIM',
        sessaoId: 1,
        pautaId: 1,
        associadoId: 1,
      });
    });

    expect(screen.queryByText('Voto registrado com sucesso!')).toBeInTheDocument();
  });

  it('deve exibir uma mensagem de erro ao falhar na submissão', async () => {
    (api.post as jest.Mock).mockRejectedValueOnce(new Error('Erro ao registrar o voto.'));

    render(<CreateFormVoto />);

    fireEvent.change(screen.getByLabelText('ID da Sessão'), { target: { value: 1 } });
    fireEvent.change(screen.getByLabelText('ID da Pauta'), { target: { value: 1 } });
    fireEvent.change(screen.getByLabelText('ID do Associado'), { target: { value: 1 } });
    fireEvent.click(screen.getByLabelText('Sim'));

    const submitButton = screen.getByText('Registrar');
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(screen.queryByText('Erro ao registrar o voto. Tente novamente.')).toBeInTheDocument();
    });
  });
});
