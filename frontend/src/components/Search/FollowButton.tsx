import { Component } from 'react';

import style from './FollowButton.module.css';


export interface FollowButtonProps {
    text: string;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
}

export default class FollowButton extends Component<FollowButtonProps> {
    render() {
        return (
            <button className={[style.buttonGreen].join(' ')} onClick={this.props.onClick} id={this.props.id}>
                {this.props.text}
            </button>
        );
    }
}

