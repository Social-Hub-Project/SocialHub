import style from './Register.module.css';

import Page from '../../components/Page/Page';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import { Link, useNavigate } from 'react-router-dom';
import { useRef, useState } from 'react';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/register`;



function Register() {
    const navigate = useNavigate();
    const name = useRef<HTMLInputElement>(null);
    const sex = useRef<HTMLInputElement>(null);
    const residence = useRef<HTMLInputElement>(null);
    const surname = useRef<HTMLInputElement>(null);
    const birthday = useRef<HTMLInputElement>(null);
    const email = useRef<HTMLInputElement>(null);
    const password = useRef<HTMLInputElement>(null);
    const passwordConfirmation = useRef<HTMLInputElement>(null);

    async function sendRegisterRequest() {

        const body = {
            name: name.current?.value,
            surname: surname.current?.value,
            residence: residence.current?.value,
            sex: sex.current?.value,
            dateOfBirth: birthday.current?.value,
            email: email.current?.value,
            password: password.current?.value,
            passwordConfirmation: passwordConfirmation.current?.value,
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body),

        };

        try {
            const response = await fetch("http://127.0.0.1:8080/auth/register", requestOptions);
            if (!response.ok) throw response;

            navigate('/login');
            alert('Registration successful! Please log in to continue.');
        } catch (err) {
            if (err instanceof Response) {
                const message = await err.text();
                if (err.headers.get('Content-Type')?.includes('text/plain')) {
                    alert(`Error: ${message}`);
                } else {
                    alert('Error: Connection error. Please try again later.');
                }
            }
        }
    };

    return (
        <Page content>
            <div className={style.container}>
                <img className={style.socialhubLogo} alt='Socialhub logo' />
                <div className={style.title}>Register</div>
                <div className={style.formContainer}>
                    <div className='leftBar'>
                        <Input useRef={name} className={style.registerInput} text="Name" />
                        <Input useRef={surname} className={style.registerInput} text="Surname" />
                        <Input useRef={birthday} className={style.registerInput} text="Birthday" />
                        <Input useRef={sex} className={style.registerInput} text="Sex" />
                        <div className={style.linkAnchor}>Already have account &#8594;</div>
                        <Link to='/login'>
                            <Button text="LOGIN" />
                        </Link>

                    </div>
                    <div className='rightBar'>
                        <Input useRef={residence} className={style.registerInput} text="Residence" />
                        <Input useRef={email} className={style.registerInput} text="Email" />
                        <Input useRef={password} className={style.registerInput} text="Password" />
                        <Input useRef={passwordConfirmation} className={style.registerInput} text="Congirm Password" />
                        <div className={style.linkAnchor}>Create your account here &#8594;</div>
                        <Link to='/register'>
                            <Button onClick={sendRegisterRequest} text="SIGN UP" />
                        </Link>
                    </div>
                </div>

            </div>
        </Page>
    );
}


export default Register;

