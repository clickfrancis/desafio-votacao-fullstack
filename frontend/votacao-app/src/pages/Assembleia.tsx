import React, { useState } from 'react';
import { Input, message, Table } from 'antd';
import api from './../service/api/api';
import CustomButton from '../components/buttons/CustomButton';

type Pauta = {
  id: number;
  titulo: string;
  descricao: string;
};

type Assembleia = {
  id: number;
  nome: string;
  dataCriacao: string;
  pautas: Pauta[];
};

const ListarAssembleias: React.FC = () => {
  const [assembleias, setAssembleias] = useState<Assembleia[]>([]);
  const [buscaId, setBuscaId] = useState<string>('');

  const listarAssembleias = async () => {
    try {
      const response = await api.get<Assembleia[]>('/assembleia');
      if (Array.isArray(response.data)) {
        setAssembleias(response.data);
        message.success('Assembleias carregadas com sucesso!');
      } else {
        message.error('A resposta da API não contém dados válidos.');
      }
    } catch (error) {
      console.error('Erro ao carregar assembleias:', error);
      message.error('Erro ao carregar as assembleias. Tente novamente.');
    }
  };

  const buscarAssembleiaPorId = async () => {
    if (!buscaId.trim()) {
      message.warning('Por favor, insira um ID para buscar.');
      return;
    }

    try {
      const response = await api.get<Assembleia>(`/assembleia/${buscaId}`);
      if (response.data) {
        setAssembleias([response.data]);
        message.success('Assembleia encontrada!');
      } else {
        message.warning('Nenhuma assembleia encontrada com este ID.');
      }
    } catch (error) {
      console.error('Erro ao buscar assembleia:', error);
      message.error('Erro ao buscar a assembleia. Verifique o ID informado.');
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
      title: 'Data de Criação',
      dataIndex: 'dataCriacao',
      key: 'dataCriacao',
    },
    {
      title: 'Pautas',
      key: 'pautas',
      render: (record: Assembleia) =>
        record.pautas.length > 0
          ? record.pautas.map((pauta) => `${pauta.titulo}`).join(', ')
          : 'Sem pautas',
    },
  ];

  return (
    <div style={{ padding: '20px' }}>
      <h1>Lista de Assembleias</h1>

      <div style={{ marginBottom: '20px', display: 'flex', gap: '10px' }}>
        <Input
          placeholder="Buscar por ID"
          value={buscaId}
          onChange={(e) => setBuscaId(e.target.value)}
          style={{ width: '300px' }}
        />
        <CustomButton
          label="Buscar por ID"
          onClick={buscarAssembleiaPorId}
          type='primary'
        />
        <CustomButton
          label="Carregar todas"
          onClick={listarAssembleias}
          type='default'
        />
      </div>
      <Table dataSource={assembleias} columns={columns} rowKey="id" />
    </div>
  );
};

export default ListarAssembleias;
