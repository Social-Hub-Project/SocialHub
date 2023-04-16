import style from './TopBar.module.css';

import { Component, HTMLAttributes } from 'react';
import user_logo from '../../resources/logo_user.png';
import logo from '../../resources/topbar_logo.png';

interface TopBarProps {
    user?: boolean;
    content?: boolean;
    background?: boolean;
    contentClassName?: string;
    children?: HTMLAttributes<HTMLDivElement>['children'];
}

export default class TopBar extends Component<TopBarProps> {
    private static defaultProps: TopBarProps = {
        user: true,
        content: true,
        background: false,
        contentClassName: '',
    };

    render() {
        return (
            <div className={style.topbar}>
                <div className={style.pozdiv}></div>
                <img className={style.logo} src={logo} alt='Socialhub logo' />
                <img className={style.userImage} src={user_logo} alt='user logo' />
            </div>

        );
    }
}

