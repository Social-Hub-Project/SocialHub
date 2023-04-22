import { Component, createRef, RefObject } from 'react';

import style from './Post.module.css';
import userLogo from '../../resources/logo_user.png';
import postPhoto from '../../resources/postPhoto.png';
import likeGrey from '../../resources/like_grey.png';
import likeBlue from '../../resources/like_blue.png';
import commentsImg from '../../resources/comments.png';
import Comment from './Comment';

const likeUrl = `${process.env.REACT_APP_BACKEND_URL}/app/ratingPost`;

export interface PostProps {
    id: number,
    name: string;
    surname: string;
    date: string;
    content: string;
    likes: number;
    dislikes: number;
    liked: boolean;
    disliked: boolean;
    comments: Array<Array<String>>;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}
export interface PostState {
    expanded: boolean;
    liked: boolean;
    disliked: boolean;
}

export default class Post extends Component<PostProps, PostState> {
    private inputRef!: RefObject<HTMLInputElement>;
    private allComments!: Array<JSX.Element>;
    constructor(props: PostProps) {
        super(props);
        this.state = {
            expanded: false,
            liked: this.props.liked,
            disliked: this.props.disliked,
        };
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
        this.allComments = [];
        this.props.comments.forEach(com => {
            this.allComments.push(<Comment com={com}></Comment>);
        })
    };
    private ratePost = (rating: number) => {
        const body = {
            token: sessionStorage.getItem("userToken"),
            like: rating,
            idPost: this.props.id,
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body)
        };

        try {
            const response = fetch(likeUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    console.log(body);
                });
        } catch (err) {
            console.log("conn error");
        }
    }

    private dislikeClick = () => {
        this.setState({ disliked: !this.state.disliked });
        if (this.state.liked)
            this.setState({ liked: !this.state.liked });
        this.ratePost(-1);

    };
    private likeClick = () => {
        this.setState({ liked: !this.state.liked });
        if (this.state.disliked)
            this.setState({ disliked: !this.state.disliked });
        this.ratePost(1);
    };

    private bluelikeClick = () => {
        this.setState({ liked: !this.state.liked });
    };
    private bluedislikeClick = () => {
        this.setState({ disliked: !this.state.disliked });
        this.ratePost(0);
    };
    private toogleComments = () => {
        this.setState({ expanded: !this.state.expanded });
        this.ratePost(0);
    };

    render() {
        return (
            <div className={[style.post, this.props.className].join(' ')}>
                <div className={style.userInfo}>
                    <img alt="pg" className={style.userPhoto} src={userLogo} />
                    <div>
                        <p className={style.userNames}>{this.props.name} {this.props.surname}</p>
                        <p className={style.postDate}>{this.props.date}</p>
                    </div>
                </div>
                <p className={style.content}>{this.props.content}</p>
                <img className={style.postPhoto} alt="post_photo" src={postPhoto} />
                <div className={style.likesContainer}>




                    {!this.state.liked ?
                        <><img onClick={this.likeClick} className={style.like} alt="likeGrey" src={likeGrey} />
                            <p className={style.likeNum}>{this.props.likes}</p>
                        </>
                        :
                        <><img className={style.like} onClick={this.bluelikeClick} alt="likeBlue" src={likeBlue} /><p className={style.likeNym}>{this.props.likes + 1}</p></>
                    }

                    {!this.state.disliked ?
                        <><img className={style.dislike} onClick={this.dislikeClick} alt="dislikeGrey" src={likeGrey} /><p className={style.dislikeNum}>{this.props.dislikes}</p></>
                        :
                        <><img className={style.dislike} onClick={this.bluedislikeClick} alt="dislikeGrey" src={likeBlue} /><p className={style.dislikeNum}>{this.props.dislikes + 1}</p></>
                    }


                    <img onClick={this.toogleComments} className={style.comments} alt="dislikeGrey" src={commentsImg} />
                    <p className={style.commentsNum}>{this.props.comments.length}</p>


                </div>
                {this.state.expanded ?
                    <div className={style.commentsContainer}>

                        <>{this.allComments}</>
                    </div>

                    : null}
            </div>
        );
    }
}

