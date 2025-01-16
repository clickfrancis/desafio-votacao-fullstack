import React from 'react';
import { Button } from 'antd';

type CustomButtonProps = {
  label: string;
  onClick?: () => void;
  htmlType?: 'button' | 'submit' | 'reset';
  type?: 'primary' | 'default' | 'dashed' | 'text' | 'link';
  disabled?: boolean;
  style?: React.CSSProperties;
};

const CustomButton: React.FC<CustomButtonProps> = ({
  label,
  onClick,
  htmlType = 'button',
  type = 'default',
  disabled = false,
  style,
}) => {
  return (
    <Button
      type={type}
      onClick={onClick}
      htmlType={htmlType}
      disabled={disabled}
      style={style}
    >
      {label}
    </Button>
  );
};

export default CustomButton;
