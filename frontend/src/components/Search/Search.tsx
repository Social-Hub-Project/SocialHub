import { Component, createRef, RefObject } from 'react';

import style from './Search.module.css';
import ResultUser from './ResultUser';
import searchIcon from '../../resources/search_icon.png';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/searchUser`;


export interface SearchProps {
    text: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface SearchState {
    message: string;
    results: Array<JSX.Element>;
}

export default class Search extends Component<SearchProps, SearchState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: SearchProps) {
        super(props);

        this.state = {
            message: '',
            results: [],
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };
    fetchData() {
        console.log(this.inputRef.current?.value)
        const body = {
            token: "Bearer " + sessionStorage.getItem("userToken"),
            name: this.inputRef.current?.value,
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
                .then((response) => response.json())
                .then((body) => {
                    console.log(body);
                    var array = [];
                    body.forEach((p: any) => {
                        console.log(p)
                        array.push(<ResultUser photoUrl={p.photoUrl} name={p.name} surname={p.surname}></ResultUser>)
                    })


                });
        } catch (err) {
            console.log("conn error");
        }


    }


    render() {
        return (
            <div className={[style.searchbox, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Who to Follow</h3>
                <div>
                    <input ref={this.inputRef} placeholder='Search for friend?'
                        className={style.searchinput} onChange={this.fetchData} onClick={this.props.onClick}
                        type='text' id={this.props.id} />
                    <img className={style.searchIcon} alt='search' src={searchIcon} />
                </div>
                <div className={style.resultsContainer}>
                    {this.state.results}

                </div>
            </div>
        );
    }
}

