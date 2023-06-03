import { Component, createRef, RefObject } from 'react';

import style from './UserInfo.module.css';
import postPhoto from '../../resources/postPhoto.png'
import profilePhoto from '../../resources/logo_user.png'
import FollowButton from '../Search/FollowButton';
import ProfilePhoto from '../ProfilePhoto/ProfilePhoto';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUserInfoById`;
const unFollowfetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/unfollowUser`;
const followfetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/followUser`;


export interface UserInfoProps {
    width?: string;
    onClick?: () => void;
    className?: string;
    id: string | null;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface UserInfoState {
    message: string;
    name: string;
    profileImage: string;
    backgroundImage: string;
    followed: boolean;
}

export default class Search extends Component<UserInfoProps, UserInfoState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: UserInfoProps) {
        super(props);
        this.follow = this.follow.bind(this);
        this.unfollow = this.unfollow.bind(this);
        this.state = {
            message: '',
            name: "",
            profileImage: "",
            backgroundImage: "",
            followed: false,
        };
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                token: "Bearer " + sessionStorage.getItem("userToken"),
                id: this.props.id,
            })

        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    console.log(body)
                    this.setState({
                        name: body.name + " " + body.surname,
                        profileImage: body.profilePhoto,
                        backgroundImage: body.backgroundPhoto,
                        followed: body.followed
                    })
                });
        } catch (err) {
            console.log("conn error");
        }
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
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
                        this.setState({ followed: false });

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    follow() {
        console.log(this.props.id)

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
            const response = fetch(followfetchUrl, requestOptions)
                .then((body) => {
                    if (body.status === 200)
                        this.setState({ followed: true });

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    render() {
        return (
            <div className={[style.userInfoBox, this.props.className].join(' ')} >
                <img className={style.bgPhoto} src={`data:image/jpeg;base64,${this.state.backgroundImage}`} alt="bg" ></img>

                <div className={style.info}>
                    <ProfilePhoto className={style.profilephoto} data={this.state.profileImage}></ProfilePhoto>

                    <div className={style.name}>{this.state != null ? this.state.name : this.props.id}</div>

                    {this.state.followed ?
                        <FollowButton onClick={this.unfollow} text='Unfollow'></FollowButton>
                        :
                        <FollowButton onClick={this.follow} text='Follow'></FollowButton>
                    }

                </div>

            </div>
        );
    }
}

