import { Component, createRef, RefObject } from 'react';

import style from './Search.module.css';
import ResultUser from './ResultUser';
import searchIcon from '../../resources/search_icon.png';


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
}

export default class Search extends Component<SearchProps, SearchState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: SearchProps) {
        super(props);

        this.state = {
            message: '',
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.searchbox, this.props.className].join(' ')} >
                <h3 className={style.h3title}>Who to Follow</h3>
                <div>
                    <input ref={this.props.useRef} placeholder='Search for friend?'
                        className={style.searchinput} onClick={this.props.onClick}
                        type='text' id={this.props.id} />
                    <img className={style.searchIcon} alt='search' src={searchIcon} />
                </div>
                <div className={style.resultsContainer}>
                    <ResultUser name="Lorem" surname='Ipsum'></ResultUser>
                    <ResultUser name="Lorem" surname='Ipsum'></ResultUser>

                </div>
            </div>
        );
    }
}

