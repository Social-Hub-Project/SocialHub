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


function MyAccount(this: any) {


    return (
        <Page sidebar={true}>
            <LeftBar>
                <Search text={'search'}></Search>
                <Contacts></Contacts>
            </LeftBar>
            <CenterBar>
                <UserInfo userObj={{}} ></UserInfo>
            </CenterBar>


            <RightBar>
                <h3>Information</h3>
                <div className={style.userInfo}>
                    <div>Tyler Smith</div>
                    <div>20.09.2000</div>
                    <div>Krakow</div>
                    <div>Male</div>
                </div>

                <Button text='Home Page' onClick={() => { window.location.replace('/'); }}></Button>
                <ChangePassword></ChangePassword>
            </RightBar>
            <TopBar></TopBar>

        </Page>
    );
}


export default MyAccount;

