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

const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUser`;
const ConvertedPhoto = ({ data }: { data: string }) => <img className="postPhoto" src={`data:image/jpeg;base64,${data}`} />

function MyAccount(this: any) {
    var userId: number;
    var userObj: any;
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
                userObj = body;
            });
    } catch (err) {
        console.log("conn error");
    }

    return (
        <Page sidebar={true}>
            <LeftBar>
                <Search text={'search'}></Search>
                <Contacts></Contacts>
            </LeftBar>
            <CenterBar>
                <UserInfo userObj={{}} ></UserInfo>
                <MyPosts ></MyPosts>
            </CenterBar>


            <RightBar>
                <h3>Information</h3>
                <div className={style.userInfo}>
                    <div></div>
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

