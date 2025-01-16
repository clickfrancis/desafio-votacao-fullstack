import React, { useState } from 'react';
import { Input, Table, message } from 'antd';
import api from './../service/api/api';
import CustomButton from '../components/buttons/CustomButton';

type Voto = {
  id: number;
  voto: string;
  sessaoId: number;
  pautaId: number;
  associadoId: number;
};

type SessaoVotacao = {
  id: number;
  inicio: string;
  fim: string;
  pauta: null | {
    id: number;
    titulo: string;
    descricao: string;
  };
  votos: Voto[];
};

const SessaoVotacaoTable: React.FC = () => {
  const [sessoes, setSessoes] = useState<SessaoVotacao[]>([]);
  const [buscaId, setBuscaId] = useState<string>('');

  const listarSessoes = async () => {
    try {
      const response = await api.get('/sessao-votacao');
      if (Array.isArray(response.data)) {
        setSessoes(response.data);
        message.success('Sessões de votação carregadas com sucesso!');
      } else {
        message.error('A resposta da API não contém dados válidos.');
      }
    } catch (error) {
      console.error('Erro ao carregar sessões:', error);
      message.error('Erro ao carregar as sessões. Tente novamente.');
    }
  };

  const buscarSessaoPorId = async () => {
    if (!buscaId.trim()) {
      message.warning('Por favor, insira um ID para buscar.');
      return;
    }

    try {
      const response = await api.get<SessaoVotacao>(`/sessao-votacao/${buscaId}`);
      setSessoes([response.data]);
      message.success('Sessão de votação carregada com sucesso!');
    } catch (error) {
      console.error('Erro ao buscar sessão:', error);
      message.error('Erro ao buscar a sessão. Verifique o ID e tente novamente.');
    }
  };

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Início',
      dataIndex: 'inicio',
      key: 'inicio',
      render: (inicio: string) => new Date(inicio).toLocaleString(),
    },
    {
      title: 'Fim',
      dataIndex: 'fim',
      key: 'fim',
      render: (fim: string) => new Date(fim).toLocaleString(),
    },
    {
      title: 'Pauta',
      dataIndex: 'pauta',
      key: 'pauta',
      render: (pauta: any) => (pauta ? pauta.titulo : 'Não vinculada'),
    },
    {
      title: 'Quantidade de Votos',
      dataIndex: 'votos',
      key: 'votos',
      render: (votos: Voto[]) => votos.length,
    },
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h1>Lista de Sessões de Votação</h1>
      <div style={{ marginBottom: '20px', display: 'flex', gap: '10px' }}>
        <Input
          placeholder="Digite o ID da sessão"
          value={buscaId}
          onChange={(e) => setBuscaId(e.target.value)}
          style={{ width: '300px' }}
        />
        <CustomButton
          label="Buscar por ID"
          onClick={buscarSessaoPorId}
          type='primary'
        />
        <CustomButton
          label="Carregar Todas"
          onClick={listarSessoes}
          type='default'
        />
      </div>
      <Table dataSource={sessoes} columns={columns} rowKey="id" />
    </div>
  );
};

export default SessaoVotacaoTable;
