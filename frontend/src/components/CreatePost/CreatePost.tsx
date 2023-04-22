import { Component, createRef, RefObject, ChangeEvent } from 'react';


import style from './CreatePost.module.css';
import arrow from '../../resources/arrow.png'
import Multiline from './Multiline';

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
    image: string | null;
}
export default class CreatePost extends Component<CreatePostProps, CreatePostState> {
    private inputRef!: RefObject<HTMLInputElement>;

    constructor(props: CreatePostProps) {
        super(props);

        this.state = {
            expanded: false,
            image: null,
        };

        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
    };
    private toogleState = () => {
        this.setState({ expanded: !this.state.expanded });
    };
    // private onImageChange = (event: SyntheticEvent) => {
    //     if (event.target.files && event.target.files[0]) {
    //         let img = event.target.files[0];
    //         this.setState({
    //             image: URL.createObjectURL(img)
    //         });
    //     }
    // };

    render() {
        return (
            <div className={[style.createPost, this.props.className].join(' ')} >

                {this.state.expanded ?
                    <div className={style.Expanded}>
                        <Multiline></Multiline>
                        <div className={style.uploadPhotoBox}>
                            <p>Upload image here:</p>
                            <input className={style.input} type='file' id={this.props.id} />
                        </div>
                        <button className={style.comfirmButton} >Create post</button>
                        <img src={arrow} alt='arrow' onClick={this.toogleState} />
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

