import { Component, createRef, RefObject, SyntheticEvent } from 'react';

import style from './ChangePictures.module.css';

const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUserInfoById`;
const profphotoUrl = `${process.env.REACT_APP_BACKEND_URL}/app/changeProfilePhoto`;
const bgphotoUrl = `${process.env.REACT_APP_BACKEND_URL}/app/changeBackgroundPhoto`;

const ConvertedPhoto = ({ data }: { data: string }) => <img alt="img2" className={style.profilephoto} src={`data:image/jpeg;base64,${data}`} />
const ConvertedPhoto2 = ({ data }: { data: string }) => <img alt="img2" className={style.bgPhoto} src={`data:image/jpeg;base64,${data}`} />

export interface ChangePicturesProps {
    userObj: any;
    width?: string;
    onClick?: () => void;
    className?: string;
    id?: string | null;
    useRef?: RefObject<HTMLInputElement>;
    value?: string;
    img1: string;
    img2: string;
}
export interface ChangePicturesState {
    message: string;
    name: string;
    imageFile: File | null;
    image: string | null;
    imageFile2: File | null;
    image2: string | null;
}

export default class Search extends Component<ChangePicturesProps, ChangePicturesState> {
    private inputRef!: RefObject<HTMLInputElement>;
    private inputRef2!: RefObject<HTMLInputElement>;

    constructor(props: ChangePicturesProps) {
        super(props);

        this.state = {
            message: '',
            name: "",
            image: null,
            image2: null,
            imageFile: null,
            imageFile2: null,

        };


        this.inputRef = createRef();
        this.inputRef2 = createRef();

    };
    private onImageChange = (e: SyntheticEvent) => {
        if (this.inputRef.current?.files != null && this.inputRef.current?.files.length !== 0) {
            const fileLoaded = URL.createObjectURL(this.inputRef.current?.files[0]);
            this.setState({ image: fileLoaded });
            this.setState({ imageFile: this.inputRef.current?.files[0] });
            const token = sessionStorage.getItem("userToken");
            var myHeaders = new Headers();
            myHeaders.append("Authorization", "Bearer " + token);
            var formdata = new FormData();
            if (token) formdata.append("token", token);
            if (this.inputRef.current.files[0] != null)
                formdata.append("photo", this.inputRef.current.files[0], this.inputRef.current.files[0].name);
            console.log(formdata)
            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: formdata,
            };

            fetch(profphotoUrl, requestOptions)
                .then(response => response.text())
                .then((result) => {
                }

                )
                .catch(error => alert('error: ' + error));

        } else {
            this.setState({ image: null })
        }
    };
    private onImageChange2 = (e: SyntheticEvent) => {

        if (this.inputRef2.current?.files != null && this.inputRef2.current?.files.length !== 0) {

            const fileLoaded = URL.createObjectURL(this.inputRef2.current?.files[0]);
            this.setState({ image2: fileLoaded });
            this.setState({ imageFile2: this.inputRef2.current?.files[0] });

            const token = sessionStorage.getItem("userToken");
            var myHeaders = new Headers();
            myHeaders.append("Authorization", "Bearer " + token);
            var formdata = new FormData();
            if (token) formdata.append("token", token);
            if (this.inputRef2.current.files[0] != null)
                formdata.append("photo", this.inputRef2.current.files[0], this.inputRef2.current.files[0].name);
            console.log(formdata)
            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: formdata,
            };

            fetch(bgphotoUrl, requestOptions)
                .then(response => response.text())
                .then((result) => {
                }

                )
                .catch(error => alert('error: ' + error));

        } else {
            this.setState({ image2: null })

        }
    };
    render() {
        return (
            <div className={[style.userInfoBox, this.props.className].join(' ')} >
                <div >

                    {this.state.image2 != null ?
                        <img src={this.state.image2} className={style.bgPhoto} alt="test" />
                        : <ConvertedPhoto2 data={this.props.img2} />
                    }
                    <div className={style.info}>
                        {this.state.image != null ?
                            <img src={this.state.image} className={style.profilephoto} alt="test" />
                            : <ConvertedPhoto data={this.props.img1} />
                        }

                        <div className={style.uploadPhotoBox}>
                            <p>Change profile picture here:</p>
                            <input ref={this.inputRef} onChange={this.onImageChange} className={style.fileInput} type='file' />
                        </div>
                        <div className={style.uploadPhotoBox}>
                            <p>Change background picture here:</p>
                            <input ref={this.inputRef2} onChange={this.onImageChange2} className={style.fileInput} type='file' />
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

