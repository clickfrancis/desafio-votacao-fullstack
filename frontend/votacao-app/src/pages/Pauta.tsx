import React, { useState } from 'react';
import { Input, Table, message } from 'antd';
import api from './../service/api/api';
import CustomButton from '../components/buttons/CustomButton';

type Pauta = {
  id: number;
  titulo: string;
  descricao: string;
  assembleia: {
    id: number;
    nome: string;
    dataCriacao: string;
  } | null;
};

const ListarPautas: React.FC = () => {
  const [pautas, setPautas] = useState<Pauta[]>([]);
  const [buscaId, setBuscaId] = useState<string>('');

  const listarPautas = async () => {
    try {
      const response = await api.get('/pauta');
      if (Array.isArray(response.data)) {
        setPautas(response.data);
        message.success('Pautas carregadas com sucesso!');
      } else {
        message.error('A resposta da API não contém dados válidos.');
      }
    } catch (error) {
      console.error('Erro ao carregar pautas:', error);
      message.error('Erro ao carregar as pautas. Tente novamente.');
    }
  };

  const buscarPautaPorId = async () => {
    if (!buscaId.trim()) {
      message.warning('Por favor, insira um ID para buscar.');
      return;
    }

    try {
      const response = await api.get<Pauta>(`/pauta/${buscaId}`);
      setPautas([response.data]);
      message.success('Pauta carregada com sucesso!');
    } catch (error) {
      console.error('Erro ao buscar pauta:', error);
      message.error('Erro ao buscar a pauta. Verifique o ID e tente novamente.');
    }
  };

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Título',
      dataIndex: 'titulo',
      key: 'titulo',
    },
    {
      title: 'Descrição',
      dataIndex: 'descricao',
      key: 'descricao',
    },
    {
      title: 'Assembleia',
      key: 'assembleia',
      render: (_: unknown, record: Pauta) =>
        record.assembleia ? record.assembleia.nome : 'Sem assembleia',
    }
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h1>Lista de Pautas</h1>
      <div style={{ marginBottom: '20px', display: 'flex', gap: '10px' }}>
        <Input
          placeholder="Digite o ID da pauta"
          value={buscaId}
          onChange={(e) => setBuscaId(e.target.value)}
          style={{ width: '300px' }}
        />
        <CustomButton
        label="Buscar por ID"
        onClick={buscarPautaPorId}
        type='primary'
      />
        <CustomButton
        label="Carregar Todas"
        onClick={listarPautas}
        type='default'
      />
      </div>
      <Table dataSource={pautas} columns={columns} rowKey="id" />
    </div>
  );
};

export default ListarPautas;
