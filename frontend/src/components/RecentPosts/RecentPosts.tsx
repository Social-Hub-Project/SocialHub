import { Component, createRef, RefObject } from 'react';

import style from './RecentPosts.module.css';
import Post from '../Post/Post';
export interface RecentPostsProps {
    foruser: boolean;
    userId?: string | null;
    onClick?: () => void;
    photoUrl?: string;
    useRef?: RefObject<HTMLInputElement>;
    className?: string;
}

export interface RecentPostsState {
    loaded: boolean;
}
export default class RecentPosts extends Component<RecentPostsProps, RecentPostsState> {

    private fetchUrl!: string;
    private allPosts!: Array<JSX.Element>;
    constructor(props: RecentPostsProps) {
        super(props);
        var requestOptions;
        this.state = {
            loaded: false,
        };

        if (!this.props.foruser) {
            this.fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getAllPosts`;
            requestOptions = {
                method: 'GET',
                headers: {
                    'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Credentials': 'true'
                },

            };
        }
        else {
            this.fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getAllPostsForUser`;
            if (this.props.userId != null) {
                const body = {
                    token: "" + sessionStorage.getItem("userToken"),
                    userId: parseInt(this.props.userId),
                };
                requestOptions = {
                    method: 'POST',
                    headers: {
                        'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*',
                        'Access-Control-Allow-Credentials': 'true'
                    },
                    body: JSON.stringify(body)

                };
            }


        }

        this.allPosts = [];


        try {
            const response = fetch(this.fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {
                    body.forEach((p: any) => {
                        console.log(p.lickedByUser)
                        this.allPosts.push(<Post key={p.post.id} lickedByUser={p.lickedByUser} id={p.post.id} name={p.post.userEntity.userInfo.name} surname={p.post.userEntity.userInfo.surname}
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

