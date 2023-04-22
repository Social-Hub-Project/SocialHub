import style from './Login.module.css';
import { login as apiLogin } from '../../auth';

import Page from '../../components/Page/Page';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import { Link, useNavigate } from 'react-router-dom';
import { useRef } from 'react';
import logo from '../../resources/socialhub_logo.png';


function Login() {
    const email = useRef<HTMLInputElement>(null);
    const password = useRef<HTMLInputElement>(null);

    const navigate = useNavigate();

    async function sendLoginRequest() {
        if (!password.current?.value || !email.current?.value)
            return;
        else {
            const resp = await apiLogin(email.current?.value, password.current?.value, navigate);
            if (resp !== undefined) alert(`Error: ${resp}`);
        }

    };
    return (
        <Page content>
            <div className={style.container}>
                <div className={style.formContainer}>
                    <div className='leftBar'>
                        <img src={logo} alt='Socialhub logo' />
                    </div>
                    <div className={style.rightBar}>
                        <div className={style.title}>Login</div>

                        <Input useRef={email} className={style.registerInput} text="Email" />
                        <Input useRef={password} className={style.registerInput} text="Password" />
                        <Link className={style.linkAnchor} to='/forgotPassword'>
                            <div className={style.linkAnchor} >Forgot your password &#8594;</div>
                        </Link>
                        <Link to='/login'>
                            <Button onClick={sendLoginRequest} text="LOGIN" />
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

