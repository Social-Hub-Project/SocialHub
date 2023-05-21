import { Component, createRef, RefObject } from 'react';

import style from './RecentPosts.module.css';
import Post from '../Post/Post';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getAllPosts`;
export interface RecentPostsProps {

    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}

export interface RecentPostsState {
    loaded: boolean;
}
export default class RecentPosts extends Component<RecentPostsProps, RecentPostsState> {

    private allPosts!: Array<JSX.Element>;
    constructor(props: RecentPostsProps) {
        super(props);
        this.state = {
            loaded: false,
        };
        this.allPosts = [];
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },

        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    console.log(body);
                    body.forEach((p: any) => {
                        this.allPosts.push(<Post id={p.post.id} name={p.post.userEntity.userInfo.name} surname={p.post.userEntity.userInfo.surname}
                            date={p.post.create_at} content={p.post.description} likes={p.like} photoUrl={p.image} dislikes={p.dislike}
                            liked={false} disliked={false} comments={p.comments} ></Post>);

                    })
                    console.log(this.allPosts)
                    this.setState({ loaded: true });

                });
        } catch (err) {
            console.log("conn error");
        }
    }
    render() {
        return (
            <div className={[style.postContainer, this.props.className].join(' ')}>
                {this.state.loaded ? this.allPosts
                    :
                    null}

            </div>
        );
    }
}

