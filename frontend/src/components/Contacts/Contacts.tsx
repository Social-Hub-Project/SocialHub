import { Component, createRef, RefObject } from 'react';

import style from './Contacts.module.css';
import SingleContact from './SingleContact';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getFriendsList`;


export interface ContactsProps {
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}

export interface ContactsState {
    results: Array<JSX.Element>;
}
export default class Contacts extends Component<ContactsProps, ContactsState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: ContactsProps) {
        super(props);

        this.state = {
            results: [],
        };

        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            }
        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    var array: Array<JSX.Element> = [];
                    body.forEach((p: any) => {
                        array.push(<SingleContact
                            id={p.id} key={p.id} photoUrl={p.photoUrl}
                            name={p.userInfo.name} surname={p.userInfo.surname}></SingleContact>)
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
            <div className={[style.contacts, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Online contacts</h3>
                {this.state.results}
            </div>
        );
    }
}

