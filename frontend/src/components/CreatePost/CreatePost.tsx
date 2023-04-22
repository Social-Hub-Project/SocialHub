import { Component, createRef, RefObject, ChangeEvent, SyntheticEvent } from 'react';


import style from './CreatePost.module.css';
import arrow from '../../resources/arrow.png'
import Multiline from './Multiline';
import { SyntheticExpression } from 'typescript';

const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/createPost`;

export interface CreatePostProps {
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;

}
export interface CreatePostState {
    expanded: boolean;
    imageFile: File | null;
    image: string | null;
}
export default class CreatePost extends Component<CreatePostProps, CreatePostState> {
    private inputRef!: RefObject<HTMLInputElement>;
    private contentRef!: RefObject<HTMLTextAreaElement>;

    constructor(props: CreatePostProps) {
        super(props);

        this.state = {
            imageFile: null,
            expanded: false,
            image: null,
        };
        this.contentRef = createRef();
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };
    private toogleState = () => {
        this.setState({ expanded: !this.state.expanded });
    };
    private onImageChange = (e: SyntheticEvent) => {

        if (this.inputRef.current?.files != null && this.inputRef.current?.files.length !== 0) {
            const fileLoaded = URL.createObjectURL(this.inputRef.current?.files[0]);
            console.log(fileLoaded)
            this.setState({ image: fileLoaded });
            this.setState({ imageFile: this.inputRef.current?.files[0] });
        } else {
            this.setState({ image: null })

        }
    };

    private addPost = async () => {

        if (this.state.image == null || this.contentRef.current?.value == null)
            return;
        if (this.state.imageFile != null) {

            const token = sessionStorage.getItem("userToken");
            const body = {
                description: this.contentRef.current?.value,
                image: this.state.imageFile,
                token: sessionStorage.getItem("userToken"),
            };
            console.log(body.image)

            if (sessionStorage.getItem("userToken") != null) {
                const requestOptions = {
                    method: 'POST',
                    headers: {
                        'Authorization': "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiVVNFUiJdLCJzdWIiOiJhQGEuYSIsImlhdCI6MTY4MjE4MDEyMSwiZXhwIjoxNjgyMjY2NTIxfQ.YHkyo3_9d9mWmDRXbLZDKHASwcNsQP5b9CiLptN8q0c",
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*',
                        'Access-Control-Allow-Credentials': 'true'
                    },
                    body: JSON.stringify(body),
                };

                try {
                    const response = await fetch(fetchUrl, requestOptions);
                    if (!response.ok) throw response;


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
            }
        }
    }

    render() {
        return (
            <div className={[style.createPost, this.props.className].join(' ')} >

                {this.state.expanded ?
                    <div className={style.Expanded}>
                        <Multiline useRef={this.contentRef}></Multiline>
                        <div className={style.uploadPhotoBox}>
                            <p>Upload image here:</p>
                            <input ref={this.inputRef} onChange={this.onImageChange} className={style.fileInput} type='file' id={this.props.id} />
                        </div>
                        {this.state.image != null ?
                            <img src={this.state.image} className={style.providedImage} alt="test" />

                            : null
                        }
                        <button onClick={this.addPost} className={style.comfirmButton} >Create post</button>
                        <img src={arrow} className={style.hideArrow} alt='arrow' onClick={this.toogleState} />
                    </div>

                    :
                    //not expanded
                    <div onClick={this.toogleState} className={style.notExpanded}>
                        <div></div>
                        <p>What's on your mind?</p>
                        <img src={arrow} alt='arrow' />
                    </div>
                }

            </div>
        );
    }
}

function navigate(arg0: string) {
    throw new Error('Function not implemented.');
}

