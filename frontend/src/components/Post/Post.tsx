import { Component, createRef, RefObject } from 'react';

import style from './Post.module.css';
import userLogo from '../../resources/logo_user.png';
import addIcon from '../../resources/addIcon.png';
import likeGrey from '../../resources/like_grey.png';
import likeBlue from '../../resources/like_blue.png';
import commentsImg from '../../resources/comments.png';
import Comment from './Comment';
import Button from '../Button/Button';
import ProfilePhoto from '../ProfilePhoto/ProfilePhoto';
const commentUrl = `${process.env.REACT_APP_BACKEND_URL}/app/commentPost`;
const likeUrl = `${process.env.REACT_APP_BACKEND_URL}/app/ratingPost`;
const unlockUrl = `${process.env.REACT_APP_BACKEND_URL}/app/unlockComments`;
const blockUrl = `${process.env.REACT_APP_BACKEND_URL}/app/blockComments`;
const deleteUrl = `${process.env.REACT_APP_BACKEND_URL}/app/deletePost`;


// eslint-disable-next-line jsx-a11y/alt-text
const ConvertedPhoto = ({ data }: { data: string }) => <img className="postPhoto" src={`data:image/jpeg;base64,${data}`} />

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
    lickedByUser: number;
    blocked: boolean;
    myAccount: boolean;
    profilephoto: string;
    onClick?: () => void;
    photoUrl: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}
export interface PostState {
    expanded: boolean;
    liked: boolean;
    disliked: boolean;
    img: string;
}

export default class Post extends Component<PostProps, PostState> {
    private inputRef!: RefObject<HTMLInputElement>;
    private allComments!: Array<JSX.Element>;
    private likesNum!: number;
    private dislikesNum!: number;

    constructor(props: PostProps) {
        super(props);
        if (this.props.lickedByUser === 1) {
            this.likesNum = this.props.likes;
            this.dislikesNum = this.props.dislikes;

            this.state = {
                expanded: false,
                liked: true,
                disliked: false,
                img: "",
            };
        } else if (this.props.lickedByUser === -1) {
            this.likesNum = this.props.likes;
            this.dislikesNum = this.props.dislikes;
            this.state = {
                expanded: false,
                liked: false,
                disliked: true,
                img: "",
            };
        } else {
            this.likesNum = this.props.likes;
            this.dislikesNum = this.props.dislikes;
            this.state = {
                expanded: false,
                liked: false,
                disliked: false,
                img: "",
            };
        }
        if (this.props.useRef === undefined) this.inputRef = createRef();
        else this.inputRef = this.props.useRef;
        this.allComments = [];
        this.props.comments.forEach(com => {
            this.allComments.push(<Comment com={com}></Comment>);
        })




    };
    private unblock = () => {
        const body = {
            token: sessionStorage.getItem("userToken"),
            postId: this.props.id,
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
            const response = fetch(unlockUrl, requestOptions)
                .then((body) => {
                    // eslint-disable-next-line no-restricted-globals
                    location.reload();

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    private block = () => {
        const body = {
            token: sessionStorage.getItem("userToken"),
            postId: this.props.id,
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
            const response = fetch(blockUrl, requestOptions)
                .then((body) => {
                    // eslint-disable-next-line no-restricted-globals
                    location.reload();

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    private deletePost = () => {
        const body = {
            token: sessionStorage.getItem("userToken"),
            postId: this.props.id,
        };
        const requestOptions = {
            method: 'DELETE',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify(body)
        };

        try {
            const response = fetch(deleteUrl, requestOptions)
                .then((body) => {
                    // eslint-disable-next-line no-restricted-globals
                    location.reload();

                });
        } catch (err) {
            console.log("conn error");
        }
    }
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
        if (this.state.liked) {
            this.setState({ liked: !this.state.liked });
            this.likesNum -= 1;
        }
        this.ratePost(-1);
        this.dislikesNum += 1;
    };
    private likeClick = () => {
        this.setState({ liked: !this.state.liked });
        if (this.state.disliked) {
            this.dislikesNum -= 1;
            this.setState({ disliked: !this.state.disliked });
        }
        this.ratePost(1);
        this.likesNum += 1;

    };

    private bluelikeClick = () => {
        this.setState({ liked: !this.state.liked });
        this.ratePost(0);
        this.likesNum -= 1;
    };
    private bluedislikeClick = () => {
        this.setState({ disliked: !this.state.disliked });
        this.ratePost(0);
        this.dislikesNum -= 1;
    };
    private toogleComments = () => {
        if (!this.props.blocked)
            this.setState({ expanded: !this.state.expanded });
    };
    private addComment = () => {
        if (this.inputRef.current?.value === "")
            return;

        const body = {
            token: sessionStorage.getItem("userToken"),
            description: this.inputRef.current?.value,
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
            const response = fetch(commentUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    console.log(body);
                });
        } catch (err) {
            console.log("conn error");
        }

    };
    render() {
        return (
            <div className={[style.post, this.props.className].join(' ')}>
                <div className={style.userInfo}>
                    <ProfilePhoto data={this.props.profilephoto}></ProfilePhoto>

                    <div>
                        <p className={style.userNames}>{this.props.name} {this.props.surname}</p>
                        <p className={style.postDate}>{this.props.date}</p>
                    </div>
                </div>
                {this.props.myAccount ?
                    <div className={style.optionsPost}>
                        {this.props.blocked ?
                            <Button onClick={this.unblock} text='Unlock comments'></Button>
                            : <Button onClick={this.block} text='Block comments'></Button>

                        }
                        <Button onClick={this.deletePost} text='Delete post'></Button>
                    </div >
                    : null}


                <p className={style.content}>{this.props.content}</p>
                <ConvertedPhoto data={this.props.photoUrl} />
                <div className={style.likesContainer}>

                    {!this.state.liked ?
                        <><img onClick={this.likeClick} className={style.like} alt="likeGrey" src={likeGrey} /> <p className={style.likeNum}>{this.likesNum}</p>                        </>
                        :
                        <><img className={style.like} onClick={this.bluelikeClick} alt="likeBlue" src={likeBlue} /><p className={style.likeNym}>{this.likesNum}</p></>
                    }

                    {!this.state.disliked ?
                        <><img className={style.dislike} onClick={this.dislikeClick} alt="dislikeGrey" src={likeGrey} /><p className={style.dislikeNum}>{this.dislikesNum}</p></>
                        :
                        <><img className={style.dislike} onClick={this.bluedislikeClick} alt="dislikeGrey" src={likeBlue} /><p className={style.dislikeNum}>{this.dislikesNum}</p></>
                    }
                    {!this.props.blocked ?
                        <>
                            <img onClick={this.toogleComments} className={style.comments} alt="dislikeGrey" src={commentsImg} />
                            <p className={style.commentsNum}>{this.props.comments.length}</p>

                        </>
                        : null}

                </div>
                {this.state.expanded ?
                    <div className={style.commentsContainer}>

                        <>
                            <div>
                                <div className={style.addComment}>
                                    <input ref={this.inputRef} placeholder='Write a comment...'
                                        className={style.searchinput}
                                        type='text' />
                                    <img className={style.searchIcon} src={addIcon} alt='search' onClick={this.addComment} />
                                </div>
                                {this.allComments}
                            </div></>
                    </div>

                    : null}
            </div>
        );
    }
}

