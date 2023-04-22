import style from './TopBar.module.css';
import { logout } from '../../auth';

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
export interface TopBarState {
    expanded: boolean;

}


export default class TopBar extends Component<TopBarProps, TopBarState> {
    constructor(props: TopBarProps) {
        super(props);

        this.state = {
            expanded: false,
        };

    };
    private toogleState = () => {
        this.setState({ expanded: !this.state.expanded });
    };
    private logout = async () => {
        await logout();
    }

    render() {
        return (
            <div className={style.topbar}>
                <div className={style.pozdiv}></div>
                <img className={style.logo} src={logo} alt='Socialhub logo' />


                <nav >
                    <img onClick={this.toogleState} className={style.userImage} src={user_logo} alt='user logo' />

                    {this.state.expanded ?
                        <div className={style.menu}>
                            <div className={style.menuElement}>My account</div>
                            <div className={style.menuElement}>Support</div>
                            <div onClick={this.logout} className={style.menuElement}>Logout</div>
                        </div>
                        : null}
                </nav>

            </div>

        );
    }
}

