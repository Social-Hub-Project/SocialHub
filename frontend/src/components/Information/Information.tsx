import { Component, createRef, RefObject } from 'react';

import style from './Information.module.css';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUserInfoById`;


export interface InformationProps {
    onClick?: () => void;
    className?: string;
    id?: string | null;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;
}

export interface InformationState {
    name: string;
    birth: string;
    place: string;
    sex: string;
}
export default class Information extends Component<InformationProps, InformationState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: InformationProps) {
        super(props);
        this.setState({
            name: "",
            birth: "",
            place: "",
            sex: ""
        })
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                token: "Bearer " + sessionStorage.getItem("userToken"),
                id: this.props.id,
            })

        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {

                    console.log(body)
                    this.setState({
                        name: body.name + " " + body.surname,
                        birth: body.dateOfBirth,
                        place: body.residence,
                        sex: body.sex,
                    })
                });
        } catch (err) {
            console.log("conn error");
        }


        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.contacts, this.props.className].join(' ')} >
                <h3>Information</h3>
                <div className={style.userInfo}>
                    <div>{this.state != null ? this.state.name : this.props.id}</div>
                    <div>{this.state != null ? this.state.birth : this.props.id}</div>
                    <div>{this.state != null ? this.state.place : this.props.id}</div>
                    <div>{this.state != null ? this.state.sex : this.props.id}</div>
                </div>
            </div>
        );
    }
}

