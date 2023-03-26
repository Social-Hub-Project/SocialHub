import style from './Login.module.css';

import Page from '../../components/Page/Page';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import { Link } from 'react-router-dom';



function Login() {


    return (
        <Page content>
            <div className={style.container}>
                <div className={style.formContainer}>
                    <div className='leftBar'>
                        <img alt='Socialhub logo' />
                    </div>
                    <div className={style.rightBar}>
                        <div className={style.title}>Login</div>

                        <Input className={style.registerInput} text="Email" />
                        <Input className={style.registerInput} text="Password" />
                        <Link className={style.linkAnchor} to='/forgotPassword'>
                            <div className={style.linkAnchor} >Forgot your password &#8594;</div>
                        </Link>
                        <Link to='/login'>
                            <Button text="LOGIN" />
                        </Link>
                        <Link to='/register'>
                            <Button text="SIGN UP" />
                        </Link>
                    </div>
                </div>

            </div>
        </Page>
    );
}


export default Login;

