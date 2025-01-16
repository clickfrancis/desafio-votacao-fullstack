import React from 'react';
import { Form, InputNumber, Radio, message } from 'antd';
import type { FormProps } from 'antd';
import api from '../../service/api/api';
import CustomButton from '../buttons/CustomButton';

type VotoRequestDTO = {
  votoOpcao: 'SIM' | 'NAO';
  sessaoId: number;
  pautaId: number;
  associadoId: number;
};

const CreateFormVoto: React.FC = () => {
  const onFinish: FormProps<VotoRequestDTO>['onFinish'] = async (values) => {
    try {
      const response = await api.post('/voto', values);
      message.success('Voto registrado com sucesso!');
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Erro ao registrar o voto:', error);
      message.error('Erro ao registrar o voto. Tente novamente.');
    }
  };

  const onFinishFailed: FormProps<VotoRequestDTO>['onFinishFailed'] = (errorInfo) => {
    console.log('Validation Failed:', errorInfo);
    message.error('Por favor, preencha todos os campos corretamente.');
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Registrar seu voto</h1>
      <Form
        name="voto-form"
        layout="vertical"
        style={{ maxWidth: 600 }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item<VotoRequestDTO>
          label="Opção de Voto"
          name="votoOpcao"
          rules={[{ required: true, message: 'Por favor, escolha uma opção de voto!' }]}
        >
          <Radio.Group>
            <Radio value="SIM">Sim</Radio>
            <Radio value="NAO">Não</Radio>
          </Radio.Group>
        </Form.Item>

        <Form.Item<VotoRequestDTO>
          label="ID da Sessão"
          name="sessaoId"
          rules={[{ required: true, message: 'Por favor, insira o ID da sessão!' }]}
        >
          <InputNumber min={1} style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item<VotoRequestDTO>
          label="ID da Pauta"
          name="pautaId"
          rules={[{ required: true, message: 'Por favor, insira o ID da pauta!' }]}
        >
          <InputNumber min={1} style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item<VotoRequestDTO>
          label="ID do Associado"
          name="associadoId"
          rules={[{ required: true, message: 'Por favor, insira o ID do associado!' }]}
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

export default CreateFormVoto;
