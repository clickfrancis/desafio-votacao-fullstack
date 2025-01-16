import React from 'react';
import { Form, Input, message } from 'antd';
import type { FormProps } from 'antd';
import api from './../../service/api/api';
import CustomButton from '../buttons/CustomButton';

type FieldType = {
  nome: string;
  cpf: string;
};

const CreateFormAssociado: React.FC = () => {
  const onFinish: FormProps<FieldType>['onFinish'] = async (values) => {
    try {
      const response = await api.post('/associado', values);
      message.success('Associado criado com sucesso!');
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Erro ao criar a associado:', error);
      message.error('Erro ao criar a associado. Tente novamente.');
    }
  };

  const onFinishFailed: FormProps<FieldType>['onFinishFailed'] = (errorInfo) => {
    console.log('Validation Failed:', errorInfo);
    message.error('Por favor, preencha todos os campos corretamente.');
  };

  return (
    <div style={{ padding: '20px' }}>



      <h1>Registrar Associado</h1>
      <Form
        name="associado-form"
        layout="vertical"
        style={{ maxWidth: 600 }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item<FieldType>
          label="Nome"
          name="nome"
          rules={[{ required: true, message: 'Por favor, insira o nome!' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item<FieldType>
          label="CPF"
          name="cpf"
          rules={[{ required: true, message: 'Por favor, insira o cpf!' }]}
        >
          <Input />
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

export default CreateFormAssociado;
