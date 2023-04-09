import style from './Home.module.css';

import Page from '../../components/Page/Page';
import { Link } from 'react-router-dom';
import TopBar from '../../components/TopBar/TopBar';
import LeftBar from '../../components/LeftBar/LeftBar';
import RightBar from '../../components/RightBar/RightBar';
import Search from '../../components/Search/Search';



function Home() {


  return (
    <Page sidebar={true}>
      <TopBar></TopBar>
      <LeftBar>
        <Search text={'search'}></Search>

      </LeftBar>

      <span className={style.catchphrase}>HOME PAGE</span>
      <RightBar></RightBar>

    </Page>
  );
}


export default Home;

