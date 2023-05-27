import { Component, createRef, RefObject } from 'react';

import style from './ResultUser.module.css';
import userLogo from '../../resources/logo_user.png';
import Button from '../Button/Button';
import FollowButton from './FollowButton';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/followUser`;
const unFollowfetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/unfollowUser`;

export interface ResultUserProps {
    id: string;
    name: string;
    surname: string;
    isFollowed: boolean;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}
export interface ResultUserState {
    isFollowed: boolean;
}
export default class ResultUser extends Component<ResultUserProps, ResultUserState> {

    constructor(props: ResultUserProps) {
        super(props);
        this.reload = this.reload.bind(this);
        this.follow = this.follow.bind(this);
        this.unfollow = this.unfollow.bind(this);
        this.state = {
            isFollowed: this.props.isFollowed,
        }
    };
    unfollow() {

        const body = {
            token: sessionStorage.getItem("userToken"),
            userFollowingId: this.props.id,
        };
        console.log(JSON.stringify(body))

        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body)

        };
        try {
            const response = fetch(unFollowfetchUrl, requestOptions)
                .then((body) => {
                    if (body.status === 200)
                        this.setState({ isFollowed: false });

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    follow() {

        const body = {
            token: sessionStorage.getItem("userToken"),
            userFollowingId: this.props.id,
        };
        console.log(JSON.stringify(body))

        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body)

        };
        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((body) => {
                    if (body.status === 200)
                        this.setState({ isFollowed: true });

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    reload() {
        // eslint-disable-next-line no-restricted-globals
        window.location.href = "/user?userId=" + this.props.id;
    }
    render() {
        return (
            <div className={[style.resultUser, this.props.className].join(' ')} >
                <img alt="user_photo" src={userLogo} onClick={this.reload} />
                <p onClick={this.reload}>{this.props.name} {this.props.surname}</p>
                {this.state.isFollowed ?
                    <FollowButton onClick={this.unfollow} text='Unfollow'></FollowButton>
                    :
                    <FollowButton onClick={this.follow} text='Follow'></FollowButton>
                }
            </div>
        );
    }
}

