import { Component, createRef, RefObject } from 'react';

import style from './Events.module.css';
import user_logo from '../../resources/logo_user.png';
import SingleEvent from './SingleEvent';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getLastEvents`;


export interface EventsProps {
    text?: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}

export interface EventsState {
    results: Array<JSX.Element>;
}
export default class Events extends Component<EventsProps, EventsState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: EventsProps) {
        super(props);
        this.state = {
            results: [],
        };

        const body = {
            token: sessionStorage.getItem("userToken"),
            numberOfEvents: 4
        };
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body)

        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    console.log("dupa")
                    console.log(body)
                    var array: Array<JSX.Element> = [];
                    body.forEach((p: any) => {
                        array.push(<SingleEvent
                            id={p.id} key={p.id} photoUrl={p.profilePhoto}
                            name={p.eventCreator}
                            surname={p.eventCreator} date={p.created_at.slice(0, 10)}
                            eventText={p.message}></SingleEvent>)
                    })
                    this.setState({
                        results: array,
                    })

                });
        } catch (err) {
            console.log("conn error");
        }
    };

    render() {
        return (
            <div className={[style.eventContainer, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Events</h3>
                {this.state.results}
            </div>
        );
    }
}

