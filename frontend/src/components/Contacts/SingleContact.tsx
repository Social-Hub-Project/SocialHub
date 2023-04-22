import { Component, createRef, RefObject } from 'react';

import style from './Contacts.module.css';
import logo from '../../resources/logo_user.png';


export interface SingleContactProps {
    name: string;
    surname: string;
    photoUrl?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class SingleContact extends Component<SingleContactProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: SingleContactProps) {
        super(props);

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.singleContact, this.props.className].join(' ')} >
                <img alt="pg" src={logo} />
                <p>{this.props.name} {this.props.surname}</p>
            </div>
        );
    }
}

