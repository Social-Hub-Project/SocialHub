import style from './Page.module.css';

import { Component, HTMLAttributes } from 'react';
import icon from '@resources/img/icon.svg';

interface PageProps {
  title?: string;
  sidebar?: boolean;
  topbar?: boolean;
  titlebar?: boolean;
  user?: boolean;
  content?: boolean;
  background?: boolean;
  contentClassName?: string;
  children?: HTMLAttributes<HTMLDivElement>['children'];
}

export default class Page extends Component<PageProps> {
  private static defaultProps: PageProps = {
    title: '',
    sidebar: true,
    topbar: true,
    titlebar: true,
    user: true,
    content: true,
    background: false,
    contentClassName: '',
  };

  render() {
    return (
      <div className={style.app}>

        {this.props.titlebar &&
          <div className={style.titleBar}>
            <span>{this.props.title}</span>
          </div>
        }

        {this.props.content ?
          <div className={[
            style.content,
            this.props.background ? style.contentBackground : '',
            this.props.contentClassName,
          ].join(' ')}> {this.props.children}</div> :
          this.props.children
        }
      </div>

    );
  }

  private get containerClasses(): string {
    if (this.props.sidebar) return [style.container, style.containerPadded].join(' ');
    return style.container;
  }
}

