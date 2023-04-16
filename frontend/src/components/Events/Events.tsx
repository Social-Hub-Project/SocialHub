import { Component, createRef, RefObject } from 'react';

import style from './Events.module.css';
import user_logo from '../../resources/logo_user.png';
import SingleEvent from './SingleEvent';


export interface EventsProps {
    text?: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class Events extends Component<EventsProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: EventsProps) {
        super(props);
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.eventContainer, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Events</h3>
                <SingleEvent name='Lorem' surname='Ipsum' date='June 21, 12:45 pm' eventText='Liked your post!'></SingleEvent>
                <SingleEvent name='Lorem' surname='Ipsum' date='June 21, 12:45 pm' eventText='Liked your post!'></SingleEvent>

                <SingleEvent name='Lorem' surname='Ipsum' date='June 21, 12:45 pm' eventText='Liked your post!'></SingleEvent>
                <SingleEvent name='Lorem' surname='Ipsum' date='June 21, 12:45 pm' eventText='Liked your post!'></SingleEvent>

            </div>
        );
    }
}

