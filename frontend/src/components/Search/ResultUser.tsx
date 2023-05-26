import { Component, createRef, RefObject } from 'react';

import style from './ResultUser.module.css';
import userLogo from '../../resources/logo_user.png';
import Button from '../Button/Button';
import FollowButton from './FollowButton';

export interface ResultUserProps {
    id: string;
    name: string;
    surname: string;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}
export default class ResultUser extends Component<ResultUserProps> {

    constructor(props: ResultUserProps) {
        super(props);
        this.reload = this.reload.bind(this);

    };

    reload() {
        // eslint-disable-next-line no-restricted-globals
        window.location.href = "/user?userId=" + this.props.id;
    }
    render() {
        return (
            <div className={[style.resultUser, this.props.className].join(' ')} >
                <img alt="user_photo" src={userLogo} onClick={this.reload} />
                <p onClick={this.reload}>{this.props.name} {this.props.surname}</p>
                <FollowButton text='Follow'></FollowButton>
            </div>
        );
    }
}

