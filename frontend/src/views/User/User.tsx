/* eslint-disable no-restricted-globals */
import style from './User.module.css';

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
import { useSearchParams } from 'react-router-dom';
import Information from '../../components/Information/Information';


function User(this: any) {

    const [searchParams, setSearchParams] = useSearchParams();
    const userId = searchParams.get("userId");

    return (
        <Page sidebar={true}>
            <LeftBar>
                <Search text={'search'}></Search>
                <Contacts></Contacts>
            </LeftBar>
            <CenterBar>
                <UserInfo id={userId} userObj={{}} ></UserInfo>
                <RecentPosts foruser={true} userId={userId} ></RecentPosts>
            </CenterBar>


            <RightBar>
                <Information id={userId}></Information>
                <Button text='Home Page' onClick={() => { window.location.replace('/'); }}></Button>
            </RightBar>
            <TopBar></TopBar>

        </Page>
    );
}


export default User;

