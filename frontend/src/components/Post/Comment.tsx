import { Component, createRef, RefObject } from 'react';

import style from './Post.module.css';
import userLogo from '../../resources/logo_user.png';


export interface CommentProps {
    com: Array<String>;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}

export default class Comment extends Component<CommentProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: CommentProps) {
        super(props);

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.comment, this.props.className].join(' ')}>
                <div className={style.userInfo}>
                    <img alt="pg" className={style.userPhoto} src={userLogo} />
                    <div>
                        <p className={style.userNames}>{this.props.com[0]} {this.props.com[1]}</p>
                        <p className={style.postDate}>{this.props.com[2]}</p>
                    </div>
                </div>
                <p className={style.content}>{this.props.com[3]}</p>
            </div>
        );
    }
}

