import { Component, createRef, RefObject, useEffect, useLayoutEffect, useRef } from 'react';

import style from './Search.module.css';
import ResultUser from './ResultUser';
import searchIcon from '../../resources/search_icon.png';
import React from 'react';
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
    constructor(props: SearchProps) {
        super(props);

        this.state = {
            message: '',
            results: [],
        };
        this.fetchData = this.fetchData.bind(this);

    };

    fetchData(e: any) {
        if (e.target.value === "") {
            this.setState({
                results: [],
            })
            return;

        }
        const body = {
            token: "Bearer " + sessionStorage.getItem("userToken"),
            word: e.target.value,
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
                    var array: Array<JSX.Element> = [];
                    body.forEach((p: any) => {
                        array.push(<ResultUser id={p.id} key={p.id} photoUrl={p.photoUrl} name={p.name} surname={p.surname}></ResultUser>)
                    })
                    console.log(array)
                    this.setState({
                        results: array,
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
                    <input placeholder='Search for friend?'
                        className={style.searchinput} onChange={this.fetchData}
                        type='text' />
                    <img className={style.searchIcon} alt='search' src={searchIcon} />
                </div>
                <div className={style.resultsContainer}>
                    {this.state.results}

                </div>
            </div>
        );
    }
}

