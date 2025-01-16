import React, { useState } from 'react';
import { Input, Table, message } from 'antd';
import api from './../service/api/api';
import CustomButton from '../components/buttons/CustomButton';

type Associado = {
  id: number;
  nome: string;
  cpf: string;
  status: string;
};

const ListarAssociado: React.FC = () => {
  const [associados, setAssociados] = useState<Associado[]>([]);
  const [buscaId, setBuscaId] = useState<string>('');

  const listarAssociados = async () => {
    try {
      const response = await api.get('/associado');
      if (Array.isArray(response.data)) {
        setAssociados(response.data);
        message.success('Associados carregados com sucesso!');
      } else {
        message.error('A resposta da API não contém dados válidos.');
      }
    } catch (error) {
      console.error('Erro ao carregar associados:', error);
      message.error('Erro ao carregar os associados. Tente novamente.');
    }
  };

  const buscarAssociadoPorId = async () => {
    if (!buscaId.trim()) {
      message.warning('Por favor, insira um ID para buscar.');
      return;
    }

    try {
      const response = await api.get<Associado>(`/associado/${buscaId}`);
      setAssociados([response.data]);
      message.success('Associado carregado com sucesso!');
    } catch (error) {
      console.error('Erro ao buscar associado:', error);
      message.error('Erro ao buscar o associado. Verifique o ID e tente novamente.');
    }
  };

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Nome',
      dataIndex: 'nome',
      key: 'nome',
    },
    {
      title: 'CPF',
      dataIndex: 'cpf',
      key: 'cpf',
    },
    {
      title: 'Status',
      dataIndex: 'status',
      key: 'status',
      render: (status: string) => (status === 'ABLE_TO_VOTE' ? 'Apto a votar' : 'Não apto a votar'),
    },
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h1>Lista de Associados</h1>
      <div style={{ marginBottom: '20px', display: 'flex', gap: '10px' }}>
        <Input
          placeholder="Digite o ID do associado"
          value={buscaId}
          onChange={(e) => setBuscaId(e.target.value)}
          style={{ width: '300px' }}
        />
        <CustomButton
          label="Buscar por ID"
          onClick={buscarAssociadoPorId}
          type='primary'
        />
        <CustomButton
          label="Carregar todos"
          onClick={listarAssociados}
          type='default'
        />
      </div>
      <Table dataSource={associados} columns={columns} rowKey="id" />
    </div>
  );
};

export default ListarAssociado;
