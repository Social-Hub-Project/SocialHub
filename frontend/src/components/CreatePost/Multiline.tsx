import { Component, createRef, RefObject, SyntheticEvent, useRef } from 'react';

import style from './Multiline.module.css';


export interface MultilineProps {
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLTextAreaElement>;
    value?: string;
}

export default class Multiline extends Component<MultilineProps> {

    private inputRef!: RefObject<HTMLTextAreaElement>;

    private onChangeHandler = (e: SyntheticEvent) => {
        const target = e.target as HTMLTextAreaElement;
        target.style.height = "30px";
        target.style.height = `${target.scrollHeight}px`;
    };

    constructor(props: MultilineProps) {
        super(props);

        this.state = {
            expanded: false,
        };
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };


    render() {
        return (

            <textarea onChange={this.onChangeHandler} ref={this.props.useRef} placeholder="What's up" className={style.multiLine} id={this.props.id}></textarea>

        );
    }
}


