import { Component, createRef, RefObject, SyntheticEvent } from 'react';


import style from './CreatePost.module.css';
import arrow from '../../resources/arrow.png'
import Multiline from './Multiline';

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
        const token = sessionStorage.getItem("userToken");
        var myHeaders = new Headers();
        myHeaders.append("Authorization", "Bearer " + token);

        var formdata = new FormData();
        if (token) formdata.append("token", token);
        if (this.contentRef.current?.value) formdata.append("description", this.contentRef.current?.value);
        if (this.state.imageFile != null)
            formdata.append("image", this.state.imageFile, this.state.imageFile.name);

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: formdata,
        };

        fetch(fetchUrl, requestOptions)
            .then(response => response.text())
            .then((result) => {
                console.log(result)
                // eslint-disable-next-line no-restricted-globals
                location.reload();
            }

            )
            .catch(error => console.log('error', error));

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



