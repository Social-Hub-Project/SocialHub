import { Component, createRef, RefObject } from 'react';

import style from './Input.module.css';


export interface InputProps {
    text: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface InputState {
    message: string;
}

export default class Input extends Component<InputProps, InputState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: InputProps) {
        super(props);

        this.state = {
            message: '',
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.inputBox, this.props.className].join(' ')} >
                <div className={style.placeholderText}>{this.props.text}</div>
                <input ref={this.props.useRef} placeholder={this.props.text} className={style.input} onClick={this.props.onClick} type='text' id={this.props.id} />
            </div>
        );
    }
}

