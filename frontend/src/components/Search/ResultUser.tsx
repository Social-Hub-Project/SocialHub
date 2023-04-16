import { Component, createRef, RefObject } from 'react';

import style from './ResultUser.module.css';
import userLogo from '../../resources/logo_user.png';
import Button from '../Button/Button';
import FollowButton from './FollowButton';

export interface ResultUserProps {
    name: string;
    surname: string;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}
export default class ResultUser extends Component<ResultUserProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: ResultUserProps) {
        super(props);

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.resultUser, this.props.className].join(' ')} >
                <img alt="user_photo" src={userLogo} />
                <p>{this.props.name} {this.props.surname}</p>
                <FollowButton text='Follow'></FollowButton>
            </div>
        );
    }
}

