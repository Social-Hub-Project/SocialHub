import style from './Home.module.css';

import Page from '../../components/Page/Page';
import { Link } from 'react-router-dom';
import TopBar from '../../components/TopBar/TopBar';
import LeftBar from '../../components/LeftBar/LeftBar';
import RightBar from '../../components/RightBar/RightBar';
import Search from '../../components/Search/Search';
import Contacts from '../../components/Contacts/Contacts';
import Events from '../../components/Events/Events';
import CreatePost from '../../components/CreatePost/CreatePost';
import CenterBar from '../../components/CenterBar/CenterBar';
import Post from '../../components/Post/Post';
import RecentPosts from '../../components/RecentPosts/RecentPosts';


function Home() {


  return (
    <Page sidebar={true}>
      <LeftBar>
        <Search text={'search'}></Search>
        <Contacts></Contacts>
      </LeftBar>
      <CenterBar>
        <CreatePost></CreatePost>
        <RecentPosts />
      </CenterBar>


      <RightBar>
        <Events></Events>
      </RightBar>
      <TopBar></TopBar>

    </Page>
  );
}


export default Home;

