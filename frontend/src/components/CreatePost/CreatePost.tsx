import { Component, createRef, RefObject } from 'react';

import style from './CreatePost.module.css';
import { Link } from 'react-router-dom';


export interface CreatePostProps {
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}

export default class CreatePost extends Component<CreatePostProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: CreatePostProps) {
        super(props);

        this.state = {
            message: '',
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.createPost, this.props.className].join(' ')} >
                <Link to={''} >
                    <img src='' alt='addPost' />
                </Link>
            </div>
        );
    }
}

