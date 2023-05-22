import { Component, createRef, RefObject } from 'react';

import style from './UserInfo.module.css';
import postPhoto from '../../resources/postPhoto.png'
import profilePhoto from '../../resources/logo_user.png'
import FollowButton from '../Search/FollowButton';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUserInfoById`;


export interface UserInfoProps {
    userObj: any;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string | null;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface UserInfoState {
    message: string;
    name: string;
}

export default class Search extends Component<UserInfoProps, UserInfoState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: UserInfoProps) {
        super(props);

        this.state = {
            message: '',
            name: "",
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

                    })
                });
        } catch (err) {
            console.log("conn error");
        }
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.userInfoBox, this.props.className].join(' ')} >
                <img className={style.bgPhoto} src={postPhoto} alt="bg" ></img>
                <div className={style.info}>
                    <img className={style.profilephoto} src={profilePhoto} alt="bg" ></img>
                    <div className={style.name}>{this.state != null ? this.state.name : this.props.id}</div>

                    <FollowButton text='Follow'></FollowButton>

                </div>

            </div>
        );
    }
}

