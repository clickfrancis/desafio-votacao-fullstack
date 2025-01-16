import React from 'react';
import { Form, Input, InputNumber, message } from 'antd';
import type { FormProps } from 'antd';
import api from './../../service/api/api';
import CustomButton from '../buttons/CustomButton';

type FieldType = {
  titulo: string;
  descricao: string;
  assembleiaId: number;
};

const CreateFormPauta: React.FC = () => {
  const onFinish: FormProps<FieldType>['onFinish'] = async (values) => {
    try {
      const response = await api.post('/pauta', values);
      message.success('Pauta criada com sucesso!');
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Erro ao criar a pauta:', error);
      message.error('Erro ao criar a pauta. Tente novamente.');
    }
  };

  const onFinishFailed: FormProps<FieldType>['onFinishFailed'] = (errorInfo) => {
    console.log('Validation Failed:', errorInfo);
    message.error('Por favor, preencha todos os campos corretamente.');
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Registrar Pauta Da Assembleia</h1>
      <Form
        name="pauta-form"
        layout="vertical"
        style={{ maxWidth: 600 }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item<FieldType>
          label="Título"
          name="titulo"
          rules={[{ required: true, message: 'Por favor, insira o título!' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item<FieldType>
          label="Descrição"
          name="descricao"
          rules={[{ required: true, message: 'Por favor, insira a descrição!' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item<FieldType>
          label="Assembleia ID"
          name="assembleiaId"
          rules={[{ required: true, message: 'Por favor, insira o ID da assembleia!' }]}
        >
          <InputNumber min={1} style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item>
        <CustomButton
            label="Registrar"
            htmlType="submit"
            type="primary"
          />
        </Form.Item>
      </Form>
    </div>
  );
};

export default CreateFormPauta;
