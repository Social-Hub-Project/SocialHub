import { Component, createRef, RefObject } from 'react';

import style from './ProfilePhoto.module.css';
import logo from '../../resources/logo_user.png';
const ProfilePhoto = ({ data }: { data: string }) => <img className={style.userPhoto} src={`data:image/jpeg;base64,${data}`} />


export interface ProfilePhotoProps {
    data: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}


export default class SingleEvent extends Component<ProfilePhotoProps> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: ProfilePhotoProps) {
        super(props);

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };

    render() {
        return (
            <img alt="prof" className={[style.userPhoto, this.props.className].join(' ')}
                onClick={this.props.onClick} src={`data:image/jpeg;base64,${this.props.data}`} />
        );
    }
}

