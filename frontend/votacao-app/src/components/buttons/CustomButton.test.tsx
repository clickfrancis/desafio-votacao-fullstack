import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import CustomButton from './CustomButton';

describe('CustomButton Component', () => {
  it('deve renderizar o botão com o label correto', () => {
    render(<CustomButton label="Registrar" />);
    expect(screen.getByText('Registrar')).toBeInTheDocument();
  });

  it('deve chamar a função onClick quando clicado', () => {
    const onClickMock = vi.fn();
    render(<CustomButton label="Clique aqui" onClick={onClickMock} />);
    const button = screen.getByText('Clique aqui');

    fireEvent.click(button);

    expect(onClickMock).toHaveBeenCalledTimes(1);
  });

  it('deve ter o tipo HTML correto', () => {
    render(<CustomButton label="Registrar" htmlType="submit" />);
    const button = screen.getByText('Registrar');
    expect(button).toHaveAttribute('type', 'submit');
  });


  it('deve aplicar o tipo visual (type) correto', () => {
    render(<CustomButton label="Registrar" type="primary" />);
    const button = screen.getByText('Registrar');

    expect(button).toHaveClass('ant-btn-primary');
  });
});
