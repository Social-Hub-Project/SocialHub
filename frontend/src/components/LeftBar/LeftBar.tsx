import style from './LeftBar.module.css';

import { Component, HTMLAttributes } from 'react';
import user_logo from '../../resources/logo_user.png';
import logo from '../../resources/topbar_logo.png';

interface LeftBarProps {
    user?: boolean;
    content?: boolean;
    background?: boolean;
    contentClassName?: string;
    children?: HTMLAttributes<HTMLDivElement>['children'];
}

export default class LeftBar extends Component<LeftBarProps> {
    private static defaultProps: LeftBarProps = {
        user: true,
        content: true,
        background: false,
        contentClassName: '',
    };

    render() {
        return (
            <div className={style.leftBar}>
                {this.props.children}
            </div>

        );
    }
}

