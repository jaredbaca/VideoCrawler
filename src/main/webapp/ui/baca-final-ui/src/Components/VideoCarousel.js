import React, { Component, useEffect } from 'react';
import './VideoCarousel.css';
import {Carousel} from 'react-bootstrap';
import ReactPlayer from 'react-player';
import {DefaultPlayer as Video} from 'react-html5video';
import "bootstrap/dist/css/bootstrap.css";

const VideoCarousel = (props) => {

    useEffect(() => {
        props={props}
    })

    return ( 
        <Carousel> 
            {Object.values(props.scenes).map((scene, index) => {
                return (
                    <Carousel.Item key={scene}>
                        <Video 
                            src={scene}
                            // width="320"
                            // height="240%"
                            pip={true}
                            controls={true}
                            playing={false}
                        />
                        <Carousel.Caption>
                            <h3>{scene.key}</h3>
                        </Carousel.Caption>
                    </Carousel.Item>
                )
            })}
        </Carousel>
     );
};

export default VideoCarousel;