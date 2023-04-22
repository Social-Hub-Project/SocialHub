import style from './CenterBar.module.css';

import { Component, HTMLAttributes } from 'react';


interface CenterBarProps {
    user?: boolean;
    content?: boolean;
    background?: boolean;
    contentClassName?: string;
    children?: HTMLAttributes<HTMLDivElement>['children'];
}

export default class CenterBar extends Component<CenterBarProps> {
    private static defaultProps: CenterBarProps = {
        user: true,
        content: true,
        background: false,
        contentClassName: '',
    };

    render() {
        return (
            <div className={style.centerBar}>
                {this.props.children}
            </div>

        );
    }
}

