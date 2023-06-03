import { Component, createRef, RefObject } from 'react';

import style from './Post.module.css';
import userLogo from '../../resources/logo_user.png';
import ProfilePhoto from '../ProfilePhoto/ProfilePhoto';


export interface CommentProps {
    com: any;
    onClick?: () => void;
    photoUrl: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}

export default class Comment extends Component<CommentProps> {
    private inputRef!: RefObject<HTMLInputElement>;
    constructor(props: CommentProps) {
        super(props);
        console.log(this.props.com)
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.comment, this.props.className].join(' ')}>
                <div className={style.userInfo}>
                    <ProfilePhoto data={this.props.photoUrl}></ProfilePhoto>
                    <div>
                        <p className={style.userNames}>{this.props.com.user_entity_id.userInfo.name} {this.props.com.user_entity_id.userInfo.surname}</p>
                        <p className={style.postDate}>{this.props.com.created_at}</p>
                    </div>
                </div>
                <p className={style.content}>{this.props.com.content}</p>
            </div>
        );
    }
}

