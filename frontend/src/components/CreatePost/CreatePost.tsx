import { Component, createRef, RefObject } from 'react';

import style from './CreatePost.module.css';
import arrow from '../../resources/arrow.png'
import Multiline from './Multiline';

export interface CreatePostProps {
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface CreatePostState {
    expanded: boolean;
}
export default class CreatePost extends Component<CreatePostProps, CreatePostState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: CreatePostProps) {
        super(props);

        this.state = {
            expanded: false,
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };
    private toogleState = () => {
        this.setState({ expanded: !this.state.expanded });
    };

    render() {
        return (
            <div className={[style.createPost, this.props.className].join(' ')} >

                {this.state.expanded ?
                    <div className={style.Expanded}>
                        <Multiline></Multiline>
                        <div>Upload image here:
                            <button>upload image</button>
                        </div>

                        <img src={arrow} alt='arrow' onClick={this.toogleState} />
                    </div>

                    :
                    //not expanded
                    <div onClick={this.toogleState} className={style.notExpanded}>
                        <div></div>
                        <p>What's on your mind?</p>
                        <img src={arrow} alt='arrow' />
                    </div>
                }

            </div>
        );
    }
}

