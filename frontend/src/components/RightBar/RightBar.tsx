import style from './RightBar.module.css';

import { Component, HTMLAttributes } from 'react';
import user_logo from '../../resources/logo_user.png';
import logo from '../../resources/topbar_logo.png';

interface RightBarProps {
    user?: boolean;
    content?: boolean;
    background?: boolean;
    contentClassName?: string;
    children?: HTMLAttributes<HTMLDivElement>['children'];
}

export default class RightBar extends Component<RightBarProps> {
    private static defaultProps: RightBarProps = {
        user: true,
        content: true,
        background: false,
        contentClassName: '',
    };

    render() {
        return (
            <div className={style.rightBar}>
                {this.props.children}
            </div>

        );
    }
}

