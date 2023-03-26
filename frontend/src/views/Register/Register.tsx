import style from './Register.module.css';

import Page from '../../components/Page/Page';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import { Link } from 'react-router-dom';



function Register() {


    return (
        <Page content>
            <div className={style.container}>
                <img className={style.socialhubLogo} alt='Socialhub logo' />
                <div className={style.title}>Register</div>
                <div className={style.formContainer}>
                    <div className='leftBar'>
                        <Input className={style.registerInput} text="Name" />
                        <Input className={style.registerInput} text="Surname" />
                        <Input className={style.registerInput} text="Birthday" />
                        <Input className={style.registerInput} text="Sex" />
                        <div className={style.linkAnchor}>Already have account &#8594;</div>
                        <Link to='/login'>
                            <Button text="LOGIN" />
                        </Link>

                    </div>
                    <div className='rightBar'>
                        <Input className={style.registerInput} text="Residence" />
                        <Input className={style.registerInput} text="Email" />
                        <Input className={style.registerInput} text="Password" />
                        <Input className={style.registerInput} text="Congirm Password" />
                        <div className={style.linkAnchor}>Create your account here &#8594;</div>
                        <Link to='/register'>
                            <Button text="SIGN UP" />
                        </Link>
                    </div>
                </div>

            </div>
        </Page>
    );
}


export default Register;

