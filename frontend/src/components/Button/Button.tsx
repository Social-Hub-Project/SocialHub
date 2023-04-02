import { Component } from 'react';

import style from './Button.module.css';


export interface InputProps {
    text: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
}

export default class Button extends Component<InputProps> {
    render() {
        return (
            <button className={[style.buttonGreen].join(' ')} onClick={this.props.onClick} id={this.props.id}>
                {this.props.text}
            </button>
        );
    }
}

