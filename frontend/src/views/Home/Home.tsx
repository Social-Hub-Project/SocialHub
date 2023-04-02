import style from './Home.module.css';

import Page from '../../components/Page/Page';
import { Link } from 'react-router-dom';



function Home() {


  return (
    <Page title='Welcome'>
      <img alt='Student Benchmark' />

      <span className={style.catchphrase}>HOME PAGE</span>

      <Link to='/tests'>
      </Link>
    </Page>
  );
}


export default Home;

