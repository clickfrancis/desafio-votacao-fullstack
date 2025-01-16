import React from 'react';
import type { MenuProps } from 'antd';
import { Menu } from 'antd';
import { useNavigate } from 'react-router-dom';

type MenuItem = Required<MenuProps>['items'][number];

const items: MenuItem[] = [
  {
    key: 'assembleia',
    label: 'Assembleia',
    children: [
      { key: '/registrar-assembleia', label: 'Registrar Assembleia' },
      { key: '/', label: 'Listar Assembleias' },
    ],
  },
  {
    key: 'pauta',
    label: 'Pauta',
    children: [
      { key: '/registrar-pauta', label: 'Registrar Pauta' },
      { key: '/listar-pautas', label: 'Listar Pautas' },
    ],
  },
  {
    key: 'associado',
    label: 'Associado',
    children: [
      { key: '/registrar-associado', label: 'Registrar Associado' },
      { key: '/listar-associados', label: 'Listar Associados' },
    ],
  },
  {
    key: 'sessao-votacao',
    label: 'Sessão de votação',
    children: [
      { key: '/registrar-sessao', label: 'Registrar Sessão' },
      { key: '/listar-sessoes', label: 'Listar Sessões' },
    ],
  },
  {
    key: 'voto',
    label: 'Voto',
    children: [
      { key: '/registrar-voto', label: 'Registrar Voto' },
      { key: '/listar-votos', label: 'Listar Votos' },
    ],
  },
];

const MenuComponent: React.FC = () => {
  const navigate = useNavigate(); 

  const onClick: MenuProps['onClick'] = (e) => {
    navigate(e.key); 
  };

  return (
    <Menu
      onClick={onClick}
      style={{ width: 256 }}
      mode="inline"
      items={items}
    />
  );
};

export default MenuComponent;
