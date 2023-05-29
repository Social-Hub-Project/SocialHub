import { Component, createRef, RefObject } from 'react';

import style from './ChangePassword.module.css';
import Input from '../Input/Input';
import Button from '../Button/Button';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/changePassword `;

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
        this.inputRef = createRef();
        this.inputConfRef = createRef();

        this.savePass = this.savePass.bind(this);

    };
    savePass() {
        console.log(this.inputRef.current)
        console.log(this.inputConfRef.current)

        const body = {
            token: sessionStorage.getItem("userToken"),
            newPassword: this.inputRef.current?.value,
            newPasswordConfirm: this.inputConfRef.current?.value
        };
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body),
        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((body) => {
                    if (body.status == 200)
                        alert("password changed");
                    else {
                        alert("error")
                    }
                });
        } catch (err) {
            console.log("conn error");
        }
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

