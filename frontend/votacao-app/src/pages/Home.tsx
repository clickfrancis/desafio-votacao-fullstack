import React from 'react';
import type { MenuProps } from 'antd';
import { Menu } from 'antd';

type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
  {
    key: 'Assembleia',
    label: 'Assembleia',
    children: [
      { key: '1', label: 'Registrar Assembleia' },
      { key: '2', label: 'Listar Assembleias' },
    ],
  },
  {
    key: 'Pauta',
    label: 'Pauta',
    children: [
      { key: '1', label: 'Registrar Pauta' },
      { key: '2', label: 'Listar Pautas' },
    ],
  },
  {
    key: 'Associado',
    label: 'Associado',
    children: [
      { key: '1', label: 'Registrar Associado' },
      { key: '2', label: 'Listar Associados' },
    ],
  },
  {
    key: 'Sessão de votação',
    label: 'Sessão de votação',
    children: [
      { key: '1', label: 'Registrar Sessão' },
      { key: '2', label: 'Listar Sessões' },
    ],
  },
  {
    key: 'Voto',
    label: 'Voto',
    children: [
      { key: '1', label: 'Registrar Voto' },
      { key: '2', label: 'Listar Votos' },
    ],
  },
];

const App: React.FC = () => {
  const onClick: MenuProps['onClick'] = () => {
    
  };

  return (
    <Menu
      onClick={onClick}
      style={{ width: 256 }}
      defaultSelectedKeys={['1']}
      defaultOpenKeys={['sub1']}
      mode="inline"
      items={items}
    />
  );
};

export default App;