import { Component } from 'react';

import style from './Input.module.css';


export interface InputProps {
    text: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
}

export default class Input extends Component<InputProps> {
    render() {
        return (
            <div className={[style.inputBox, this.props.className].join(' ')} >
                <div className={style.placeholderText}>{this.props.text}</div>
                <input placeholder={this.props.text} className={style.input} onClick={this.props.onClick} type='text' id={this.props.id} />
            </div>
        );
    }
}

