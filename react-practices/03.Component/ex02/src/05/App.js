import React from 'react';
import './assets/scss/App.scss'
import {App, Header} from './assets/scss/App.scss';

export default function() {
    return (
        <div id={App}>
            <h1 className={Header}>SASS & SCSS</h1>
        </div>
    );
}