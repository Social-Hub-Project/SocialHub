import { Component, createRef, RefObject } from 'react';

import style from './UserInfo.module.css';
import postPhoto from '../../resources/postPhoto.png'
import profilePhoto from '../../resources/logo_user.png'
import FollowButton from '../Search/FollowButton';


export interface UserInfoProps {
    userObj: any;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface UserInfoState {
    message: string;
}

export default class Search extends Component<UserInfoProps, UserInfoState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: UserInfoProps) {
        super(props);

        this.state = {
            message: '',
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <div className={[style.userInfoBox, this.props.className].join(' ')} >
                <img className={style.bgPhoto} src={postPhoto} alt="bg" ></img>
                <div className={style.info}>
                    <img className={style.profilephoto} src={profilePhoto} alt="bg" ></img>
                    <div className={style.name}>Tyler Smith</div>
                    <div className={style.followers}>24 followers</div>
                    <FollowButton text='Follow'></FollowButton>

                </div>

            </div>
        );
    }
}

