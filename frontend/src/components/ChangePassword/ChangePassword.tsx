import { Component, createRef, RefObject } from 'react';

import style from './ChangePassword.module.css';
import Input from '../Input/Input';
import Button from '../Button/Button';

export interface ChangePasswordProps {
    text?: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class ChangePassword extends Component<ChangePasswordProps> {
    private inputRef!: RefObject<HTMLInputElement>;
    private inputConfRef!: RefObject<HTMLInputElement>;

    constructor(props: ChangePasswordProps) {
        super(props);
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };
    savePass() {
        console.log("save pass");
    }

    render() {
        return (
            <div className={[style.changePass, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Change password</h3>
                <Input text='New Password' useRef={this.inputRef} type='password'></Input>
                <Input text='Confirm Password' useRef={this.inputConfRef} type='password'></Input>
                <Button onClick={this.savePass} text='Confirm'></Button>
            </div>
        );
    }
}

