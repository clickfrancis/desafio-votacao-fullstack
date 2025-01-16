import { Form, InputNumber, message } from 'antd';
import api from './../../service/api/api';
import CustomButton from '../buttons/CustomButton';



const CreateFormSessaoVotacao: React.FC = () => {
  const onFinish = async (values: { pautaId: number; tempoEmMinutos?: number }) => {
    try {
      const response = await api.post('/sessao-votacao', null, {
        params: values,
      });
      message.success('Sessão de votação criada com sucesso!');
      console.log('Nova Sessão:', response.data);
    } catch (error) {
      console.error('Erro ao criar sessão:', error);
      message.error('Erro ao criar a sessão. Tente novamente.');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Abrir Sessão de Votação</h1>
      <Form
        name="sessao-form"
        layout="vertical"
        style={{ maxWidth: 400 }}
        onFinish={onFinish}
        autoComplete="off"
      >
        <Form.Item
          label="ID da Pauta"
          name="pautaId"
          rules={[{ required: true, message: 'Por favor, insira o ID da pauta!' }]}
        >
          <InputNumber min={1} style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item label="Tempo (em minutos)" name="tempoEmMinutos">
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

export default CreateFormSessaoVotacao;
