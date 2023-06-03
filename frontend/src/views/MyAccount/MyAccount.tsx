/* eslint-disable no-restricted-globals */
import style from './MyAccount.module.css';

import Page from '../../components/Page/Page';
import TopBar from '../../components/TopBar/TopBar';
import LeftBar from '../../components/LeftBar/LeftBar';
import RightBar from '../../components/RightBar/RightBar';
import Search from '../../components/Search/Search';
import Contacts from '../../components/Contacts/Contacts';
import CenterBar from '../../components/CenterBar/CenterBar';
import RecentPosts from '../../components/RecentPosts/RecentPosts';
import Button from '../../components/Button/Button';
import UserInfo from '../../components/UserInfo/UserInfo';
import ChangePassword from '../../components/ChangePassword/ChangePassword';
import MyPosts from '../../components/MyPosts/MyPosts';
import { Component } from 'react';
import Information from '../../components/Information/Information';
import ChangePictures from '../../components/ChangePictures/ChangePictures';

const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUser`;

export interface MyAccountProps {

}
export interface MyAccountState {
    loaded: boolean;
}
export default class MyAccount extends Component<MyAccountProps, MyAccountState> {

    private fetchUrl!: string;
    private allPosts!: Array<JSX.Element>;
    private userObj!: any;
    constructor(props: MyAccountProps) {
        super(props);
        this.state = {
            loaded: false
        }
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
                    console.log(body)
                    this.userObj = body;
                    console.log(this.userObj.id)
                    this.setState({
                        loaded: true,
                    })
                });
        } catch (err) {
            console.log("conn error");
        }
    }
    render() {
        return (
            <Page sidebar={true}>
                <LeftBar>
                    <Search text={'search'}></Search>
                    <Contacts></Contacts>
                </LeftBar>
                <CenterBar>

                    {this.state.loaded ?
                        <ChangePictures img1={this.userObj.profilePhoto} img2={this.userObj.backgroundPhoto
                        } userObj={undefined}></ChangePictures>

                        : null
                    }
                    {this.state.loaded ?
                        <MyPosts userId={this.userObj.id}></MyPosts>

                        : null
                    }
                </CenterBar>


                <RightBar>
                    {this.state.loaded ?
                        <Information id={this.userObj.id}></Information>
                        : null
                    }

                    <Button text='Home Page' onClick={() => { window.location.replace('/'); }}></Button>
                    <ChangePassword></ChangePassword>
                </RightBar>
                <TopBar></TopBar>

            </Page>
        );
    }
}

