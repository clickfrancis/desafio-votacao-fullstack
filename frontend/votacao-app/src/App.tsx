import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ListarAssembleias from './pages/Assembleia';
import MenuComponent from './components/menu/Menu';
import CreateFormAssemleia from './components/forms/CreateFormAssemleia';
import ListarPautas from './pages/Pauta';
import ListarAssociados from './pages/Associado';
import SessaoVotacaoTable from './pages/Sessao';
import VotosTable from './pages/Voto';
import CreateFormSessaoVotacao from './components/forms/CreateFormSessaoVotacao';
import CreateFormPauta from './components/forms/CreateFormPauta';
import CreateFormVoto from './components/forms/CeateFormVoto';
import CreateFormAssociado from './components/forms/CreateFormAssociato';

const App: React.FC = () => {
  return (
    <Router>
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <div className='menu'>
          <MenuComponent />
        </div>
        <div style={{ height: '100vh', alignItems: 'center', paddingLeft: '100px', display: 'flex' }}>
          <Routes>
            <Route path="/" element={<ListarAssembleias />} />
            <Route path='/registrar-assembleia' element={<CreateFormAssemleia />} />
            <Route path='/listar-pautas' element={<ListarPautas />} />
            <Route path='/registrar-pauta' element={<CreateFormPauta />} />
            <Route path='/registrar-associado' element={<CreateFormAssociado />} />
            <Route path='/listar-associados' element={<ListarAssociados />} />
            <Route path='/registrar-sessao' element={<CreateFormSessaoVotacao />} />
            <Route path='/listar-sessoes' element={<SessaoVotacaoTable />} />
            <Route path='/registrar-voto' element={<CreateFormVoto />} />
            <Route path='/listar-votos' element={<VotosTable />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
};

export default App;
