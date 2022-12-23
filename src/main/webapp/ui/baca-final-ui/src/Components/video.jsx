import React, { Component } from 'react';
import ReactPlayer from 'react-player';

class Video extends Component {
    state = {  } 
    render() { 
        return (
            <div>
                <video width="320" height="240" border-radius="30px" controls>
                    <source src="/videos/lunge.mp4" type="video/mp4" />
                </video>
            </div>
        );
    }
}
 
export default Video;