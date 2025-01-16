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

const VotosTable: React.FC = () => {
  const [votos, setVotos] = useState<Voto[]>([]);
  const [buscaId, setBuscaId] = useState<string>('');

  const listarVotos = async () => {
    try {
      const response = await api.get('/voto');
      if (Array.isArray(response.data)) {
        setVotos(response.data);
        message.success('Votos carregados com sucesso!');
      } else {
        message.error('A resposta da API não contém dados válidos.');
      }
    } catch (error) {
      console.error('Erro ao carregar votos:', error);
      message.error('Erro ao carregar os votos. Tente novamente.');
    }
  };

  const buscarVotoPorId = async () => {
    if (!buscaId.trim()) {
      message.warning('Por favor, insira um ID para buscar.');
      return;
    }

    try {
      const response = await api.get<Voto>(`/voto/${buscaId}`);
      setVotos([response.data]);
      message.success('Voto carregado com sucesso!');
    } catch (error) {
      console.error('Erro ao buscar voto:', error);
      message.error('Erro ao buscar o voto. Verifique o ID e tente novamente.');
    }
  };

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Voto',
      dataIndex: 'voto',
      key: 'voto',
      render: (voto: string) => (voto === 'SIM' ? 'Sim' : 'Não'),
    },
    {
      title: 'ID da Sessão',
      dataIndex: 'sessaoId',
      key: 'sessaoId',
    },
    {
      title: 'ID da Pauta',
      dataIndex: 'pautaId',
      key: 'pautaId',
    },
    {
      title: 'ID do Associado',
      dataIndex: 'associadoId',
      key: 'associadoId',
    },
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h1>Lista de Votos</h1>
      <div style={{ marginBottom: '20px', display: 'flex', gap: '10px' }}>
        <Input
          placeholder="Digite o ID do voto"
          value={buscaId}
          onChange={(e) => setBuscaId(e.target.value)}
          style={{ width: '300px', }}
        />
        <CustomButton
          label="Buscar por ID"
          onClick={buscarVotoPorId}
          type='primary'
        />
        <CustomButton
          label="Carregar Todos"
          onClick={listarVotos}
          type='default'
        />
      </div>
      <Table dataSource={votos} columns={columns} rowKey="id" />
    </div>
  );
};

export default VotosTable;
