import style from './TopBar.module.css';
import { logout } from '../../auth';

import { Component, HTMLAttributes } from 'react';
import user_logo from '../../resources/logo_user.png';
import logo from '../../resources/topbar_logo.png';
import { Link } from 'react-router-dom';
import ProfilePhoto from '../ProfilePhoto/ProfilePhoto';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUser`;

interface TopBarProps {
    user?: boolean;
    content?: boolean;
    background?: boolean;
    contentClassName?: string;
    children?: HTMLAttributes<HTMLDivElement>['children'];
}
export interface TopBarState {
    expanded: boolean;
    loaded: boolean;
}


export default class TopBar extends Component<TopBarProps, TopBarState> {
    private photoData!: string;

    constructor(props: TopBarProps) {
        super(props);
        this.reload = this.reload.bind(this);

        this.state = {
            expanded: false,
            loaded: false
        };

        const body = {
            token: "" + sessionStorage.getItem("userToken")
        };
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
                .then((response) => response.json())
                .then((body) => {
                    this.photoData = body.profilePhoto;
                    this.setState({
                        loaded: true,
                    })
                });
        } catch (err) {
            console.log("conn error");
        }

    };
    private toogleState = () => {
        this.setState({ expanded: !this.state.expanded });
    };
    private logout = async () => {
        await logout();
    }
    reload() {
        // eslint-disable-next-line no-restricted-globals
        window.location.href = "/";
    }

    render() {
        return (
            <div className={style.topbar}>
                <div className={style.pozdiv}></div>
                <img onClick={this.reload} className={style.logo} src={logo} alt='Socialhub logo' />


                <nav >
                    <ProfilePhoto data={this.photoData} onClick={this.toogleState} className={style.userImage}></ProfilePhoto>

                    {this.state.expanded ?
                        <div className={style.menu}>
                            <Link className={style.menuElement} to={"/myAccount"}>My account</Link>
                            <div className={style.menuElement}>Support</div>
                            <div onClick={this.logout} className={style.menuElement}>Logout</div>
                        </div>
                        : null}
                </nav>

            </div>

        );
    }
}

