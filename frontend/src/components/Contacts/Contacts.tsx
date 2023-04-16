import { Component, createRef, RefObject } from 'react';

import style from './Contacts.module.css';
import SingleContact from './SingleContact';


export interface ContactsProps {
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class Contacts extends Component<ContactsProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: ContactsProps) {
        super(props);

        this.state = {
            message: '',
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.contacts, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Online contacts</h3>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>
                <SingleContact name="Lorem" surname='Ipsum'></SingleContact>


            </div>
        );
    }
}

