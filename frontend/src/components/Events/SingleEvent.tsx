import { Component, createRef, RefObject } from 'react';

import style from './SingleEvent.module.css';
import logo from '../../resources/logo_user.png';
import ProfilePhoto from '../ProfilePhoto/ProfilePhoto';


export interface SingleEventProps {
    name: string;
    surname: string;
    date: string;
    eventText: string;
    photoUrl: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class SingleEvent extends Component<SingleEventProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: SingleEventProps) {
        super(props);

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.singleEvent, this.props.className].join(' ')} >
                <div className={style.positionEvent}>
                    <ProfilePhoto data={this.props.photoUrl}></ProfilePhoto>
                    <p>{this.props.name}</p>
                    <p className={style.eventText}>{this.props.eventText}</p>
                </div>
                <p className={style.eventDate}>{this.props.date}</p>
            </div>
        );
    }
}

